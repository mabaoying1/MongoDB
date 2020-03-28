/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.entity;

import javax.validation.constraints.NotNull;

import com.healthpay.common.utils.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**
 * 接口日志管理Entity
 * @author gyp
 * @version 2016-06-20
 */
public class HpIfaceLog extends DataEntity<HpIfaceLog> {
	
	private static final long serialVersionUID = 1L;
	private String pkid;		// pkid
	private String merId;		// 商户号
	private String funcId;		// 功能码
	private Date createTime;		// 接收时间
	private String senddata;		// 发送报文原文
	private String errmsg;		// 返回错误消息
	private String retcode;		// 结果
	private String retdata;		// 返回报文
	private String ref0;		// ref0
	private String ref1;		// ref1
	private String ref2;		// ref2
	private Integer flag0;		// flag0
	private Integer flag1;		// flag1
	private Integer flag2;		// flag2
	
	public HpIfaceLog() {
		super();
	}

	public HpIfaceLog(String id){
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
	
	@Length(min=0, max=5, message="商户号长度必须介于 0 和 5 之间")
	@ExcelField(title="商户号", align=2, sort=1)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	@Length(min=0, max=30, message="功能码长度必须介于 0 和 30 之间")
	@ExcelField(title="功能码", align=2, sort=2)
	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="接收时间", align=2, sort=3)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ExcelField(title="发送报文原文", align=2, sort=4)
	public String getSenddata() {
		return senddata;
	}

	public void setSenddata(String senddata) {
		this.senddata = senddata;
	}
	
	@Length(min=0, max=100, message="返回错误消息长度必须介于 0 和 100 之间")
	@ExcelField(title="返回错误消息", align=2, sort=5)
	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	@Length(min=0, max=30, message="结果长度必须介于 0 和 30 之间")
	@ExcelField(title="结果", align=2, sort=6)
	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	
	@ExcelField(title="返回报文", align=2, sort=7)
	public String getRetdata() {
		return retdata;
	}

	public void setRetdata(String retdata) {
		this.retdata = retdata;
	}
	
	@Length(min=0, max=100, message="ref0长度必须介于 0 和 100 之间")
	@ExcelField(title="ref0", align=2, sort=8)
	public String getRef0() {
		return ref0;
	}

	public void setRef0(String ref0) {
		this.ref0 = ref0;
	}
	
	@Length(min=0, max=100, message="ref1长度必须介于 0 和 100 之间")
	@ExcelField(title="ref1", align=2, sort=9)
	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	
	@Length(min=0, max=100, message="ref2长度必须介于 0 和 100 之间")
	@ExcelField(title="ref2", align=2, sort=10)
	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
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

	@Override
	public boolean getIsNewRecord() {
		return isNewRecord || null == pkid;
	}
}