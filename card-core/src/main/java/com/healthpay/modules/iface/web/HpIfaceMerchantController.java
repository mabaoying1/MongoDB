/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.web;


import java.io.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.healthpay.common.utils.IdGen;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.h2.mvstore.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
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
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.service.AreaService;

/**
 * 商户管理Controller
 * @author gyp
 * @version 2016-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/interface/hpIfaceMerchant")
public class HpIfaceMerchantController extends BaseController {

	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute
	public HpIfaceMerchant get(@RequestParam(required=false) String merId) {
		HpIfaceMerchant entity = null;
		if (StringUtils.isNotBlank(merId)){
			entity = hpIfaceMerchantService.get(merId);
		}
		if (entity == null){
			entity = new HpIfaceMerchant();
		}
		return entity;
	}
	
	/**
	 * 商户列表页面
	 */
	@RequiresPermissions("interface:hpIfaceMerchant:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpIfaceMerchant hpIfaceMerchant, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpIfaceMerchant> page = hpIfaceMerchantService.findPage(new Page<HpIfaceMerchant>(request, response), hpIfaceMerchant); 
		model.addAttribute("page", page);
		return "modules/iface/hpIfaceMerchantList";
	}

	/**
	 * 查看，增加，编辑商户表单页面
	 */
	@RequiresPermissions(value={"interface:hpIfaceMerchant:view","interface:hpIfaceMerchant:add","interface:hpIfaceMerchant:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpIfaceMerchant hpIfaceMerchant, Model model) {
		model.addAttribute("hpIfaceMerchant", hpIfaceMerchant);
		return "modules/iface/hpIfaceMerchantForm";
	}

	/**
	 * 增加商户表单页面
	 */
	@RequiresPermissions(value="interface:hpIfaceMerchant:add",logical=Logical.OR)
	@RequestMapping(value = "addForm")
	public String addForm(HpIfaceMerchant hpIfaceMerchant, Model model) {
		model.addAttribute("hpIfaceMerchant", hpIfaceMerchant);
		return "modules/iface/hpIfaceMerchantAddForm";
	}

	/**
	 * 保存商户
	 */
	@RequiresPermissions(value="interface:hpIfaceMerchant:add",logical=Logical.OR)
	@RequestMapping(value = "saveHpIfaceMerchant")
	public String saveHpIfaceMerchant(HpIfaceMerchant hpIfaceMerchant, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfaceMerchant)){
			return form(hpIfaceMerchant, model);
		}
		HpIfaceMerchant merchant=hpIfaceMerchantService.getMerchantByOrgCode(hpIfaceMerchant.getOrgCode());
		if(null !=merchant){
			addMessage(redirectAttributes, "该机构已存在！");
		}else{
			//新增表单保存
			hpIfaceMerchant.setIsNewRecord(true);
			hpIfaceMerchant.setStatus("1");
			hpIfaceMerchant.setMerType("1");
			//查询地区信息
			Area bean = areaService.get(hpIfaceMerchant.getOrgAddr().getId());
			hpIfaceMerchant.setOrgAddr(bean);
			
			hpIfaceMerchantService.saveHpIfaceMerchant(hpIfaceMerchant);//保存
			addMessage(redirectAttributes, "保存商户成功");
		}
		/*HpIfaceMerchant t = hpIfaceMerchantService.get(hpIfaceMerchant.getMerId());//从数据库取出记录的值
		if(null!=t){
			addMessage(redirectAttributes, "该商户号已存在！");
		}else{
			//新增表单保存
			hpIfaceMerchant.setIsNewRecord(true);
			hpIfaceMerchant.setStatus("1");
			hpIfaceMerchant.setMerType("1");
			//hpIfaceMerchant.setMerId(IdGen.generateNumber());
			//hpIfaceMerchant.setDigitalKey(StringUtils.getStringRandom(64));
			hpIfaceMerchantService.saveHpIfaceMerchant(hpIfaceMerchant);//保存
			addMessage(redirectAttributes, "保存商户成功");
		}*/
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
	}

	/**
	 * 更新商户
	 */
	@RequiresPermissions(value="interface:hpIfaceMerchant:edit",logical=Logical.OR)
	@RequestMapping(value = "updateHpIfacemerchant")
	public String updateHpIfacemerchant(HpIfaceMerchant hpIfaceMerchant, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpIfaceMerchant)){
			return form(hpIfaceMerchant, model);
		}
			
		//编辑表单保存
		HpIfaceMerchant t = hpIfaceMerchantService.get(hpIfaceMerchant.getMerId());//从数据库取出记录的值
		MyBeanUtils.copyBeanNotNull2Bean(hpIfaceMerchant, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
		//查询地区信息
		Area bean = areaService.get(hpIfaceMerchant.getOrgAddr().getId());
		t.setOrgAddr(bean);
		hpIfaceMerchantService.updateHpIfacemerchant(t);//保存

		addMessage(redirectAttributes, "更新商户成功");
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
	}
	
	/**
	 * 删除商户
	 */
	@RequiresPermissions("interface:hpIfaceMerchant:del")
	@RequestMapping(value = "delete")
	public String delete(HpIfaceMerchant hpIfaceMerchant, RedirectAttributes redirectAttributes) {
		hpIfaceMerchantService.delete(hpIfaceMerchant);
		addMessage(redirectAttributes, "删除商户成功");
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
	}
	
	/**
	 * 批量删除商户
	 */
	@RequiresPermissions("interface:hpIfaceMerchant:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpIfaceMerchantService.delete(hpIfaceMerchantService.get(id));
		}
		addMessage(redirectAttributes, "删除商户成功");
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("interface:hpIfaceMerchant:export")
    @RequestMapping(value = "export")
    public String exportFile(HpIfaceMerchant hpIfaceMerchant, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = hpIfaceMerchant.getMerId()+".crt";
            ByteArrayInputStream bais = new ByteArrayInputStream(hpIfaceMerchant.getDigitalKey().getBytes());
            BufferedInputStream bis = new BufferedInputStream(bais);
            OutputStream fos = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            String result = "";
            int byteRead = 0;
            byte[] buffer = new byte[8192];
            while((byteRead=bis.read(buffer,0,8192))!=-1){
                bos.write(buffer,0,byteRead);
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            bos.flush();
            bais.close();
            bis.close();
            fos.close();
            bos.close();
//            Page<HpIfaceMerchant> page = hpIfaceMerchantService.findPage(new Page<HpIfaceMerchant>(request, response, -1), hpIfaceMerchant);
//    		new ExportExcel("商户", HpIfaceMerchant.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("interface:hpIfaceMerchant:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpIfaceMerchant> list = ei.getDataList(HpIfaceMerchant.class);
			for (HpIfaceMerchant hpIfaceMerchant : list){
				try{
					hpIfaceMerchantService.save(hpIfaceMerchant);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条商户记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条商户记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入商户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
    }
	
	/**
	 * 下载导入商户数据模板
	 */
	@RequiresPermissions("interface:hpIfaceMerchant:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商户数据导入模板.xlsx";
    		List<HpIfaceMerchant> list = Lists.newArrayList(); 
    		new ExportExcel("商户数据", HpIfaceMerchant.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/interface/hpIfaceMerchant/?repage";
    }
	
	
	

}