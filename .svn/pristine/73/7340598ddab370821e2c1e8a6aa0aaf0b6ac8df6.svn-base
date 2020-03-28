/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.service.TreeService;
import com.healthpay.modules.sys.dao.OfficeDao;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * 
 * @author jeeplus
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public Office getOfficeByCode(String code){
		return dao.getOfficeByCode(code);
	}
	public List<Office> findAll() {
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll) {
		if (isAll != null && isAll) {
			return UserUtils.getOfficeAllList();
		} else {
			return UserUtils.getOfficeList();
		}
	}

	@Transactional(readOnly = true)
	public List<Office> findList(Office office) {
		office.setParentIds(office.getParentIds() + "%");
		return dao.findByParentIdsLike(office);
	}

	@Transactional(readOnly = true)
	public Office getByCode(String code) {
		return dao.getByCode(code);
	}

	@Transactional(readOnly = true)
	public Office getById(String id) {
		return dao.getById(id);
	}

	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = true)
	public String getAreaIdById(String officeId) {
		return dao.getAreaIdById(officeId);
	}

}
