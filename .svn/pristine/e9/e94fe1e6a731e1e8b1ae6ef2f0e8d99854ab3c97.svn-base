package com.healthpay.modules.iface.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.entity.HpIfacePlatform;
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
import com.healthpay.modules.iface.entity.HpIfacePlatformarea;
import com.healthpay.modules.iface.service.HpIfacePlatformareaService;

/**
 * 外部平台管辖范围Controller
 * @author gaoyp
 * @version 2016-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/iface/hpIfacePlatformarea")
public class HpIfacePlatformareaController extends BaseController {

	@Autowired
	private HpIfacePlatformareaService hpIfacePlatformareaService;
	
	@ModelAttribute
	public HpIfacePlatformarea get(@RequestParam(required=false) String id) {
		HpIfacePlatformarea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpIfacePlatformareaService.get(id);
		}
		if (entity == null){
			entity = new HpIfacePlatformarea();
		}
		return entity;
	}
	
	/**
	 * 外部平台管辖范围列表页面
	 */
	@RequiresPermissions("iface:hpIfacePlatformarea:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfacePlatformarea hpIfacePlatformarea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpIfacePlatformarea> page = hpIfacePlatformareaService.findPage(new Page<HpIfacePlatformarea>(request, response), hpIfacePlatformarea); 
		model.addAttribute("page", page);
		return "modules/iface/hpIfacePlatformareaList";
	}

	/**
	 * 查看，增加，编辑外部平台管辖范围表单页面
	 */
	@RequiresPermissions(value={"iface:hpIfacePlatformarea:view","iface:hpIfacePlatformarea:add","iface:hpIfacePlatformarea:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfacePlatformarea hpIfacePlatformarea, Model model) {
		model.addAttribute("hpIfacePlatformarea", hpIfacePlatformarea);
		return "modules/iface/hpIfacePlatformareaForm";
	}

	/**
	 * 保存外部平台管辖范围
	 */
	@RequiresPermissions(value={"iface:hpIfacePlatformarea:add","iface:hpIfacePlatformarea:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpIfacePlatformarea hpIfacePlatformarea, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfacePlatformarea)){
			return form(hpIfacePlatformarea, model);
		}
		if(!hpIfacePlatformarea.getIsNewRecord()){//编辑表单保存
			HpIfacePlatformarea t = hpIfacePlatformareaService.get(hpIfacePlatformarea.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpIfacePlatformarea, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpIfacePlatformareaService.save(t);//保存
		}else{//新增表单保存
			hpIfacePlatformareaService.save(hpIfacePlatformarea);//保存
		}
		addMessage(redirectAttributes, "保存外部平台管辖范围成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatformarea/?repage";
	}
	
	/**
	 * 删除外部平台管辖范围
	 */
	@RequiresPermissions("iface:hpIfacePlatformarea:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfacePlatformarea hpIfacePlatformarea, RedirectAttributes redirectAttributes) {
		hpIfacePlatformareaService.delete(hpIfacePlatformarea);
		addMessage(redirectAttributes, "删除外部平台管辖范围成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatformarea/?repage";
	}
	
	/**
	 * 批量删除外部平台管辖范围
	 */
	@RequiresPermissions("iface:hpIfacePlatformarea:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfacePlatformareaService.delete(hpIfacePlatformareaService.get(id));
		}
		addMessage(redirectAttributes, "删除外部平台管辖范围成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatformarea/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("iface:hpIfacePlatformarea:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpIfacePlatformarea hpIfacePlatformarea, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "外部平台管辖范围"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpIfacePlatformarea> page = hpIfacePlatformareaService.findPage(new Page<HpIfacePlatformarea>(request, response, -1), hpIfacePlatformarea);
    		new ExportExcel("外部平台管辖范围", HpIfacePlatformarea.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出外部平台管辖范围记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatformarea/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("iface:hpIfacePlatformarea:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfacePlatformarea> list = ei.getDataList(HpIfacePlatformarea.class);
			for (HpIfacePlatformarea hpIfacePlatformarea : list){
				try{
					hpIfacePlatformareaService.save(hpIfacePlatformarea);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条外部平台管辖范围记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条外部平台管辖范围记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入外部平台管辖范围失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatformarea/?repage";
    }
	
	/**
	 * 下载导入外部平台管辖范围数据模板
	 */
	@RequiresPermissions("iface:hpIfacePlatformarea:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "外部平台管辖范围数据导入模板.xlsx";
    		List<HpIfacePlatformarea> list = Lists.newArrayList(); 
    		new ExportExcel("外部平台管辖范围数据", HpIfacePlatformarea.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfacePlatformarea/?repage";
    }

	
	

}