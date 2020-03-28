package com.healthpay.modules.gen.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;

public class TempalteUtil {
	private static Configuration configuration;

	static {
		configuration = new Configuration();
		configuration.setClassForTemplateLoading(TempalteUtil.class, "/");
	}

	public static String process(String tplName, String encoding, Map<String, Object> paras) {
		try {
			StringWriter swriter = new StringWriter();
			Template template = configuration.getTemplate(tplName, encoding);
			template.process(paras, swriter);
			return swriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

	public final String processUIF8(String tplName, Map<String, Object> paras) {
		return process(tplName, "utf-8", paras);
	}
}