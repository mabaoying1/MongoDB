package com.healthpay.common.utils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.xml.rpc.ServiceException;

public class HttpPostUtils {
	private static Logger log = Logger.getLogger(HttpPostUtils.class);
	Map<String, String> params;
	String url;

	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);

		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	public static String post(String url, String content) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String body = null;

		log.info("create httppost:" + url);
		HttpPost httpPost = new HttpPost(url);
		ContentType contentType = ContentType.create("application/x-www-form-urlencode", Consts.UTF_8);
		StringEntity entity = new StringEntity(content, contentType);
		httpPost.setEntity(entity);

		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity responseEntity = response.getEntity();

		if (null != responseEntity)
			body = EntityUtils.toString(responseEntity, Consts.UTF_8);

		EntityUtils.consume(responseEntity);

		return body;
	}

	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {

		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);

		return body;
	}

	private static String paseResponse(HttpResponse response) {
		log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

		log.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
		log.info(charset);

		String body = null;
		try {
			body = EntityUtils.toString(entity);
			log.info(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		log.info("execute post...");
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}

	public String post() {

		// Map<String, String> params = new HashMap<String, String>();
		// params.put("name", "thinkgem");
		// params.put("password", "admin");

		String xml = HttpPostUtils.post(url, params);

		return xml;
	}

	// 电子健康卡注册
	@Test
	public void doA1001() {
		Map<String, String> params = new HashMap<>();
		long time = System.currentTimeMillis();
		String funcid = "A1001";
		String appId = "9D19FF05-4D51-4596-BEC5-CAD5818A0385";
		String appSecret = "6046887813FCFA19";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Timer>" + time + "</Timer><Funcid>"
				+ funcid + "</Funcid><Merid>" + appId + "</Merid></Head><Body>"
				+ "<Nationality>CHN</Nationality><DocuType>01</DocuType><DocuId>513030199412148012</DocuId><Name>肖工</Name><Birth>19941214</Birth>"
				+ "<Profession>1</Profession><Sex>1</Sex><Nation>01</Nation><MaritalStatus>10</MaritalStatus><Address>四川达州</Address><Address2>成都高新区</Address2>"
				+ "<Phone>18398605498</Phone><Tel>18398605498</Tel><AppliDate>2019-08-29 15:00:30</AppliDate><ApplyType>01</ApplyType><Rzfs>05</Rzfs>"
				+ "</Body></Data>";
		String sign = MD5Utils.toMD5(appSecret + funcid + time + xml);
		params.put("xml", xml);
		params.put("sign", sign);
		System.out.println("?xml=" + xml + "&sign=" + sign);
		String resp = HttpPostUtils.post("http://127.0.0.1:8280/card-interface/hp/http/doHealth", params);
		System.out.println("xml====" + resp);
	}

	// 获取电子健康卡二维码
	@Test
	public void doA1026() {
		Map<String, String> params = new HashMap<>();
		long time = System.currentTimeMillis();
		String funcid = "A1026";
		String appId = "9D19FF05-4D51-4596-BEC5-CAD5818A0385";
		String appSecret = "6046887813FCFA19";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Timer>" + time + "</Timer><Funcid>"
				+ funcid + "</Funcid><Merid>" + appId
				+ "</Merid></Head><Body><HealthCardId>FAAD3E79D27904BCDF4A978C3874C8838380CF3384BFE9782B104D5072AEFA5F</HealthCardId><Ewmbs>1</Ewmbs><Token></Token></Body></Data>";
		String sign = MD5Utils.toMD5(appSecret + funcid + time + xml);
		params.put("xml", xml);
		params.put("sign", sign);
		System.out.println("?xml=" + xml + "&sign=" + sign);
		String resp = HttpPostUtils.post("http://127.0.0.1:8280/card-interface/hp/http/doHealth", params);
		System.out.println("请求结果xml====" + resp);
	}

	// 验证电子健康卡二维码
	@Test
	public void doA1027() {
		Map<String, String> params = new HashMap<>();
		long time = System.currentTimeMillis();
		String funcid = "A1027";
		String appId = "9D19FF05-4D51-4596-BEC5-CAD5818A0385";
		String appSecret = "6046887813FCFA19";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Timer>" + time + "</Timer><Funcid>"
				+ funcid + "</Funcid><Merid>" + appId
				+ "</Merid></Head><Body><QrCode>FAAD3E79D27904BCDF4A978C3874C8838380CF3384BFE9782B104D5072AEFA5F:1</QrCode><MedstepCode>010101</MedstepCode><TerminalCode>1001-1</TerminalCode><ChannelCode>01</ChannelCode></Body></Data>";
		String sign = MD5Utils.toMD5(appSecret + funcid + time + xml);
		params.put("xml", xml);
		params.put("sign", sign);
		System.out.println("?xml=" + xml + "&sign=" + sign);
		String resp = HttpPostUtils.post("http://127.0.0.1:8280/card-interface/hp/http/doHealth", params);
		System.out.println("请求结果xml====" + resp);
	}

	// 3.4 查询电子健康卡信息 电子健康卡号
	@Test
	public void doA10281() {
		Map<String, String> params = new HashMap<>();
		long time = System.currentTimeMillis();
		String funcid = "A1028";
		String appId = "9D19FF05-4D51-4596-BEC5-CAD5818A0385";
		String appSecret = "6046887813FCFA19";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Timer>" + time + "</Timer><Funcid>"
				+ funcid + "</Funcid><Merid>" + appId
				+ "</Merid></Head><Body><HealthCardId>FAAD3E79D27904BCDF4A978C3874C8838380CF3384BFE9782B104D5072AEFA5F</HealthCardId><Rzfs>01</Rzfs></Body></Data>";
		String sign = MD5Utils.toMD5(appSecret + funcid + time + xml);
		params.put("xml", xml);
		params.put("sign", sign);
		System.out.println("?xml=" + xml + "&sign=" + sign);
		String resp = HttpPostUtils.post("http://127.0.0.1:8280/card-interface/hp/http/doHealth", params);
		System.out.println("请求结果xml====" + resp);
	}
	//3.4 查询电子健康卡信息  通过身份证类型+身份证号
	@Test
	public void doA10282() {
		Map<String, String> params = new HashMap<>();
		long time = System.currentTimeMillis();
		String funcid = "A1028";
		String appId = "9D19FF05-4D51-4596-BEC5-CAD5818A0385";
		String appSecret = "6046887813FCFA19";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Timer>" + time + "</Timer><Funcid>"
				+ funcid + "</Funcid><Merid>" + appId
				+ "</Merid></Head><Body><DocuType>01</DocuType><DocuId>511322199408242123</DocuId><Rzfs>01</Rzfs></Body></Data>";
		String sign = MD5Utils.toMD5(appSecret + funcid + time + xml);
		params.put("xml", xml);
		params.put("sign", sign);
		System.out.println("?xml=" + xml + "&sign=" + sign);
		String resp = HttpPostUtils.post("http://127.0.0.1:8280/card-interface/hp/http/doHealth", params);
		System.out.println("请求结果xml====" + resp);
	}

	// 3.4 上传电子健康卡信息
	@Test
	public void doA1030() {
		Map<String, String> params = new HashMap<>();
		long time = System.currentTimeMillis();
		String funcid = "A1030";
		String appId = "9D19FF05-4D51-4596-BEC5-CAD5818A0385";
		String appSecret = "6046887813FCFA19";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Timer>" + time + "</Timer><Funcid>"
				+ funcid + "</Funcid><Merid>" + appId
				+ "</Merid></Head><Body><HealthCardId>01511321199208162552</HealthCardId><MedstepCode>000000</MedstepCode><TerminalCode>123456</TerminalCode><ChannelCode>01</ChannelCode><UseTime>2019-08-29 15:30:22</UseTime></Body></Data>";
		String sign = MD5Utils.toMD5(appSecret + funcid + time + xml);
		params.put("xml", xml);
		params.put("sign", sign);
		System.out.println("?xml=" + xml + "&sign=" + sign);
		String resp = HttpPostUtils.post("http://127.0.0.1:8280/card-interface/hp/http/doHealth", params);
		System.out.println("请求结果xml====" + resp);
	}
}
