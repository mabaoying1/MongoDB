/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.test.entity.onetomany;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import com.healthpay.modules.sys.entity.Area;

/**
 * 票务代理Entity
 * @author liugf
 * @version 2016-05-06
 */
public class TestDataChild3 extends DataEntity<TestDataChild3> {
	
	private static final long serialVersionUID = 1L;
	private Area startArea;		// 出发地
	private Area endArea;		// 目的地
	private Double price;		// 代理价格
	private TestDataMain testDataMain;		// 外键 父类
	
	public TestDataChild3() {
		super();
	}

	public TestDataChild3(String id){
		super(id);
	}

	public TestDataChild3(TestDataMain testDataMain){
		this.testDataMain = testDataMain;
	}

	@NotNull(message="出发地不能为空")
	@ExcelField(title="出发地", fieldType=Area.class, value="startArea.name", align=2, sort=1)
	public Area getStartArea() {
		return startArea;
	}

	public void setStartArea(Area startArea) {
		this.startArea = startArea;
	}
	
	@NotNull(message="目的地不能为空")
	@ExcelField(title="目的地", fieldType=Area.class, value="endArea.name", align=2, sort=2)
	public Area getEndArea() {
		return endArea;
	}

	public void setEndArea(Area endArea) {
		this.endArea = endArea;
	}
	
	@ExcelField(title="代理价格", align=2, sort=3)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Length(min=1, max=64, message="外键长度必须介于 1 和 64 之间")
	public TestDataMain getTestDataMain() {
		return testDataMain;
	}

	public void setTestDataMain(TestDataMain testDataMain) {
		this.testDataMain = testDataMain;
	}
	
}