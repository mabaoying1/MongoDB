/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
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
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;

/**
 * 发送队列管理Controller
 * @author gyp
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/iface/hpIfaceMsgqueue")
public class HpIfaceMsgqueueController extends BaseController {

	@Autowired
	private HpIfaceMsgqueueService hpIfaceMsgqueueService;
	
	@ModelAttribute
	public HpIfaceMsgqueue get(@RequestParam(required=false) String id) {
		HpIfaceMsgqueue entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpIfaceMsgqueueService.get(id);
		}
		if (entity == null){
			entity = new HpIfaceMsgqueue();
		}
		return entity;
	}
	
	/**
	 * 发送队列列表页面
	 */
	@RequiresPermissions("iface:hpIfaceMsgqueue:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfaceMsgqueue hpIfaceMsgqueue, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpIfaceMsgqueue> page = hpIfaceMsgqueueService.findPage(new Page<HpIfaceMsgqueue>(request, response), hpIfaceMsgqueue); 
		model.addAttribute("page", page);
		return "modules/iface/hpIfaceMsgqueueList";
	}

	/**
	 * 查看，增加，编辑发送队列表单页面
	 */
	@RequiresPermissions(value={"iface:hpIfaceMsgqueue:view","iface:hpIfaceMsgqueue:add","iface:hpIfaceMsgqueue:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfaceMsgqueue hpIfaceMsgqueue, Model model) {
		model.addAttribute("hpIfaceMsgqueue", hpIfaceMsgqueue);
		return "modules/iface/hpIfaceMsgqueueForm";
	}

	/**
	 * 保存发送队列
	 */
	@RequiresPermissions(value={"iface:hpIfaceMsgqueue:add","iface:hpIfaceMsgqueue:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpIfaceMsgqueue hpIfaceMsgqueue, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfaceMsgqueue)){
			return form(hpIfaceMsgqueue, model);
		}
		if(!hpIfaceMsgqueue.getIsNewRecord()){//编辑表单保存
			HpIfaceMsgqueue t = hpIfaceMsgqueueService.get(hpIfaceMsgqueue.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpIfaceMsgqueue, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpIfaceMsgqueueService.save(t);//保存
		}else{//新增表单保存
			hpIfaceMsgqueueService.save(hpIfaceMsgqueue);//保存
		}
		addMessage(redirectAttributes, "保存发送队列成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceMsgqueue/?repage";
	}
	
	/**
	 * 删除发送队列
	 */
	@RequiresPermissions("iface:hpIfaceMsgqueue:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfaceMsgqueue hpIfaceMsgqueue, RedirectAttributes redirectAttributes) {
		hpIfaceMsgqueueService.delete(hpIfaceMsgqueue);
		addMessage(redirectAttributes, "删除发送队列成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceMsgqueue/?repage";
	}
	
	/**
	 * 批量删除发送队列
	 */
	@RequiresPermissions("iface:hpIfaceMsgqueue:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfaceMsgqueueService.delete(hpIfaceMsgqueueService.get(id));
		}
		addMessage(redirectAttributes, "删除发送队列成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceMsgqueue/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("iface:hpIfaceMsgqueue:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpIfaceMsgqueue hpIfaceMsgqueue, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "发送队列"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpIfaceMsgqueue> page = hpIfaceMsgqueueService.findPage(new Page<HpIfaceMsgqueue>(request, response, -1), hpIfaceMsgqueue);
    		new ExportExcel("发送队列", HpIfaceMsgqueue.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出发送队列记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceMsgqueue/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("iface:hpIfaceMsgqueue:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfaceMsgqueue> list = ei.getDataList(HpIfaceMsgqueue.class);
			for (HpIfaceMsgqueue hpIfaceMsgqueue : list){
				try{
					hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条发送队列记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条发送队列记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入发送队列失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceMsgqueue/?repage";
    }
	
	/**
	 * 下载导入发送队列数据模板
	 */
	@RequiresPermissions("iface:hpIfaceMsgqueue:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "发送队列数据导入模板.xlsx";
    		List<HpIfaceMsgqueue> list = Lists.newArrayList(); 
    		new ExportExcel("发送队列数据", HpIfaceMsgqueue.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceMsgqueue/?repage";
    }
	
	
	

}