package com.healthpay.modules.hc.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.entity.User;

/**
 * 健康卡数据Entity
 * 
 * @author yyl
 * @version 2016-05-25
 */
public class HpHealthcard extends DataEntity<HpHealthcard> {

	private static final long serialVersionUID = 1L;
	private String pkid; // 电子健康卡id 通过省卡管注册获取
	private String cardId; // 卡号（居民健康卡主索引ID）通过省卡管注册获取
	private String identityId; // 身份证号码 证件类型+证件号码
	private Integer securityCode; // 安全码
	private String chipSerialid; // 芯片序列号
	private Integer cardType; // 卡的类别
	private String version; // 规范版本
	private Office office; // 发卡机构
	private Date startDate; // 发卡时间
	private Date validDate; // 卡有效期
	private String countrycode; // 国家代码（卡归属地 ，发卡时等于常住地）
	private String countryname; // 国家名称
	private String provcode; // 省代码
	private String provname; // 省名称
	private String citycode; // 市代码
	private String cityname; // 市名称
	private String countycode; // 县级代码
	private String countyname; // 县级名称
	private String towncode; // 镇级代码
	private String townname; // 镇级名称
	private String villagecode; // 村代码
	private String villagename; // 村名称
	private String countrycode2; // 国家代码（卡归属地 ，发卡时等于常住地）
	private String countryname2; // 国家名称
	private String provcode2; // 省代码
	private String provname2; // 省名称
	private String citycode2; // 市代码
	private String cityname2; // 市名称
	private String countycode2; // 县级代码
	private String countyname2; // 县级名称
	private String towncode2; // 镇级代码
	private String townname2; // 镇级名称
	private String villagecode2; // 村代码
	private String villagename2; // 村名称
	private String address; // 地址
	private Long isOpenpay; // 是否开通健康钱包 0 - 否 1 - 是
	private Date openpayDate;
	private Date firstOpenpayDate;
	private Double paylimit; // 支付限额 0 or null 表示没有限额
	private String password; // 支付密码 开通支付必须填密码
	private Long isPwdfree; // 小额免密 0 - 否 1 - 是
	private Double smalllimit; // 小额额度
	private String mobile; // 预留手机号
	private String phone;		// 本人手机
	private String reserved; // 预留文字信息
	private Date auditDate; // 审核时间
	private User auditBy; // 审核人ID
	private String reason; // 审核不通过原因
	private Long source; // 来源
	private String url0; // 发卡机构公章图片地址
	private String url1; // url1
	private String url2; // url2
	private String url3; // url3
	private String url4; // url4
	private String url5; // url5
	private Long flag00; // flag00
	private Long flag01; // flag01
	private Long flag02; // flag02
	private Long flag03; // flag03
	private Long flag04; // flag04
	private Long flag05; // flag05
	private String str00; // 商户号
	private String str01; // 电子健康卡申请方式
	private String str02; // 认证模式
	private String str03; // str03
	private String str04; // str04
	private String str05; // str05
	private String str06; // str06
	private String status; // 状态 0 - 申请 1- 待激活 2 - 生效( 审核通过) 3 - 注销 4 - 挂失
							// - 冻结 6 - 待激活（预开卡情况）
	private String delflag; // 删除标记（0：正常；1：删除；）
	private String remark; // 备注
	private Area area;
	private Area area2;

	private HpCardholder hpCardholder; //持卡人信息

	/*public HpHealthcard() {
		super();
	}

	public HpHealthcard(String id) {
		super(id);
	}*/

	//@Length(min = 1, max = 30, message = "主键(系统编码，考虑上亿数据，和外部对接。)编码规则：JK+ 省代码+市代码+YYYYMMDD+00000001长度必须介于 1 和 30 之间")
	//@ExcelField(title = "主键(系统编码，考虑上亿数据，和外部对接。)编码规则：JK+ 省代码+市代码+YYYYMMDD+00000001", align = 2, sort = 0)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	@Length(min = 1, max = 30, message = "卡号（即身份证号码（看国标还是厂标））长度必须介于 1 和 30 之间")
	@ExcelField(title = "卡号（即身份证号码（看国标还是厂标））", align = 2, sort = 1)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Length(min = 1, max = 30, message = "身份证号码 长度必须介于 1 和 30 之间")
	@ExcelField(title = "身份证号码 ", align = 2, sort = 2)
	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	@ExcelField(title = "安全码", align = 2, sort = 3)
	public Integer getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}

	@Length(min = 0, max = 10, message = "芯片序列号长度必须介于 0 和 10 之间")
	@ExcelField(title = "芯片序列号", align = 2, sort = 4)
	public String getChipSerialid() {
		return chipSerialid;
	}

	public void setChipSerialid(String chipSerialid) {
		this.chipSerialid = chipSerialid;
	}

	@ExcelField(title = "卡的类别", align = 2, sort = 5)
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	@Length(min = 0, max = 4, message = "规范版本长度必须介于 0 和 4 之间")
	@ExcelField(title = "规范版本", align = 2, sort = 6)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "发卡时间", align = 2, sort = 9)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "卡有效期", align = 2, sort = 10)
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	@Length(min = 0, max = 20, message = "国家代码（卡归属地 ，发卡时等于常住地）长度必须介于 0 和 20 之间")
	@ExcelField(title = "国家代码（卡归属地 ，发卡时等于常住地）", align = 2, sort = 11)
	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Length(min = 0, max = 100, message = "国家名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "国家名称", align = 2, sort = 12)
	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	@Length(min = 0, max = 20, message = "省代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "省代码", align = 2, sort = 13)
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

	@Length(min = 0, max = 100, message = "省名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "省名称", align = 2, sort = 14)
	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	@Length(min = 0, max = 20, message = "市代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "市代码", align = 2, sort = 15)
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	@Length(min = 0, max = 100, message = "市名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "市名称", align = 2, sort = 16)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	

	@Length(min = 0, max = 20, message = "县级代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "县级代码", align = 2, sort = 17)
	public String getCountycode() {
		return countycode;
	}

	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}

	@Length(min = 0, max = 100, message = "县级名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "县级名称", align = 2, sort = 18)
	public String getCountyname() {
		return countyname;
	}

	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}

	@Length(min = 0, max = 20, message = "镇级代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "镇级代码", align = 2, sort = 19)
	public String getTowncode() {
		return towncode;
	}

	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}

	@Length(min = 0, max = 100, message = "镇级名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "镇级名称", align = 2, sort = 20)
	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname = townname;
	}

	@Length(min = 0, max = 20, message = "村代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "村代码", align = 2, sort = 21)
	public String getVillagecode() {
		return villagecode;
	}

	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}

	@Length(min = 0, max = 100, message = "村名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "村名称", align = 2, sort = 22)
	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	
	@Length(min = 0, max = 20, message = "国家代码（卡归属地 ，发卡时等于常住地）长度必须介于 0 和 20 之间")
	@ExcelField(title = "国家代码（卡归属地 ，发卡时等于常住地）", align = 2, sort = 11)
	public String getCountrycode2() {
		return countrycode2;
	}

	public void setCountrycode2(String countrycode2) {
		this.countrycode2 = countrycode2;
	}

	@Length(min = 0, max = 100, message = "国家名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "国家名称", align = 2, sort = 12)
	public String getCountryname2() {
		return countryname2;
	}

	public void setCountryname2(String countryname2) {
		this.countryname2 = countryname2;
	}

	@Length(min = 0, max = 20, message = "省代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "省代码", align = 2, sort = 13)
	public String getProvcode2() {
		return provcode2;
	}

	public void setProvcode2(String provcode2) {
		this.provcode2 = provcode2;
	}

	@Length(min = 0, max = 100, message = "省名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "省名称", align = 2, sort = 14)
	public String getProvname2() {
		return provname2;
	}

	public void setProvname2(String provname2) {
		this.provname2 = provname2;
	}
	
	@Length(min = 0, max = 20, message = "市代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "市代码", align = 2, sort = 15)
	public String getCitycode2() {
		return citycode2;
	}

	public void setCitycode2(String citycode2) {
		this.citycode2 = citycode2;
	}

	@Length(min = 0, max = 100, message = "市名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "市名称", align = 2, sort = 16)
	public String getCityname2() {
		return cityname2;
	}

	public void setCityname2(String cityname2) {
		this.cityname2 = cityname2;
	}

	@Length(min = 0, max = 20, message = "县级代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "县级代码", align = 2, sort = 17)
	public String getCountycode2() {
		return countycode2;
	}

	public void setCountycode2(String countycode2) {
		this.countycode2 = countycode2;
	}

	@Length(min = 0, max = 100, message = "县级名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "县级名称", align = 2, sort = 18)
	public String getCountyname2() {
		return countyname2;
	}

	public void setCountyname2(String countyname2) {
		this.countyname2 = countyname2;
	}

	@Length(min = 0, max = 20, message = "镇级代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "镇级代码", align = 2, sort = 19)
	public String getTowncode2() {
		return towncode2;
	}

	public void setTowncode2(String towncode2) {
		this.towncode2 = towncode2;
	}

	@Length(min = 0, max = 100, message = "镇级名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "镇级名称", align = 2, sort = 20)
	public String getTownname2() {
		return townname2;
	}

	public void setTownname2(String townname2) {
		this.townname2 = townname2;
	}

	@Length(min = 0, max = 20, message = "村代码长度必须介于 0 和 20 之间")
	@ExcelField(title = "村代码", align = 2, sort = 21)
	public String getVillagecode2() {
		return villagecode2;
	}

	public void setVillagecode2(String villagecode2) {
		this.villagecode2 = villagecode2;
	}

	@Length(min = 0, max = 100, message = "村名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "村名称", align = 2, sort = 22)
	public String getVillagename2() {
		return villagename2;
	}

	public void setVillagename2(String villagename2) {
		this.villagename2 = villagename2;
	}

	@Length(min = 0, max = 150, message = "地址长度必须介于 0 和 150 之间")
	@ExcelField(title = "地址", align = 2, sort = 23)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ExcelField(title = "是否开通健康钱包  0 - 否  1 - 是", align = 2, sort = 24)
	public Long getIsOpenpay() {
		return isOpenpay;
	}

	public void setIsOpenpay(Long isOpenpay) {
		this.isOpenpay = isOpenpay;
	}

	@ExcelField(title = "支付限额 0 or null  表示没有限额", align = 2, sort = 25)
	public Double getPaylimit() {
		return paylimit;
	}

	public void setPaylimit(Double paylimit) {
		this.paylimit = paylimit;
	}

	@Length(min = 0, max = 30, message = "支付密码  开通支付必须填密码长度必须介于 0 和 30 之间")
	@ExcelField(title = "支付密码  开通支付必须填密码", align = 2, sort = 26)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ExcelField(title = "小额免密 0 - 否 1 - 是", align = 2, sort = 27)
	public Long getIsPwdfree() {
		return isPwdfree;
	}

	public void setIsPwdfree(Long isPwdfree) {
		this.isPwdfree = isPwdfree;
	}

	@ExcelField(title = "小额额度 ", align = 2, sort = 28)
	public Double getSmalllimit() {
		return smalllimit;
	}

	public void setSmalllimit(Double smalllimit) {
		this.smalllimit = smalllimit;
	}

	@Length(min = 0, max = 20, message = "预留手机号长度必须介于 0 和 20 之间")
	@ExcelField(title = "预留手机号", align = 2, sort = 29)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min = 0, max = 20, message = "预留手机号长度必须介于 0 和 20 之间")
	@ExcelField(title = "预留手机号", align = 2, sort = 29)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min = 0, max = 30, message = "预留文字信息长度必须介于 0 和 30 之间")
	@ExcelField(title = "预留文字信息", align = 2, sort = 30)
	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "审核时间", align = 2, sort = 34)
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	@Length(min = 0, max = 300, message = "审核不通过原因长度必须介于 0 和 300 之间")
	@ExcelField(title = "审核不通过原因", align = 2, sort = 40)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Length(min = 0, max = 250, message = "发卡机构公章图片地址长度必须介于 0 和 250 之间")
	@ExcelField(title = "发卡机构公章图片地址", align = 2, sort = 41)
	public String getUrl0() {
		return url0;
	}

	public void setUrl0(String url0) {
		this.url0 = url0;
	}

	@Length(min = 0, max = 250, message = "url1长度必须介于 0 和 250 之间")
	@ExcelField(title = "url1", align = 2, sort = 42)
	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	@Length(min = 0, max = 250, message = "url2长度必须介于 0 和 250 之间")
	@ExcelField(title = "url2", align = 2, sort = 43)
	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	@Length(min = 0, max = 250, message = "url3长度必须介于 0 和 250 之间")
	@ExcelField(title = "url3", align = 2, sort = 44)
	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	@Length(min = 0, max = 250, message = "url4长度必须介于 0 和 250 之间")
	@ExcelField(title = "url4", align = 2, sort = 45)
	public String getUrl4() {
		return url4;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}

	@Length(min = 0, max = 250, message = "url5长度必须介于 0 和 250 之间")
	@ExcelField(title = "url5", align = 2, sort = 46)
	public String getUrl5() {
		return url5;
	}

	public void setUrl5(String url5) {
		this.url5 = url5;
	}

	@ExcelField(title = "flag00", align = 2, sort = 47)
	public Long getFlag00() {
		return flag00;
	}

	public void setFlag00(Long flag00) {
		this.flag00 = flag00;
	}

	@ExcelField(title = "flag01", align = 2, sort = 48)
	public Long getFlag01() {
		return flag01;
	}

	public void setFlag01(Long flag01) {
		this.flag01 = flag01;
	}

	@ExcelField(title = "flag02", align = 2, sort = 49)
	public Long getFlag02() {
		return flag02;
	}

	public void setFlag02(Long flag02) {
		this.flag02 = flag02;
	}

	@ExcelField(title = "flag03", align = 2, sort = 50)
	public Long getFlag03() {
		return flag03;
	}

	public void setFlag03(Long flag03) {
		this.flag03 = flag03;
	}

	@ExcelField(title = "flag04", align = 2, sort = 51)
	public Long getFlag04() {
		return flag04;
	}

	public void setFlag04(Long flag04) {
		this.flag04 = flag04;
	}

	@ExcelField(title = "flag05", align = 2, sort = 52)
	public Long getFlag05() {
		return flag05;
	}

	public void setFlag05(Long flag05) {
		this.flag05 = flag05;
	}

	@Length(min = 0, max = 100, message = "str00长度必须介于 0 和 100 之间")
	@ExcelField(title = "str00", align = 2, sort = 53)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}

	@Length(min = 0, max = 100, message = "str01长度必须介于 0 和 100 之间")
	@ExcelField(title = "str01", align = 2, sort = 54)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}

	@Length(min = 0, max = 100, message = "str02长度必须介于 0 和 100 之间")
	@ExcelField(title = "str02", align = 2, sort = 55)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}

	@Length(min = 0, max = 100, message = "str03长度必须介于 0 和 100 之间")
	@ExcelField(title = "str03", align = 2, sort = 56)
	public String getStr03() {
		return str03;
	}

	public void setStr03(String str03) {
		this.str03 = str03;
	}

	@Length(min = 0, max = 100, message = "str04长度必须介于 0 和 100 之间")
	@ExcelField(title = "str04", align = 2, sort = 57)
	public String getStr04() {
		return str04;
	}

	public void setStr04(String str04) {
		this.str04 = str04;
	}

	@Length(min = 0, max = 100, message = "str05长度必须介于 0 和 100 之间")
	@ExcelField(title = "str05", align = 2, sort = 58)
	public String getStr05() {
		return str05;
	}

	public void setStr05(String str05) {
		this.str05 = str05;
	}

	@Length(min = 0, max = 100, message = "str06长度必须介于 0 和 100 之间")
	@ExcelField(title = "str06", align = 2, sort = 59)
	public String getStr06() {
		return str06;
	}

	public void setStr06(String str06) {
		this.str06 = str06;
	}

	@Length(min = 0, max = 2, message = "状态 0 - 注销 1- 已申请 2 - 生效( 审核通过) 3 - 审核不通过 4 - 挂失 5 - 冻结  6 - 待激活（预开卡情况）长度必须介于 0 和 2 之间")
	@ExcelField(title = "状态 0 - 注销 1- 已申请 2 - 生效( 审核通过) 3 - 审核不通过 4 - 挂失 5 - 冻结  6 - 待激活（预开卡情况）", align = 2, sort = 60)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min = 0, max = 2, message = "删除标记（0：正常；1：删除；）长度必须介于 0 和 2 之间")
	@ExcelField(title = "删除标记（0：正常；1：删除；）", align = 2, sort = 61)
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@Length(min = 0, max = 300, message = "备注长度必须介于 0 和 300 之间")
	@ExcelField(title = "备注", align = 2, sort = 62)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(User auditBy) {
		this.auditBy = auditBy;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public Date getOpenpayDate() {
		return openpayDate;
	}

	public void setOpenpayDate(Date openpayDate) {
		this.openpayDate = openpayDate;
	}

	public Date getFirstOpenpayDate() {
		return firstOpenpayDate;
	}

	public void setFirstOpenpayDate(Date firstOpenpayDate) {
		this.firstOpenpayDate = firstOpenpayDate;
	}

    public HpCardholder getHpCardholder() {
        return hpCardholder;
    }

    public void setHpCardholder(HpCardholder hpCardholder) {
        this.hpCardholder = hpCardholder;
    }

    @Override
	public boolean getIsNewRecord() {
		return isNewRecord || StringUtils.isBlank(getPkid());
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Area getArea2() {
		return area2;
	}

	public void setArea2(Area area2) {
		this.area2 = area2;
	}
}