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
import com.healthpay.modules.hc.entity.HpCardaccount;
import com.healthpay.modules.hc.service.HpCardaccountService;

/**
 * 卡帐关系Controller
 * @author yyl
 * @version 2016-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/hpCardaccount")
public class HpCardaccountController extends BaseController {

	@Autowired
	private HpCardaccountService hpCardaccountService;
	
	@ModelAttribute
	public HpCardaccount get(@RequestParam(required=false) String id) {
		HpCardaccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hpCardaccountService.get(id);
		}
		if (entity == null){
			entity = new HpCardaccount();
		}
		return entity;
	}
	
	/**
	 * 卡帐关系列表页面
	 */
	@RequiresPermissions("hc:hpCardaccount:list")
	@RequestMapping(value = {"list", ""})
	public String list(HpCardaccount hpCardaccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HpCardaccount> page = hpCardaccountService.findPage(new Page<HpCardaccount>(request, response), hpCardaccount); 
		model.addAttribute("page", page);
		return "modules/hc/hpCardaccountList";
	}

	/**
	 * 查看，增加，编辑卡帐关系表单页面
	 */
	@RequiresPermissions(value={"hc:hpCardaccount:view","hc:hpCardaccount:add","hc:hpCardaccount:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(HpCardaccount hpCardaccount, Model model) {
		model.addAttribute("hpCardaccount", hpCardaccount);
		return "modules/hc/hpCardaccountForm";
	}

	/**
	 * 保存卡帐关系
	 */
	@RequiresPermissions(value={"hc:hpCardaccount:add","hc:hpCardaccount:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(HpCardaccount hpCardaccount, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, hpCardaccount)){
			return form(hpCardaccount, model);
		}
		if(!hpCardaccount.getIsNewRecord()){//编辑表单保存
			HpCardaccount t = hpCardaccountService.get(hpCardaccount.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(hpCardaccount, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			hpCardaccountService.save(t);//保存
		}else{//新增表单保存
			hpCardaccountService.save(hpCardaccount);//保存
		}
		addMessage(redirectAttributes, "保存卡帐关系成功");
		return "redirect:"+Global.getAdminPath()+"/hc/hpCardaccount/?repage";
	}
	
	/**
	 * 删除卡帐关系
	 */
	@RequiresPermissions("hc:hpCardaccount:del")
	@RequestMapping(value = "delete")
	public String delete(HpCardaccount hpCardaccount, RedirectAttributes redirectAttributes) {
		hpCardaccountService.delete(hpCardaccount);
		addMessage(redirectAttributes, "删除卡帐关系成功");
		return "redirect:"+Global.getAdminPath()+"/hc/hpCardaccount/?repage";
	}
	
	/**
	 * 批量删除卡帐关系
	 */
	@RequiresPermissions("hc:hpCardaccount:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			hpCardaccountService.delete(hpCardaccountService.get(id));
		}
		addMessage(redirectAttributes, "删除卡帐关系成功");
		return "redirect:"+Global.getAdminPath()+"/hc/hpCardaccount/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("hc:hpCardaccount:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HpCardaccount hpCardaccount, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "卡帐关系"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<HpCardaccount> page = hpCardaccountService.findPage(new Page<HpCardaccount>(request, response, -1), hpCardaccount);
    		new ExportExcel("卡帐关系", HpCardaccount.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出卡帐关系记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hc/hpCardaccount/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("hc:hpCardaccount:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<HpCardaccount> list = ei.getDataList(HpCardaccount.class);
			for (HpCardaccount hpCardaccount : list){
				try{
					hpCardaccountService.save(hpCardaccount);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条卡帐关系记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条卡帐关系记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入卡帐关系失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hc/hpCardaccount/?repage";
    }
	
	/**
	 * 下载导入卡帐关系数据模板
	 */
	@RequiresPermissions("hc:hpCardaccount:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "卡帐关系数据导入模板.xlsx";
    		List<HpCardaccount> list = Lists.newArrayList(); 
    		new ExportExcel("卡帐关系数据", HpCardaccount.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/hc/hpCardaccount/?repage";
    }
	
	
	

}