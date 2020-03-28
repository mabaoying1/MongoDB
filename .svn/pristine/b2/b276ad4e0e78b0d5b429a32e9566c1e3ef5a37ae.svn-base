package com.healthpay.modules.iface.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**.healthpay.ctionName}Entity
 * @author gaoyp
 * @version 2016-07-29
 */
public class HpIfaceCardasyncBak extends DataEntity<HpIfaceCardasyncBak> {

	private static final long serialVersionUID = 1L;
	private String pkid;		// pkid
	private String appid;      //功能码
	private String password;    //与外部平台约定字符串
	private String iDType;		// 证件类型
	private String iDCardNum;		// 证件号码
	private String name;		// 姓名
	private String phone;		// 电话
	private String healthECardNum;		// 健康E卡号
	private String cardType;		// 实体卡类型
	private String cardNum;		// 实体卡号
	private Integer gender;		// 性别
	private Date dateOfBirth;		// 生日
	private Long extid;		// extid
	private String url;		// url
	private Date date0;		// date0
	private Date date1;		// date1

	public HpIfaceCardasyncBak() {
		super();
	}

	public HpIfaceCardasyncBak(String id){
		super(id);
	}

	@NotNull(message="pkid不能为空")
	@ExcelField(title="pkid", align=2, sort=0)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	@Length(min=0, max=2, message="证件类型长度必须介于 0 和 2 之间")
	@ExcelField(title="证件类型", align=2, sort=1)
	public String getIDType() {
		return iDType;
	}

	public void setIDType(String iDType) {
		this.iDType = iDType;
	}

	@Length(min=0, max=30, message="证件号码长度必须介于 0 和 30 之间")
	@ExcelField(title="证件号码", align=2, sort=2)
	public String getIDCardNum() {
		return iDCardNum;
	}

	public void setIDCardNum(String iDCardNum) {
		this.iDCardNum = iDCardNum;
	}

	@Length(min=0, max=60, message="姓名长度必须介于 0 和 60 之间")
	@ExcelField(title="姓名", align=2, sort=3)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=30, message="电话长度必须介于 0 和 30 之间")
	@ExcelField(title="电话", align=2, sort=4)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=30, message="健康E卡号长度必须介于 0 和 30 之间")
	@ExcelField(title="健康E卡号", align=2, sort=5)
	public String getHealthECardNum() {
		return healthECardNum;
	}

	public void setHealthECardNum(String healthECardNum) {
		this.healthECardNum = healthECardNum;
	}

	@Length(min=0, max=2, message="实体卡类型长度必须介于 0 和 2 之间")
	@ExcelField(title="实体卡类型", align=2, sort=6)
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Length(min=0, max=30, message="实体卡号长度必须介于 0 和 30 之间")
	@ExcelField(title="实体卡号", align=2, sort=7)
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@ExcelField(title="性别", align=2, sort=8)
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="生日", align=2, sort=9)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@ExcelField(title="extid", align=2, sort=10)
	public Long getExtid() {
		return extid;
	}

	public void setExtid(Long extid) {
		this.extid = extid;
	}

	@Length(min=0, max=200, message="url长度必须介于 0 和 200 之间")
	@ExcelField(title="url", align=2, sort=11)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="date0", align=2, sort=16)
	public Date getDate0() {
		return date0;
	}

	public void setDate0(Date date0) {
		this.date0 = date0;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="date1", align=2, sort=17)
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getiDType() {
		return iDType;
	}

	public void setiDType(String iDType) {
		this.iDType = iDType;
	}

	public String getiDCardNum() {
		return iDCardNum;
	}

	public void setiDCardNum(String iDCardNum) {
		this.iDCardNum = iDCardNum;
	}
}