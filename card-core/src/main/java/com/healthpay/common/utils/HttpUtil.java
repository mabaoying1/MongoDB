package com.healthpay.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url) {
		InputStream in = null;
		String sb = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Connection", "Keep-Alive");
			// 建立实际的连接
			connection.connect();
			in = connection.getInputStream();
			int length = in.available();
			if (length > 0) {
				byte[] bs = new byte[length];
				in.read(bs);
				sb = new String(bs, "UTF-8");
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			logger.error("Exception: ", e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return sb;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该JSON的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String postJSON(String url, JSONObject obj) {
		String sb = "";
		try {
			// 创建连接
			URL urls = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			// 设置通用的请求属性
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			if (obj != null) {
				out.write(obj.toString().getBytes("UTF-8"));
			}
			out.flush();
			out.close();

			// 读取响应
			InputStream in = connection.getInputStream();
			int length = in.available();
			if (length > 0) {
				byte[] bs = new byte[length];
				in.read(bs);
				sb = new String(bs, "UTF-8");
			}
			System.out.println(sb);

			// 断开连接
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * http发送接口(post)
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String sendText(String url, String text) throws ParseException, IOException {
		String res = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		HttpEntity re = new StringEntity(text, "utf-8");
		httppost.setEntity(re);
		HttpResponse response = httpClient.execute(httppost);
		res = EntityUtils.toString(response.getEntity(), "utf-8");
		httppost.releaseConnection();
		httpClient.close();
		return res;
	}

	/**
	 * http发送接口(get)
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String sendText(String url) throws ParseException, IOException {
		String res = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri1 = URI.create(url);
		HttpGet httpGet = new HttpGet(uri1);
		HttpResponse response = httpClient.execute(httpGet);
		res = EntityUtils.toString(response.getEntity(), "utf-8");
		httpGet.releaseConnection();
		httpClient.close();
		System.out.println(res);
		return res;
	}

	public static String post(String path, String params) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		// PrintWriter out=null;
		try {
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			System.out.print("params=" + params);
			// utf-8
			PrintWriter ot = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(), "utf-8"));
			ot.println(params);
			ot.flush();
			// out=new PrintWriter(httpConn.getOutputStream());
			// out.println(params);

			// out.flush();

			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				// utf-8
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {

				throw new Exception("请求出现了问题!");

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			// out.close();
			httpConn.disconnect();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {

		String resp = sendPost2("http://localhost:8888/SSO/SSOService", getRequestXml());
		System.out.println(resp);
		System.out.println("---------------------------------");
	}

	public static String getRequestXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ssos=\"SSOServer\">");
		sb.append("<soap:Header/>");
		sb.append("<soap:Body>");
		sb.append("<ssos:SSOServer>");
		sb.append(
				"<action>SubmitInfo</action><message><![CDATA[<request><person><personName>鲁好宇</personName><idCard>542300199307120694</idCard><sexCode>2</sexCode></person><sourceId>moduleId3</sourceId><sourceName>基层医疗</sourceName><localInfos><localInfo><localId>system</localId><localName>管理员</localName><localRoles><localRole><localRoleId>122</localRoleId><localRoleName>药房管理员1</localRoleName></localRole><localRole><localRoleId>1212</localRoleId><localRoleName>药房管理员2</localRoleName></localRole></localRoles></localInfo><localInfo><localId>admin</localId><localName>管理员</localName><localRoles><localRole><localRoleId>121</localRoleId><localRoleName>药房管理员</localRoleName></localRole><localRole><localRoleId>1212</localRoleId><localRoleName>药房管理员2</localRoleName></localRole></localRoles></localInfo></localInfos></request>]]></message>");
		sb.append("</ssos:SSOServer>");
		sb.append(" </soap:Body>");
		sb.append("</soap:Envelope>");
		return sb.toString();
	}
	
	public static String sendPost2(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); 
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}