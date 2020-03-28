package com.healthpay.iface.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthpay.iface.vo.ResResultVo;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.healthpay.common.utils.Format;
import com.healthpay.common.utils.StringUtils;

public class IBaseModel {

	private static Logger log = Logger.getLogger(IBaseModel.class);
	private static int iLeve = 2;

	/**
	 * 解析Bean成XML
	 * 
	 * @param header
	 *            头报文
	 * @param bean
	 * @return
	 */
	public static String parseBean2Xml(Object header, Object bean) throws Exception {
		// 解析头报文
		String strHeaderXml = parseBean2Xml(header);
		// 解析报文体
		String strBodyXml = parseBean2Xml(bean);

		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<Data>");
		sb.append("<Head>");
		sb.append(strHeaderXml);
		sb.append("</Head>");

		if (StringUtils.isNotEmpty(strBodyXml)) {
			sb.append("<Body>");
			sb.append(strBodyXml);
			sb.append("</Body>");
		}
		sb.append("</Data>");
		return sb.toString();
	}

	/**
	 * 解析bean成XML
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String parseBean2Xml(Object bean) throws Exception {
		if (null == bean)
			return "";

		StringBuffer sb = new StringBuffer();
		String tabString = "";
		for (int i = 0; i < iLeve; i++) {
			tabString = tabString + "";
		}
		// 先解析Bean的属性
		Map<String, MethodClass> fields = getMethods(bean);
		for (String key : fields.keySet()) {
			MethodClass mc = fields.get(key);
			Object objValue = mc.getMethod.invoke(bean);

			if ("java.util.List".equals(mc.dataType) || "java.util.ArrayList".equals(mc.dataType)) {
				// 资源明细
				List<Object> list = (List<Object>) objValue;
				if (null == list)
					continue;
				StringBuffer sbList = new StringBuffer();
				sbList.append(tabString + "<list name=\"" + key + "\">");
				for (Object newbean : list) {
					sbList.append(tabString + "<row>");
					iLeve = iLeve + 2;
					sbList.append(parseBean2Xml(newbean));
					iLeve = iLeve - 2;
					sbList.append(tabString + "</row>");
				}
				sbList.append(tabString + "</list>");
				sb.append(sbList.toString());
			} else {
				String name = key.substring(0, 1).toUpperCase() + key.substring(1);
				sb.append(tabString + "<" + name + ">" + getValue(objValue, mc.dataType) + "</" + name + ">");
			}
		}

		return sb.toString();
	}

	/**
	 * 根据XML返回bean的内容
	 * 
	 * @param bean
	 *            需要设置的From，必须在调用前初始化
	 * @param xml
	 *            数据XML
	 */
	public static void loadXmlBean(Object bean, String xml) throws Exception {
		loadXml2Bean(bean, xml, "Head");
	}

	/**
	 * 根据XML返回bean的内容
	 * 
	 * @param bean
	 *            需要设置的From，必须在调用前初始化
	 * @param xml
	 *            数据XML
	 */
	public static void loadXml2Bean(Object bean, String xml) throws Exception {
		loadXml2Bean(bean, xml, "Body");
	}

	/**
	 * 根据XML返回bean的内容
	 * 
	 * @param bean
	 *            需要设置的From，必须在调用前初始化
	 * @param xml
	 *            数据XML
	 * @param inTag
	 *            数据标签
	 */
	public static void loadXml2Bean(Object bean, String xml, String inTag) throws Exception {
		if (null == bean)
			return;
		// 先解析XML把
		Document doc = XmlManager.readstr2xml(xml, "UTF-8");
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getElementsByTagName(inTag);

		if (nodes.getLength() <= 0)
			return;
		if (nodes.getLength() != 1)
			throw new Exception("[" + inTag + "]域存在多行数据");
		Node node = nodes.item(0);
		setClass(bean, node);
	}
	
	/**
	 * 
	 * @param clazz
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static <T> T loadXml2Bean(Class<T> clazz, String xml) throws Exception {
		return loadXml2Bean(clazz,  xml,"Body");
	}
	
	/***
	 * 
	 * @param clazz
	 * @param xml
	 * @param inTag
	 * @return
	 * @throws Exception
	 */
	public static <T> T loadXml2Bean(Class<T> clazz, String xml, String inTag) throws Exception {
		
		// 先解析XML把
		Document doc = XmlManager.readstr2xml(xml, "UTF-8");
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getElementsByTagName(inTag);

		if (nodes.getLength() <= 0)
			return null;
		if (nodes.getLength() != 1)
			throw new Exception("[" + inTag + "]域存在多行数据");
		Node node = nodes.item(0);
		T t=clazz.newInstance();
		setClass(t, node);
		return t;
	}

	/**
	 * 设置单个Field的值
	 * 
	 * @param bean
	 * @param node
	 * @param node
	 * @throws Exception
	 */
	private static void setClass(Object bean, Node node) throws Exception {
		// 先把bean的方法全部解析出来
		Map<String, MethodClass> fields = getMethods(bean);
		NodeList nodelist = node.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node nd = nodelist.item(i);
			String nodeName = nd.getNodeName();

			if (nodeName.equals("#text"))
				continue;
			// 如果是数据明细，则
			if ("list".equalsIgnoreCase(nodeName)) {
				nodeName = XmlManager.getAttribValue(nd, "name");
			}
			nodeName = nodeName.substring(0, 1).toLowerCase() + nodeName.substring(1);
			MethodClass mc = fields.get(nodeName);
			// 这里需要根据数据类型转换
			if (null != mc) {
				Object nodeValue = null;
				// 资源明细
				if ("java.util.List".equals(mc.dataType) || "java.util.ArrayList".equals(mc.dataType)) {
					nodeValue = getListClass(mc, nd);
				} else {
					String value = XmlManager.getElementValue(nd);
					nodeValue = getValue(value, mc.dataType);
				}
				mc.setMethod.invoke(bean, nodeValue);
			}
		}

	}

	/**
	 * 对List填值
	 * 
	 * @param mc
	 * @param node
	 * @return
	 * @throws Exception
	 */
	private static List<Object> getListClass(MethodClass mc, Node node) throws Exception {
		if (StringUtils.isNull(mc.listClass))
			return null;
		NodeList nodes = node.getChildNodes();
		if (null == nodes || nodes.getLength() <= 0)
			return null;
		List<Object> list = new ArrayList<Object>();

		for (int i = 0; i < nodes.getLength(); i++) {
			Node rowNode = nodes.item(i);
			String nodeName = rowNode.getNodeName().toLowerCase();
			if (nodeName.equals("#text"))
				continue;
			if (!"row".equalsIgnoreCase(nodeName))
				continue;
			Object newClass = Class.forName(mc.listClass).newInstance();
			setClass(newClass, rowNode);
			list.add(newClass);
		}

		return list;
	}

	/**
	 * 将值转换成String
	 * 
	 * @param value
	 * @param datatype
	 * @return
	 */
	private static String getValue(Object value, String datatype) {
		if (null == value)
			return "";
		String retValue = "";

		if (value instanceof String)
			retValue = (String) value;
		if (value instanceof Date) {
			try {
				retValue = Format.format("yyyy-MM-dd", (Date) value);
			} catch (Exception e) {
				retValue = "";
			}
		}
		if (value instanceof Float || value instanceof Double) {
			Double dValue = (Double) value;
			BigDecimal big = BigDecimal.valueOf(dValue);
			retValue = big.toPlainString();
		}
		if (value instanceof Boolean || value instanceof String || value instanceof Byte || value instanceof Integer
				|| value instanceof Long || value instanceof Short) {
			retValue = value.toString();
		}

		return convert2xmlstring(retValue);
	}

	/**
	 * 将字符串转换成对应的类型：XML->数据类型
	 * 
	 * @param value
	 * @param datatype
	 * @return
	 */
	private static Object getValue(String value, String datatype) {
		if ("java.lang.String".equals(datatype))
			return value;
		if ("java.util.Date".equals(datatype)) {
			Date d = null;
			if (StringUtils.isNull(value))
				return d;

			try {
				d=Format.str2date(value, "yyyy.MM.dd HH:mm:ss");
				return d;
			} catch (Exception e) {

			}

			try {
				d=Format.str2date(value, "yyyy.MM.dd");
				return d;
			} catch (Exception e) {

			}


			try {
				d=Format.str2date(value, "yyyy-MM-dd HH:mm:ss");
				return d;
			} catch (Exception e) {

			}

			try {
				d=Format.str2date(value, "yyyy-MM-dd");
				return d;
			} catch (Exception e) {

			}

			try {
				d=Format.str2date(value, "yyyyMMdd");
				return d;
			} catch (Exception e) {

			}

			try {
				d=Format.str2date(value, "yyyy/MM/dd HH:mm:ss");
				return d;
			} catch (Exception e) {

			}

			try {
				d=Format.str2date(value, "yyyy/MM/dd");
				return d;
			} catch (Exception e) {

			}

			log.error("日期格式错误：" + value);
			// 无法解析日期，返回null
			return d;
		}
		// 解析数字
		if (null != value)
			value = value.replaceAll(",", "").trim();
		if ("java.lang.Boolean".equals(datatype)) {
			if (null == value)
				return false;
			return Boolean.parseBoolean(value);
		}
		if ("int".equals(datatype) || "java.lang.Integer".equals(datatype)) {
			Integer i = null;
			if (null == value)
				return i;
			try {
				i = new Integer(value);
			} catch (Exception e) {
				i = Double.valueOf(value).intValue();
			}

			return i;
		}
		if ("double".equals(datatype) || "java.lang.Double".equals(datatype)) {
			Double d = null;
			if (null == value)
				return d;
			return Double.parseDouble(value);
		}
		if ("float".equals(datatype) || "java.lang.Float".equals(datatype)) {
			Float f = null;
			if (null == value)
				return f;
			return Float.parseFloat(value);
		}
		if ("long".equals(datatype) || "java.lang.Long".equals(datatype)) {
			Long l = null;
			if (null == value)
				return l;
			return Long.parseLong(value);
		}
		if ("short".equals(datatype) || "java.lang.Short".equals(datatype)) {
			Short s = null;
			if (null == value)
				return s;
			return Short.parseShort(value);
		}

		return null;
	}

	/**
	 * 解析FormBean，返回各字段的get和set方法指针
	 * 
	 * @param bean
	 *            getFields返回的是某个类里的所有public类型的变量，包括继承父类的
	 *            getDeclaredFields返回的是某个类里的所有类型的变量，不包括继承父类的
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, MethodClass> getMethods(Object bean) {
		Map<String, MethodClass> fields = new HashMap<String, MethodClass>();
		List<Field> fieldslist = new ArrayList<Field>();

		Field[] fieldlist = bean.getClass().getFields();
		// 先获取 public 和 父类的属性
		for (Field field : fieldlist) {
			fieldslist.add(field);
		}
		// 再拿本类的所有属性
		fieldlist = bean.getClass().getDeclaredFields();
		for (Field field : fieldlist) {
			fieldslist.add(field);
		}
		// 如果没有获取到，试图取父类的属性
		Class c = bean.getClass().getSuperclass();
		fieldlist = c.getDeclaredFields();
		for (Field field : fieldlist) {
			fieldslist.add(field);
		}
		for (Field field : fieldslist) {
			String fieldName = field.getName();
			if (StringUtils.isNull(fieldName))
				continue;
			// 首字母大写
			fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String getMethodName = "get" + fieldName;
			String setMethodName = "set" + fieldName;
			MethodClass mc = new MethodClass();
			try {
				Method getMethod = bean.getClass().getMethod(getMethodName);
				Method setMethod = bean.getClass().getMethod(setMethodName, field.getType());

				mc.getMethod = getMethod;
				mc.setMethod = setMethod;
				mc.fieldName = field.getName();
				mc.dataType = field.getType().getName();
				// 如果数据类型是List，则取得List的构造类型
				if ("java.util.List".equals(mc.dataType) || "java.util.ArrayList".equals(mc.dataType)) {
					String fieldGenericType = field.getGenericType().toString();
					if (fieldGenericType.indexOf(">") > 0) {
						fieldGenericType = fieldGenericType.substring(fieldGenericType.indexOf("<") + 1,
								fieldGenericType.indexOf(">"));
						mc.listClass = fieldGenericType;
					} else {
						log.error("类[" + bean.getClass().getName() + "]属性[" + field.getName() + "]没有设置范类型，请配置");
					}
				}

				fields.put(field.getName(), mc);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("类[" + bean.getClass().getName() + "]属性[" + field.getName() + "]无get和set方法，请配置", e);
				continue;
			}

		}
		return fields;
	}

	public static String convert2xmlstring(String value) {
		if (StringUtils.isNull(value))
			return value;

		value = value.replaceAll("&", "&amp;");
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		value = value.replaceAll("'", "&apos;");
		value = value.replaceAll("\"", "&quot;");
		return value;
	}

	public static void main(String[] args) throws Exception {
		ResResultVo vo=new ResResultVo();
		vo.setDocuId("1");
		String a=parseBean2Xml(vo);
		System.out.println("a=="+a);
	}
}
