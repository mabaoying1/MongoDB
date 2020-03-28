package com.healthpay.modules.iface.service;

import java.util.List;

import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.modules.iface.entity.HpIfaceCardasyncBak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.iface.entity.HpIfaceCardasync;
import com.healthpay.modules.iface.dao.HpIfaceCardasyncDao;

/**
 * 健康E卡同步管理Service
 * @author gaoyp
 * @version 2016-07-29
 */
@Service
@Transactional(readOnly = true)
public class HpIfaceCardasyncService extends CrudService<HpIfaceCardasyncDao, HpIfaceCardasync> {

	@Autowired
	private HpIfaceCardasyncDao hpIfaceCardasyncDao;

	public HpIfaceCardasync get(String id) {
		return super.get(id);
	}
	
	public List<HpIfaceCardasync> findList(HpIfaceCardasync hpIfaceCardasync) {
		return super.findList(hpIfaceCardasync);
	}
	
	public Page<HpIfaceCardasync> findPage(Page<HpIfaceCardasync> page, HpIfaceCardasync hpIfaceCardasync) {
		return super.findPage(page, hpIfaceCardasync);
	}
	
	@Transactional(readOnly = false)
	public void save(HpIfaceCardasync hpIfaceCardasync) {
		super.save(hpIfaceCardasync);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpIfaceCardasync hpIfaceCardasync) {
		super.delete(hpIfaceCardasync);
	}


	/**
	 *
	 * @return
     */
	public List<HpIfaceCardasync> findList() {
		return hpIfaceCardasyncDao.findCardasyncList();
	}

	public void moveCardasyncToBak(HpIfaceCardasync hpIfaceCardasync, String errCode, String errMsg, boolean isSuccess) throws Exception {
		if(isSuccess){
			HpIfaceCardasyncBak bak = new HpIfaceCardasyncBak();
			MyBeanUtils.copyBean2Bean(bak,hpIfaceCardasync);
			hpIfaceCardasyncDao.insertBak(bak);
			hpIfaceCardasyncDao.delete(hpIfaceCardasync);
		}else {
			hpIfaceCardasyncDao.updateHpIfaceCardasync(hpIfaceCardasync.getPkid(),errCode,errMsg);
		}
	}
}