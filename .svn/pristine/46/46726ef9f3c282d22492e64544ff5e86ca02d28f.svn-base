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
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.web.BaseController;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.modules.hc.entity.HpApplycardHis;
import com.healthpay.modules.hc.service.HpApplycardHisService;

/**
 * 健康卡历史记录Controller
 * @author yyl
 * @version 2016-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/hpApplycardHis")
public class HpApplycardHisController extends BaseController {

	@Autowired
	private HpApplycardHisService hpApplycardHisService;
	
	@ModelAttribute
	public HpApplycardHis get(@RequestParam(required=false) String pkid) {
		HpApplycardHis entity = null;
		if (StringUtils.isNotBlank(pkid)){
			entity = hpApplycardHisService.get(pkid);
		}
		if (entity == null){
			entity = new HpApplycardHis();
		}
		return entity;
	}
	
	/**
	 * 健康卡历史记录列表页面
	 */
	@RequiresPermissions("hc:hpApplycardHis:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpApplycardHis hpApplycardHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpApplycardHis> page = hpApplycardHisService.findPage(new Page<HpApplycardHis>(request, response), hpApplycardHis); 
		model.addAttribute("page", page);
		return "modules/hc/hpApplycardHisList";
	}

	/**
	 * 查看，增加，编辑健康卡历史记录表单页面
	 */
	@RequiresPermissions(value={"hc:hpApplycardHis:view","hc:hpApplycardHis:add","hc:hpApplycardHis:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpApplycardHis hpApplycardHis, Model model) {
		model.addAttribute("hpApplycardHis", hpApplycardHis);
		return "modules/hc/hpApplycardHisForm";
	}

	/**
	 * 保存健康卡历史记录
	 */
	@RequiresPermissions(value={"hc:hpApplycardHis:add","hc:hpApplycardHis:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpApplycardHis hpApplycardHis, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpApplycardHis)){
			return form(hpApplycardHis, model);
		}
		if(!hpApplycardHis.getIsNewRecord()){//编辑表单保存
			HpApplycardHis t = hpApplycardHisService.get(String.valueOf(hpApplycardHis.getPkid()));//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpApplycardHis, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpApplycardHisService.save(t);//保存
		}else{//新增表单保存
			hpApplycardHisService.save(hpApplycardHis);//保存
		}
		addMessage(redirectAttributes, "保存健康卡历史记录成功");
		return "redirect:"+Global.getAdminPath()+"/hc/hpApplycardHis/?repage";
	}
	
	/**
	 * 删除健康卡历史记录
	 */
	@RequiresPermissions("hc:hpApplycardHis:del")
	@RequestMapping(value = "delete")
	public String delete(HpApplycardHis hpApplycardHis, RedirectAttributes redirectAttributes) {
		hpApplycardHisService.delete(hpApplycardHis);
		addMessage(redirectAttributes, "删除健康卡历史记录成功");
		return "redirect:"+Global.getAdminPath()+"/hc/hpApplycardHis/?repage";
	}
	
	/**
	 * 批量删除健康卡历史记录
	 */
	@RequiresPermissions("hc:hpApplycardHis:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpApplycardHisService.delete(hpApplycardHisService.get(id));
		}
		addMessage(redirectAttributes, "删除健康卡历史记录成功");
		return "redirect:"+Global.getAdminPath()+"/hc/hpApplycardHis/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("hc:hpApplycardHis:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpApplycardHis hpApplycardHis, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "健康卡历史记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpApplycardHis> page = hpApplycardHisService.findPage(new Page<HpApplycardHis>(request, response, -1), hpApplycardHis);
    		new ExportExcel("健康卡历史记录", HpApplycardHis.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出健康卡历史记录记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hc/hpApplycardHis/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("hc:hpApplycardHis:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpApplycardHis> list = ei.getDataList(HpApplycardHis.class);
			for (HpApplycardHis hpApplycardHis : list){
				try{
					hpApplycardHisService.save(hpApplycardHis);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条健康卡历史记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条健康卡历史记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入健康卡历史记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hc/hpApplycardHis/?repage";
    }
	
	/**
	 * 下载导入健康卡历史记录数据模板
	 */
	@RequiresPermissions("hc:hpApplycardHis:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "健康卡历史记录数据导入模板.xlsx";
    		List<HpApplycardHis> list = Lists.newArrayList(); 
    		new ExportExcel("健康卡历史记录数据", HpApplycardHis.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hc/hpApplycardHis/?repage";
    }
	
	
	

}