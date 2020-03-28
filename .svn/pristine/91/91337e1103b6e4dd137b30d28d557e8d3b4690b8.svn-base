package com.healthpay.modules.gen.web;

import com.google.common.collect.Lists;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.gen.dao.GenTemplateDao;
import com.healthpay.modules.gen.entity.GenScheme;
import com.healthpay.modules.gen.entity.GenTable;
import com.healthpay.modules.gen.entity.GenTableColumn;
import com.healthpay.modules.gen.service.GenSchemeService;
import com.healthpay.modules.gen.service.GenTableService;
import com.healthpay.modules.gen.service.GenTemplateService;
import com.healthpay.modules.gen.util.GenUtil;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.utils.UserUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({ "${adminPath}/gen/genTable" })
public class GenTableController extends BaseController {

	@Autowired
	public GenTemplateService genTemplateService;

	@Autowired
	public GenTableService genTableService;

	@Autowired
	public GenSchemeService genSchemeService;

	@Autowired
	public GenTemplateDao genTemplateDao;
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(1024);
	}

	private GenTable get(GenTable genTable) {
		if (StringUtils.isNotBlank(genTable.getId())) {
			return this.genTableService.get(genTable.getId());
		} else {
			genTable.setColumnList(defaultColumnList());
		}
		return genTable;
	}

	private List<GenTableColumn> defaultColumnList() {
		List<GenTableColumn> list = Lists.newArrayList();
		GenTableColumn column = new GenTableColumn("id", "主键", "varchar(64)", "String", "id", "1", "0", "1", "0", "0",
				"0", "0", "=", "input", "");
		GenTableColumn column2 = new GenTableColumn("create_by", "创建者", "varchar(64)", "String", "createBy.id", "0",
				"0", "1", "0", "0", "0", "0", "=", "input", "");
		GenTableColumn column3 = new GenTableColumn("create_date", "创建时间", "datetime", "java.util.Date", "createDate",
				"0", "0", "1", "0", "0", "0", "0", "=", "dateselect", "");
		GenTableColumn column4 = new GenTableColumn("update_by", "更新者", "varchar(64)", "String", "updateBy.id", "0",
				"1", "1", "1", "0", "0", "0", "=", "input", "");
		GenTableColumn column5 = new GenTableColumn("update_date", "更新时间", "datetime", "java.util.Date", "updateDate",
				"0", "1", "1", "1", "0", "0", "0", "=", "dateselect", "");
		GenTableColumn column6 = new GenTableColumn("remarks", "备注信息", "nvarchar(255)", "String", "remarks", "0", "1",
				"1", "1", "1", "1", "0", "=", "textarea", "");
		GenTableColumn column7 = new GenTableColumn("del_flag", "逻辑删除标记（0：显示；1：隐藏）", "varchar(64)", "String", "delFlag",
				"0", "0", "1", "0", "0", "0", "0", "=", "radiobox", "del_flag");
		list.add(column);
		list.add(column2);
		list.add(column3);
		list.add(column4);
		list.add(column5);
		list.add(column6);
		list.add(column7);
		return list;
	}

	@RequiresPermissions({ "gen:genTable:list" })
	@RequestMapping({ "list", "" })
	private String a(GenTable genTable, HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		genTable = get(genTable);
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			genTable.setCreateBy(user);
		}
		Page<GenTable> page = this.genTableService.find(new Page<GenTable>(request, response), genTable);
		model.addAttribute("page", page);
		return "modules/gen/genTableList";
	}

	@RequiresPermissions(value = { "gen:genTable:view", "gen:genTable:add", "gen:genTable:edit" }, logical = Logical.OR)
	@RequestMapping({ "form" })
	private String form(GenTable genTable, HttpServletResponse response, Model model) throws IOException {
		genTable = get(genTable);
		model.addAttribute("genTable", genTable);
		model.addAttribute("config", com.healthpay.modules.gen.util.GenUtil.configMapper());
		model.addAttribute("tableList", this.genTableService.findAll());
		return "modules/gen/genTableForm";
	}

	@RequiresPermissions(value = { "gen:genTable:add", "gen:genTable:edit" }, logical = Logical.OR)
	@RequestMapping({ "save" })
	private String save(GenTable genTable, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) throws IOException {
		if (!beanValidator(model, genTable)) {
			genTable = get(genTable);
			model.addAttribute("genTable", genTable);
			model.addAttribute("config", GenUtil.configMapper());
			model.addAttribute("tableList", this.genTableService.findAll());

			return "modules/gen/genTableForm";
		}
		if ((StringUtils.isBlank(genTable.getId())) && (!this.genTableService.checkTableName(genTable.getName()))) {
			addMessage(redirectAttributes, new String[] { "添加失败！" + genTable.getName() + " 记录已存在！" });
			return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}

		if ((StringUtils.isBlank(genTable.getId()))
				&& (!this.genTableService.checkTableNameFromDB(genTable.getName()))) {
			addMessage(redirectAttributes, new String[] { "添加失败！" + genTable.getName() + "表已经在数据库中存在,请从数据库导入表单！" });
			return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}

		this.genTableService.save(genTable);
		addMessage(redirectAttributes, new String[] { "保存业务表'" + genTable.getName() + "'成功" });
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:importDb" })
	@RequestMapping({ "importTableFromDB" })
	private String importTableFromDB(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
		genTable = get(genTable);
		if (!StringUtils.isBlank(genTable.getName())) {
			if (!this.genTableService.checkTableName(genTable.getName())) {
				addMessage(redirectAttributes, new String[] { "下一步失败！" + genTable.getName() + " 表已经添加！" });
				return "redirect:" + this.adminPath + "/gen/genTable/?repage";
			}
			genTable = this.genTableService.getTableFormDb(genTable);
			genTable.setTableType("0");
			genTable.setIsSync("1");
			this.genTableService.saveFromDB(genTable);
			addMessage(redirectAttributes, new String[] { "数据库导入表单'" + genTable.getName() + "'成功" });
			return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}

		List<GenTable> tableList = this.genTableService.findTableListFormDb(new GenTable());
		model.addAttribute("tableList", tableList);
		model.addAttribute("config", GenUtil.configMapper());
		return "modules/gen/importTableFromDB";
	}

	@RequiresPermissions({ "gen:genTable:del" })
	@RequestMapping({ "delete" })
	private String delete(GenTable genTable, RedirectAttributes redirectAttributes) {
		genTable = get(genTable);
		this.genTableService.delete(genTable);
		this.genSchemeService.delete(this.genSchemeService.findUniqueByProperty("gen_table_id", genTable.getId()));
		addMessage(redirectAttributes, new String[] { "移除业务表记录成功" });
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:del" })
	@RequestMapping({ "deleteDb" })
	private String deleteDb(GenTable genTable, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode().booleanValue()) {
			addMessage(redirectAttributes, new String[] { "演示模式，不允许操作！" });
			return "redirect:" + this.adminPath + "/gen/genTable/?repage";
		}
		genTable = get(genTable);
		this.genTableService.delete(genTable);
		this.genSchemeService.delete(this.genSchemeService.findUniqueByProperty("gen_table_id", genTable.getId()));
		StringBuffer sql = new StringBuffer();
		String dbType = Global.getConfig("jdbc.type");
		if ("mysql".equals(dbType)) {
			sql.append("drop table if exists " + genTable.getName() + " ;");
		} else if ("oracle".equals(dbType))
				sql.append("DROP TABLE " + genTable.getName());
		else if (("mssql".equals(dbType)) || ("sqlserver".equals(dbType))) {
			sql.append("if exists (select * from sysobjects where id = object_id(N'[" + genTable.getName()
					+ "]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)  drop table [" + genTable.getName() + "]");
		}
		try {
			this.genTableService.buildTable(sql.toString());
			addMessage(redirectAttributes, new String[] { "删除业务表记录和数据库表成功" });
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addMessage(redirectAttributes, new String[] { "删除业务表记录和数据库表失败" });
		}
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:del" })
	@RequestMapping({ "deleteAll" })
	private String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		for (String id : ids.split(",")) {
			this.genTableService.delete(this.genTableService.get(id));
		}
		addMessage(redirectAttributes, new String[] { "删除业务表成功" });
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:synchDb" })
	@RequestMapping({ "synchDb" })
	private String synchDb(GenTable genTable, RedirectAttributes redirectAttributes) {
		String dbType = Global.getConfig("jdbc.type");

		List<GenTableColumn> getTableColumnList = (genTable = get(genTable)).getColumnList();
		StringBuffer sql;
		if ("mysql".equals(dbType)) {
			(sql = new StringBuffer()).append("drop table if exists " + genTable.getName() + " ;");
			this.genTableService.buildTable(sql.toString());

			(sql = new StringBuffer()).append("create table " + genTable.getName() + " (");
			String pk = "";
			for (GenTableColumn column : getTableColumnList) {
				if (column.getIsPk().equals("1")) {
					sql.append("  " + column.getName() + " " + column.getJdbcType() + " comment '"
							+ column.getComments() + "',");
					pk = pk + column.getName() + ",";
				} else {
					sql.append("  " + column.getName() + " " + column.getJdbcType() + " comment '"
							+ column.getComments() + "',");
				}
			}
			sql.append("primary key (" + pk.substring(0, pk.length() - 1) + ") ");
			sql.append(") comment '" + genTable.getComments() + "'");
			this.genTableService.buildTable(sql.toString());
		} else {
			if ("oracle".equals(dbType)) {
				sql = new StringBuffer();
				try {
					sql.append("DROP TABLE " + genTable.getName());
					this.genTableService.buildTable(sql.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				sql = new StringBuffer();
				sql.append("create table " + genTable.getName() + " (");
				String pk = "";
				for (GenTableColumn column : getTableColumnList) {
					String jdbctype = column.getJdbcType();
					if (jdbctype.equalsIgnoreCase("integer"))
						jdbctype = "number(10,0)";
					else if (jdbctype.equalsIgnoreCase("datetime"))
						jdbctype = "date";
					else if (jdbctype.contains("nvarchar("))
						jdbctype = jdbctype.replace("nvarchar", "nvarchar2");
					else if (jdbctype.contains("varchar("))
						jdbctype = jdbctype.replace("varchar", "varchar2");
					else if (jdbctype.equalsIgnoreCase("double"))
						jdbctype = "float(24)";
					else if (jdbctype.equalsIgnoreCase("longblob"))
						jdbctype = "blob raw";
					else if (jdbctype.equalsIgnoreCase("longtext")) {
						jdbctype = "clob raw";
					}
					if (column.getIsPk().equals("1")) {
						sql.append("  " + column.getName() + " " + jdbctype + ",");
						pk = pk + column.getName();
					} else {
						sql.append("  " + column.getName() + " " + jdbctype + ",");
					}
				}
				sql = new StringBuffer(sql.substring(0, sql.length() - 1) + ")");
				this.genTableService.buildTable(sql.toString());
				this.genTableService
						.buildTable("comment on table " + genTable.getName() + " is  '" + genTable.getComments() + "'");
				for (GenTableColumn column : getTableColumnList) {
					this.genTableService.buildTable("comment on column " + genTable.getName() + "." + column.getName()
							+ " is  '" + column.getComments() + "'");
				}

				this.genTableService.buildTable("alter table " + genTable.getName() + " add constraint PK_"
						+ genTable.getName() + "_" + pk + " primary key (" + pk + ") ");
			} else if (("mssql".equals(dbType)) || ("sqlserver".equals(dbType))) {
				(sql = new StringBuffer()).append("if exists (select * from sysobjects where id = object_id(N'["
						+ genTable.getName() + "]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)  drop table ["
						+ genTable.getName() + "]");
				this.genTableService.buildTable(sql.toString());
				(sql = new StringBuffer()).append("create table " + genTable.getName() + " (");
				String pk = "";
				for (GenTableColumn column : getTableColumnList) {
					if (column.getIsPk().equals("1")) {
						sql.append("  " + column.getName() + " " + column.getJdbcType() + ",");
						pk = pk + column.getName() + ",";
					} else {
						sql.append("  " + column.getName() + " " + column.getJdbcType() + ",");
					}
				}
				sql.append("primary key (" + pk.substring(0, pk.length() - 1) + ") ");
				sql.append(")");
				this.genTableService.buildTable(sql.toString());
			}
		}

		this.genTableService.syncSave(genTable);

		addMessage(redirectAttributes, new String[] { "强制同步数据库表成功" });
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}

	@RequiresPermissions({ "gen:genTable:genCode" })
	@RequestMapping({ "genCodeForm" })
	private String genCodeForm(GenScheme genScheme, Model model) {
		if (StringUtils.isBlank(genScheme.getPackageName()))
			genScheme.setPackageName("com.healthpay.modules");
		GenScheme oldGenScheme = this.genSchemeService.findUniqueByProperty("gen_table_id",
				genScheme.getGenTable().getId());
		if (oldGenScheme != null) {
			genScheme = oldGenScheme;
		}
		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", GenUtil.configMapper());
		model.addAttribute("tableList", this.genTableService.findAll());
		return "modules/gen/genCodeForm";
	}

	@RequestMapping({ "genCode" })
	private String genCode(GenScheme genScheme, RedirectAttributes redirectAttributes) {
		String result = this.genSchemeService.save(genScheme);
		addMessage(redirectAttributes, new String[] { genScheme.getGenTable().getName() + "代码生成成功<br/>" + result });
		return "redirect:" + this.adminPath + "/gen/genTable/?repage";
	}
}