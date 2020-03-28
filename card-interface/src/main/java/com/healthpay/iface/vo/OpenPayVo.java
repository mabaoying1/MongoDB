package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.annotation.NotNull;

import java.util.Date;

public class OpenPayVo {
	
	private String healthCardId  ; //健康卡主键
	private Date reqDate ;
	private Double paylimit ;
	private String password ;
	private Long isPwdfree ;
	private Double smalllimit ;
	private String reserved ;
	private Long paysystem ;
	private String account ;
	private String accountname ;
	private String bankid ;
	private String bankname ;
	
	public OpenPayVo(){
		
	}
	
	@NotNull(name="健康e卡号")
	public String getHealthCardId() {
		return healthCardId;
	}
	public void setHealthCardId(String healthCardId) {
		this.healthCardId = healthCardId;
	}
	
	@NotNull(name="请求时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	
	@NotNull(name="支付密码")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public Long getPaysystem() {
		return paysystem;
	}
	public void setPaysystem(Long paysystem) {
		this.paysystem = paysystem;
	}
	@NotNull(name="健康e卡号")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@NotNull(name="户名")
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	@NotNull(name="银行ID")
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	@NotNull(name="银行名称")
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public Double getPaylimit() {
		return paylimit;
	}
	public void setPaylimit(Double paylimit) {
		this.paylimit = paylimit;
	}
	
	public Long getIsPwdfree() {
		return isPwdfree;
	}
	public void setIsPwdfree(Long isPwdfree) {
		this.isPwdfree = isPwdfree;
	}
	public Double getSmalllimit() {
		return smalllimit;
	}
	public void setSmalllimit(Double smalllimit) {
		this.smalllimit = smalllimit;
	}
	
	
	
}
