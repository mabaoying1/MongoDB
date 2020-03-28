/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**
 * 接口地址管理Entity
 * @author gyp
 * @version 2016-06-16
 */
public class HpIfaceAddress extends DataEntity<HpIfaceAddress> {

	private static final long serialVersionUID = 9118019405989792514L;
	private String pkid;		// 主键
	private String merId;		// 商户号
	private String funcId;		// 功能码
	private String funcName;		// 接口名字
	private String funcType;		// 通信方式：1-http 2- webservice 3-socekt
	private String funcAddress;		// 接口地址
	private String dataFormat;		// 数据格式：1- json 2- xml 
	private Integer flag00;		// 0 - 内部接口 1- 外部接口
	private Integer flag01;		// flag01
	private Integer flag02;		// flag02
	private String str00;		// str00
	private String str01;		// str01
	private String str02;		// str02
	private String remark;		// 备注
	private HpIfaceMerchant hpIfaceMerchant;

	public HpIfaceMerchant getHpIfaceMerchant() {
		return hpIfaceMerchant;
	}

	public void setHpIfaceMerchant(HpIfaceMerchant hpIfaceMerchant) {
		this.hpIfaceMerchant = hpIfaceMerchant;
	}

	public HpIfaceAddress() {
		super();
	}

	public HpIfaceAddress(String id){
		super(id);
	}

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
	
	@Length(min=0, max=5, message="功能码长度必须介于 0 和 5 之间")
	@ExcelField(title="功能码", align=2, sort=2)
	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	@Length(min=0, max=10, message="接口名字长度必须介于 0 和 10 之间")
	@ExcelField(title="接口名字", align=2, sort=3)
	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	
	@Length(min=0, max=2, message="通信方式：1-http 2- webservice 3-socekt长度必须介于 0 和 2 之间")
	@ExcelField(title="通信方式：1-http 2- webservice 3-socekt", dictType="", align=2, sort=4)
	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
	
	@Length(min=0, max=50, message="接口地址长度必须介于 0 和 50 之间")
	@ExcelField(title="接口地址", align=2, sort=5)
	public String getFuncAddress() {
		return funcAddress;
	}

	public void setFuncAddress(String funcAddress) {
		this.funcAddress = funcAddress;
	}
	
	@Length(min=0, max=2, message="数据格式：1- json 2- xml 长度必须介于 0 和 2 之间")
	@ExcelField(title="数据格式：1- json 2- xml ", align=2, sort=6)
	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
	@ExcelField(title="0 - 内部接口 1- 外部接口", align=2, sort=7)
	public Integer getFlag00() {
		return flag00;
	}

	public void setFlag00(Integer flag00) {
		this.flag00 = flag00;
	}
	
	@ExcelField(title="flag01", align=2, sort=8)
	public Integer getFlag01() {
		return flag01;
	}

	public void setFlag01(Integer flag01) {
		this.flag01 = flag01;
	}
	
	@ExcelField(title="flag02", align=2, sort=9)
	public Integer getFlag02() {
		return flag02;
	}

	public void setFlag02(Integer flag02) {
		this.flag02 = flag02;
	}
	
	@Length(min=0, max=300, message="str00长度必须介于 0 和 300 之间")
	@ExcelField(title="str00", align=2, sort=10)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}
	
	@Length(min=0, max=300, message="str01长度必须介于 0 和 300 之间")
	@ExcelField(title="str01", align=2, sort=11)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}
	
	@Length(min=0, max=300, message="str02长度必须介于 0 和 300 之间")
	@ExcelField(title="str02", align=2, sort=12)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}
	
	@Length(min=0, max=300, message="备注长度必须介于 0 和 300 之间")
	@ExcelField(title="备注", align=2, sort=17)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}