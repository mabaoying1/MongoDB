package com.healthpay.modules.gen.web;

import com.google.common.collect.Lists;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.gen.entity.GenScheme;
import com.healthpay.modules.gen.service.GenSchemeService;
import com.healthpay.modules.gen.service.GenTableService;
import com.healthpay.modules.gen.util.GenUtil;
import com.healthpay.modules.sys.entity.Menu;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.service.SystemService;
import com.healthpay.modules.sys.utils.UserUtils;
import java.util.List;
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
@RequestMapping({ "${adminPath}/gen/genScheme" })
public class GenSchemeController extends BaseController {

	@Autowired
	public GenSchemeService genSchemeService;

	@Autowired
	public GenTableService genTableService;

	@Autowired
	public SystemService systemService;

	@ModelAttribute
	private GenScheme get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return this.genSchemeService.get(id);
		}
		return new GenScheme();
	}

	@RequiresPermissions({ "gen:genScheme:view" })
	@RequestMapping({ "list", "" })
	private String list(GenScheme genScheme, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user =UserUtils.getUser() ;
		if (user != null && !user.isAdmin()) {
			genScheme.setCreateBy(user);
		}
		Page<GenScheme> page = this.genSchemeService.find(new Page<GenScheme>(request, response), genScheme);
		model.addAttribute("page", page);

		return "modules/gen/genSchemeList";
	}

	@RequiresPermissions({ "gen:genScheme:view" })
	@RequestMapping({ "form" })
	private String form(GenScheme genScheme, Model model) {
		if (StringUtils.isBlank(genScheme.getPackageName())) {
			genScheme.setPackageName("com.healthpay.modules");
		}

		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", GenUtil.configMapper());
		model.addAttribute("tableList", this.genTableService.findAll());
		return "modules/gen/genSchemeForm";
	}

	@RequiresPermissions({ "gen:genScheme:edit" })
	@RequestMapping({ "save" })
	private String save(GenScheme genScheme, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, genScheme)) {
			if (StringUtils.isBlank(genScheme.getPackageName())) {
				genScheme.setPackageName("com.healthpay.modules");
			}

			model.addAttribute("genScheme", genScheme);
			model.addAttribute("config", GenUtil.configMapper());
			model.addAttribute("tableList", this.genTableService.findAll());

			return "modules/gen/genSchemeForm";
		}
		String result = this.genSchemeService.save(genScheme);
		addMessage(redirectAttributes, new String[] { "操作生成方案'" + genScheme.getName() + "'成功<br/>" + result });
		return "redirect:" + this.adminPath + "/gen/genScheme/?repage";
	}

	@RequiresPermissions({ "gen:genScheme:edit" })
	@RequestMapping({ "delete" })
	private String delete(GenScheme genScheme, RedirectAttributes redirectAttributes) {
		this.genSchemeService.delete(genScheme);
		addMessage(redirectAttributes, new String[] { "删除生成方案成功" });
		return "redirect:" + this.adminPath + "/gen/genScheme/?repage";
	}

	@RequestMapping({ "menuForm" })
	private String menuForm(String gen_table_id, Menu menu, Model model) {
		if ((menu.getParent() == null) || (menu.getParent().getId() == null)) {
			menu.setParent(new Menu(Menu.getRootId()));
		}
		menu.setParent(this.systemService.getMenu(menu.getParent().getId()));

		if (StringUtils.isBlank(menu.getId())) {
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = this.systemService.findAllMenu();
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			if (list.size() > 0)
				menu.setSort(Integer.valueOf(((Menu) list.get(list.size() - 1)).getSort().intValue() + 30));
		}
		GenScheme genScheme =this.genSchemeService.findUniqueByProperty("gen_table_id", gen_table_id) ;
		if (genScheme  != null) {
			menu.setName(genScheme.getFunctionName());
		}

		model.addAttribute("menu", menu);
		model.addAttribute("gen_table_id", gen_table_id);
		return "modules/gen/genMenuForm";
	}

	@RequestMapping({ "createMenu" })
	private String createMenu(String gen_table_id, Menu menu, RedirectAttributes redirectAttributes) {
		GenScheme genScheme  = this.genSchemeService.findUniqueByProperty("gen_table_id", gen_table_id );
		if (genScheme == null) {
			addMessage(redirectAttributes, new String[] { "创建菜单失败,请先生成代码!" });
			return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}
		genScheme.setGenTable(this.genTableService.get(gen_table_id));
		this.genSchemeService.createMenu(genScheme, menu);
		addMessage(redirectAttributes, new String[] { "创建菜单'" + genScheme.getFunctionName() + "'成功<br/>" });
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}
}