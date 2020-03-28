package com.healthpay.common.service.proCard.impl;

import com.alibaba.fastjson.JSONObject;
import com.healthpay.common.entity.ProRequest;
import com.healthpay.common.entity.ProResponse;
import com.healthpay.common.service.proCard.IPCardService;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.HttpPostUtils;
import com.healthpay.common.utils.RandomUtils;
import com.healthpay.common.utils.SSLClient;
import com.yinhai.common.util.sm3.EncodeUtil;
import com.yinhai.common.util.sm4.SM4Utils;

import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public  class ProCardServiceImpl implements IPCardService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	//四川省https接口地址
	private String URL0="https://202.61.88.181:8091/erhc/ehealthcard/business.do";
	//成都市https接口地址
	private String URL1="https://221.237.182.251:18443/erhc/ehealthcard/business.do";
	//成都市http接口地址
	private String URL2="http://221.237.182.251:8090/erhc/ehealthcard/business.do";
	@Value("${proCardUrl}")
	private String proCardUrl;
 
	//接口请求封装
	public ProResponse process(ProRequest request,String appSecret) throws Exception {
		SM4Utils sm4=new SM4Utils();
		sm4.setSecretKey(appSecret);//设置加载因子

		request.setTimestamp(String.valueOf(System.currentTimeMillis()));//时间戳
		String digest=createDigest(request,appSecret);//生成摘要
		request.setDigest(digest);
		logger.info("生成的摘要值为："+digest);
		//请求参数集合sm4加密
		String encryptData=sm4.encryptData_ECB(request.getBiz_content());
		logger.info("参数集合加密内容为："+encryptData);
		request.setBiz_content(encryptData);
		//发送https请求
		System.out.println(JSONObject.toJSONString(request));
		String respStr= SSLClient.httpsSendPosthttpclient(proCardUrl, JSONObject.toJSONString(request));
		//发送http请求
		//String respStr= HttpPostUtils.post(URL2, JSONObject.toJSONString(request));
		//将json字符串转换为ProResponse对象
		ProResponse proResponse= JSONObject.parseObject(respStr,ProResponse.class);
		//解密成json字符串
		String jsonStr=sm4.decryptData_ECB(proResponse.getBiz_content());
		proResponse.setBiz_content(jsonStr);
		logger.info("解密后的内容==="+proResponse.getBiz_content());
		return proResponse;
	}


	/**
	 *  生成摘要
	 * @param request
	 * @param appSecret
	 * @return
	 */
	private String createDigest(ProRequest request,String appSecret) throws Exception {
		// 参数按照首字母升序排序
		StringBuilder sortedParam = new StringBuilder();
		sortedParam.append("app_id=" + request.getApp_id() + "&");
		sortedParam.append("biz_content=" + request.getBiz_content() + "&");
		sortedParam.append("digest_type=" + request.getDigest_type() + "&");
		sortedParam.append("enc_type=" + request.getEnc_type() + "&");
		sortedParam.append("method=" + request.getMethod() + "&");
		sortedParam.append("term_id=" + request.getTerm_id() + "&");
		sortedParam.append("timestamp=" + request.getTimestamp() + "&");
		sortedParam.append("version=" + request.getVersion() + "&");
		sortedParam.append("app_secret="+ appSecret);
		//生成摘要值
		String digest =new EncodeUtil().SM3(sortedParam.toString());
		return digest;
	}



	/**************************************************************/
	/********************接口测试***********************************/
	/**************************************************************/
	//省卡管注册电子健康卡
	@Test
	public void register1() throws Exception {
		Map<String, Object> content = new HashMap<>();
		content.put("register_no", RandomUtils.getAlterNo());//外部注册流水号
		content.put("register_time", "20190828114159");//外部注册时间
		content.put("apply_type", "01");//申请方式
		content.put("id_type", "01");//证件类型
		content.put("id_no", "513030199412148012");//证件号
		content.put("name","肖垚");//姓名
		content.put("gender","1");//性别
		content.put("nation","01");//民族
		content.put("nationality", "CHN");//国籍
		content.put("domicile", "四川成都");//户籍地址
		content.put("birthday","1994-12-14");//出生日期
		content.put("cellphone","18398605498");//手机号码
		content.put("telephone","18398605498");//联系电话
		content.put("address","成都");//居住地址
		content.put("workcode","1");//职业代码
		content.put("maritalstatus","10");//婚姻状态
		content.put("rzfs","05");//认证模式
		content.put("file1","");//认证资料

		ProRequest proRequest = new ProRequest();
		proRequest.setApp_id("9D19FF05-4D51-4596-BEC5-CAD5818A0385");
		proRequest.setMethod("ehc.ehealthcard.register");//接口名称
		proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
		ProCardServiceImpl pro=new ProCardServiceImpl();
		ProResponse response=pro.process(proRequest,"6046887813FCFA19");
		System.out.println("解密内容为===="+response);
		System.out.println("内容为===="+response.getBiz_content());
	}
	
	//成都市卡管注册电子健康卡
		@Test
		public void register2() throws Exception {
			Map<String, Object> content = new HashMap<>();
			content.put("register_no", RandomUtils.getAlterNo());//外部注册流水号
			content.put("register_time", "20200108144859");//外部注册时间
			content.put("apply_type", "01");//申请方式
			content.put("id_type", "01");//证件类型
			content.put("id_no", "510121199001101021");//证件号
			content.put("name","刘海燕");//姓名
			content.put("gender","2");//性别
			content.put("nation","01");//民族
			content.put("nationality", "CHN");//国籍
			content.put("domicile", "四川成都");//户籍地址
			content.put("birthday","1990-01-10");//出生日期
			content.put("cellphone","18398605498");//手机号码
			content.put("telephone","18398605498");//联系电话
			content.put("address","成都");//居住地址
			content.put("workcode","1");//职业代码
			content.put("maritalstatus","10");//婚姻状态
			content.put("rzfs","05");//认证模式
			content.put("file1","");//认证资料

			ProRequest proRequest = new ProRequest();
			proRequest.setApp_id("03C28F65-4154-4BF9-86C4-314F5902B69A");
			proRequest.setMethod("ehc.ehealthcard.register");//接口名称
			proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
			ProCardServiceImpl pro=new ProCardServiceImpl();
			ProResponse response=pro.process(proRequest,"F52429A934723DB0");
			System.out.println("解密内容为===="+response);
			System.out.println("内容为===="+response.getBiz_content());
		}

	//省卡管获取电子健康卡二维码
	@Test
	public void generate1() throws Exception {
		Map<String, Object> content = new HashMap<>();
		content.put("ehealth_card_id", "FAAD3E79D27904BCDF4A978C3874C8838380CF3384BFE9782B104D5072AEFA5F");//电子健康卡ID
		content.put("ewmbs", "1");//二维码标识
		//content.put("token", "");//金融支付数据
		content.put("generate_no", RandomUtils.getAlterNo());//外部生成流水
		content.put("generate_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));//外部生成时间
		ProRequest proRequest = new ProRequest();
		proRequest.setApp_id("9D19FF05-4D51-4596-BEC5-CAD5818A0385");
		proRequest.setMethod("ehc.ehealthcode.generate");//接口名称
		proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
		ProCardServiceImpl pro=new ProCardServiceImpl();
		ProResponse response=pro.process(proRequest,"6046887813FCFA19");
		System.out.println("解密内容为===="+response);
		System.out.println("内容为===="+response.getBiz_content());
	}
	
	//成都市卡管获取电子健康卡二维码
		@Test
		public void generate2() throws Exception {
			Map<String, Object> content = new HashMap<>();
			content.put("ehealth_card_id", "6836D27C966E1C1BB192F050778358E4AA0208EFBA111FC5CB90AD689124A19B");//电子健康卡ID
			content.put("ewmbs", "1");//二维码标识
			//content.put("token", "");//金融支付数据
			content.put("generate_no", RandomUtils.getAlterNo());//外部生成流水
			content.put("generate_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));//外部生成时间
			ProRequest proRequest = new ProRequest();
			proRequest.setApp_id("03C28F65-4154-4BF9-86C4-314F5902B69A");
			proRequest.setMethod("ehc.ehealthcode.generate");//接口名称
			proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
			ProCardServiceImpl pro=new ProCardServiceImpl();
			ProResponse response=pro.process(proRequest,"F52429A934723DB0");
			System.out.println("解密内容为===="+response);
			System.out.println("内容为===="+response.getBiz_content());
		}
	
	//省卡管获取验证电子健康卡二维码
	@Test
	public void verify() throws Exception {
		Map<String, Object> content = new HashMap<>();
		content.put("ehealth_code", "6836D27C966E1C1BB192F050778358E4AA0208EFBA111FC5CB90AD689124A19B:1::5100A0013");//二维码数据内容
		content.put("medstepcode", "010101");//诊疗环节代码	
		content.put("terminalcode", "ceshi1001");//扫码枪终端标识号
		content.put("channelcode", "01");//刷卡终端类型编号
		ProRequest proRequest = new ProRequest();
		proRequest.setApp_id("9D19FF05-4D51-4596-BEC5-CAD5818A0385");
		proRequest.setMethod("ehc.ehealthcode.verify");//接口名称
		proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
		ProCardServiceImpl pro=new ProCardServiceImpl();
		ProResponse response=pro.process(proRequest,"6046887813FCFA19");
		System.out.println("解密内容为===="+response);
		System.out.println("内容为===="+response.getBiz_content());
	}

	//注销电子健康卡
	@Test
	public void close() throws Exception {
		Map<String, Object> content = new HashMap<>();
		content.put("ehealth_card_id", "01513030199412148012");//电子健康卡ID
		content.put("revoke_no", RandomUtils.getAlterNo());//外部生成流水
		content.put("revoke_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));//外部生成时间
		ProRequest proRequest = new ProRequest();
		proRequest.setApp_id("9D19FF05-4D51-4596-BEC5-CAD5818A0385");
		proRequest.setMethod("ehc.ehealthcard.close");//接口名称
		proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
		ProCardServiceImpl pro=new ProCardServiceImpl();
		ProResponse response=pro.process(proRequest,"6046887813FCFA19");
		System.out.println("解密内容为===="+response);
		System.out.println("内容为===="+response.getBiz_content());
	}

	//查询电子健康卡信息
	@Test
	public void query() throws Exception {
		Map<String, Object> content = new HashMap<>();
		content.put("ehealth_card_id", "FAAD3E79D27904BCDF4A978C3874C8838380CF3384BFE9782B104D5072AEFA5F");//电子健康卡ID
		content.put("id_type", null);//证件类型
		content.put("id_no", null);//证件号
		content.put("rzfs", "05");//认证模式
		content.put("file1", null);//认证资料
		ProRequest proRequest = new ProRequest();
		proRequest.setApp_id("9D19FF05-4D51-4596-BEC5-CAD5818A0385");
		proRequest.setMethod("ehc.ehealthcode.query");//接口名称
		proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
		ProCardServiceImpl pro=new ProCardServiceImpl();
		ProResponse response=pro.process(proRequest,"6046887813FCFA19");
		System.out.println("解密内容为===="+response);
		System.out.println("内容为===="+response.getBiz_content());
	}
	
	
	//四川省卡管机构注册
		@Test
		public void orgResiter1() throws Exception {
			Map<String, Object> content = new HashMap<>();
			content.put("orgname", "四川省骨科医院");//机构名称
			content.put("orgcode", "45075129-0");//机构编码
			content.put("orgproperty", "23");//机构性质
			content.put("accessmethod", "06");//接入方式
			content.put("validtime", "2050-01-01");//有效时间
			content.put("orgaddr", "5101");//机构行政区划
			content.put("orglevel", "05");//机构等级
			content.put("orghierarchy", "0");//机构等次
			ProRequest proRequest = new ProRequest();
			proRequest.setApp_id("9D19FF05-4D51-4596-BEC5-CAD5818A0385");
			proRequest.setMethod("ehc.ehealthcode.addorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
			proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
			ProCardServiceImpl pro=new ProCardServiceImpl();
			ProResponse response=pro.process(proRequest,"6046887813FCFA19");
			System.out.println("解密内容为===="+response);
			System.out.println("内容为===="+response.getBiz_content());
		}
		
		//成都市卡管机构注册
				@Test
				public void orgResiter2() throws Exception {
					Map<String, Object> content = new HashMap<>();
					content.put("orgname", "医院测试2");//机构名称
					content.put("orgcode", "45075129-3");//机构编码
					content.put("orgproperty", "23");//机构性质
					content.put("accessmethod", "06");//接入方式
					content.put("validtime", "2033-01-01");//有效时间
					content.put("orgaddr", "成都市武侯区");//机构行政区划
					content.put("orglevel", "05");//机构等级
					content.put("orghierarchy", "0");//机构等次
					ProRequest proRequest = new ProRequest();
					proRequest.setApp_id("03C28F65-4154-4BF9-86C4-314F5902B69A");
					proRequest.setMethod("ehc.ehealthcode.addorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
					proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
					ProCardServiceImpl pro=new ProCardServiceImpl();
					ProResponse response=pro.process(proRequest,"F52429A934723DB0");
					System.out.println("解密内容为===="+response);
					System.out.println("内容为===="+response.getBiz_content());
				}
				
				//成都市卡管机构更新
				@Test
				public void updateorg() throws Exception {
					Map<String, Object> content = new HashMap<>();
					content.put("orgname", "医院测试1");//机构名称
					content.put("orgcode", "45075129-1");//机构编码
					content.put("orgproperty", "23");//机构性质
					content.put("accessmethod", "06");//接入方式
					content.put("validtime", "2030-01-01");//有效时间
					content.put("orgaddr", "5102");//机构行政区划
					content.put("orglevel", "05");//机构等级
					content.put("orghierarchy", "0");//机构等次
					ProRequest proRequest = new ProRequest();
					proRequest.setApp_id("03C28F65-4154-4BF9-86C4-314F5902B69A");
					proRequest.setMethod("ehc.ehealthcode.updateorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
					proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
					ProCardServiceImpl pro=new ProCardServiceImpl();
					ProResponse response=pro.process(proRequest,"F52429A934723DB0");
					System.out.println("解密内容为===="+response);
					System.out.println("内容为===="+response.getBiz_content());
				}
	
		//成都市卡管机构查询
		@Test
		public void queryOrg2() throws Exception{
			
			Map<String, Object> content = new HashMap<>();
			content.put("orgname", "医院测试2");//机构名称
			content.put("orgcode", "45075129-3");//机构编码
			ProRequest proRequest = new ProRequest();
			proRequest.setApp_id("A1ADF361-BE0E-4CBB-A5B3-81F4065233B1");
			proRequest.setMethod("ehc.ehealthcode.queryorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
			proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
			ProCardServiceImpl pro=new ProCardServiceImpl();
			ProResponse response=pro.process(proRequest,"AA57EF5F0E9A4168");
			System.out.println("解密内容为===="+response);
			System.out.println("内容为===="+response.getBiz_content());
		}
		
		//成都市卡管机构注销
				@Test
				public void queryUpdateorg() throws Exception{
					
					Map<String, Object> content = new HashMap<>();
					content.put("orgname", "四川省骨科医院");//机构名称
					content.put("orgcode", "45075129-0");//机构编码
					content.put("valid", "0");
					ProRequest proRequest = new ProRequest();
					proRequest.setApp_id("03C28F65-4154-4BF9-86C4-314F5902B69A");
					proRequest.setMethod("ehc.ehealthcode.updateorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
					proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
					ProCardServiceImpl pro=new ProCardServiceImpl();
					ProResponse response=pro.process(proRequest,"F52429A934723DB0");
					System.out.println("解密内容为===="+response);
					System.out.println("内容为===="+response.getBiz_content());
				}
	
	public static void main(String[] args) {
		ProCardServiceImpl ps=new ProCardServiceImpl();
		try {
		/*	System.out.println("-----四川省卡管注册电子健康卡----");
			ps.register1();
			System.out.println("-----四川省生成电子健康卡二维码----");
			ps.generate1();
			*/
			
		/*	System.out.println("-----成都市注册电子健康卡----");
			ps.register2();
			System.out.println("-----生成电子健康卡二维码----");
			ps.generate2();
			*/
			
		  /*String qrCode="FAAD3E79D27904BCDF4A978C3874C8838380CF3384BFE9782B104D5072AEFA5F:1::5100A0013";
			System.out.println(qrCode.substring(0,qrCode.indexOf(":")));
			String[] str= qrCode.split(":");
			String healthCardId = str[0];
			System.out.println("healthCardId==="+healthCardId);*/
			
			//System.out.println("------成都市卡管机构注册-------");
			//ps.orgResiter2();
			System.out.println("------成都市卡管机构查询-------");
			ps.queryOrg2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
