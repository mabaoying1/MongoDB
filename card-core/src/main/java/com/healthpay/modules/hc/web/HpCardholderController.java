package com.healthpay.modules.hc.web;

import java.util.List;

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
import com.healthpay.common.Constant;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.service.HpCardholderService;
import com.healthpay.modules.hc.service.HpHealthcardService;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.AreaBean;
import com.healthpay.modules.sys.service.AreaService;

/**
 * 持卡人档案Controller
 *
 * @author yyl
 * @version 2016-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/hpCardholder")
public class HpCardholderController extends BaseController {
	@Autowired
	private HpHealthcardService hpHealthcardService;
	@Autowired
	private HpCardholderService hpCardholderService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;
	@Autowired
	private HpIfaceMsgqueueService hpIfaceMsgqueueService;

	/**
	 * Method description
	 *
	 * @param hpCardholder
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:defriend")
	@RequestMapping(value = "defriend")
	public String defriend(HpCardholder hpCardholder, RedirectAttributes redirectAttributes) {
		HpCardholder t = hpCardholderService.get(hpCardholder.getIdentityId()); // 从数据库取出记录的值

		t.setStatus(2L);
		hpCardholderService.defriendHpCardHoler(t);
		addMessage(redirectAttributes, "拉黑持卡人信息成功");

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
	}

	/**
	 * 销户持卡人
	 *
	 * @param hpCardholder
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:del")
	@RequestMapping(value = "delete")
	public String delete(HpCardholder hpCardholder, RedirectAttributes redirectAttributes) {
		HpCardholder t = hpCardholderService.get(hpCardholder.getIdentityId()); // 从数据库取出记录的值

		t.setStatus(0L);// 0已销户 1正常 2拉黑
		hpCardholderService.closeHpCardHoler(t);
		addMessage(redirectAttributes, "持卡人销户成功");

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
	}

	/**
	 * 批量销户持卡人档案
	 *
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] = ids.split(",");

		for (String id : idArray) {
			HpCardholder t = hpCardholderService.get(id); // 从数据库取出记录的值

			t.setStatus(0L);
			hpCardholderService.closeHpCardHoler(t);
		}

		addMessage(redirectAttributes, "删除持卡人档案成功");

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
	}

	/**
	 * 导出excel文件
	 *
	 * @param hpCardholder
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(HpCardholder hpCardholder, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "持卡人档案" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<HpCardholder> page = hpCardholderService.findPage(new Page<HpCardholder>(request, response, -1),
					hpCardholder);

			new ExportExcel("持卡人档案", HpCardholder.class).setDataList(page.getList()).write(response, fileName)
					.dispose();

			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出持卡人档案记录失败！失败信息：" + e.getMessage());
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
	}

	/**
	 * 查看，增加，编辑持卡人档案表单页面
	 *
	 * @param hpCardholder
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { "hc:hpCardholder:view", "hc:hpCardholder:add",
			"hc:hpCardholder:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpCardholder hpCardholder, Model model) {
		if ((null != hpCardholder) && StringUtils.isNotBlank(hpCardholder.getIdentityId())) {
			hpCardholder = hpCardholderService.get(hpCardholder.getIdentityId());
		}

		model.addAttribute("hpCardholder", hpCardholder);

		return "modules/hc/hpCardholderForm";
	}

	/**
	 * 导入Excel数据
	 *
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpCardholder> list = ei.getDataList(HpCardholder.class);

			for (HpCardholder hpCardholder : list) {
				try {
					hpCardholderService.save(hpCardholder);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}

			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条持卡人档案记录。");
			}

			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条持卡人档案记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入持卡人档案失败！失败信息：" + e.getMessage());
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
	}

	/**
	 * 下载导入持卡人档案数据模板
	 *
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "持卡人档案数据导入模板.xlsx";
			List<HpCardholder> list = Lists.newArrayList();

			new ExportExcel("持卡人档案数据", HpCardholder.class, 1).setDataList(list).write(response, fileName).dispose();

			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
	}

	/**
	 * 持卡人档案列表页面
	 *
	 * @param hpCardholder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hc:hpCardholder:list")
	@RequestMapping(value = { "list", "" })
	public String list(HpCardholder hpCardholder, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<HpCardholder> page = hpCardholderService.findPage(new Page<HpCardholder>(request, response), hpCardholder);

		model.addAttribute("page", page);

		return "modules/hc/hpCardholderList";
	}

	/**
	 * 保存持卡人档案
	 *
	 * @param hpCardholder
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "hc:hpCardholder:add", "hc:hpCardholder:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpCardholder hpCardholder, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, hpCardholder)) {
			return form(hpCardholder, model);
		}

		if (!hpCardholder.getIsNewRecord()) { // 编辑表单保存
			HpCardholder t = hpCardholderService.get(hpCardholder.getIdentityId()); // 从数据库取出记录的值
			HpHealthcard hpHealthcard = hpHealthcardService.getByIdentityId(hpCardholder.getIdentityId());
			AreaBean areaBean = new AreaBean();

			if (StringUtils.isNotBlank(hpCardholder.getArea2().getId())) {
				Area area = this.areaService.get(hpCardholder.getArea2().getId());
				String[][] areas = getAreas(area, new String[6][2]);

				hpCardholder.setCountrycode2(areas[0][0]);
				hpCardholder.setCountryname2(areas[0][1]);
				hpCardholder.setProvcode2(areas[1][0]);
				hpCardholder.setProvname2(areas[1][1]);
				hpCardholder.setCitycode2(areas[2][0]);
				hpCardholder.setCityname2(areas[2][1]);
				hpCardholder.setCountycode2(areas[3][0]);
				hpCardholder.setCountyname2(areas[3][1]);
				hpCardholder.setTowncode2(areas[4][0]);
				hpCardholder.setTownname2(areas[4][1]);
				hpCardholder.setVillagecode2(areas[5][0]);
				hpCardholder.setVillagename2(areas[5][1]);
			}

			if (StringUtils.isNotBlank(hpCardholder.getArea().getId())) {
				Area area = this.areaService.get(hpCardholder.getArea().getId());
				int length = Integer.parseInt(area.getType());
				String[][] areas = getAreas(area, new String[6][2]);

				hpCardholder.setCountrycode(areas[0][0]);
				hpCardholder.setCountryname(areas[0][1]);
				hpCardholder.setProvcode(areas[1][0]);
				hpCardholder.setProvname(areas[1][1]);
				hpCardholder.setCitycode(areas[2][0]);
				hpCardholder.setCityname(areas[2][1]);
				hpCardholder.setCountycode(areas[3][0]);
				hpCardholder.setCountyname(areas[3][1]);
				hpCardholder.setTowncode(areas[4][0]);
				hpCardholder.setTownname(areas[4][1]);
				hpCardholder.setVillagecode(areas[5][0]);
				hpCardholder.setVillagename(areas[5][1]);
			}

			MyBeanUtils.copyBean2Bean(areaBean, hpCardholder);
			MyBeanUtils.copyBeanNotNull2Bean(hpCardholder, t);
			MyBeanUtils.copyBeanNotNull2Bean(hpCardholder, hpHealthcard);
			MyBeanUtils.copyBean2Bean(t, areaBean);
			MyBeanUtils.copyBean2Bean(hpHealthcard, areaBean);

			// 将编辑表单中的非NULL值覆盖数据库记录中的值
			hpCardholderService.update(t); // 保存
			hpHealthcardService.update(hpHealthcard);

			// --------------------银联异步--------------------
			// 把传送给网银的绑定记录写入队列表
			// HpIfaceMerchant gateway =
			// hpIfaceMerchantService.getGateway("支付网关",99);
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1007");
			// hpIfaceMsgqueue.setHealthcardid(hpHealthcard.getPkid());
			// hpIfaceMsgqueue.setDocuid(t.getDocuId());
			// hpIfaceMsgqueue.setDocutype(t.getDocuType());
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId("");
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
		} else { // 新增表单保存
			hpCardholderService.save(hpCardholder); // 保存

		}

		addMessage(redirectAttributes, "保存持卡人档案成功");

		return "redirect:" + Global.getAdminPath() + "/hc/hpCardholder/?repage";
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
	 * Method description
	 *
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public HpCardholder get(@RequestParam(required = false) String id) {
		HpCardholder entity = null;

		if (StringUtils.isNotBlank(id)) {
			entity = hpCardholderService.get(id);
		}

		if (entity == null) {
			entity = new HpCardholder();
		}

		return entity;
	}
}

// ~ Formatted by Jindent --- http://www.jindent.com
