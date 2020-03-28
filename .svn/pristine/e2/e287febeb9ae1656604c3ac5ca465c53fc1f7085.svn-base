package com.healthpay.common.entity;

import java.io.Serializable;

/**
 * 接口公共部分请求参数封装
 */
public class ProRequest implements Serializable {
	private String method;//接口名称
	private String app_id;//应用编号
	private String term_id="4307000005";//终端编号
	private String version="V1.1";//接口版本号
	private String timestamp;//请求时间戳
	private String digest_type="SM3";//摘要类型
	private String digest;//摘要值
	private String enc_type="SM4";//加密类型
	private String biz_content;// 请求参数集合

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

	public String getTerm_id() {
		return term_id;
	}

	public void setTerm_id(String term_id) {
		this.term_id = term_id;
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
		return "ProRequest{" +
				"method='" + method + '\'' +
				", app_id='" + app_id + '\'' +
				", term_id='" + term_id + '\'' +
				", version='" + version + '\'' +
				", timestamp='" + timestamp + '\'' +
				", digest_type='" + digest_type + '\'' +
				", digest='" + digest + '\'' +
				", enc_type='" + enc_type + '\'' +
				", biz_content='" + biz_content + '\'' +
				'}';
	}
}
