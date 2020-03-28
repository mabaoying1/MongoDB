/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.web;

import java.util.Iterator;
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
import com.healthpay.modules.iface.entity.HpIfaceLog;
import com.healthpay.modules.iface.service.HpIfaceLogService;

/**
 * 接口日志管理Controller
 * @author gyp
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/iface/hpIfaceLog")
public class HpIfaceLogController extends BaseController {

	@Autowired
	private HpIfaceLogService hpIfaceLogService;
	
	@ModelAttribute
	public HpIfaceLog get(@RequestParam(required=false) String id) {
		HpIfaceLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpIfaceLogService.get(id);
		}
		if (entity == null){
			entity = new HpIfaceLog();
		}
		return entity;
	}
	
	/**
	 * 接口日志列表页面
	 */
	@RequiresPermissions("iface:hpIfaceLog:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfaceLog hpIfaceLog, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<HpIfaceLog> page = hpIfaceLogService.findPage(new Page<HpIfaceLog>(request, response), hpIfaceLog);
		List<HpIfaceLog> logList = page.getList();
		Iterator<HpIfaceLog> it = logList.iterator();
		while(it.hasNext()){
			HpIfaceLog log = it.next();
			String senddata = log.getSenddata();
			String retdata = log.getRetdata();
			if(null!=retdata && retdata.length()>230){
				log.setRef0(retdata.substring(0,230)+"...");
			}else{
				log.setRef0(retdata);
			}
			if(null!=senddata && senddata.length()>230){
				log.setRef1(senddata.substring(0,230)+"...");
			}else{
				log.setRef1(senddata);
			}
		}
		model.addAttribute("page", page);
		return "modules/iface/hpIfaceLogList";
	}

	/**
	 * 查看，增加，编辑接口日志表单页面
	 */
	@RequiresPermissions(value={"iface:hpIfaceLog:view","iface:hpIfaceLog:add","iface:hpIfaceLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfaceLog hpIfaceLog, Model model) {
		model.addAttribute("hpIfaceLog", hpIfaceLog);
		return "modules/iface/hpIfaceLogForm";
	}

	/**
	 * 保存接口日志
	 */
	@RequiresPermissions(value={"iface:hpIfaceLog:add","iface:hpIfaceLog:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpIfaceLog hpIfaceLog, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfaceLog)){
			return form(hpIfaceLog, model);
		}
		if(!hpIfaceLog.getIsNewRecord()){//编辑表单保存
			HpIfaceLog t = hpIfaceLogService.get(hpIfaceLog.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpIfaceLog, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpIfaceLogService.save(t);//保存
		}else{//新增表单保存
			hpIfaceLogService.save(hpIfaceLog);//保存
		}
		addMessage(redirectAttributes, "保存接口日志成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceLog/?repage";
	}
	
	/**
	 * 删除接口日志
	 */
	@RequiresPermissions("iface:hpIfaceLog:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfaceLog hpIfaceLog, RedirectAttributes redirectAttributes) {
		hpIfaceLogService.delete(hpIfaceLog);
		addMessage(redirectAttributes, "删除接口日志成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceLog/?repage";
	}
	
	/**
	 * 批量删除接口日志
	 */
	@RequiresPermissions("iface:hpIfaceLog:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfaceLogService.delete(hpIfaceLogService.get(id));
		}
		addMessage(redirectAttributes, "删除接口日志成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceLog/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("iface:hpIfaceLog:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpIfaceLog hpIfaceLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "接口日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpIfaceLog> page = hpIfaceLogService.findPage(new Page<HpIfaceLog>(request, response, -1), hpIfaceLog);
    		new ExportExcel("接口日志", HpIfaceLog.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出接口日志记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceLog/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("iface:hpIfaceLog:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfaceLog> list = ei.getDataList(HpIfaceLog.class);
			for (HpIfaceLog hpIfaceLog : list){
				try{
					hpIfaceLogService.save(hpIfaceLog);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条接口日志记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条接口日志记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入接口日志失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceLog/?repage";
    }
	
	/**
	 * 下载导入接口日志数据模板
	 */
	@RequiresPermissions("iface:hpIfaceLog:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "接口日志数据导入模板.xlsx";
    		List<HpIfaceLog> list = Lists.newArrayList(); 
    		new ExportExcel("接口日志数据", HpIfaceLog.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceLog/?repage";
    }
	
	
	

}