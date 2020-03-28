package com.healthpay.modules.iface.entity;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.healthpay.modules.sys.entity.Office;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

import java.util.List;

/**.healthpay.ctionName}Entity
 * @author gaoyp
 * @version 2016-07-29
 */
public class HpIfacePlatform extends DataEntity<HpIfacePlatform> {
	
	private static final long serialVersionUID = 1L;
	private String pkid;		// pkid
	private String name;		// 平台名称
	private String funcCode;		// 功能代码
	private String scretkey;		// 功能密钥
	private String posturl;		// 传送地址
	private String datatype;		// 报文类型 如：xml,json
	private String urltype;		// 地址类型 如：HTTP,WEBSERVICE
	private Integer platformtype;		// 平台级别：1 市级 2 县级
	private Integer flag0;		// flag0
	private Integer flag1;		// flag1
	private Integer flag2;		// flag2
	private String param0;		// param0
	private String param1;		// param1
	private String param2;		// param2

	private List<HpIfacePlatformarea> hpIfacePlatformareaList = Lists.newArrayList();
	
	public HpIfacePlatform() {
		super();
	}

	public HpIfacePlatform(String id){
		super(id);
	}



	@ExcelField(title="pkid", align=2, sort=0)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	@Length(min=0, max=30, message="平台名称长度必须介于 0 和 30 之间")
	@ExcelField(title="平台名称", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=30, message="功能代码长度必须介于 0 和 30 之间")
	@ExcelField(title="功能代码", align=2, sort=2)
	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	
	@Length(min=0, max=30, message="功能密钥长度必须介于 0 和 30 之间")
	@ExcelField(title="功能密钥", align=2, sort=3)
	public String getScretkey() {
		return scretkey;
	}

	public void setScretkey(String scretkey) {
		this.scretkey = scretkey;
	}
	
	@Length(min=0, max=200, message="传送地址长度必须介于 0 和 200 之间")
	@ExcelField(title="传送地址", align=2, sort=4)
	public String getPosturl() {
		return posturl;
	}

	public void setPosturl(String posturl) {
		this.posturl = posturl;
	}
	
	@Length(min=0, max=20, message="报文类型 如：xml,json长度必须介于 0 和 20 之间")
	@ExcelField(title="报文类型 如：xml,json", align=2, sort=5)
	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	@Length(min=0, max=20, message="地址类型 如：HTTP,WEBSERVICE长度必须介于 0 和 20 之间")
	@ExcelField(title="地址类型 如：HTTP,WEBSERVICE", align=2, sort=6)
	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}
	
	@ExcelField(title="平台级别：1 市级 2 县级", align=2, sort=7)
	public Integer getPlatformtype() {
		return platformtype;
	}

	public void setPlatformtype(Integer platformtype) {
		this.platformtype = platformtype;
	}
	
	@ExcelField(title="flag0", align=2, sort=8)
	public Integer getFlag0() {
		return flag0;
	}

	public void setFlag0(Integer flag0) {
		this.flag0 = flag0;
	}
	
	@ExcelField(title="flag1", align=2, sort=9)
	public Integer getFlag1() {
		return flag1;
	}

	public void setFlag1(Integer flag1) {
		this.flag1 = flag1;
	}
	
	@ExcelField(title="flag2", align=2, sort=10)
	public Integer getFlag2() {
		return flag2;
	}

	public void setFlag2(Integer flag2) {
		this.flag2 = flag2;
	}
	
	@Length(min=0, max=60, message="param0长度必须介于 0 和 60 之间")
	@ExcelField(title="param0", align=2, sort=11)
	public String getParam0() {
		return param0;
	}

	public void setParam0(String param0) {
		this.param0 = param0;
	}
	
	@Length(min=0, max=60, message="param1长度必须介于 0 和 60 之间")
	@ExcelField(title="param1", align=2, sort=12)
	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}
	
	@Length(min=0, max=60, message="param2长度必须介于 0 和 60 之间")
	@ExcelField(title="param2", align=2, sort=13)
	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	@Override
	public boolean getIsNewRecord() {
		return this.isNewRecord || null == pkid;
	}

	public List<HpIfacePlatformarea> getHpIfacePlatformareaList() {
		return hpIfacePlatformareaList;
	}

	public void setHpIfacePlatformareaList(List<HpIfacePlatformarea> hpIfacePlatformareaList) {
		this.hpIfacePlatformareaList = hpIfacePlatformareaList;
	}

	public String getAreaIds() {
		return StringUtils.join(getAreaIdList(), ",");
	}

	public void setAreaIds(String areaIds) {
		hpIfacePlatformareaList = Lists.newArrayList();
		if (areaIds != null){
			String[] ids = StringUtils.split(areaIds, ",");
			setAreaIdList(Lists.newArrayList(ids));
		}
	}

	public List<String> getAreaIdList() {
		List<String> areaIdList = Lists.newArrayList();
		for (HpIfacePlatformarea hpIfacePlatformarea : hpIfacePlatformareaList) {
			areaIdList.add(hpIfacePlatformarea.getAreaid());
		}
		return areaIdList;
	}

	public void setAreaIdList(List<String> areaIdList) {
		hpIfacePlatformareaList = Lists.newArrayList();
		for (String areaId : areaIdList) {
			HpIfacePlatformarea hpIfacePlatformarea = new HpIfacePlatformarea();
			hpIfacePlatformarea.setAreaid(areaId);
			hpIfacePlatformareaList.add(hpIfacePlatformarea);
		}
	}
}