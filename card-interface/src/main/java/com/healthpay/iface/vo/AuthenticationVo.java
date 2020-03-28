package com.healthpay.iface.vo;

import java.util.Date;

public class AuthenticationVo {
	
	private String identityId ;
	private String healthCardId  ; //健康卡主键
	private Date reqDate ;
	
	public AuthenticationVo(){}
	
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public String getHealthCardId() {
		return healthCardId;
	}
	public void setHealthCardId(String healthCardId) {
		this.healthCardId = healthCardId;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	
	
}
