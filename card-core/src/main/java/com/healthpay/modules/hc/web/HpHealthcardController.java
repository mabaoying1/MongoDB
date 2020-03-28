package com.healthpay.modules.hc.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.healthpay.common.Constant;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.hc.service.HpCardholderService;
import com.healthpay.modules.hc.service.HpRealCardService;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.AreaBean;
import com.healthpay.modules.sys.service.AreaService;
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
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.web.BaseController;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.service.HpHealthcardService;

/**
 * 健康卡数据Controller
 * 
 * @author yyl
 * @version 2016-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/hpHealthcard")
public class HpHealthcardController extends BaseController {

	@Autowired
	private HpHealthcardService hpHealthcardService;
	@Autowired
	private HpCardholderService hpCardholderService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private HpRealCardService hpRealCardService;
	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;
	@Autowired
	private HpIfaceMsgqueueService hpIfaceMsgqueueService;

	@ModelAttribute
	public HpHealthcard get(@RequestParam(required = false) String id) {
		HpHealthcard entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = hpHealthcardService.get(id);
		}
		if (entity == null) {
			entity = new HpHealthcard();
		}
		return entity;
	}

	/**
	 * 健康卡列表页面
	 */
	@RequiresPermissions("hc:hpHealthcard:list")
	@RequestMapping(value = { "list", "" })
	public String list(HpHealthcard hpHealthcard, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<HpHealthcard> page = hpHealthcardService.findPage(new Page<HpHealthcard>(request, response), hpHealthcard);
		model.addAttribute("page", page);
		return "modules/hc/hpHealthcardList";
	}

	/**
	 * 查看，增加，编辑健康卡表单页面
	 */
	@RequiresPermissions(value = { "hc:hpHealthcard:view", "hc:hpHealthcard:add",
			"hc:hpHealthcard:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpHealthcard hpHealthcard, Model model) {
		HpHealthcard entity = null;
		List<HpRealCard> realCardList = null;
		if (StringUtils.isNotBlank(hpHealthcard.getPkid())) {
			entity = hpHealthcardService.get(hpHealthcard.getPkid());
			if(entity!=null) {
				HpRealCard realCard = new HpRealCard();
				realCard.setCardPkid(entity.getPkid());
				realCardList = this.hpRealCardService.findList(realCard);
			}
		}
		if (entity == null) {
			entity = new HpHealthcard();
		}
		if(realCardList == null){
			realCardList = new ArrayList<HpRealCard>();
		}
		model.addAttribute("hpHealthcard", entity);
		model.addAttribute("realCardList", realCardList);
		return "modules/hc/hpHealthcardForm";
	}

	private String[][] getAreas(Area area, String[][] areas) {
		int x = Integer.valueOf(area.getType()) - 1;

		if (Constant.AREA_TYPE_6.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		}
		if (Constant.AREA_TYPE_5.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_4.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_3.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_2.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_1.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		}

		if (StringUtils.isNotNull(area.getParentId())) {
			Area area2 = areaService.get(area.getParentId());
			if (null != area2) {
				getAreas(area2, areas);
			}
		}

		return areas;
	}

	/**
	 * 保存健康卡
	 */
	@RequiresPermissions(value = { "hc:hpHealthcard:add", "hc:hpHealthcard:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpHealthcard hpHealthcard, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, hpHealthcard)) {
			return form(hpHealthcard, model);
		}
		if (hpHealthcard.getIsNewRecord()==false) {
			HpHealthcard t = hpHealthcardService.get(hpHealthcard.getPkid());// 从数据库取出记录的值
			HpCardholder hpCardholder = hpCardholderService.get(t.getIdentityId());
			AreaBean areaBean = new AreaBean();
			if (StringUtils.isNotBlank(hpHealthcard.getArea().getId())) {
				Area area = this.areaService.get(hpHealthcard.getArea().getId());
				String[][] areas = getAreas(area, new String[6][2]);

				hpHealthcard.setCountrycode2(areas[0][0]);
				hpHealthcard.setCountryname2(areas[0][1]);
				hpHealthcard.setProvcode2(areas[1][0]);
				hpHealthcard.setProvname2(areas[1][1]);
				hpHealthcard.setCitycode2(areas[2][0]);
				hpHealthcard.setCityname2(areas[2][1]);
				hpHealthcard.setCountycode2(areas[3][0]);
				hpHealthcard.setCountyname2(areas[3][1]);
				hpHealthcard.setTowncode2(areas[4][0]);
				hpHealthcard.setTownname2(areas[4][1]);
				hpHealthcard.setVillagecode2(areas[5][0]);
				hpHealthcard.setVillagename2(areas[5][1]);
			}
			MyBeanUtils.copyBean2Bean(areaBean, hpHealthcard);
			MyBeanUtils.copyBeanNotNull2Bean(hpHealthcard, t);// 将编辑表单中的非NULL值覆盖数据库记录中的值
			MyBeanUtils.copyBeanNotNull2Bean(hpHealthcard, hpCardholder);
			MyBeanUtils.copyBean2Bean(t, areaBean);
			MyBeanUtils.copyBean2Bean(hpCardholder, areaBean);
			//如果没有开通支付，把支付限额设成空
			if(0 == t.getIsOpenpay()){
				t.setPaylimit(null);
			}
			hpHealthcardService.update(t);// 保存
			hpCardholderService.update(hpCardholder);

			//--------------------银联异步--------------------
			//把传送给网银的绑定记录写入队列表
//			HpIfaceMerchant gateway = hpIfaceMerchantService.getGateway("支付网关",99);
//			if(gateway!=null){
//				HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
//				hpIfaceMsgqueue.setMerId(gateway.getMerId());
//				hpIfaceMsgqueue.setFuncid("P1007");
//				hpIfaceMsgqueue.setHealthcardid(hpHealthcard.getPkid());
//				hpIfaceMsgqueue.setDocuid(hpCardholder.getDocuId());
//				hpIfaceMsgqueue.setDocutype(hpCardholder.getDocuType());
//				hpIfaceMsgqueue.setCreatetime(new Date());
//				hpIfaceMsgqueue.setIcCardId("");
//				hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
//			}
		} else {// 新增表单保存
			hpHealthcardService.save(hpHealthcard);// 保存
		}
		addMessage(redirectAttributes, "保存健康卡成功");
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

	/**
	 * 激活健康卡
	 * 
	 * @param hpHealthcard
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions(" hc:hpHealthcard:activate ")
	@RequestMapping(value = "activate")
	public String activate(HpHealthcard hpHealthcard, RedirectAttributes redirectAttributes) {
		HpHealthcard entity = hpHealthcardService.get(hpHealthcard.getPkid());
		String msg = "激活健康卡失败";
		if (null != entity) {
			entity.setStatus("2");// 状态为2
			hpHealthcardService.save(entity);
			msg = "激活健康卡成功";
		}
		addMessage(redirectAttributes, msg);
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

	/**
	 * 注销健康卡
	 */
	@RequiresPermissions("hc:hpHealthcard:del")
	@RequestMapping(value = "delete")
	public String delete(HpHealthcard hpHealthcard, RedirectAttributes redirectAttributes) {
		HpHealthcard t = hpHealthcardService.get(hpHealthcard.getPkid());
		t.setStatus("3");
		hpHealthcardService.update(t);
		//--------------------银联异步--------------------
		//把传送给网银的绑定记录写入队列表
//		HpIfaceMerchant gateway = hpIfaceMerchantService.getGateway("支付网关",99);
//		if(gateway!=null){
//			HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
//			hpIfaceMsgqueue.setMerId(gateway.getMerId());
//			hpIfaceMsgqueue.setFuncid("P1002");
//			hpIfaceMsgqueue.setHealthcardid(hpHealthcard.getPkid());
//			hpIfaceMsgqueue.setCreatetime(new Date());
//			hpIfaceMsgqueue.setIcCardId("");
//			hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
//		}

		addMessage(redirectAttributes, "注销健康卡成功");
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

	/**
	 * 批量删除健康卡
	 */
	@RequiresPermissions("hc:hpHealthcard:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			HpHealthcard t = hpHealthcardService.get(id);
			t.setStatus("3");
			hpHealthcardService.delete(t);
		}
		addMessage(redirectAttributes, "删除健康卡成功");
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("hc:hpHealthcard:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(HpHealthcard hpHealthcard, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "健康卡" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<HpHealthcard> page = hpHealthcardService.findPage(new Page<HpHealthcard>(request, response, -1),
					hpHealthcard);
			new ExportExcel("健康卡", HpHealthcard.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出健康卡记录失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

	/**
	 * 导入Excel数据
	 * 
	 */
	@RequiresPermissions("hc:hpHealthcard:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpHealthcard> list = ei.getDataList(HpHealthcard.class);
			for (HpHealthcard hpHealthcard : list) {
				try {
					hpHealthcardService.save(hpHealthcard);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条健康卡记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条健康卡记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入健康卡失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

	/**
	 * 下载导入健康卡数据模板
	 */
	@RequiresPermissions("hc:hpHealthcard:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "健康卡数据导入模板.xlsx";
			List<HpHealthcard> list = Lists.newArrayList();
			new ExportExcel("健康卡数据", HpHealthcard.class, 1).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hc/hpHealthcard/?repage";
	}

}