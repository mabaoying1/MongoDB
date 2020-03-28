/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.entity;


import com.healthpay.common.utils.StringUtils;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import com.healthpay.modules.sys.entity.Area;

/**
 * 商户管理Entity
 * @author gyp
 * @version 2016-06-14
 */
public class HpIfaceMerchant extends DataEntity<HpIfaceMerchant> {
	
	private static final long serialVersionUID = 1L;
	private String merId;		// 商户号
	private String merName;		// 机构名称
	private String merEname;		// 机构英文名称
	private String merAbbrName;		// mer_abbr_name
	private String merAbbrEname;		// mer_abbr_ename
	private String status;		// status
	private String merType;		// mer_type
	private String digitalKey;  //数字证书码
	private String privateKey;  //私钥
	private String publicKey;   //公钥
	private Integer source;     //数据来源
	private boolean isAutoAudit; //是否自动审核
	private Integer flag02;		// flag02
	private Integer flag01;		// flag01
	private Integer flag00;		// flag00
	private String str02;		// str02
	private String str01;		// str01
	private String str00;		// str00
	private String remark;		// remark
	private String orgCode;//机构编码
	private String orgProperty;//机构性质
	private String orgIp;//接入ip
	private String accessMethod;//接入方式
	private Date validTime;//有效时间
	private Area orgAddr;//机构行政区划
	private String orgLevel;//机构等级
	private String orgHierarchy;//机构等次
	
	
	@Length(min=1, max=20, message="机构编码长度必须介于 0 和 20 之间")
	@ExcelField(title="org_code", align=2, sort=20)
	public String getOrgCode() {
		return orgCode;
	}

	@Length(min=1, max=4, message="机构性质长度必须介于 0 和4 之间")
	@ExcelField(title="org_property", align=2, sort=21)
	public String getOrgProperty() {
		return orgProperty;
	}

	@Length(min=1, max=20, message="接入ip地址长度必须介于 0 和20 之间")
	@ExcelField(title="org_ip", align=2, sort=22)
	public String getOrgIp() {
		return orgIp;
	}

	@Length(min=1, max=32, message="接入方式长度必须介于 0 和32 之间")
	@ExcelField(title="access_method", align=2, sort=23)
	public String getAccessMethod() {
		return accessMethod;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="出生日期不能为空")
	@ExcelField(title="有效日期", align=2, sort=24)
	public Date getValidTime() {
		return validTime;
	}

	public Area getOrgAddr() {
		return orgAddr;
	}

	@Length(min=1, max=2, message="机构等级长度必须介于 0 和2 之间")
	@ExcelField(title="org_level", align=2, sort=26)
	public String getOrgLevel() {
		return orgLevel;
	}

	@Length(min=1, max=2, message="机构等次长度必须介于 0 和2 之间")
	@ExcelField(title="org_hierarchy", align=2, sort=27)
	public String getOrgHierarchy() {
		return orgHierarchy;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOrgProperty(String orgProperty) {
		this.orgProperty = orgProperty;
	}

	public void setOrgIp(String orgIp) {
		this.orgIp = orgIp;
	}

	public void setAccessMethod(String accessMethod) {
		this.accessMethod = accessMethod;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public void setOrgAddr(Area orgAddr) {
		this.orgAddr = orgAddr;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public void setOrgHierarchy(String orgHierarchy) {
		this.orgHierarchy = orgHierarchy;
	}

	public HpIfaceMerchant() {
		super();
	}

	public HpIfaceMerchant(String id){
		super(id);
	}

	@ExcelField(title="mer_id", align=2, sort=0)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	@Length(min=1, max=100, message="商户名称长度必须介于 0 和 100 之间")
	@ExcelField(title="mer_name", align=2, sort=1)
	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}
	
	@Length(min=0, max=100, message="英文名称长度必须介于 0 和 100 之间")
	@ExcelField(title="mer_ename", align=2, sort=2)
	public String getMerEname() {
		return merEname;
	}

	public void setMerEname(String merEname) {
		this.merEname = merEname;
	}
	
	@Length(min=0, max=50, message="中文简称长度必须介于 0 和 50 之间")
	@ExcelField(title="mer_abbr_name", align=2, sort=3)
	public String getMerAbbrName() {
		return merAbbrName;
	}

	public void setMerAbbrName(String merAbbrName) {
		this.merAbbrName = merAbbrName;
	}
	
	@Length(min=0, max=50, message="英文简称长度必须介于 0 和 50 之间")
	@ExcelField(title="mer_abbr_ename", align=2, sort=4)
	public String getMerAbbrEname() {
		return merAbbrEname;
	}

	public void setMerAbbrEname(String merAbbrEname) {
		this.merAbbrEname = merAbbrEname;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	@ExcelField(title="status", align=2, sort=5)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="商户类型长度必须介于 0 和 1 之间")
	@ExcelField(title="mer_type", align=2, sort=6)
	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}
	
	@ExcelField(title="flag02", align=2, sort=7)
	public Integer getFlag02() {
		return flag02;
	}

	public void setFlag02( Integer flag02) {
		this.flag02 = flag02;
	}
	
	@ExcelField(title="flag01", align=2, sort=8)
	public Integer getFlag01() {
		return flag01;
	}

	public void setFlag01(Integer flag01) {
		this.flag01 = flag01;
	}
	
	@ExcelField(title="flag00", align=2, sort=9)
	public  Integer getFlag00() {
		return flag00;
	}

	public void setFlag00(Integer flag00) {
		this.flag00 = flag00;
	}

	@ExcelField(title="str02", align=2, sort=10)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}

	@ExcelField(title="str01", align=2, sort=11)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}

	@ExcelField(title="str00", align=2, sort=12)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}

	@ExcelField(title="remark", align=2, sort=17)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public boolean getIsNewRecord() {
		return isNewRecord || StringUtils.isEmpty(merId);
	}

	public String getDigitalKey() {
		return digitalKey;
	}

	public void setDigitalKey(String digitalKey) {
		this.digitalKey = digitalKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public boolean getIsAutoAudit() {
		return isAutoAudit;
	}

	public void setIsAutoAudit(boolean isAutoAudit) {
		this.isAutoAudit = isAutoAudit;
	}

	@Override
	public String toString() {
		return "HpIfaceMerchant [merId=" + merId + ", merName=" + merName + ", merEname=" + merEname + ", merAbbrName="
				+ merAbbrName + ", merAbbrEname=" + merAbbrEname + ", status=" + status + ", merType=" + merType
				+ ", digitalKey=" + digitalKey + ", privateKey=" + privateKey + ", publicKey=" + publicKey + ", source="
				+ source + ", isAutoAudit=" + isAutoAudit + ", flag02=" + flag02 + ", flag01=" + flag01 + ", flag00="
				+ flag00 + ", str02=" + str02 + ", str01=" + str01 + ", str00=" + str00 + ", remark=" + remark
				+ ", orgCode=" + orgCode + ", orgProperty=" + orgProperty + ", orgIp=" + orgIp + ", accessMethod="
				+ accessMethod + ", validTime=" + validTime + ", orgAddr=" + orgAddr + ", orgLevel=" + orgLevel
				+ ", orgHierarchy=" + orgHierarchy + "]";
	}
	
}