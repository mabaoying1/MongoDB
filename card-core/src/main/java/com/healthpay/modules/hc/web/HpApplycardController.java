package com.healthpay.modules.hc.web;

import java.util.Date;
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
import com.healthpay.common.exception.BusException;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.service.HpApplycardService;
import com.healthpay.modules.hc.service.HpCardholderService;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.service.AreaService;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * 健康卡申请记录Controller
 *
 * @author yyl
 * @version 2016-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/hpApplycard")
public class HpApplycardController extends BaseController {
	@Autowired
	private HpApplycardService hpApplycardService;
	@Autowired
	private HpCardholderService hpCardholderService;
	@Autowired
	private AreaService areaService;

	/**
	 * 审核
	 *
	 * @param hpApplycard
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequestMapping(value = "audit")
	public String audit(HpApplycard hpApplycard, RedirectAttributes redirectAttributes) {
		try {
			hpApplycard.setAuditDate(new Date());
			User user = UserUtils.getUser();
			hpApplycard.setAuditByName(user.getName());
			setHpApplycard(hpApplycard);
			String operate = hpApplycard.getOperate();

			if (StringUtils.isBlank(operate)) {
				throw new BusException("操作类型错误");
			}

			hpApplycardService.audit(hpApplycard, operate, true);
			addMessage(redirectAttributes, "审核健康卡成功");
		} catch (BusException e) {
			addMessage(redirectAttributes, e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			addMessage(redirectAttributes, "审核健康卡失败");
			e.printStackTrace();
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?operate=audit";
	}

	/**
	 * 审核健康卡申请记录表单页面
	 *
	 * @param hpApplycard
	 * @param model
	 *
	 * @return
	 */
	@RequiresPermissions(value = { "hc:hpApplycard:audit" })
	@RequestMapping(value = "auditForm")
	public String auditForm(HpApplycard hpApplycard, Model model) {
		model.addAttribute("hpApplycard", hpApplycard);

		return "modules/hc/hpApplycardAuditForm";
	}

	/**
	 * 自动审核
	 *
	 * @param hpApplycard
	 * @param model
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequestMapping(value = "autoAudit")
	public String autoAudit(HpApplycard hpApplycard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hpApplycard)) {
			return form(hpApplycard, "add", model);
		}

		try {
			/*
			 * if (isRepeatPhone(hpApplycard.getPhone())) { addMessage(model,
			 * "手机号重复");
			 * 
			 * return form(hpApplycard, "add", model); }
			 */

			if ( StringUtils.isNotBlank(hpApplycard.getDocuId())
					&& StringUtils.isNotBlank(hpApplycard.getDocuType())) {
				// id = String.valueOf(hpApplycard.getDocuType()) + "-" +
				// hpApplycard.getDocuId();
				// 生成身份证号码 国籍+证件类型+证件号码
				String id = hpApplycard.getDocuType() + hpApplycard.getDocuId();
				HpCardholder holder = hpCardholderService.get(id);

				if (holder != null) {
					throw new BusException("该身份证已经已申请过了");
				}
			}

			setHpApplycard(hpApplycard);
			hpApplycardService.audit(hpApplycard, "1", true);
			addMessage(model, hpApplycard.getName() + "发卡成功");
			model.addAttribute("success", "success");
		} catch (BusException e) {
			addMessage(model, e.getMessage());
			e.printStackTrace();

			return form(hpApplycard, "add", model);
		} catch (Exception e) {
			addMessage(model, "发卡失败");
			e.printStackTrace();

			return form(hpApplycard, "add", model);
		}

		HpApplycard bean = new HpApplycard();

		bean.setSource(4L);

		return form(bean, "add", model);
	}

	/**
	 * 删除健康卡申请记录
	 *
	 * @param hpApplycard
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequiresPermissions("hc:hpApplycard:del")
	@RequestMapping(value = "delete")
	public String delete(HpApplycard hpApplycard, RedirectAttributes redirectAttributes) {
		hpApplycardService.delete(hpApplycard);
		addMessage(redirectAttributes, "删除健康卡申请记录成功");

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?repage";
	}

	/**
	 * 批量删除健康卡申请记录
	 *
	 * @param ids
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequiresPermissions("hc:hpApplycard:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] = ids.split(",");

		for (String id : idArray) {
			hpApplycardService.delete(hpApplycardService.get(id));
		}

		addMessage(redirectAttributes, "删除健康卡申请记录成功");

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?repage";
	}

	/**
	 * 导出excel文件
	 *
	 * @param hpApplycard
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequiresPermissions("hc:hpApplycard:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(HpApplycard hpApplycard, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "健康卡申请记录" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<HpApplycard> page = hpApplycardService.findPage(new Page<HpApplycard>(request, response, -1),
					hpApplycard);

			new ExportExcel("健康卡申请记录", HpApplycard.class).setDataList(page.getList()).write(response, fileName)
					.dispose();

			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出健康卡申请记录记录失败！失败信息：" + e.getMessage());
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?repage";
	}

	/**
	 * 查看，增加，编辑健康卡申请记录表单页面
	 *
	 * @param hpApplycard
	 * @param operate
	 * @param model
	 *
	 * @return
	 */
	@RequiresPermissions(value = { "hc:hpApplycard:view", "hc:hpApplycard:add", "hc:hpApplycard:edit",
			"hc:hpApplycard:audit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpApplycard hpApplycard, String operate, Model model) {
		Area bean = hpApplycard.getArea();

		if (null != bean) {
			String allName = setAllParentName(bean.getParentIds(), bean.getName());

			bean.setAllName(allName);
		}

		Area bean1 = hpApplycard.getArea1();

		if (null != bean1) {
			String allName = setAllParentName(bean1.getParentIds(), bean1.getName());

			bean1.setAllName(allName);
		}

		hpApplycard.setArea(bean);
		hpApplycard.setArea1(bean1);
		model.addAttribute("hpApplycard", hpApplycard);
		model.addAttribute("operate", operate);

		return "modules/hc/hpApplycardForm";
	}

	/**
	 * 导入Excel数据
	 *
	 *
	 * @param file
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequiresPermissions("hc:hpApplycard:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpApplycard> list = ei.getDataList(HpApplycard.class);

			for (HpApplycard hpApplycard : list) {
				try {
					hpApplycardService.save(hpApplycard);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}

			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条健康卡申请记录记录。");
			}

			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条健康卡申请记录记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入健康卡申请记录失败！失败信息：" + e.getMessage());
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?repage";
	}

	/**
	 * 下载导入健康卡申请记录数据模板
	 *
	 * @param response
	 * @param redirectAttributes
	 *
	 * @return
	 */
	@RequiresPermissions("hc:hpApplycard:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "健康卡申请记录数据导入模板.xlsx";
			List<HpApplycard> list = Lists.newArrayList();

			new ExportExcel("健康卡申请记录数据", HpApplycard.class, 1).setDataList(list).write(response, fileName).dispose();

			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?repage";
	}

	/**
	 * 健康卡申请记录列表页面
	 *
	 * @param hpApplycard
	 * @param operate
	 * @param request
	 * @param response
	 * @param model
	 *
	 * @return
	 */
	@RequiresPermissions("hc:hpApplycard:list")
	@RequestMapping(value = { "list", "" })
	public String list(HpApplycard hpApplycard, String operate, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<HpApplycard> page = hpApplycardService.findPage(new Page<HpApplycard>(request, response), hpApplycard);

		model.addAttribute("page", page);
		model.addAttribute("operate", operate);

		return "modules/hc/hpApplycardList";
	}

	/**
	 * 保存健康卡申请记录
	 *
	 * @param hpApplycard
	 * @param model
	 * @param operate
	 * @param redirectAttributes
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	@RequiresPermissions(value = { "hc:hpApplycard:add", "hc:hpApplycard:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpApplycard hpApplycard, Model model, String operate, RedirectAttributes redirectAttributes)
			throws Exception {
		if (!beanValidator(model, hpApplycard)) {
			return form(hpApplycard, operate, model);
		}

		try {
			// 属性设值
			setHpApplycard(hpApplycard);

			// 判断是否为注册
			if (!hpApplycard.getIsNewRecord()) { // 编辑表单保存
				HpApplycard t = hpApplycardService.get(String.valueOf(hpApplycard.getPkid())); // 从数据库取出记录的值

				/*
				 * if (!t.getPhone().equals(hpApplycard.getPhone())) { if
				 * (isRepeatPhone(hpApplycard.getPhone())) { addMessage(model,
				 * "手机号重复");
				 * 
				 * return form(hpApplycard, operate, model); } }
				 */

				MyBeanUtils.copyBeanNotNull2Bean(hpApplycard, t); // 将编辑表单中的非NULL值覆盖数据库记录中的值
				hpApplycardService.save(t); // 保存
			} else { // 新增表单保存
				/*
				 * if (isRepeatPhone(hpApplycard.getPhone())) {
				 * addMessage(model, "手机号重复");
				 * 
				 * return form(hpApplycard, operate, model); }
				 */

				HpApplycard a = hpApplycardService.getByIdentityId(hpApplycard.getIdentityId());

				if (a != null) {
					throw new BusException("该身份证已经已申请过了");
				}

				hpApplycardService.save(hpApplycard); // 保存
			}

			addMessage(redirectAttributes, "保存健康卡申请记录成功");
		} catch (BusException e) {
			addMessage(redirectAttributes, e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存健康卡申请记录失败");
			e.printStackTrace();
		}

		return "redirect:" + Global.getAdminPath() + "/hc/hpApplycard/?repage";
	}

	private String setAllParentName(String parentIds, String name) {
		StringBuffer str = new StringBuffer();

		if (StringUtils.isNotNull(parentIds)) {
			String[] pIds = parentIds.split(",");

			for (int i = 0; i < pIds.length; i++) {
				Area bean = areaService.get(pIds[i]);

				if ((bean != null) && !"1".equals(bean.getType())) {
					str.append(bean.getName());
				}
			}
		}

		str.append(name);

		return str.toString();
	}

	/**
	 * 递归获取区域数据
	 *
	 * @param area
	 * @param areas
	 * @return
	 * @author yyl
	 * @date 2016年5月27日 上午11:49:07
	 */
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
	 *
	 * @param pkid
	 *
	 * @return
	 */
	@ModelAttribute
	public HpApplycard get(@RequestParam(required = false) String pkid) {
		HpApplycard entity = null;

		if (StringUtils.isNotBlank(pkid)) {
			entity = hpApplycardService.get(pkid);
		}

		if (entity == null) {
			entity = new HpApplycard();
		}

		return entity;
	}

	/**
	 * 属性赋值
	 *
	 * @param hpApplycard
	 * @author yyl
	 * @date 2016年5月27日 上午11:07:37
	 */
	private void setHpApplycard(HpApplycard hpApplycard) {
		if (StringUtils.isBlank(hpApplycard.getCardId())) {
			hpApplycard.setCardId("111");
		}

		if (hpApplycard.getSource() == null) {
			hpApplycard.setSource(1L);
		}

		User user = UserUtils.getUser();

		if (null != user) {
			hpApplycard.setOffice(user.getCompany());
		}

		if (StringUtils.isNotBlank(hpApplycard.getArea1().getId())) {
			Area area = this.areaService.get(hpApplycard.getArea1().getId());
			String[][] areas = getAreas(area, new String[6][2]);

			hpApplycard.setCountrycode2(areas[0][0]);
			hpApplycard.setCountryname2(areas[0][1]);
			hpApplycard.setProvcode2(areas[1][0]);
			hpApplycard.setProvname2(areas[1][1]);
			hpApplycard.setCitycode2(areas[2][0]);
			hpApplycard.setCityname2(areas[2][1]);
			hpApplycard.setCountycode2(areas[3][0]);
			hpApplycard.setCountyname2(areas[3][1]);
			hpApplycard.setTowncode2(areas[4][0]);
			hpApplycard.setTownname2(areas[4][1]);
			hpApplycard.setVillagecode2(areas[5][0]);
			hpApplycard.setVillagename2(areas[5][1]);
		}

		if (StringUtils.isNotBlank(hpApplycard.getArea().getId())) {
			Area area = this.areaService.get(hpApplycard.getArea().getId());
			int length = Integer.parseInt(area.getType());
			String[][] areas = getAreas(area, new String[6][2]);

			hpApplycard.setCountrycode(areas[0][0]);
			hpApplycard.setCountryname(areas[0][1]);
			hpApplycard.setProvcode(areas[1][0]);
			hpApplycard.setProvname(areas[1][1]);
			hpApplycard.setCitycode(areas[2][0]);
			hpApplycard.setCityname(areas[2][1]);
			hpApplycard.setCountycode(areas[3][0]);
			hpApplycard.setCountyname(areas[3][1]);
			hpApplycard.setTowncode(areas[4][0]);
			hpApplycard.setTownname(areas[4][1]);
			hpApplycard.setVillagecode(areas[5][0]);
			hpApplycard.setVillagename(areas[5][1]);
		} /*
			 * else { hpApplycard.setCountrycode(hpApplycard.getCountrycode2());
			 * hpApplycard.setCountryname(hpApplycard.getCountryname2());
			 * hpApplycard.setProvcode(hpApplycard.getProvcode2());
			 * hpApplycard.setProvname(hpApplycard.getProvname2());
			 * hpApplycard.setCitycode(hpApplycard.getCitycode2());
			 * hpApplycard.setCityname(hpApplycard.getCityname2());
			 * hpApplycard.setCountycode(hpApplycard.getCountycode2());
			 * hpApplycard.setCountyname(hpApplycard.getCountyname2());
			 * hpApplycard.setTowncode(hpApplycard.getTowncode2());
			 * hpApplycard.setTownname(hpApplycard.getTownname2());
			 * hpApplycard.setVillagecode(hpApplycard.getVillagecode2());
			 * hpApplycard.setVillagename(hpApplycard.getVillagename2()); }
			 */
	}

	/**
	 * 判断手机号是否重复
	 * 
	 * @param phone
	 * @return
	 * @author yyl
	 * @date 2016年6月6日 下午9:06:14
	 */
	private boolean isRepeatPhone(String phone) {
		boolean flag = false;
		HpCardholder bean = hpCardholderService.getByPhone(phone);

		if (bean != null) {
			flag = true;
		} else {
			HpApplycard bean1 = hpApplycardService.getByPhone(phone);

			if (bean1 != null) {
				flag = true;
			}
		}

		return flag;
	}
}

// ~ Formatted by Jindent --- http://www.jindent.com
