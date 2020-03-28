/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.iface.entity.HpIfaceLog;
import com.healthpay.modules.iface.dao.HpIfaceLogDao;

/**
 * 接口日志管理Service
 * @author gyp
 * @version 2016-06-20
 */
@Service
@Transactional(readOnly = true)
public class HpIfaceLogService extends CrudService<HpIfaceLogDao, HpIfaceLog> {

	public HpIfaceLog get(String id) {
		return super.get(id);
	}
	
	public List<HpIfaceLog> findList(HpIfaceLog hpIfaceLog) {
		return super.findList(hpIfaceLog);
	}
	
	public Page<HpIfaceLog> findPage(Page<HpIfaceLog> page, HpIfaceLog hpIfaceLog) {
		page.setOrderBy("a.create_time desc");
		return super.findPage(page, hpIfaceLog);
	}
	
	@Transactional(readOnly = false)
	public void save(HpIfaceLog hpIfaceLog) {
		super.save(hpIfaceLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpIfaceLog hpIfaceLog) {
		super.delete(hpIfaceLog);
	}
	
	
	
	
}