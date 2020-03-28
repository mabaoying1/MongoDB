/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.test.entity.grid;

import org.hibernate.validator.constraints.Length;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**
 * 商品分类Entity
 * @author liugf
 * @version 2016-05-06
 */
public class Category extends DataEntity<Category> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 类型名
	
	public Category() {
		super();
	}

	public Category(String id){
		super(id);
	}

	@Length(min=0, max=64, message="类型名长度必须介于 0 和 64 之间")
	@ExcelField(title="类型名", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}