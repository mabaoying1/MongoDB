package com.healthpay.modules.gen.web;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.gen.entity.GenTemplate;
import com.healthpay.modules.gen.service.GenTemplateService;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.utils.UserUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({ "${adminPath}/gen/genTemplate" })
public class GenTemplateController extends BaseController {

	@Autowired
	private GenTemplateService genTemplateService;

	@ModelAttribute
	private GenTemplate get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return genTemplateService.get(id);
		}
		return new GenTemplate();
	}

	@RequiresPermissions({ "gen:genTemplate:view" })
	@RequestMapping({ "list", "" })
	private String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user;
		if (!(user = UserUtils.getUser()).isAdmin()) {
			genTemplate.setCreateBy(user);
		}
		Page<GenTemplate> page = genTemplateService.find(new Page<GenTemplate>(request, response), genTemplate);
		model.addAttribute("page", page);
		return "modules/gen/genTemplateList";
	}

	@RequiresPermissions({ "gen:genTemplate:view" })
	@RequestMapping({ "form" })
	private static String form(GenTemplate genTemplate, Model model) {
		model.addAttribute("genTemplate", genTemplate);
		return "modules/gen/genTemplateForm";
	}

	@RequiresPermissions({ "gen:genTemplate:edit" })
	@RequestMapping({ "save" })
	private String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, genTemplate, new Class[0])) {

			model.addAttribute("genTemplate", genTemplate);

			return "modules/gen/genTemplateForm";
		}
		genTemplateService.save(genTemplate);
		addMessage(redirectAttributes, new String[] { "保存代码模板'" + genTemplate.getName() + "'成功" });
		return "redirect:" + this.adminPath + "/gen/genTemplate/?repage";
	}

	@RequiresPermissions({ "gen:genTemplate:del" })
	@RequestMapping({ "delete" })
	private String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
		genTemplateService.delete(genTemplate);
		addMessage(redirectAttributes, new String[] { "删除代码模板成功" });
		return "redirect:" + this.adminPath + "/gen/genTemplate/?repage";
	}
}