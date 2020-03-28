package com.healthpay.common.entity;

import java.io.Serializable;

public class ProResponse implements Serializable {
	private String code;//	返回结果码	String(10)	N	见字典	10
	private String message	;//返回结果说明	String(200)	N		正确
	private String  method	;//接口名称	String(50)	N	与请求报文一致	
	private String  app_id	;//应用编号	String(20)	N	与请求报文一致	
	private String  version	;//接口版本号	String(10)	N	与请求报文一致	
	private String  timestamp	;//响应报文时间戳	String(20)	N		
	private String  digest_type;//	摘要类型	String(10)	N		SM3
	private String  digest	;//摘要值	String(256)	N		
	private String  enc_type;//	加密类型	String(10)	Y		SM4
	private String 	biz_content	;//响应参数集合	String(-)	Y	响应参数的集合，最大长度不限，除公共响应参数外所有请求参数都必须放在这个参数中传递，具体参照各接口<<接口响应参数>>
	
	public String toLocalJson(){
		return "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDigest_type() {
		return digest_type;
	}

	public void setDigest_type(String digest_type) {
		this.digest_type = digest_type;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getEnc_type() {
		return enc_type;
	}

	public void setEnc_type(String enc_type) {
		this.enc_type = enc_type;
	}

	public String getBiz_content() {
		return biz_content;
	}

	public void setBiz_content(String biz_content) {
		this.biz_content = biz_content;
	}

	@Override
	public String toString() {
		return "ProResponse{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", method='" + method + '\'' +
				", app_id='" + app_id + '\'' +
				", version='" + version + '\'' +
				", timestamp='" + timestamp + '\'' +
				", digest_type='" + digest_type + '\'' +
				", digest='" + digest + '\'' +
				", enc_type='" + enc_type + '\'' +
				", biz_content='" + biz_content + '\'' +
				'}';
	}
}
