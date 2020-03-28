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
import org.springframework.web.bind.annotation.*;
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
import com.healthpay.modules.iface.entity.HpIfaceAddress;
import com.healthpay.modules.iface.service.HpIfaceAddressService;

/**
 * 接口地址管理Controller
 * @author gyp
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/iface/hpIfaceAddress")
public class HpIfaceAddressController extends BaseController {

	@Autowired
	private HpIfaceAddressService hpIfaceAddressService;
	
	@ModelAttribute
	public HpIfaceAddress get(@RequestParam(required=false) String id) {
		HpIfaceAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpIfaceAddressService.get(id);
		}
		if (entity == null){
			entity = new HpIfaceAddress();
		}
		return entity;
	}
	
	/**
	 * 接口地址列表页面
	 */
	@RequiresPermissions("iface:hpIfaceAddress:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfaceAddress hpIfaceAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpIfaceAddress> page = hpIfaceAddressService.findPage(new Page<HpIfaceAddress>(request, response), hpIfaceAddress); 
		model.addAttribute("page", page);
		return "modules/iface/hpIfaceAddressList";
	}

	/**
	 * 查看，增加，编辑接口地址表单页面
	 */
	@RequiresPermissions(value={"iface:hpIfaceAddress:view","iface:hpIfaceAddress:add","iface:hpIfaceAddress:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfaceAddress hpIfaceAddress, Model model) {
		model.addAttribute("hpIfaceAddress", hpIfaceAddress);
		return "modules/iface/hpIfaceAddressForm";
	}

	/**
	 * 保存接口地址
	 */
	@RequiresPermissions(value={"iface:hpIfaceAddress:add","iface:hpIfaceAddress:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpIfaceAddress hpIfaceAddress, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfaceAddress)){
			return form(hpIfaceAddress, model);
		}
		if(!hpIfaceAddress.getIsNewRecord()){//编辑表单保存
			HpIfaceAddress t = hpIfaceAddressService.get(hpIfaceAddress.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpIfaceAddress, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpIfaceAddressService.save(t);//保存
		}else{//新增表单保存
			hpIfaceAddressService.save(hpIfaceAddress);//保存
		}
		addMessage(redirectAttributes, "保存接口地址成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceAddress/?repage";
	}
	
	/**
	 * 删除接口地址
	 */
	@RequiresPermissions("iface:hpIfaceAddress:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfaceAddress hpIfaceAddress, RedirectAttributes redirectAttributes) {
		hpIfaceAddressService.delete(hpIfaceAddress);
		addMessage(redirectAttributes, "删除接口地址成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceAddress/?repage";
	}
	
	/**
	 * 批量删除接口地址
	 */
	@RequiresPermissions("iface:hpIfaceAddress:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfaceAddressService.delete(hpIfaceAddressService.get(id));
		}
		addMessage(redirectAttributes, "删除接口地址成功");
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceAddress/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("iface:hpIfaceAddress:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpIfaceAddress hpIfaceAddress, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "接口地址"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpIfaceAddress> page = hpIfaceAddressService.findPage(new Page<HpIfaceAddress>(request, response, -1), hpIfaceAddress);
    		new ExportExcel("接口地址", HpIfaceAddress.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出接口地址记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceAddress/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("iface:hpIfaceAddress:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfaceAddress> list = ei.getDataList(HpIfaceAddress.class);
			for (HpIfaceAddress hpIfaceAddress : list){
				try{
					hpIfaceAddressService.save(hpIfaceAddress);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条接口地址记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条接口地址记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入接口地址失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceAddress/?repage";
    }
	
	/**
	 * 下载导入接口地址数据模板
	 */
	@RequiresPermissions("iface:hpIfaceAddress:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "接口地址数据导入模板.xlsx";
    		List<HpIfaceAddress> list = Lists.newArrayList(); 
    		new ExportExcel("接口地址数据", HpIfaceAddress.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/iface/hpIfaceAddress/?repage";
    }

    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "checkAddress")
	public String checkAddressMerIdAndFuncId(String oldFuncId,String oldMerId,String merId,String funcId){
		if(StringUtils.isNotEmpty(oldFuncId) && StringUtils.isNotEmpty(oldMerId) && oldFuncId.equals(funcId) && oldMerId.equals(merId)){
			return "true";
		}
		if(StringUtils.isEmpty(merId) || StringUtils.isEmpty(funcId)){
			return "true";
		}
		if(null == hpIfaceAddressService.getIfaceAddressByMerid(merId, funcId)){
			return "true";
		}
		return "false";
	}
	

}