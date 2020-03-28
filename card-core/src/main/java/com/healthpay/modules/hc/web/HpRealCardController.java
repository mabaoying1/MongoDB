package com.healthpay.modules.hc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.hc.service.HpApplycardService;
import com.healthpay.modules.hc.service.HpCardholderService;
import com.healthpay.modules.hc.service.HpHealthcardService;
import com.healthpay.modules.hc.service.HpMerCardlistService;
import com.healthpay.modules.hc.service.HpRealCardService;
import com.healthpay.modules.iface.service.HpIfaceAddressService;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;
import com.healthpay.modules.sys.dao.UserDao;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * 健康卡实体卡绑定Controller
 * 
 * @author gaoyp
 * @version 2016-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/hpRealCard")
public class HpRealCardController extends BaseController {

	@Autowired
	private HpRealCardService hpRealCardService;
	@Autowired
	private HpIfaceMsgqueueService hpIfaceMsgqueueService;
	@Autowired
	private HpMerCardlistService hpMerCardlistService;
	@Autowired
	private HpIfaceAddressService hpIfaceAddressService;
	@Autowired
	private HpHealthcardService hpHealthcardService;
	@Autowired
	private HpCardholderService hpCardholderService;
	@Autowired
	private HpApplycardService hpApplycardService;
	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;
	@Autowired
	UserDao userDao;

	@ModelAttribute
	public HpRealCard get(@RequestParam(required = false) String id) {
		HpRealCard entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = hpRealCardService.get(id);
		}
		if (entity == null) {
			entity = new HpRealCard();
		}
		return entity;
	}

	/**
	 * 实体卡列表页面
	 */
	@RequiresPermissions("hc:hpRealCard:list")
	@RequestMapping(value = { "list", "" })
	public String list(HpRealCard hpRealCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpRealCard> page = hpRealCardService.findPage(new Page<HpRealCard>(request, response), hpRealCard);
		model.addAttribute("page", page);
		return "modules/hc/hpRealCardList";
	}

	/**
	 * 查看，增加，编辑实体卡表单页面
	 */
	@RequiresPermissions(value = { "hc:hpRealCard:view", "hc:hpRealCard:add",
			"hc:hpRealCard:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpRealCard hpRealCard, Model model) {
		List<Map<String, String>> list = hpMerCardlistService.getMerNameByIccardId(hpRealCard.getIccardid());
		model.addAttribute("hpRealCard", hpRealCard);
		model.addAttribute("merNameList", list);
		return "modules/hc/hpRealCardForm";
	}

	/**
	 * 保存实体卡
	 */
	@RequiresPermissions(value = { "hc:hpRealCard:add", "hc:hpRealCard:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpRealCard hpRealCard, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, hpRealCard)) {
			return form(hpRealCard, model);
		}
		hpRealCard.setStatus(1L);
		if (!hpRealCard.getIsNewRecord()) {// 实体卡修改
			HpRealCard t = hpRealCardService.get(hpRealCard.getId());// 从数据库取出记录的值
			if (t.getStatus() != 1L) {// 实体卡已注销
				addMessage(redirectAttributes, "该卡已注销或挂失，无法修改");
			} else {
				MyBeanUtils.copyBeanNotNull2Bean(hpRealCard, t);// 将编辑表单中的非NULL值覆盖数据库记录中的值
				hpRealCardService.save(t);// 保存
				addMessage(redirectAttributes, "保存实体卡成功");

			}

		} else {// 新增表单保存
				// 当前实体卡类型与卡号是否存在记录
			HpRealCard existsCard = hpRealCardService.getHealthCardIdTwo(hpRealCard.getIccardid(),
					hpRealCard.getType());
			HpRealCard existsIcIdCard = hpRealCardService.getIcCardId(hpRealCard.getCardPkid(), hpRealCard.getStatus(),
					hpRealCard.getType());
			if (existsCard != null) {
				addMessage(redirectAttributes, "该实体卡号已绑定过虚拟卡");
			} else if (existsIcIdCard != null) {
				addMessage(redirectAttributes, "该健康卡已绑定有实体卡");
			} else {
				// 获取当前操作用户
				User currentUser = UserUtils.getUser();
				// 保存绑定实体卡的操作员的对应的机构商户号
				hpRealCard.setStr03(currentUser.getCompany().getMerId());
				hpRealCardService.save(hpRealCard);// 保存

				// --------------------银联异步--------------------
				// 把传送给网银的绑定记录写入队列表
				// HpIfaceMerchant gateway =
				// hpIfaceMerchantService.getGateway("支付网关",99);
				// if(gateway!=null){
				// HpHealthcard healthcard =
				// hpHealthcardService.getByPkid(hpRealCard.getCardPkid());
				// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
				// hpIfaceMsgqueue.setMerId(gateway.getMerId());
				// hpIfaceMsgqueue.setFuncid("P1003");
				// hpIfaceMsgqueue.setHealthcardid(hpRealCard.getCardPkid());
				// hpIfaceMsgqueue.setCreatetime(new Date());
				// hpIfaceMsgqueue.setIcCardId(hpRealCard.getIccardid());
				// hpIfaceMsgqueue.setDocutype(healthcard.getHpCardholder().getDocuType());
				// hpIfaceMsgqueue.setDocuid(healthcard.getHpCardholder().getDocuId());
				// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(hpRealCard.getType())));
				// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
				// }
				addMessage(redirectAttributes, "保存实体卡成功");
			}
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpRealCard/?repage";
	}

	/**
	 * 注销实体卡
	 */

	@RequiresPermissions("hc:hpRealCard:del")
	@RequestMapping(value = "delete")
	public String delete(HpRealCard hpRealCard, RedirectAttributes redirectAttributes) {

		if (hpRealCard.getType() == 1L) {
			addMessage(redirectAttributes, "社保卡不能注销");
		} else if (0 == hpRealCard.getStatus().intValue()) {
			addMessage(redirectAttributes, "该卡已注销");
		} else if (2 == hpRealCard.getStatus().intValue()) {
			addMessage(redirectAttributes, "该卡已挂失");
		} else {
			hpRealCard.setStatus(0L);
			// int ret =
			// hpRealCardService.updateRealCardStatus(hpRealCard.getCardPkid(),hpRealCard.getStatus(),hpRealCard.getType());

			hpRealCardService.deleteAndSysn(hpRealCard);

			addMessage(redirectAttributes, "注销实体卡成功");

		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpRealCard/?repage";
	}

	/**
	 * 批量删除实体卡
	 */
	@RequiresPermissions("hc:hpRealCard:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			HpRealCard hpRealCard = hpRealCardService.get(id);
			hpRealCard.setStatus(0L);
			hpRealCard.setIsNewRecord(false);
			hpRealCardService.save(hpRealCard);
		}
		addMessage(redirectAttributes, "删除实体卡成功");
		return "redirect:" + Global.getAdminPath() + "/hc/hpRealCard/?repage";
	}

	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("hc:hpRealCard:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(HpRealCard hpRealCard, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "实体卡" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<HpRealCard> page = hpRealCardService.findPage(new Page<HpRealCard>(request, response, -1), hpRealCard);
			new ExportExcel("实体卡", HpRealCard.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出实体卡记录失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpRealCard/?repage";
	}

	/**
	 * 导入Excel数据
	 */
	@RequiresPermissions("hc:hpRealCard:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpRealCard> list = ei.getDataList(HpRealCard.class);
			for (HpRealCard hpRealCard : list) {
				try {
					hpRealCardService.save(hpRealCard);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条实体卡记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条实体卡记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入实体卡失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpRealCard/?repage";
	}

	/**
	 * 下载导入实体卡数据模板
	 */
	@RequiresPermissions("hc:hpRealCard:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "实体卡数据导入模板.xlsx";
			List<HpRealCard> list = Lists.newArrayList();
			new ExportExcel("实体卡数据", HpRealCard.class, 1).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpRealCard/?repage";
	}
}