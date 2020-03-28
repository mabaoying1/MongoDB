package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.annotation.NotNull;

import java.util.Date;

/**
 * 
 * Description:   P001接口-开卡申请数据域
 * Filename   :   JsonApplycardVo.java  
 * @author    :   yyl 
 * @version   :   1.0  
 * Create at  :   2016年5月31日 下午3:27:12  
 *  
 *
 */
public class ApplycardUpdateVo {
	
	private String healthCardId  ; 
	private String healthId;		// 健康档案编号
	private String newFarmid;		// 新农合证（卡）号
	private String name;		// 姓名
	private Date birth;		// 出生日期
	private String profession;		// 职业（代码）
	private Integer addressType;		// 地址类别（代码）
	private Integer sex;		// 性别（代码）
	private String nation;		// 民族（代码）
	private String nationality ; //国籍
	private String maritalStatus;		// 婚姻状况（代码）
	private Integer educationLevel;		// 文化程度（代码）
	private String countrycode;		// 国家代码(户籍)
	private String countryname;		// 国家名称(户籍)
	private String provcode;		// 省代码(户籍)
	private String provname;		// 省名称(户籍)
	private String citycode;		// 市代码(户籍)
	private String cityname;		// 市名称(户籍)
	private String countycode;		// 县级代码(户籍)
	private String countyname;		// 县级名称(户籍)
	private String towncode;		// 镇级代码(户籍)
	private String townname;		// 镇级名称(户籍)
	private String villagecode;		// 村代码(户籍)
	private String villagename;		// 村名称(户籍)
	private String postcode;		// 邮编
	private String countrycode2;		// 国家代码(居住地，为空时，表示和户籍地址一致)
	private String countryname2;		// 国家名称(居住地)
	private String provcode2;		// 省代码(居住地)
	private String provname2;		// 省名称(居住地)
	private String citycode2;		// 市代码(居住地)
	private String cityname2;		// 市名称(居住地)
	private String countycode2;		// 县级代码(居住地)
	private String countyname2;		// 县级名称(居住地)
	private String towncode2;		// 镇级代码(居住地)
	private String townname2;		// 镇级名称(居住地)
	private String villagecode2;		// 村代码(居住地)
	private String villagename2;		// 村名称(居住地)
	private String address2;		// 地址(居住地)
	private Integer paytype;		// 医疗费用支付方式（代码）
	private String phone;		// 本人手机
	private String tel;		// 本人电话
	private String email;		// 本人Email
	private String attnName;		// 联系人姓名
	private String attnPhone;		// 联系人手机
	private String attnRela;		// 联系人关系
	private String attnName2;		// 联系人姓名2
	private String attnPhone2;		// 联系人手机2
	private String attnRela2;		// 联系人关系2
	private String attnName3;		// 联系人姓名3
	private String attnPhone3;		// 联系人手机3
	private String attnRela3;		// 联系人关系3
	private Long source;		// 数据来源：1- 移动端注册 2 - 电脑端 3 -  批量预开卡
	private Date appliDate;		// 审核时间
	private Integer securityCode;		// 安全码
	private String chipSerialid;		// 芯片序列号
	private Integer cardType;		// 卡的类别
	private String version;		// 规范版本
	private String office;		// 发卡机构代码
	private Date startDate;		// 发卡时间
	private Date validDate;		// 卡有效期
	private Long isOpenpay;		// 是否开通健康钱包  0 - 否  1 - 是
	private Double paylimit;		// 支付限额 0 or null  表示没有限额
	private String password;		// 支付密码  开通支付必须填密码
	private Long isPwdfree;		// 小额免密 0 - 否 1 - 是
	private Double smalllimit;		// 小额额度 
	private String mobile;		// 预留手机号
	private String reserved;		// 预留文字信息
	private String reason;		// 审核不通过原因
	private Long paysystem;		// 支付系统类型（1 银行 2 银联 3 APPLEPAY...建立数据字典）
	private String account;		// 账号
	private String accountname;		// 户名
	private String mobile2;		// 预留手机号
	private String bankid;		// 支付类型为银行时 ，记录银行
	private String bankname;		// bankname
	private Long accounttype;		// 账号类型 0 借记卡 1 信用卡
	private String backnum;		// 卡背后号码
	private Date validDate2;		// 卡有效期
	
	public ApplycardUpdateVo(){}
	
	
	@NotNull(name="健康e卡号")
	public String getHealthCardId() {
		return healthCardId;
	}


	public void setHealthCardId(String healthCardId) {
		this.healthCardId = healthCardId;
	}

	public String getHealthId() {
		return healthId;
	}
	public void setHealthId(String healthId) {
		this.healthId = healthId;
	}
	public String getNewFarmid() {
		return newFarmid;
	}
	public void setNewFarmid(String newFarmid) {
		this.newFarmid = newFarmid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Integer getAddressType() {
		return addressType;
	}
	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Integer getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(Integer educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getProvcode() {
		return provcode;
	}
	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCountycode() {
		return countycode;
	}
	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}
	public String getCountyname() {
		return countyname;
	}
	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	public String getTowncode() {
		return towncode;
	}
	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	public String getTownname() {
		return townname;
	}
	public void setTownname(String townname) {
		this.townname = townname;
	}
	public String getVillagecode() {
		return villagecode;
	}
	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}
	public String getVillagename() {
		return villagename;
	}
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountrycode2() {
		return countrycode2;
	}
	public void setCountrycode2(String countrycode2) {
		this.countrycode2 = countrycode2;
	}
	public String getCountryname2() {
		return countryname2;
	}
	public void setCountryname2(String countryname2) {
		this.countryname2 = countryname2;
	}
	public String getProvcode2() {
		return provcode2;
	}
	public void setProvcode2(String provcode2) {
		this.provcode2 = provcode2;
	}
	public String getProvname2() {
		return provname2;
	}
	public void setProvname2(String provname2) {
		this.provname2 = provname2;
	}
	public String getCitycode2() {
		return citycode2;
	}
	public void setCitycode2(String citycode2) {
		this.citycode2 = citycode2;
	}
	public String getCityname2() {
		return cityname2;
	}
	public void setCityname2(String cityname2) {
		this.cityname2 = cityname2;
	}
	public String getCountycode2() {
		return countycode2;
	}
	public void setCountycode2(String countycode2) {
		this.countycode2 = countycode2;
	}
	public String getCountyname2() {
		return countyname2;
	}
	public void setCountyname2(String countyname2) {
		this.countyname2 = countyname2;
	}
	public String getTowncode2() {
		return towncode2;
	}
	public void setTowncode2(String towncode2) {
		this.towncode2 = towncode2;
	}
	public String getTownname2() {
		return townname2;
	}
	public void setTownname2(String townname2) {
		this.townname2 = townname2;
	}
	public String getVillagecode2() {
		return villagecode2;
	}
	public void setVillagecode2(String villagecode2) {
		this.villagecode2 = villagecode2;
	}
	public String getVillagename2() {
		return villagename2;
	}
	public void setVillagename2(String villagename2) {
		this.villagename2 = villagename2;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Integer getPaytype() {
		return paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAttnName() {
		return attnName;
	}
	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}
	public String getAttnPhone() {
		return attnPhone;
	}
	public void setAttnPhone(String attnPhone) {
		this.attnPhone = attnPhone;
	}
	public String getAttnRela() {
		return attnRela;
	}
	public void setAttnRela(String attnRela) {
		this.attnRela = attnRela;
	}
	public String getAttnName2() {
		return attnName2;
	}
	public void setAttnName2(String attnName2) {
		this.attnName2 = attnName2;
	}
	public String getAttnPhone2() {
		return attnPhone2;
	}
	public void setAttnPhone2(String attnPhone2) {
		this.attnPhone2 = attnPhone2;
	}
	public String getAttnRela2() {
		return attnRela2;
	}
	public void setAttnRela2(String attnRela2) {
		this.attnRela2 = attnRela2;
	}
	public String getAttnName3() {
		return attnName3;
	}
	public void setAttnName3(String attnName3) {
		this.attnName3 = attnName3;
	}
	public String getAttnPhone3() {
		return attnPhone3;
	}
	public void setAttnPhone3(String attnPhone3) {
		this.attnPhone3 = attnPhone3;
	}
	public String getAttnRela3() {
		return attnRela3;
	}
	public void setAttnRela3(String attnRela3) {
		this.attnRela3 = attnRela3;
	}
	public Long getSource() {
		return source;
	}
	public void setSource(Long source) {
		this.source = source;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAppliDate() {
		return appliDate;
	}
	public void setAppliDate(Date appliDate) {
		this.appliDate = appliDate;
	}
	public Integer getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}
	public String getChipSerialid() {
		return chipSerialid;
	}
	public void setChipSerialid(String chipSerialid) {
		this.chipSerialid = chipSerialid;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public Long getIsOpenpay() {
		return isOpenpay;
	}
	public void setIsOpenpay(Long isOpenpay) {
		this.isOpenpay = isOpenpay;
	}
	public Double getPaylimit() {
		return paylimit;
	}
	public void setPaylimit(Double paylimit) {
		this.paylimit = paylimit;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getPaysystem() {
		return paysystem;
	}
	public void setPaysystem(Long paysystem) {
		this.paysystem = paysystem;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public Long getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(Long accounttype) {
		this.accounttype = accounttype;
	}
	public String getBacknum() {
		return backnum;
	}
	public void setBacknum(String backnum) {
		this.backnum = backnum;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidDate2() {
		return validDate2;
	}
	public void setValidDate2(Date validDate2) {
		this.validDate2 = validDate2;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
