package com.healthpay.modules.gen.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.healthpay.common.config.Global;
import com.healthpay.common.mapper.JaxbMapper;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.FileUtils;
import com.healthpay.common.utils.FreeMarkers;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.gen.entity.GenCategory;
import com.healthpay.modules.gen.entity.GenConfig;
import com.healthpay.modules.gen.entity.GenScheme;
import com.healthpay.modules.gen.entity.GenTable;
import com.healthpay.modules.gen.entity.GenTableColumn;
import com.healthpay.modules.gen.entity.GenTemplate;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.utils.UserUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

public class GenUtil {
	private static Logger logger = LoggerFactory.getLogger(GenUtil.class);

	public static void setColumnValue(GenTable genTable) {
		List<GenTableColumn> list = genTable.getColumnList() ;
		for (GenTableColumn column : list) {
			if (StringUtils.isNotBlank(column.getId())) {
				continue;
			}
			if (StringUtils.isBlank(column.getComments())) {
				column.setComments(column.getName());
			}

			if ((StringUtils.startsWithIgnoreCase(column.getJdbcType(), "CHAR"))
					|| (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "VARCHAR"))
					|| (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NARCHAR"))) {
				column.setJavaType("String");
			} else if ((StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATETIME"))
					|| (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATE"))
					|| (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "TIMESTAMP"))) {
				column.setJavaType("java.util.Date");
				column.setShowType("dateselect");
			} else if ((StringUtils.startsWithIgnoreCase(column.getJdbcType(), "BIGINT"))
					|| (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NUMBER"))) {
				String[] ss;
				if (((ss = StringUtils.split(StringUtils.substringBetween(column.getJdbcType(), "(", ")"),
						",")) != null) && (ss.length == 2) && (Integer.parseInt(ss[1]) > 0)) {
					column.setJavaType("Double");
				} else if ((ss != null) && (ss.length == 1) && (Integer.parseInt(ss[0]) <= 10)) {
					column.setJavaType("Integer");
				} else {
					column.setJavaType("Long");
				}

			}

			column.setJavaField(StringUtils.toCamelCase(column.getName()));

			column.setIsPk(genTable.getPkList().contains(column.getName()) ? "1" : "0");

			column.setIsInsert("1");

			if ((!StringUtils.equalsIgnoreCase(column.getName(), "id"))
					&& (!StringUtils.equalsIgnoreCase(column.getName(), "create_by"))
					&& (!StringUtils.equalsIgnoreCase(column.getName(), "create_date"))
					&& (!StringUtils.equalsIgnoreCase(column.getName(), "del_flag"))) {
				column.setIsEdit("1");
			}else {
				column.setIsEdit("0");
			}

			if ((StringUtils.equalsIgnoreCase(column.getName(), "name"))
					|| (StringUtils.equalsIgnoreCase(column.getName(), "title"))
					|| (StringUtils.equalsIgnoreCase(column.getName(), "remarks"))
					|| (StringUtils.equalsIgnoreCase(column.getName(), "update_date")))
				column.setIsList("1");
			else {
				column.setIsList("0");
			}

			if ((StringUtils.equalsIgnoreCase(column.getName(), "name"))
					|| (StringUtils.equalsIgnoreCase(column.getName(), "title")))
				column.setIsQuery("1");
			else {
				column.setIsQuery("0");
			}

			if ((StringUtils.equalsIgnoreCase(column.getName(), "name"))
					|| (StringUtils.equalsIgnoreCase(column.getName(), "title")))
				column.setQueryType("like");
			else {
				column.setQueryType("=");
			}

			if (StringUtils.startsWithIgnoreCase(column.getName(), "user_id")) {
				column.setJavaType(User.class.getName());
				column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
				column.setShowType("userselect");
			} else if (StringUtils.startsWithIgnoreCase(column.getName(), "office_id")) {
				column.setJavaType(Office.class.getName());
				column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
				column.setShowType("officeselect");
			} else if (StringUtils.startsWithIgnoreCase(column.getName(), "area_id")) {
				column.setJavaType(Area.class.getName());
				column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
				column.setShowType("areaselect");
			} else {
				if ((StringUtils.startsWithIgnoreCase(column.getName(), "create_by"))
						|| (StringUtils.startsWithIgnoreCase(column.getName(), "update_by"))) {
					column.setJavaType(User.class.getName());
					column.setJavaField(column.getJavaField() + ".id");
				} else {
					if ((StringUtils.startsWithIgnoreCase(column.getName(), "create_date"))
							|| (StringUtils.startsWithIgnoreCase(column.getName(), "update_date"))) {
						column.setShowType("dateselect");
						continue;
					}

					if ((StringUtils.equalsIgnoreCase(column.getName(), "remarks"))
							|| (StringUtils.equalsIgnoreCase(column.getName(), "content"))) {
						column.setShowType("textarea");
						continue;
					}

					if (StringUtils.equalsIgnoreCase(column.getName(), "parent_id")) {
						column.setJavaType("This");
						column.setJavaField("parent.id|name");
						column.setShowType("treeselect");
						continue;
					}

					if (StringUtils.equalsIgnoreCase(column.getName(), "parent_ids")) {
						column.setShowType("input");
						column.setQueryType("like");
						continue;
					}

					if (StringUtils.equalsIgnoreCase(column.getName(), "del_flag")) {
						column.setShowType("radiobox");
						column.setDictType("del_flag");
						continue;
					}
				}
				column.setShowType("input");
			}
		}
	}

	public static String getGenUtilPath() {
		try {
			File file;
			if ((file = new DefaultResourceLoader().getResource("").getFile()) != null) {
				return file.getAbsolutePath() + File.separator
						+ StringUtils.replaceEach(GenUtil.class.getName(),
								new String[] { "util." + GenUtil.class.getSimpleName(), "." },
								new String[] { "template", File.separator });
			}
		} catch (Exception e) {
			logger.error("{}", e);
		}

		return "";
	}

	@SuppressWarnings("unchecked")
	private static <T> T configMapper(String fileName, Class<?> clazz) {
		try {
			String pathName = "/templates/modules/gen/" + fileName;

			InputStream is = (new ClassPathResource(pathName)).getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			if (is != null) {
				is.close();
			}

			br.close();

			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			logger.warn("Error file convert: {}", e.getMessage());
		}

		return null;
	}

	public static GenConfig configMapper() {
		return (GenConfig) configMapper("config.xml", GenConfig.class);
	}

	public static List<GenTemplate> genTemplateListByConfig(GenConfig config, String category, boolean isChildTable) {
		List<String> list = new ArrayList<String>();
		List<GenTemplate> templateList = Lists.newArrayList();
		if ((config != null) && (config.getCategoryList() != null) && (category != null)) {
			for (GenCategory e : config.getCategoryList()) {
				if (!category.equals(e.getValue()))
					continue;
				if (!isChildTable)
					list = e.getTemplate();
				else {
					list = e.getChildTableTemplate();
				}
				if (list == null)
					break;
				for (String s : list) {
					if (StringUtils.startsWith(s, GenCategory.CATEGORY_REF)) {
						templateList.addAll(genTemplateListByConfig(config, StringUtils.replace(s, GenCategory.CATEGORY_REF, ""), false));
					} else {
						GenTemplate template;
						if ((template = (GenTemplate) configMapper(s, GenTemplate.class)) != null) {
							templateList.add(template);
						}
					}
				}
				break;
			}
		}

		return templateList;
	}

	public static Map<String, Object> getTemplateModel(GenScheme genScheme) {
		Map<String, Object> model = Maps.newHashMap() ;
		model.put("packageName", StringUtils.lowerCase(genScheme.getPackageName()));
		model.put("lastPackageName", StringUtils.substringAfterLast((String) model.get("packageName"), "."));
		model.put("moduleName", StringUtils.lowerCase(genScheme.getModuleName()));
		model.put("subModuleName", StringUtils.lowerCase(genScheme.getSubModuleName()));
		model.put("className", StringUtils.uncapitalize(genScheme.getGenTable().getClassName()));
		model.put("ClassName", StringUtils.capitalize(genScheme.getGenTable().getClassName()));

		model.put("functionName", genScheme.getFunctionName());
		model.put("functionNameSimple", genScheme.getFunctionNameSimple());
		model.put("functionAuthor", StringUtils.isNotBlank(genScheme.getFunctionAuthor())
				? genScheme.getFunctionAuthor() : UserUtils.getUser().getName());
		model.put("functionVersion", DateUtils.getDate());

		model.put("urlPrefix",
				model.get("moduleName")
						+ (StringUtils.isNotBlank(genScheme.getSubModuleName())
								? "/" + StringUtils.lowerCase(genScheme.getSubModuleName()) : "")
						+ "/" + model.get("className"));
		model.put("viewPrefix", model.get("urlPrefix"));
		model.put("permissionPrefix",
				model.get("moduleName")
						+ (StringUtils.isNotBlank(genScheme.getSubModuleName())
								? ":" + StringUtils.lowerCase(genScheme.getSubModuleName()) : "")
						+ ":" + model.get("className"));

		model.put("dbType", Global.getConfig("jdbc.type"));

		model.put("table", genScheme.getGenTable());

		return model;
	}

	public static String writeFile(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile) {
		String fileName = Global.getProjectPath() + File.separator
				+ StringUtils.replaceEach(
						FreeMarkers.renderString(
								new StringBuilder(String.valueOf(tpl.getFilePath())).append("/").toString(), model),
						new String[] { "//", "/", "." },
						new String[] { File.separator, File.separator, File.separator })
				+ FreeMarkers.renderString(tpl.getFileName(), model);
		logger.debug(" fileName === " + fileName);

		String content = FreeMarkers.renderString(StringUtils.trimToEmpty(tpl.getContent()), model);
		logger.debug(" content === \r\n" + content);

		FileUtils.deleteFile(fileName);

		if (FileUtils.createFile(fileName)) {
			FileUtils.writeToFile(fileName, content, true);
			logger.debug(" file create === " + fileName);
			return "生成成功：" + fileName + "<br/>";
		}
		logger.debug(" file extents === " + fileName);
		return "文件已存在：" + fileName + "<br/>";
	}
}
