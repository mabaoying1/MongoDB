/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 发送队列管理Entity
 * @author gyp
 * @version 2016-06-16
 */
public class HpIfaceMsgqueueBak extends DataEntity<HpIfaceMsgqueueBak> {

	private static final long serialVersionUID = 1L;
	private String pkid;		// pkid
	private String merId;		// 商户号
	private String posturl;		// 发送地址
	private String funcid;		// 功能码
	private String healthcardid;		// 健康卡编码
	private String nationality;		// 国籍/地区
	private Integer docutype;		// 证件类型
	private String docuid;		// 证件号码
	private String param0;		// 其他参数
	private String param1;		// 其他参数
	private String param2;		// param2
	private Integer flag0;		// flag0
	private Integer flag1;		// flag1
	private Integer flag2;		// flag2
	private String icCardId;    //实体卡卡号
	private Integer type;       //类型
	private Integer optType;    //操作类型
	private Date createtime;		// 创建时间
	private Date lastsendtime;		// 最后一次发送时间

	public HpIfaceMsgqueueBak() {
		super();
	}

	public HpIfaceMsgqueueBak(String id){
		super(id);
	}

	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	@Length(min=0, max=10, message="商户号长度必须介于 0 和 10 之间")
	@ExcelField(title="商户号", align=2, sort=1)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	@Length(min=0, max=60, message="发送地址长度必须介于 0 和 60 之间")
	@ExcelField(title="发送地址", align=2, sort=2)
	public String getPosturl() {
		return posturl;
	}

	public void setPosturl(String posturl) {
		this.posturl = posturl;
	}
	
	@Length(min=0, max=30, message="功能码长度必须介于 0 和 30 之间")
	@ExcelField(title="功能码", align=2, sort=3)
	public String getFuncid() {
		return funcid;
	}

	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}
	
	@Length(min=0, max=30, message="健康卡编码长度必须介于 0 和 30 之间")
	@ExcelField(title="健康卡编码", align=2, sort=4)
	public String getHealthcardid() {
		return healthcardid;
	}

	public void setHealthcardid(String healthcardid) {
		this.healthcardid = healthcardid;
	}
	
	@Length(min=0, max=3, message="国籍/地区长度必须介于 0 和 3 之间")
	@ExcelField(title="国籍/地区", align=2, sort=5)
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@ExcelField(title="证件类型", align=2, sort=6)
	public Integer getDocutype() {
		return docutype;
	}

	public void setDocutype(Integer docutype) {
		this.docutype = docutype;
	}
	
	@Length(min=0, max=11, message="证件号码长度必须介于 0 和 11 之间")
	@ExcelField(title="证件号码", align=2, sort=7)
	public String getDocuid() {
		return docuid;
	}

	public void setDocuid(String docuid) {
		this.docuid = docuid;
	}
	
	@Length(min=0, max=60, message="其他参数长度必须介于 0 和 60 之间")
	@ExcelField(title="其他参数", align=2, sort=8)
	public String getParam0() {
		return param0;
	}

	public void setParam0(String param0) {
		this.param0 = param0;
	}
	
	@Length(min=0, max=60, message="其他参数长度必须介于 0 和 60 之间")
	@ExcelField(title="其他参数", align=2, sort=9)
	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}
	
	@Length(min=0, max=60, message="param2长度必须介于 0 和 60 之间")
	@ExcelField(title="param2", align=2, sort=10)
	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}
	
	@ExcelField(title="flag0", align=2, sort=11)
	public Integer getFlag0() {
		return flag0;
	}

	public void setFlag0(Integer flag0) {
		this.flag0 = flag0;
	}
	
	@ExcelField(title="flag1", align=2, sort=12)
	public Integer getFlag1() {
		return flag1;
	}

	public void setFlag1(Integer flag1) {
		this.flag1 = flag1;
	}
	
	@ExcelField(title="flag2", align=2, sort=13)
	public Integer getFlag2() {
		return flag2;
	}

	public void setFlag2(Integer flag2) {
		this.flag2 = flag2;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", align=2, sort=14)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="最后一次发送时间", align=2, sort=15)
	public Date getLastsendtime() {
		return lastsendtime;
	}

	public void setLastsendtime(Date lastsendtime) {
		this.lastsendtime = lastsendtime;
	}

	public String getIcCardId() {
		return icCardId;
	}

	public void setIcCardId(String icCardId) {
		this.icCardId = icCardId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

	@Override
	public boolean getIsNewRecord() {
		return isNewRecord || null == pkid;
	}
}