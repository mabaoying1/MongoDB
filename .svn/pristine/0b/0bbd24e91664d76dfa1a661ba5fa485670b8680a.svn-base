package com.healthpay.modules.iface.web;

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
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.web.BaseController;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.modules.iface.entity.HpIfaceCardasync;
import com.healthpay.modules.iface.service.HpIfaceCardasyncService;

/**
 * 健康E卡同步管理Controller
 * @author gaoyp
 * @version 2016-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/iface/hpIfaceCardasync")
public class HpIfaceCardasyncController extends BaseController {

	@Autowired
	private HpIfaceCardasyncService hpIfaceCardasyncService;
	
	@ModelAttribute
	public HpIfaceCardasync get(@RequestParam(required=false) String id) {
		HpIfaceCardasync entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpIfaceCardasyncService.get(id);
		}
		if (entity == null){
			entity = new HpIfaceCardasync();
		}
		return entity;
	}
	
	/**
	 * 健康E卡列表页面
	 */
	@RequiresPermissions("iface:hpIfaceCardasync:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfaceCardasync hpIfaceCardasync, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpIfaceCardasync> page = hpIfaceCardasyncService.findPage(new Page<HpIfaceCardasync>(request, response), hpIfaceCardasync); 
		model.addAttribute("page", page);
		return "modules/iface/hpIfaceCardasyncList";
	}

	/**
	 * 查看，增加，编辑健康E卡表单页面
	 */
	@RequiresPermissions(value={"iface:hpIfaceCardasync:view","iface:hpIfaceCardasync:add","iface:hpIfaceCardasync:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfaceCardasync hpIfaceCardasync, Model model) {
		model.addAttribute("hpIfaceCardasync", hpIfaceCardasync);
		return "modules/iface/hpIfaceCardasyncForm";
	}

	/**
	 * 保存健康E卡
	 */
	@RequiresPermissions(value={"iface:hpIfaceCardasync:add","iface:hpIfaceCardasync:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpIfaceCardasync hpIfaceCardasync, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfaceCardasync)){
			return form(hpIfaceCardasync, model);
		}
		if(!hpIfaceCardasync.getIsNewRecord()){//编辑表单保存
			HpIfaceCardasync t = hpIfaceCardasyncService.get(hpIfaceCardasync.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpIfaceCardasync, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpIfaceCardasyncService.save(t);//保存
		}else{//新增表单保存
			hpIfaceCardasyncService.save(hpIfaceCardasync);//保存
		}
		addMessage(redirectAttributes, "保存健康E卡成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceCardasync/?repage";
	}
	
	/**
	 * 删除健康E卡
	 */
	@RequiresPermissions("iface:hpIfaceCardasync:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfaceCardasync hpIfaceCardasync, RedirectAttributes redirectAttributes) {
		hpIfaceCardasyncService.delete(hpIfaceCardasync);
		addMessage(redirectAttributes, "删除健康E卡成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceCardasync/?repage";
	}
	
	/**
	 * 批量删除健康E卡
	 */
	@RequiresPermissions("iface:hpIfaceCardasync:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfaceCardasyncService.delete(hpIfaceCardasyncService.get(id));
		}
		addMessage(redirectAttributes, "删除健康E卡成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceCardasync/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("iface:hpIfaceCardasync:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpIfaceCardasync hpIfaceCardasync, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "健康E卡"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpIfaceCardasync> page = hpIfaceCardasyncService.findPage(new Page<HpIfaceCardasync>(request, response, -1), hpIfaceCardasync);
    		new ExportExcel("健康E卡", HpIfaceCardasync.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出健康E卡记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceCardasync/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("iface:hpIfaceCardasync:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfaceCardasync> list = ei.getDataList(HpIfaceCardasync.class);
			for (HpIfaceCardasync hpIfaceCardasync : list){
				try{
					hpIfaceCardasyncService.save(hpIfaceCardasync);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条健康E卡记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条健康E卡记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入健康E卡失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceCardasync/?repage";
    }
	
	/**
	 * 下载导入健康E卡数据模板
	 */
	@RequiresPermissions("iface:hpIfaceCardasync:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "健康E卡数据导入模板.xlsx";
    		List<HpIfaceCardasync> list = Lists.newArrayList(); 
    		new ExportExcel("健康E卡数据", HpIfaceCardasync.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceCardasync/?repage";
    }
	
	
	

}