package com.healthpay.modules.iface.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**.healthpay.ctionName}Entity
 * @author gaoyp
 * @version 2016-07-29
 */
public class HpIfacePlatformarea extends DataEntity<HpIfacePlatformarea> {
	
	private static final long serialVersionUID = 1L;
	private String pkid;		// pkid
	private String areaid;
	private String code;		// 区域代码
	private String name;		// 区域名称
	private String platformid;		// 平台ID
	private Integer platformtype;		// 平台级别:1 市级 2 县级
	
	public HpIfacePlatformarea() {
		super();
	}

	public HpIfacePlatformarea(String id){
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
	
	@Length(min=0, max=20, message="区域代码长度必须介于 0 和 20 之间")
	@ExcelField(title="区域代码", align=2, sort=1)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=30, message="区域名称长度必须介于 0 和 30 之间")
	@ExcelField(title="区域名称", align=2, sort=2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="平台ID", align=2, sort=3)
	public String getPlatformid() {
		return platformid;
	}

	public void setPlatformid(String platformid) {
		this.platformid = platformid;
	}
	
	@ExcelField(title="平台级别:1 市级 2 县级", align=2, sort=4)
	public Integer getPlatformtype() {
		return platformtype;
	}

	public void setPlatformtype(Integer platformtype) {
		this.platformtype = platformtype;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
}