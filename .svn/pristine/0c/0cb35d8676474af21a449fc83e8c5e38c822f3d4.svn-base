package com.healthpay.iface.vo;

import com.healthpay.common.annotation.NotNull;

public class QueryIdVo{
	
	private String nationality ; //国籍
	private String docuType;		// 证件类型
	private String docuId;		//证件号码
	
	public QueryIdVo(){}

	public QueryIdVo(String nationality,String docuType,String docuId){
		this.nationality=nationality;
		this.docuType=docuType;
		this.docuId=docuId;
	}

	@NotNull(name="国籍/地区")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@NotNull(name="证件类型")
	public String getDocuType() {
		return docuType;
	}

	public void setDocuType(String docuType) {
		this.docuType = docuType;
	}
	@NotNull(name="证件号码")
	public String getDocuId() {
		return docuId;
	}

	public void setDocuId(String docuId) {
		this.docuId = docuId;
	}
}
