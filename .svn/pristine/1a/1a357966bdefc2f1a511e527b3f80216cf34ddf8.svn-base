package com.healthpay.modules.gen.service;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.BaseService;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.gen.dao.GenSchemeDao;
import com.healthpay.modules.gen.dao.GenTableColumnDao;
import com.healthpay.modules.gen.dao.GenTableDao;
import com.healthpay.modules.gen.entity.GenConfig;
import com.healthpay.modules.gen.entity.GenScheme;
import com.healthpay.modules.gen.entity.GenTable;
import com.healthpay.modules.gen.entity.GenTableColumn;
import com.healthpay.modules.gen.entity.GenTemplate;
import com.healthpay.modules.gen.util.GenUtil;
import com.healthpay.modules.sys.entity.Menu;
import com.healthpay.modules.sys.service.SystemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {

	@Autowired
	private GenSchemeDao genSchemeDao;

	@Autowired
	private GenTableDao genTableDao;

	@Autowired
	private GenTableColumnDao genTableColumnDao;

	@Autowired
	private SystemService systemService;

	public GenScheme get(String id) {
		return (GenScheme) this.genSchemeDao.get(id);
	}

	public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		GenUtil.getGenUtilPath();
		genScheme.setPage(page);
		page.setList(this.genSchemeDao.findList(genScheme));
		return page;
	}

	@Transactional(readOnly = false)
	public String save(GenScheme genScheme) {
		if (StringUtils.isBlank(genScheme.getId())) {
			genScheme.preInsert();
			this.genSchemeDao.insert(genScheme);
		} else {
			genScheme.preUpdate();
			this.genSchemeDao.update(genScheme);
		}
		return generateCode(genScheme);
	}

	@Transactional(readOnly = false)
	public void createMenu(GenScheme genScheme, Menu topMenu) {
		Menu addMenu;
		String permissionPrefix = StringUtils.lowerCase(genScheme.getModuleName())
				+ (StringUtils.isNotBlank(genScheme.getSubModuleName())
						? ":" + StringUtils.lowerCase(genScheme.getSubModuleName()) : "")
				+ ":" + StringUtils.uncapitalize(genScheme.getGenTable().getClassName());
		String url = "/" + StringUtils.lowerCase(genScheme.getModuleName())
				+ (StringUtils.isNotBlank(genScheme.getSubModuleName())
						? "/" + StringUtils.lowerCase(genScheme.getSubModuleName()) : "")
				+ "/" + StringUtils.uncapitalize(genScheme.getGenTable().getClassName());

		topMenu.setName(genScheme.getFunctionName());
		topMenu.setHref(url);
		topMenu.setIsShow("1");
		topMenu.setPermission(permissionPrefix + ":list");
		this.systemService.saveMenu(topMenu);

		(addMenu = new Menu()).setName("增加");
		addMenu.setIsShow("0");
		addMenu.setSort(Integer.valueOf(30));
		addMenu.setPermission(permissionPrefix + ":add");
		addMenu.setParent(topMenu);
		this.systemService.saveMenu(addMenu);
		Menu delMenu;
		(delMenu = new Menu()).setName("删除");
		delMenu.setIsShow("0");
		delMenu.setSort(Integer.valueOf(60));
		delMenu.setPermission(permissionPrefix + ":del");
		delMenu.setParent(topMenu);
		this.systemService.saveMenu(delMenu);
		Menu editMenu;
		(editMenu = new Menu()).setName("编辑");
		editMenu.setIsShow("0");
		editMenu.setSort(Integer.valueOf(90));
		editMenu.setPermission(permissionPrefix + ":edit");
		editMenu.setParent(topMenu);
		this.systemService.saveMenu(editMenu);
		Menu viewMenu;
		(viewMenu = new Menu()).setName("查看");
		viewMenu.setIsShow("0");
		viewMenu.setSort(Integer.valueOf(120));
		viewMenu.setPermission(permissionPrefix + ":view");
		viewMenu.setParent(topMenu);
		this.systemService.saveMenu(viewMenu);
		Menu importMenu;
		(importMenu = new Menu()).setName("导入");
		importMenu.setIsShow("0");
		importMenu.setSort(Integer.valueOf(150));
		importMenu.setPermission(permissionPrefix + ":import");
		importMenu.setParent(topMenu);
		this.systemService.saveMenu(importMenu);
		Menu exportMenu;
		(exportMenu = new Menu()).setName("导出");
		exportMenu.setIsShow("0");
		exportMenu.setSort(Integer.valueOf(180));
		exportMenu.setPermission(permissionPrefix + ":export");
		exportMenu.setParent(topMenu);
		this.systemService.saveMenu(exportMenu);
	}

	@Transactional(readOnly = false)
	public void delete(GenScheme genScheme) {
		this.genSchemeDao.delete(genScheme);
	}

	private String generateCode(GenScheme genScheme) {
		StringBuilder result = new StringBuilder();
		GenTable genTable = (GenTable) this.genTableDao.get(genScheme.getGenTable().getId());
		genTable.setColumnList(this.genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
		GenConfig config;
		List<GenTemplate> templateList = GenUtil.genTemplateListByConfig(config = GenUtil.configMapper(),
				genScheme.getCategory(), false);
		List<GenTemplate> childTableTemplateList;
		if ((childTableTemplateList = GenUtil.genTemplateListByConfig(config, genScheme.getCategory(), true))
				.size() > 0) {
			GenTable parentTable;
			(parentTable = new GenTable()).setParentTable(genTable.getName());
			genTable.setChildList(this.genTableDao.findList(parentTable));
		}
		for (GenTable childTable : genTable.getChildList()) {
			childTable.setParent(genTable);
			childTable.setColumnList(
					this.genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
			genScheme.setGenTable(childTable);
			Map<String, Object> childTableModel = GenUtil.getTemplateModel(genScheme);
			for (GenTemplate tpl : childTableTemplateList) {
				result.append(GenUtil.writeFile(tpl, childTableModel, true));
			}
		}
		genScheme.setGenTable(genTable);
		Map<String, Object> model = GenUtil.getTemplateModel(genScheme);
		for (GenTemplate ftl : templateList) {
			result.append(GenUtil.writeFile(ftl, model, true));
		}
		return result.toString();
	}

	public GenScheme findUniqueByProperty(String propertyName, String value) {
		return (GenScheme) this.genSchemeDao.findUniqueByProperty(propertyName, value);
	}
}
