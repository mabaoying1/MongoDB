package com.healthpay.iface.vo;

/**
 * 
 * Description: 返回结果通用vo Filename : ResResultVo.java
 * 
 * @author : yyl
 * @version : 1.0 Create at : 2016年6月1日 下午6:58:56
 * 
 *
 */
public class ResResultVo {

	private String nationality ; //国籍
	private String docuType;		// 证件类型
	private String docuId;		//证件号码
	private String healthCardId;
	private String icCardId;  //实体卡卡号
	private Long type;  //实体卡类型
	private String status;
	private String reason;
	private String qrCode;//电子健康卡二维码密文
	private String mindexId;//居民健康卡主索引ID

	public ResResultVo() {

	}

	public ResResultVo( String healthCardId, String status, String reason) {
		this.healthCardId = healthCardId;
		this.status = status;
		this.reason = reason;
	}
	
	public ResResultVo( String nationality,String docuType ,String docuId ,  String status, String reason) {
		this.nationality = nationality;
		this.docuType = docuType ;
		this.docuId = docuId ;
		this.status = status;
		this.reason = reason;
	}
	
	public ResResultVo( String nationality,String docuType ,String docuId ,String healthCardId ,String icCardId,  Long type,String status, String reason) {
		this.nationality = nationality;
		this.docuType = docuType ;
		this.docuId = docuId ;
		this.healthCardId = healthCardId ;
		this.icCardId = icCardId;
		this.type = type;
		this.status = status;
		this.reason = reason;
	}

	

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDocuType() {
		return docuType;
	}

	public void setDocuType(String docuType) {
		this.docuType = docuType;
	}

	public String getDocuId() {
		return docuId;
	}

	public void setDocuId(String docuId) {
		this.docuId = docuId;
	}

	public String getHealthCardId() {
		return healthCardId;
	}

	public void setHealthCardId(String healthCardId) {
		this.healthCardId = healthCardId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIcCardId() {
		return icCardId;
	}

	public void setIcCardId(String icCardId) {
		this.icCardId = icCardId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getMindexId() {
		return mindexId;
	}

	public void setMindexId(String mindexId) {
		this.mindexId = mindexId;
	}
}
