/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.security;

/**
 * 用户和密码（包含验证码）令牌类
 * @author jeeplus
 * @version 2013-5-19
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;
	private boolean mobileLogin;
	private String userType;
	private String idNumber;
	
	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password,
								  boolean rememberMe, String host, String captcha, boolean mobileLogin,String userType) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.mobileLogin = mobileLogin;
		this.userType = userType;
	}

	public UsernamePasswordToken(String username,String password,boolean rememberMe ,String host,String userType,String idNumber) {
		super(username, password, rememberMe, host);
		this.userType  = userType;
		this.idNumber = idNumber;
		this.userType = userType;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}