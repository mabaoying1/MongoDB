package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author mabaoying
 * @ClassName: ResResultHealthCardVo
 * @Description: 返回电子健康卡信息
 * @date: 2019/8/14
 * @最后修改人:
 * @最后修改时间:
 */
public class ResResultHealthCardVo {

	private String healthCardId;//电子健康卡id
	private String mindexId;//居民健康卡主索引ID
	private String docuType;// 证件类型
	private String docuId;//证件号码
	private String name;//姓名
	private String sex;//性别
	private String nation;//民族
	private String nationality ;//国籍
	private String address;//地址（户籍地）
	private Date birth;//出生日期 日期格式：yyyyMMdd
	private String phone;//本人手机
	private String tel;//本人电话
	private String address2;//地址（居住地）
	private String profession;//职业
	private String MaritalStatus;//婚姻状况
	private String status;
	private String reason;

	public String getHealthCardId() {
		return healthCardId;
	}

	public void setHealthCardId(String healthCardId) {
		this.healthCardId = healthCardId;
	}

	public String getMindexId() {
		return mindexId;
	}

	public void setMindexId(String mindexId) {
		this.mindexId = mindexId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getMaritalStatus() {
		return MaritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
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
}
