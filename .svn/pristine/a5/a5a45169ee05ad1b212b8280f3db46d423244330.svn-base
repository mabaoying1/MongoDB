package com.healthpay.modules.iface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.iface.entity.HpIfacePlatformarea;
import com.healthpay.modules.iface.dao.HpIfacePlatformareaDao;

/**
 * 外部平台管辖范围Service
 * @author gaoyp
 * @version 2016-07-29
 */
@Service
@Transactional(readOnly = true)
public class HpIfacePlatformareaService extends CrudService<HpIfacePlatformareaDao, HpIfacePlatformarea> {

	@Autowired
	private HpIfacePlatformareaDao hpIfacePlatformareaDao;

	public HpIfacePlatformarea get(String id) {
		return super.get(id);
	}
	
	public List<HpIfacePlatformarea> findList(HpIfacePlatformarea hpIfacePlatformarea) {
		return super.findList(hpIfacePlatformarea);
	}
	
	public Page<HpIfacePlatformarea> findPage(Page<HpIfacePlatformarea> page, HpIfacePlatformarea hpIfacePlatformarea) {
		return super.findPage(page, hpIfacePlatformarea);
	}
	
	@Transactional(readOnly = false)
	public void save(HpIfacePlatformarea hpIfacePlatformarea) {
		super.save(hpIfacePlatformarea);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpIfacePlatformarea hpIfacePlatformarea) {
		super.delete(hpIfacePlatformarea);
	}

	public List<Long> getByCode(String cityCode, String countyCode) {
		return hpIfacePlatformareaDao.getByCode(cityCode,countyCode);
	}
}