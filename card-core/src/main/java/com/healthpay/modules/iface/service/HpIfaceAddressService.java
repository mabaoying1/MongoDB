/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.iface.entity.HpIfaceAddress;
import com.healthpay.modules.iface.dao.HpIfaceAddressDao;

/**
 * 接口地址管理Service
 * @author gyp
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = true)
public class HpIfaceAddressService extends CrudService<HpIfaceAddressDao, HpIfaceAddress> {
	@Autowired
	private HpIfaceAddressDao hpIfaceAddressDao;

	public HpIfaceAddress get(String id) {
		return super.get(id);
	}
	
	public List<HpIfaceAddress> findList(HpIfaceAddress hpIfaceAddress) {
		return super.findList(hpIfaceAddress);
	}
	
	public Page<HpIfaceAddress> findPage(Page<HpIfaceAddress> page, HpIfaceAddress hpIfaceAddress) {
		return super.findPage(page, hpIfaceAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(HpIfaceAddress hpIfaceAddress) {
		super.save(hpIfaceAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpIfaceAddress hpIfaceAddress) {
		super.delete(hpIfaceAddress);
	}

	public HpIfaceAddress getIfaceAddressByMerid(String merId, String funcid){
		return hpIfaceAddressDao.getIfaceAddressByMerid(merId,funcid);
	}


	public List<HpIfaceAddress> getAddressList(String funcType) {
		return hpIfaceAddressDao.getAddressList(funcType);
	}
}