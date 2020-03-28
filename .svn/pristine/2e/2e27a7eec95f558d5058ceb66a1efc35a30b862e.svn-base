package com.healthpay.modules.hc.entity;

import javax.validation.constraints.NotNull;

import com.healthpay.common.utils.DateUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.entity.User;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**
 * 健康卡历史记录Entity
 * @author yyl
 * @version 2016-05-25
 */
public class HpApplycardHis extends DataEntity<HpApplycardHis> {
	
	private static final long serialVersionUID = 1L;
	private String pkid;		// 主键uuid
	private String identityId;		// 身份证号码 （主键，程序控制18位）
	private String nationality ; //国籍
	private String docuType;		// 证件类型
	private String docuId;		// 证件号码
	private String healthId;		// 健康档案编号
	private String newFarmid;		// 新农合证（卡）号
	private String name;		// 姓名
	private Date birth;		// 出生日期
	private String profession;		// 职业（代码）
	private Integer addressType;		// 地址类别（代码）
	private Integer sex;		// 性别（代码）
	private String nation;		// 民族（代码）
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
	private String address;		// 地址(户籍)
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
	private String villagename2;		// 村名称
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
	private Long status;		// 状态：  1 待审核 2 - 审核通过 3 - 审核不通过 
	private Long source;		// 数据来源：1- 移动端注册 2 - 电脑端 3 -  批量预开卡
	private Long isBlack;		// 是否黑名单 0- 否 1- 是
	private Long isDead;		// 是否死亡 0-否 1- 是
	private String createByName;		// 创建人
	private Date auditDate;		// 审核时间
	private String auditBy;		// 审核人ID
	private String auditByName;		// 审核人
	private String cardId;		// 申请时为空，审核通过分配
	private Integer securityCode;		// 安全码
	private String chipSerialid;		// 芯片序列号
	private Integer cardType;		// 卡的类别
	private String version;		// 规范版本
	private String organizationName;		// 发卡机构名称
	private Long organizationCode;		// 发卡机构代码
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
	private Long flag00;		// 身份证正反面上传标识：0-未上传 1-已上传
	private Long flag01;		// flag01
	private Long flag02;		// flag02
	private Long flag03;		// flag03
	private Long flag04;		// flag04
	private Long flag05;		// flag05
	private String url0;		// 身份证正面图片地址
	private String url1;		// 身份证背面图片地址
	private String url2;		// url2
	private String url3;		// url3
	private String url4;		// url4
	private String url5;		// url5
	private String url6;		// url6
	private String url7;		// url7
	private String url8;		// url8
	private String str00;		// 商户号
	private String str01;		// 电子健康卡申请方式
	private String str02;		// 认证模式
	private String str03;		// str03
	private String str04;		// str04
	private String str05;		// str05
	private String remark;		// 备注
	
	private String refuse;	//拒绝原因
	
	private Area area ; //户籍地
	private Area area1 ; //居住地
	private Area area2 ; //卡归属地
	private Office office ; //发卡机构

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public HpApplycardHis() {
		super();
	}

	public HpApplycardHis(String id){
		super(id);
	}

	@ExcelField(title="主键", align=2, sort=0)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	@Length(min=1, max=30, message="身份证号码长度必须介于 1 和 30 之间")
	@ExcelField(title="身份证号码", align=2, sort=1)
	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	
	@ExcelField(title="证件类型", align=2, sort=2)
	public String getDocuType() {
		return docuType;
	}

	public void setDocuType(String docuType) {
		this.docuType = docuType;
	}
	
	@Length(min=0, max=30, message="证件号码长度必须介于 0 和 30 之间")
	@ExcelField(title="证件号码", align=2, sort=3)
	public String getDocuId() {
		return docuId;
	}

	public void setDocuId(String docuId) {
		this.docuId = docuId;
	}
	
	@Length(min=0, max=30, message="健康档案编号长度必须介于 0 和 30 之间")
	@ExcelField(title="健康档案编号", align=2, sort=4)
	public String getHealthId() {
		return healthId;
	}

	public void setHealthId(String healthId) {
		this.healthId = healthId;
	}
	
	@Length(min=0, max=30, message="新农合证（卡）号长度必须介于 0 和 30 之间")
	@ExcelField(title="新农合证（卡）号", align=2, sort=5)
	public String getNewFarmid() {
		return newFarmid;
	}

	public void setNewFarmid(String newFarmid) {
		this.newFarmid = newFarmid;
	}
	
	@Length(min=1, max=30, message="姓名长度必须介于 1 和 30 之间")
	@ExcelField(title="姓名", align=2, sort=6)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="出生日期不能为空")
	@ExcelField(title="出生日期", align=2, sort=7)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
		if(null != birth){
			this.setAge(DateUtils.getAge(birth));
		}
	}
	
	@Length(min=1, max=3, message="职业长度必须介于 1 和 3 之间")
	@ExcelField(title="职业", align=2, sort=8)
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@ExcelField(title="地址类别", align=2, sort=9)
	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}
	
	@NotNull(message="性别不能为空")
	@ExcelField(title="性别", align=2, sort=10)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@NotNull(message="民族不能为空")
	@ExcelField(title="民族", align=2, sort=11)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=1, max=2, message="婚姻状况长度必须介于 1 和 2 之间")
	@ExcelField(title="婚姻状况", align=2, sort=12)
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
//	@NotNull(message="文化程度不能为空")
	@ExcelField(title="文化程度", align=2, sort=13)
	public Integer getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(Integer educationLevel) {
		this.educationLevel = educationLevel;
	}
	
	@Length(min=0, max=20, message="国家代码长度必须介于 0 和 20 之间")
	@ExcelField(title="国家代码", align=2, sort=14)
	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	@Length(min=0, max=100, message="国家名称长度必须介于 0 和 100 之间")
	@ExcelField(title="国家名称", align=2, sort=15)
	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	
	@Length(min=0, max=20, message="省代码长度必须介于 0 和 20 之间")
	@ExcelField(title="省代码", align=2, sort=16)
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	
	@Length(min=0, max=100, message="省名称长度必须介于 0 和 100 之间")
	@ExcelField(title="省名称", align=2, sort=17)
	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}
	
	@Length(min=0, max=20, message="市代码长度必须介于 0 和 20 之间")
	@ExcelField(title="市代码", align=2, sort=18)
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=0, max=100, message="市名称长度必须介于 0 和 100 之间")
	@ExcelField(title="市名称", align=2, sort=19)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Length(min=0, max=20, message="县级代码长度必须介于 0 和 20 之间")
	@ExcelField(title="县级代码", align=2, sort=20)
	public String getCountycode() {
		return countycode;
	}

	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}
	
	@Length(min=0, max=100, message="县级名称长度必须介于 0 和 100 之间")
	@ExcelField(title="县级名称", align=2, sort=21)
	public String getCountyname() {
		return countyname;
	}

	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	
	@Length(min=0, max=20, message="镇级代码长度必须介于 0 和 20 之间")
	@ExcelField(title="镇级代码", align=2, sort=22)
	public String getTowncode() {
		return towncode;
	}

	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	
	@Length(min=0, max=100, message="镇级名称长度必须介于 0 和 100 之间")
	@ExcelField(title="镇级名称", align=2, sort=23)
	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname = townname;
	}
	
	@Length(min=0, max=20, message="村代码长度必须介于 0 和 20 之间")
	@ExcelField(title="村代码", align=2, sort=24)
	public String getVillagecode() {
		return villagecode;
	}

	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}
	
	@Length(min=0, max=100, message="村名称长度必须介于 0 和 100 之间")
	@ExcelField(title="村名称", align=2, sort=25)
	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	
	@Length(min=0, max=150, message="地址长度必须介于 0 和 150 之间")
	@ExcelField(title="地址", align=2, sort=26)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=30, message="邮编长度必须介于 0 和 30 之间")
	@ExcelField(title="邮编", align=2, sort=27)
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Length(min=0, max=20, message="国家代码(居住地，为空时，表示和户籍地址一致)长度必须介于 0 和 20 之间")
	@ExcelField(title="国家代码(居住地，为空时，表示和户籍地址一致)", align=2, sort=28)
	public String getCountrycode2() {
		return countrycode2;
	}

	public void setCountrycode2(String countrycode2) {
		this.countrycode2 = countrycode2;
	}
	
	@Length(min=0, max=100, message="国家名称长度必须介于 0 和 100 之间")
	@ExcelField(title="国家名称", align=2, sort=29)
	public String getCountryname2() {
		return countryname2;
	}

	public void setCountryname2(String countryname2) {
		this.countryname2 = countryname2;
	}
	
	@Length(min=0, max=20, message="省代码长度必须介于 0 和 20 之间")
	@ExcelField(title="省代码", align=2, sort=30)
	public String getProvcode2() {
		return provcode2;
	}

	public void setProvcode2(String provcode2) {
		this.provcode2 = provcode2;
	}
	
	@Length(min=0, max=100, message="省名称长度必须介于 0 和 100 之间")
	@ExcelField(title="省名称", align=2, sort=31)
	public String getProvname2() {
		return provname2;
	}

	public void setProvname2(String provname2) {
		this.provname2 = provname2;
	}
	
	@Length(min=0, max=20, message="市代码长度必须介于 0 和 20 之间")
	@ExcelField(title="市代码", align=2, sort=32)
	public String getCitycode2() {
		return citycode2;
	}

	public void setCitycode2(String citycode2) {
		this.citycode2 = citycode2;
	}
	
	@Length(min=0, max=100, message="市名称长度必须介于 0 和 100 之间")
	@ExcelField(title="市名称", align=2, sort=33)
	public String getCityname2() {
		return cityname2;
	}

	public void setCityname2(String cityname2) {
		this.cityname2 = cityname2;
	}
	
	@Length(min=0, max=20, message="县级代码长度必须介于 0 和 20 之间")
	@ExcelField(title="县级代码", align=2, sort=34)
	public String getCountycode2() {
		return countycode2;
	}

	public void setCountycode2(String countycode2) {
		this.countycode2 = countycode2;
	}
	
	@Length(min=0, max=100, message="县级名称长度必须介于 0 和 100 之间")
	@ExcelField(title="县级名称", align=2, sort=35)
	public String getCountyname2() {
		return countyname2;
	}

	public void setCountyname2(String countyname2) {
		this.countyname2 = countyname2;
	}
	
	@Length(min=0, max=20, message="镇级代码长度必须介于 0 和 20 之间")
	@ExcelField(title="镇级代码", align=2, sort=36)
	public String getTowncode2() {
		return towncode2;
	}

	public void setTowncode2(String towncode2) {
		this.towncode2 = towncode2;
	}
	
	@Length(min=0, max=100, message="镇级名称长度必须介于 0 和 100 之间")
	@ExcelField(title="镇级名称", align=2, sort=37)
	public String getTownname2() {
		return townname2;
	}

	public void setTownname2(String townname2) {
		this.townname2 = townname2;
	}
	
	@Length(min=0, max=20, message="村代码长度必须介于 0 和 20 之间")
	@ExcelField(title="村代码", align=2, sort=38)
	public String getVillagecode2() {
		return villagecode2;
	}

	public void setVillagecode2(String villagecode2) {
		this.villagecode2 = villagecode2;
	}
	
	@Length(min=0, max=100, message="村名称长度必须介于 0 和 100 之间")
	@ExcelField(title="村名称", align=2, sort=39)
	public String getVillagename2() {
		return villagename2;
	}

	public void setVillagename2(String villagename2) {
		this.villagename2 = villagename2;
	}
	
	@Length(min=0, max=150, message="地址长度必须介于 0 和 150 之间")
	@ExcelField(title="地址", align=2, sort=40)
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@ExcelField(title="医疗费用支付方式", align=2, sort=41)
	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	
	@Length(min=0, max=20, message="本人手机长度必须介于 0 和 20 之间")
	@ExcelField(title="本人手机", align=2, sort=42)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=20, message="本人电话长度必须介于 0 和 20 之间")
	@ExcelField(title="本人电话", align=2, sort=43)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=300, message="本人Email长度必须介于 0 和 300 之间")
	@ExcelField(title="本人Email", align=2, sort=44)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=30, message="联系人姓名长度必须介于 0 和 30 之间")
	@ExcelField(title="联系人姓名", align=2, sort=45)
	public String getAttnName() {
		return attnName;
	}

	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}
	
	@Length(min=0, max=20, message="联系人手机长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人手机", align=2, sort=46)
	public String getAttnPhone() {
		return attnPhone;
	}

	public void setAttnPhone(String attnPhone) {
		this.attnPhone = attnPhone;
	}
	
	@Length(min=0, max=20, message="联系人关系长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人关系", align=2, sort=47)
	public String getAttnRela() {
		return attnRela;
	}

	public void setAttnRela(String attnRela) {
		this.attnRela = attnRela;
	}
	
	@Length(min=0, max=30, message="联系人姓名2长度必须介于 0 和 30 之间")
	@ExcelField(title="联系人姓名2", align=2, sort=48)
	public String getAttnName2() {
		return attnName2;
	}

	public void setAttnName2(String attnName2) {
		this.attnName2 = attnName2;
	}
	
	@Length(min=0, max=20, message="联系人手机2长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人手机2", align=2, sort=49)
	public String getAttnPhone2() {
		return attnPhone2;
	}

	public void setAttnPhone2(String attnPhone2) {
		this.attnPhone2 = attnPhone2;
	}
	
	@Length(min=0, max=20, message="联系人关系2长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人关系2", align=2, sort=50)
	public String getAttnRela2() {
		return attnRela2;
	}

	public void setAttnRela2(String attnRela2) {
		this.attnRela2 = attnRela2;
	}
	
	@Length(min=0, max=30, message="联系人姓名3长度必须介于 0 和 30 之间")
	@ExcelField(title="联系人姓名3", align=2, sort=51)
	public String getAttnName3() {
		return attnName3;
	}

	public void setAttnName3(String attnName3) {
		this.attnName3 = attnName3;
	}
	
	@Length(min=0, max=20, message="联系人手机3长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人手机3", align=2, sort=52)
	public String getAttnPhone3() {
		return attnPhone3;
	}

	public void setAttnPhone3(String attnPhone3) {
		this.attnPhone3 = attnPhone3;
	}
	
	@Length(min=0, max=20, message="联系人关系3长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人关系3", align=2, sort=53)
	public String getAttnRela3() {
		return attnRela3;
	}

	public void setAttnRela3(String attnRela3) {
		this.attnRela3 = attnRela3;
	}
	
	@ExcelField(title="状态：  1 待审核 2 - 审核通过 3 - 审核不通过 ", align=2, sort=54)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	@ExcelField(title="数据来源：1- 移动端注册 2 - 电脑端 3 -  批量预开卡", align=2, sort=55)
	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}
	
	@ExcelField(title="是否黑名单 0- 否 1- 是", align=2, sort=56)
	public Long getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Long isBlack) {
		this.isBlack = isBlack;
	}
	
	@ExcelField(title="是否死亡 0-否 1- 是", align=2, sort=57)
	public Long getIsDead() {
		return isDead;
	}

	public void setIsDead(Long isDead) {
		this.isDead = isDead;
	}
	
	@ExcelField(title="创建人", align=2, sort=60)
	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="审核时间", align=2, sort=61)
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Length(min=0, max=20, message="审核人ID长度必须介于 0 和 20 之间")
	@ExcelField(title="审核人ID", align=2, sort=62)
	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	
	@Length(min=0, max=30, message="审核人长度必须介于 0 和 30 之间")
	@ExcelField(title="审核人", align=2, sort=63)
	public String getAuditByName() {
		return auditByName;
	}

	public void setAuditByName(String auditByName) {
		this.auditByName = auditByName;
	}
	
	@Length(min=1, max=30, message="申请时为空，审核通过分配长度必须介于 1 和 30 之间")
	@ExcelField(title="申请时为空，审核通过分配", align=2, sort=64)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	@ExcelField(title="安全码", align=2, sort=65)
	public Integer getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}
	
	@Length(min=0, max=10, message="芯片序列号长度必须介于 0 和 10 之间")
	@ExcelField(title="芯片序列号", align=2, sort=66)
	public String getChipSerialid() {
		return chipSerialid;
	}

	public void setChipSerialid(String chipSerialid) {
		this.chipSerialid = chipSerialid;
	}
	
	@ExcelField(title="卡的类别", align=2, sort=67)
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
	@Length(min=0, max=4, message="规范版本长度必须介于 0 和 4 之间")
	@ExcelField(title="规范版本", align=2, sort=68)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=30, message="发卡机构名称长度必须介于 0 和 30 之间")
	@ExcelField(title="发卡机构名称", align=2, sort=69)
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	@ExcelField(title="发卡机构代码", align=2, sort=70)
	public Long getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(Long organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发卡时间", align=2, sort=71)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="卡有效期", align=2, sort=72)
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	@ExcelField(title="是否开通健康钱包", align=2, sort=73)
	public Long getIsOpenpay() {
		return isOpenpay;
	}

	public void setIsOpenpay(Long isOpenpay) {
		this.isOpenpay = isOpenpay;
	}
	
	@ExcelField(title="支付限额 表示没有限额", align=2, sort=74)
	public Double getPaylimit() {
		return paylimit;
	}

	public void setPaylimit(Double paylimit) {
		this.paylimit = paylimit;
	}
	
	@Length(min=0, max=30, message="支付密码 必须介于 0 和 30 之间")
	@ExcelField(title="支付密码", align=2, sort=75)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@ExcelField(title="小额免密 0 - 否 1 - 是", align=2, sort=76)
	public Long getIsPwdfree() {
		return isPwdfree;
	}

	public void setIsPwdfree(Long isPwdfree) {
		this.isPwdfree = isPwdfree;
	}
	
	@ExcelField(title="小额额度 ", align=2, sort=77)
	public Double getSmalllimit() {
		return smalllimit;
	}

	public void setSmalllimit(Double smalllimit) {
		this.smalllimit = smalllimit;
	}
	
	@Length(min=0, max=20, message="预留手机号长度必须介于 0 和 20 之间")
	@ExcelField(title="预留手机号", align=2, sort=78)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=30, message="预留文字信息长度必须介于 0 和 30 之间")
	@ExcelField(title="预留文字信息", align=2, sort=79)
	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	
	@Length(min=0, max=300, message="审核不通过原因长度必须介于 0 和 300 之间")
	@ExcelField(title="审核不通过原因", align=2, sort=80)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@ExcelField(title="支付系统类型", align=2, sort=81)
	public Long getPaysystem() {
		return paysystem;
	}

	public void setPaysystem(Long paysystem) {
		this.paysystem = paysystem;
	}
	
	@Length(min=0, max=30, message="账号长度必须介于 0 和 30 之间")
	@ExcelField(title="账号", align=2, sort=82)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=30, message="户名长度必须介于 0 和 30 之间")
	@ExcelField(title="户名", align=2, sort=83)
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=0, max=30, message="预留手机号长度必须介于 0 和 30 之间")
	@ExcelField(title="预留手机号", align=2, sort=84)
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
	
	@Length(min=0, max=100, message="银行名称长度必须介于 0 和 100 之间")
	@ExcelField(title="银行名称", align=2, sort=86)
	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	@ExcelField(title="账号类型", align=2, sort=87)
	public Long getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Long accounttype) {
		this.accounttype = accounttype;
	}
	
	@Length(min=0, max=30, message="卡背后号码长度必须介于 0 和 30 之间")
	@ExcelField(title="卡背后号码", align=2, sort=88)
	public String getBacknum() {
		return backnum;
	}

	public void setBacknum(String backnum) {
		this.backnum = backnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="卡有效期", align=2, sort=89)
	public Date getValidDate2() {
		return validDate2;
	}

	public void setValidDate2(Date validDate2) {
		this.validDate2 = validDate2;
	}
	
	@ExcelField(title="身份证正反面上传标识", align=2, sort=90)
	public Long getFlag00() {
		return flag00;
	}

	public void setFlag00(Long flag00) {
		this.flag00 = flag00;
	}
	
	@ExcelField(title="flag01", align=2, sort=91)
	public Long getFlag01() {
		return flag01;
	}

	public void setFlag01(Long flag01) {
		this.flag01 = flag01;
	}
	
	@ExcelField(title="flag02", align=2, sort=92)
	public Long getFlag02() {
		return flag02;
	}

	public void setFlag02(Long flag02) {
		this.flag02 = flag02;
	}
	
	@ExcelField(title="flag03", align=2, sort=93)
	public Long getFlag03() {
		return flag03;
	}

	public void setFlag03(Long flag03) {
		this.flag03 = flag03;
	}
	
	@ExcelField(title="flag04", align=2, sort=94)
	public Long getFlag04() {
		return flag04;
	}

	public void setFlag04(Long flag04) {
		this.flag04 = flag04;
	}
	
	@ExcelField(title="flag05", align=2, sort=95)
	public Long getFlag05() {
		return flag05;
	}

	public void setFlag05(Long flag05) {
		this.flag05 = flag05;
	}
	
	@Length(min=0, max=250, message="身份证正面图片地址长度必须介于 0 和 250 之间")
	@ExcelField(title="身份证正面图片地址", align=2, sort=96)
	public String getUrl0() {
		return url0;
	}

	public void setUrl0(String url0) {
		this.url0 = url0;
	}
	
	@Length(min=0, max=250, message="身份证背面图片地址长度必须介于 0 和 250 之间")
	@ExcelField(title="身份证背面图片地址", align=2, sort=97)
	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	
	@Length(min=0, max=250, message="url2长度必须介于 0 和 250 之间")
	@ExcelField(title="url2", align=2, sort=98)
	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	
	@Length(min=0, max=250, message="url3长度必须介于 0 和 250 之间")
	@ExcelField(title="url3", align=2, sort=99)
	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	
	@Length(min=0, max=250, message="url4长度必须介于 0 和 250 之间")
	@ExcelField(title="url4", align=2, sort=100)
	public String getUrl4() {
		return url4;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}
	
	@Length(min=0, max=250, message="url5长度必须介于 0 和 250 之间")
	@ExcelField(title="url5", align=2, sort=101)
	public String getUrl5() {
		return url5;
	}

	public void setUrl5(String url5) {
		this.url5 = url5;
	}
	
	@Length(min=0, max=250, message="url6长度必须介于 0 和 250 之间")
	@ExcelField(title="url6", align=2, sort=102)
	public String getUrl6() {
		return url6;
	}

	public void setUrl6(String url6) {
		this.url6 = url6;
	}
	
	@Length(min=0, max=250, message="url7长度必须介于 0 和 250 之间")
	@ExcelField(title="url7", align=2, sort=103)
	public String getUrl7() {
		return url7;
	}

	public void setUrl7(String url7) {
		this.url7 = url7;
	}
	
	@Length(min=0, max=250, message="url8长度必须介于 0 和 250 之间")
	@ExcelField(title="url8", align=2, sort=104)
	public String getUrl8() {
		return url8;
	}

	public void setUrl8(String url8) {
		this.url8 = url8;
	}
	
	@Length(min=0, max=100, message="str00长度必须介于 0 和 100 之间")
	@ExcelField(title="str00", align=2, sort=105)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}
	
	@Length(min=0, max=100, message="str01长度必须介于 0 和 100 之间")
	@ExcelField(title="str01", align=2, sort=106)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}
	
	@Length(min=0, max=100, message="str02长度必须介于 0 和 100 之间")
	@ExcelField(title="str02", align=2, sort=107)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}
	
	@Length(min=0, max=100, message="str03长度必须介于 0 和 100 之间")
	@ExcelField(title="str03", align=2, sort=108)
	public String getStr03() {
		return str03;
	}

	public void setStr03(String str03) {
		this.str03 = str03;
	}
	
	@Length(min=0, max=100, message="str04长度必须介于 0 和 100 之间")
	@ExcelField(title="str04", align=2, sort=109)
	public String getStr04() {
		return str04;
	}

	public void setStr04(String str04) {
		this.str04 = str04;
	}
	
	@Length(min=0, max=100, message="str05长度必须介于 0 和 100 之间")
	@ExcelField(title="str05", align=2, sort=110)
	public String getStr05() {
		return str05;
	}

	public void setStr05(String str05) {
		this.str05 = str05;
	}
	
	@Length(min=0, max=300, message="备注长度必须介于 0 和 300 之间")
	@ExcelField(title="备注", align=2, sort=111)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Area getArea1() {
		return area1;
	}

	public void setArea1(Area area1) {
		this.area1 = area1;
	}

	public Area getArea2() {
		return area2;
	}

	public void setArea2(Area area2) {
		this.area2 = area2;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRefuse() {
		return refuse;
	}

	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}
	
}