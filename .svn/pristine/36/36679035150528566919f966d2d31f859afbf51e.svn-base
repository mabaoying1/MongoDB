package com.healthpay.modules.hc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 持卡人档案Entity
 * @author yyl
 * @version 2016-05-25
 */
public class HpCardholder extends DataEntity<HpCardholder> {
	
	private static final long serialVersionUID = 1L;
	private String identityId;		// 身份证号码 （主键，程序控制18位）
	private String nationality ; //国籍
	private String docuType;		// 证件类型
	private String docuId;		//证件号码
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
	private Long status;		// 状态： 0 - 已销户 1 - 正常 2 - 已拉黑
	private Long source;		// 数据来源：1- 移动端注册 2 - 电脑端 3 -  批量预开卡
	private Long isBlack;		// 是否黑名单 0- 否 1- 是
	private Long isDead;		// 是否死亡 0-否 1- 是
	private User createByName;		// 创建人
	private Date auditDate;		// 审核时间
	private String auditBy;		// 审核人ID
	private String auditByName;		// 审核人
	private User updateByName;		// 更新人
	private Long flag00;		// 身份证正反面上传标识：0-未上传 1-已上传
	private Long flag01;		// flag01
	private Long flag02;		// flag02
	private Long flag03;		// flag03
	private Long flag04;		// flag04
	private Long flag05;		// flag05
	private Long flag06;		// flag06
	private Long flag07;		// flag07
	private Long flag08;		// flag08
	private Long flag09;		// flag09
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
	private String str06;		// str06
	private String str07;		// str07
	private String str08;		// str08
	private String str09;		// str09
	private String str10;		// str10
	private String opt00;		// opt00
	private String opt01;		// opt01
	private String opt02;		// opt02
	private String opt03;		// opt03
	private String opt04;		// opt04
	private String opt05;		// opt05
	private String remark;		// 备注
	private String delflag;		// 删除标记（0：正常；1：删除；）
	private Area area;
	private Area area2;

	private Integer count;
    private String createDateStr;

	private String beginDate;
	private String endDate;

	private String year;
	private Integer age;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public HpCardholder() {
		super();
	}

	public HpCardholder(String id){
		super(id);
	}

	@Length(min=1, max=30, message="身份证号码 （主键，程序控制18位）长度必须介于 1 和 30 之间")
	@ExcelField(title="身份证号码 （主键，程序控制18位）", align=2, sort=0)
	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	
	@ExcelField(title="证件类型", align=2, sort=1)
	public String getDocuType() {
		return docuType;
	}

	public void setDocuType(String docuType) {
		this.docuType = docuType;
	}
	
	@Length(min=0, max=30, message="证件号码长度必须介于 0 和 30 之间")
	@ExcelField(title="证件号码", align=2, sort=2)
	public String getDocuId() {
		return docuId;
	}

	public void setDocuId(String docuId) {
		this.docuId = docuId;
	}
	
	@Length(min=0, max=30, message="健康档案编号长度必须介于 0 和 30 之间")
	@ExcelField(title="健康档案编号", align=2, sort=3)
	public String getHealthId() {
		return healthId;
	}

	public void setHealthId(String healthId) {
		this.healthId = healthId;
	}
	
	@Length(min=0, max=30, message="新农合证（卡）号长度必须介于 0 和 30 之间")
	@ExcelField(title="新农合证（卡）号", align=2, sort=4)
	public String getNewFarmid() {
		return newFarmid;
	}

	public void setNewFarmid(String newFarmid) {
		this.newFarmid = newFarmid;
	}
	
	@Length(min=1, max=30, message="姓名长度必须介于 1 和 30 之间")
	@ExcelField(title="姓名", align=2, sort=5)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="出生日期", align=2, sort=6)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
		if(null != birth){
			this.setAge(DateUtils.getAge(birth));
		}
	}
	
	@Length(min=1, max=3, message="职业（代码）长度必须介于 1 和 3 之间")
	@ExcelField(title="职业（代码）", align=2, sort=7)
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@ExcelField(title="地址类别（代码）", align=2, sort=8)
	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}
	
	@NotNull(message="性别（代码）不能为空")
	@ExcelField(title="性别（代码）", align=2, sort=9)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@ExcelField(title="民族（代码）", align=2, sort=10)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=1, max=2, message="婚姻状况（代码）长度必须介于 1 和 2 之间")
	@ExcelField(title="婚姻状况（代码）", align=2, sort=11)
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
//	@NotNull(message="文化程度（代码）不能为空")
	@ExcelField(title="文化程度（代码）", align=2, sort=12)
	public Integer getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(Integer educationLevel) {
		this.educationLevel = educationLevel;
	}
	
	@Length(min=0, max=20, message="国家代码(户籍)长度必须介于 0 和 20 之间")
	@ExcelField(title="国家代码(户籍)", align=2, sort=13)
	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	@Length(min=0, max=100, message="国家名称(户籍)长度必须介于 0 和 100 之间")
	@ExcelField(title="国家名称(户籍)", align=2, sort=14)
	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	
	@Length(min=0, max=20, message="省代码(户籍)长度必须介于 0 和 20 之间")
	@ExcelField(title="省代码(户籍)", align=2, sort=15)
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	
	@Length(min=0, max=100, message="省名称(户籍)长度必须介于 0 和 100 之间")
	@ExcelField(title="省名称(户籍)", align=2, sort=16)
	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}
	
	@Length(min=0, max=20, message="市代码(户籍)长度必须介于 0 和 20 之间")
	@ExcelField(title="市代码(户籍)", align=2, sort=17)
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=0, max=100, message="市名称(户籍)长度必须介于 0 和 100 之间")
	@ExcelField(title="市名称(户籍)", align=2, sort=18)
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Length(min=0, max=20, message="县级代码(户籍)长度必须介于 0 和 20 之间")
	@ExcelField(title="县级代码(户籍)", align=2, sort=19)
	public String getCountycode() {
		return countycode;
	}

	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}
	
	@Length(min=0, max=100, message="县级名称(户籍)长度必须介于 0 和 100 之间")
	@ExcelField(title="县级名称(户籍)", align=2, sort=20)
	public String getCountyname() {
		return countyname;
	}

	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	
	@Length(min=0, max=20, message="镇级代码(户籍)长度必须介于 0 和 20 之间")
	@ExcelField(title="镇级代码(户籍)", align=2, sort=21)
	public String getTowncode() {
		return towncode;
	}

	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	
	@Length(min=0, max=100, message="镇级名称(户籍)长度必须介于 0 和 100 之间")
	@ExcelField(title="镇级名称(户籍)", align=2, sort=22)
	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname = townname;
	}
	
	@Length(min=0, max=20, message="村代码(户籍)长度必须介于 0 和 20 之间")
	@ExcelField(title="村代码(户籍)", align=2, sort=23)
	public String getVillagecode() {
		return villagecode;
	}

	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}
	
	@Length(min=0, max=100, message="村名称(户籍)长度必须介于 0 和 100 之间")
	@ExcelField(title="村名称(户籍)", align=2, sort=24)
	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	
	@Length(min=0, max=150, message="地址(户籍)长度必须介于 0 和 150 之间")
	@ExcelField(title="地址(户籍)", align=2, sort=25)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=30, message="邮编长度必须介于 0 和 30 之间")
	@ExcelField(title="邮编", align=2, sort=26)
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Length(min=0, max=20, message="国家代码(居住地，为空时，表示和户籍地址一致)长度必须介于 0 和 20 之间")
	@ExcelField(title="国家代码(居住地，为空时，表示和户籍地址一致)", align=2, sort=27)
	public String getCountrycode2() {
		return countrycode2;
	}

	public void setCountrycode2(String countrycode2) {
		this.countrycode2 = countrycode2;
	}
	
	@Length(min=0, max=100, message="国家名称(居住地)长度必须介于 0 和 100 之间")
	@ExcelField(title="国家名称(居住地)", align=2, sort=28)
	public String getCountryname2() {
		return countryname2;
	}

	public void setCountryname2(String countryname2) {
		this.countryname2 = countryname2;
	}
	
	@Length(min=0, max=20, message="省代码(居住地)长度必须介于 0 和 20 之间")
	@ExcelField(title="省代码(居住地)", align=2, sort=29)
	public String getProvcode2() {
		return provcode2;
	}

	public void setProvcode2(String provcode2) {
		this.provcode2 = provcode2;
	}
	
	@Length(min=0, max=100, message="省名称(居住地)长度必须介于 0 和 100 之间")
	@ExcelField(title="省名称(居住地)", align=2, sort=30)
	public String getProvname2() {
		return provname2;
	}

	public void setProvname2(String provname2) {
		this.provname2 = provname2;
	}
	
	@Length(min=0, max=20, message="市代码(居住地)长度必须介于 0 和 20 之间")
	@ExcelField(title="市代码(居住地)", align=2, sort=31)
	public String getCitycode2() {
		return citycode2;
	}

	public void setCitycode2(String citycode2) {
		this.citycode2 = citycode2;
	}
	
	@Length(min=0, max=100, message="市名称(居住地)长度必须介于 0 和 100 之间")
	@ExcelField(title="市名称(居住地)", align=2, sort=32)
	public String getCityname2() {
		return cityname2;
	}

	public void setCityname2(String cityname2) {
		this.cityname2 = cityname2;
	}
	
	@Length(min=0, max=20, message="县级代码(居住地)长度必须介于 0 和 20 之间")
	@ExcelField(title="县级代码(居住地)", align=2, sort=33)
	public String getCountycode2() {
		return countycode2;
	}

	public void setCountycode2(String countycode2) {
		this.countycode2 = countycode2;
	}
	
	@Length(min=0, max=100, message="县级名称(居住地)长度必须介于 0 和 100 之间")
	@ExcelField(title="县级名称(居住地)", align=2, sort=34)
	public String getCountyname2() {
		return countyname2;
	}

	public void setCountyname2(String countyname2) {
		this.countyname2 = countyname2;
	}
	
	@Length(min=0, max=20, message="镇级代码(居住地)长度必须介于 0 和 20 之间")
	@ExcelField(title="镇级代码(居住地)", align=2, sort=35)
	public String getTowncode2() {
		return towncode2;
	}

	public void setTowncode2(String towncode2) {
		this.towncode2 = towncode2;
	}
	
	@Length(min=0, max=100, message="镇级名称(居住地)长度必须介于 0 和 100 之间")
	@ExcelField(title="镇级名称(居住地)", align=2, sort=36)
	public String getTownname2() {
		return townname2;
	}

	public void setTownname2(String townname2) {
		this.townname2 = townname2;
	}
	
	@Length(min=0, max=20, message="村代码(居住地)长度必须介于 0 和 20 之间")
	@ExcelField(title="村代码(居住地)", align=2, sort=37)
	public String getVillagecode2() {
		return villagecode2;
	}

	public void setVillagecode2(String villagecode2) {
		this.villagecode2 = villagecode2;
	}
	
	@Length(min=0, max=100, message="村名称(居住地)长度必须介于 0 和 100 之间")
	@ExcelField(title="村名称(居住地)", align=2, sort=38)
	public String getVillagename2() {
		return villagename2;
	}

	public void setVillagename2(String villagename2) {
		this.villagename2 = villagename2;
	}
	
	@Length(min=0, max=150, message="地址(居住地)长度必须介于 0 和 150 之间")
	@ExcelField(title="地址(居住地)", align=2, sort=39)
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@ExcelField(title="医疗费用支付方式（代码）", align=2, sort=40)
	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	
	@Length(min=0, max=20, message="本人手机长度必须介于 0 和 20 之间")
	@ExcelField(title="本人手机", align=2, sort=41)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=20, message="本人电话长度必须介于 0 和 20 之间")
	@ExcelField(title="本人电话", align=2, sort=42)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=300, message="本人Email长度必须介于 0 和 300 之间")
	@ExcelField(title="本人Email", align=2, sort=43)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=30, message="联系人姓名长度必须介于 0 和 30 之间")
	@ExcelField(title="联系人姓名", align=2, sort=44)
	public String getAttnName() {
		return attnName;
	}

	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}
	
	@Length(min=0, max=20, message="联系人手机长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人手机", align=2, sort=45)
	public String getAttnPhone() {
		return attnPhone;
	}

	public void setAttnPhone(String attnPhone) {
		this.attnPhone = attnPhone;
	}
	
	@Length(min=0, max=20, message="联系人关系长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人关系", align=2, sort=46)
	public String getAttnRela() {
		return attnRela;
	}

	public void setAttnRela(String attnRela) {
		this.attnRela = attnRela;
	}
	
	@Length(min=0, max=30, message="联系人姓名2长度必须介于 0 和 30 之间")
	@ExcelField(title="联系人姓名2", align=2, sort=47)
	public String getAttnName2() {
		return attnName2;
	}

	public void setAttnName2(String attnName2) {
		this.attnName2 = attnName2;
	}
	
	@Length(min=0, max=20, message="联系人手机2长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人手机2", align=2, sort=48)
	public String getAttnPhone2() {
		return attnPhone2;
	}

	public void setAttnPhone2(String attnPhone2) {
		this.attnPhone2 = attnPhone2;
	}
	
	@Length(min=0, max=20, message="联系人关系2长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人关系2", align=2, sort=49)
	public String getAttnRela2() {
		return attnRela2;
	}

	public void setAttnRela2(String attnRela2) {
		this.attnRela2 = attnRela2;
	}
	
	@Length(min=0, max=30, message="联系人姓名3长度必须介于 0 和 30 之间")
	@ExcelField(title="联系人姓名3", align=2, sort=50)
	public String getAttnName3() {
		return attnName3;
	}

	public void setAttnName3(String attnName3) {
		this.attnName3 = attnName3;
	}
	
	@Length(min=0, max=20, message="联系人手机3长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人手机3", align=2, sort=51)
	public String getAttnPhone3() {
		return attnPhone3;
	}

	public void setAttnPhone3(String attnPhone3) {
		this.attnPhone3 = attnPhone3;
	}
	
	@Length(min=0, max=20, message="联系人关系3长度必须介于 0 和 20 之间")
	@ExcelField(title="联系人关系3", align=2, sort=52)
	public String getAttnRela3() {
		return attnRela3;
	}

	public void setAttnRela3(String attnRela3) {
		this.attnRela3 = attnRela3;
	}
	
	@ExcelField(title="状态 ", align=2, sort=53)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	@ExcelField(title="数据来源", align=2, sort=54)
	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}
	
	@ExcelField(title="是否黑名单", align=2, sort=55)
	public Long getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Long isBlack) {
		this.isBlack = isBlack;
	}
	
	@ExcelField(title="是否死亡", align=2, sort=56)
	public Long getIsDead() {
		return isDead;
	}

	public void setIsDead(Long isDead) {
		this.isDead = isDead;
	}
	
	@ExcelField(title="创建人", align=2, sort=59)
	public User getCreateByName() {
		return createByName;
	}

	public void setCreateByName(User createByName) {
		this.createByName = createByName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="审核时间", align=2, sort=60)
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Length(min=0, max=20, message="审核人ID长度必须介于 0 和 20 之间")
	@ExcelField(title="审核人ID", align=2, sort=61)
	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	
	@Length(min=0, max=30, message="审核人长度必须介于 0 和 30 之间")
	@ExcelField(title="审核人", align=2, sort=62)
	public String getAuditByName() {
		return auditByName;
	}

	public void setAuditByName(String auditByName) {
		this.auditByName = auditByName;
	}
	
	@ExcelField(title="更新人", align=2, sort=65)
	public User getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(User updateByName) {
		this.updateByName = updateByName;
	}
	
	@ExcelField(title="身份证正反面上传标识：0-未上传 1-已上传", align=2, sort=66)
	public Long getFlag00() {
		return flag00;
	}

	public void setFlag00(Long flag00) {
		this.flag00 = flag00;
	}
	
	@ExcelField(title="flag01", align=2, sort=67)
	public Long getFlag01() {
		return flag01;
	}

	public void setFlag01(Long flag01) {
		this.flag01 = flag01;
	}
	
	@ExcelField(title="flag02", align=2, sort=68)
	public Long getFlag02() {
		return flag02;
	}

	public void setFlag02(Long flag02) {
		this.flag02 = flag02;
	}
	
	@ExcelField(title="flag03", align=2, sort=69)
	public Long getFlag03() {
		return flag03;
	}

	public void setFlag03(Long flag03) {
		this.flag03 = flag03;
	}
	
	@ExcelField(title="flag04", align=2, sort=70)
	public Long getFlag04() {
		return flag04;
	}

	public void setFlag04(Long flag04) {
		this.flag04 = flag04;
	}
	
	@ExcelField(title="flag05", align=2, sort=71)
	public Long getFlag05() {
		return flag05;
	}

	public void setFlag05(Long flag05) {
		this.flag05 = flag05;
	}
	
	@ExcelField(title="flag06", align=2, sort=72)
	public Long getFlag06() {
		return flag06;
	}

	public void setFlag06(Long flag06) {
		this.flag06 = flag06;
	}
	
	@ExcelField(title="flag07", align=2, sort=73)
	public Long getFlag07() {
		return flag07;
	}

	public void setFlag07(Long flag07) {
		this.flag07 = flag07;
	}
	
	@ExcelField(title="flag08", align=2, sort=74)
	public Long getFlag08() {
		return flag08;
	}

	public void setFlag08(Long flag08) {
		this.flag08 = flag08;
	}
	
	@ExcelField(title="flag09", align=2, sort=75)
	public Long getFlag09() {
		return flag09;
	}

	public void setFlag09(Long flag09) {
		this.flag09 = flag09;
	}
	
	@Length(min=0, max=250, message="身份证正面图片地址长度必须介于 0 和 250 之间")
	@ExcelField(title="身份证正面图片地址", align=2, sort=76)
	public String getUrl0() {
		return url0;
	}

	public void setUrl0(String url0) {
		this.url0 = url0;
	}
	
	@Length(min=0, max=250, message="身份证背面图片地址长度必须介于 0 和 250 之间")
	@ExcelField(title="身份证背面图片地址", align=2, sort=77)
	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	
	@Length(min=0, max=250, message="url2长度必须介于 0 和 250 之间")
	@ExcelField(title="url2", align=2, sort=78)
	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	
	@Length(min=0, max=250, message="url3长度必须介于 0 和 250 之间")
	@ExcelField(title="url3", align=2, sort=79)
	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	
	@Length(min=0, max=250, message="url4长度必须介于 0 和 250 之间")
	@ExcelField(title="url4", align=2, sort=80)
	public String getUrl4() {
		return url4;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}
	
	@Length(min=0, max=250, message="url5长度必须介于 0 和 250 之间")
	@ExcelField(title="url5", align=2, sort=81)
	public String getUrl5() {
		return url5;
	}

	public void setUrl5(String url5) {
		this.url5 = url5;
	}
	
	@Length(min=0, max=250, message="url6长度必须介于 0 和 250 之间")
	@ExcelField(title="url6", align=2, sort=82)
	public String getUrl6() {
		return url6;
	}

	public void setUrl6(String url6) {
		this.url6 = url6;
	}
	
	@Length(min=0, max=250, message="url7长度必须介于 0 和 250 之间")
	@ExcelField(title="url7", align=2, sort=83)
	public String getUrl7() {
		return url7;
	}

	public void setUrl7(String url7) {
		this.url7 = url7;
	}
	
	@Length(min=0, max=250, message="url8长度必须介于 0 和 250 之间")
	@ExcelField(title="url8", align=2, sort=84)
	public String getUrl8() {
		return url8;
	}

	public void setUrl8(String url8) {
		this.url8 = url8;
	}
	
	@Length(min=0, max=100, message="str00长度必须介于 0 和 100 之间")
	@ExcelField(title="str00", align=2, sort=85)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}
	
	@Length(min=0, max=100, message="str01长度必须介于 0 和 100 之间")
	@ExcelField(title="str01", align=2, sort=86)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}
	
	@Length(min=0, max=100, message="str02长度必须介于 0 和 100 之间")
	@ExcelField(title="str02", align=2, sort=87)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}
	
	@Length(min=0, max=100, message="str03长度必须介于 0 和 100 之间")
	@ExcelField(title="str03", align=2, sort=88)
	public String getStr03() {
		return str03;
	}

	public void setStr03(String str03) {
		this.str03 = str03;
	}
	
	@Length(min=0, max=100, message="str04长度必须介于 0 和 100 之间")
	@ExcelField(title="str04", align=2, sort=89)
	public String getStr04() {
		return str04;
	}

	public void setStr04(String str04) {
		this.str04 = str04;
	}
	
	@Length(min=0, max=100, message="str05长度必须介于 0 和 100 之间")
	@ExcelField(title="str05", align=2, sort=90)
	public String getStr05() {
		return str05;
	}

	public void setStr05(String str05) {
		this.str05 = str05;
	}
	
	@Length(min=0, max=100, message="str06长度必须介于 0 和 100 之间")
	@ExcelField(title="str06", align=2, sort=91)
	public String getStr06() {
		return str06;
	}

	public void setStr06(String str06) {
		this.str06 = str06;
	}
	
	@Length(min=0, max=100, message="str07长度必须介于 0 和 100 之间")
	@ExcelField(title="str07", align=2, sort=92)
	public String getStr07() {
		return str07;
	}

	public void setStr07(String str07) {
		this.str07 = str07;
	}
	
	@Length(min=0, max=100, message="str08长度必须介于 0 和 100 之间")
	@ExcelField(title="str08", align=2, sort=93)
	public String getStr08() {
		return str08;
	}

	public void setStr08(String str08) {
		this.str08 = str08;
	}
	
	@Length(min=0, max=100, message="str09长度必须介于 0 和 100 之间")
	@ExcelField(title="str09", align=2, sort=94)
	public String getStr09() {
		return str09;
	}

	public void setStr09(String str09) {
		this.str09 = str09;
	}
	
	@Length(min=0, max=100, message="str10长度必须介于 0 和 100 之间")
	@ExcelField(title="str10", align=2, sort=95)
	public String getStr10() {
		return str10;
	}

	public void setStr10(String str10) {
		this.str10 = str10;
	}
	
	@Length(min=0, max=30, message="opt00长度必须介于 0 和 30 之间")
	@ExcelField(title="opt00", align=2, sort=96)
	public String getOpt00() {
		return opt00;
	}

	public void setOpt00(String opt00) {
		this.opt00 = opt00;
	}
	
	@Length(min=0, max=30, message="opt01长度必须介于 0 和 30 之间")
	@ExcelField(title="opt01", align=2, sort=97)
	public String getOpt01() {
		return opt01;
	}

	public void setOpt01(String opt01) {
		this.opt01 = opt01;
	}
	
	@Length(min=0, max=30, message="opt02长度必须介于 0 和 30 之间")
	@ExcelField(title="opt02", align=2, sort=98)
	public String getOpt02() {
		return opt02;
	}

	public void setOpt02(String opt02) {
		this.opt02 = opt02;
	}
	
	@Length(min=0, max=30, message="opt03长度必须介于 0 和 30 之间")
	@ExcelField(title="opt03", align=2, sort=99)
	public String getOpt03() {
		return opt03;
	}

	public void setOpt03(String opt03) {
		this.opt03 = opt03;
	}
	
	@Length(min=0, max=30, message="opt04长度必须介于 0 和 30 之间")
	@ExcelField(title="opt04", align=2, sort=100)
	public String getOpt04() {
		return opt04;
	}

	public void setOpt04(String opt04) {
		this.opt04 = opt04;
	}
	
	@Length(min=0, max=30, message="opt05长度必须介于 0 和 30 之间")
	@ExcelField(title="opt05", align=2, sort=101)
	public String getOpt05() {
		return opt05;
	}

	public void setOpt05(String opt05) {
		this.opt05 = opt05;
	}
	
	@Length(min=0, max=300, message="备注长度必须介于 0 和 300 之间")
	@ExcelField(title="备注", align=2, sort=102)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=2, message="删除标记（0：正常；1：删除；）长度必须介于 0 和 2 之间")
	@ExcelField(title="删除标记（0：正常；1：删除；）", align=2, sort=103)
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Area getArea2() {
		return area2;
	}

	public void setArea2(Area area2) {
		this.area2 = area2;
	}
	
	@Override
	public boolean getIsNewRecord() {
		return isNewRecord || StringUtils.isBlank(getIdentityId());
	}

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
}