package com.healthpay.modules.iface.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.healthpay.common.exception.BusException;
import com.healthpay.modules.iface.entity.HpIfacePlatformarea;
import com.healthpay.modules.iface.service.HpIfacePlatformareaService;
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
import com.healthpay.modules.iface.entity.HpIfacePlatform;
import com.healthpay.modules.iface.service.HpIfacePlatformService;

/**
 * 外部平台管理Controller
 * @author gaoyp
 * @version 2016-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/iface/hpIfacePlatform")
public class HpIfacePlatformController extends BaseController {

	@Autowired
	private HpIfacePlatformService hpIfacePlatformService;
	@Autowired
	private HpIfacePlatformareaService hpIfacePlatformareaService;
	
	@ModelAttribute
	public HpIfacePlatform get(@RequestParam(required=false) String id) {
		HpIfacePlatform entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpIfacePlatformService.get(id);
		}
		if (entity == null){
			entity = new HpIfacePlatform();
		}
		return entity;
	}
	
	/**
	 * 外部平台列表页面
	 */
	@RequiresPermissions("iface:hpIfacePlatform:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfacePlatform hpIfacePlatform, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpIfacePlatform> page = hpIfacePlatformService.findPage(new Page<HpIfacePlatform>(request, response), hpIfacePlatform); 
		model.addAttribute("page", page);
		return "modules/iface/hpIfacePlatformList";
	}

	/**
	 * 查看，增加，编辑外部平台表单页面
	 */
	@RequiresPermissions(value={"iface:hpIfacePlatform:view","iface:hpIfacePlatform:add","iface:hpIfacePlatform:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfacePlatform hpIfacePlatform, Model model) {
		model.addAttribute("hpIfacePlatform", hpIfacePlatform);
		return "modules/iface/hpIfacePlatformForm";
	}

	/**
	 * 保存外部平台
	 */
	@RequiresPermissions(value={"iface:hpIfacePlatform:add","iface:hpIfacePlatform:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpIfacePlatform hpIfacePlatform, Model model, RedirectAttributes redirectAttributes){
		if (!beanValidator(model, hpIfacePlatform)){
			return form(hpIfacePlatform, model);
		}
		try {
			hpIfacePlatformService.saveHpIfacePlatform(hpIfacePlatform);
		} catch (BusException e) {
			logger.error(e.getMessage(),e);
			addMessage(model,new String[]{e.getMessage()});
			return form(hpIfacePlatform,model);
		}
		addMessage(redirectAttributes, "保存外部平台成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatform/?repage";
	}
	
	/**
	 * 删除外部平台
	 */
	@RequiresPermissions("iface:hpIfacePlatform:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfacePlatform hpIfacePlatform, RedirectAttributes redirectAttributes) {
		hpIfacePlatformService.delete(hpIfacePlatform);
		addMessage(redirectAttributes, "删除外部平台成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatform/?repage";
	}
	
	/**
	 * 批量删除外部平台
	 */
	@RequiresPermissions("iface:hpIfacePlatform:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfacePlatformService.delete(hpIfacePlatformService.get(id));
		}
		addMessage(redirectAttributes, "删除外部平台成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatform/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("iface:hpIfacePlatform:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpIfacePlatform hpIfacePlatform, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "外部平台"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpIfacePlatform> page = hpIfacePlatformService.findPage(new Page<HpIfacePlatform>(request, response, -1), hpIfacePlatform);
    		new ExportExcel("外部平台", HpIfacePlatform.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出外部平台记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatform/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("iface:hpIfacePlatform:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfacePlatform> list = ei.getDataList(HpIfacePlatform.class);
			for (HpIfacePlatform hpIfacePlatform : list){
				try{
					hpIfacePlatformService.save(hpIfacePlatform);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条外部平台记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条外部平台记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入外部平台失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatform/?repage";
    }
	
	/**
	 * 下载导入外部平台数据模板
	 */
	@RequiresPermissions("iface:hpIfacePlatform:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "外部平台数据导入模板.xlsx";
    		List<HpIfacePlatform> list = Lists.newArrayList(); 
    		new ExportExcel("外部平台数据", HpIfacePlatform.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatform/?repage";
    }
}