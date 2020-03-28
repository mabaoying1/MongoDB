/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.test.service.grid;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.test.dao.grid.CategoryDao;
import com.healthpay.modules.test.entity.grid.Category;

/**
 * 商品分类Service
 * @author liugf
 * @version 2016-05-06
 */
@Service
@Transactional(readOnly = true)
public class CategoryService extends CrudService<CategoryDao, Category> {

	public Category get(String id) {
		return super.get(id);
	}
	
	public List<Category> findList(Category category) {
		return super.findList(category);
	}
	
	public Page<Category> findPage(Page<Category> page, Category category) {
		return super.findPage(page, category);
	}
	
	@Transactional(readOnly = false)
	public void save(Category category) {
		super.save(category);
	}
	
	@Transactional(readOnly = false)
	public void delete(Category category) {
		super.delete(category);
	}
	
	
	
	
}