/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.service;

import java.util.List;

import com.healthpay.common.utils.DbUtil;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueueBak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.dao.HpIfaceMsgqueueDao;

/**
 * 发送队列管理Service
 * @author gyp
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = true)
public class HpIfaceMsgqueueService extends CrudService<HpIfaceMsgqueueDao, HpIfaceMsgqueue> {

	@Autowired
	private HpIfaceMsgqueueDao hpIfaceMsgQueueDao;

	public HpIfaceMsgqueue get(String id) {
		return super.get(id);
	}
	
	public List<HpIfaceMsgqueue> findList(HpIfaceMsgqueue hpIfaceMsgqueue) {
		return super.findList(hpIfaceMsgqueue);
	}
	
	public Page<HpIfaceMsgqueue> findPage(Page<HpIfaceMsgqueue> page, HpIfaceMsgqueue hpIfaceMsgqueue) {
		return super.findPage(page, hpIfaceMsgqueue);
	}

	@Transactional(readOnly = false)
	public void save(HpIfaceMsgqueue hpIfaceMsgqueue) {
		super.save(hpIfaceMsgqueue);
	}

	@Transactional(readOnly = false)
	public void delete(HpIfaceMsgqueue hpIfaceMsgqueue) {
		super.delete(hpIfaceMsgqueue);
	}


	public List<HpIfaceMsgqueue> findMsgQueueList() {
		return hpIfaceMsgQueueDao.findMsgQueueList();
	}

	public void moveToBack(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
		HpIfaceMsgqueueBak hpIfaceMsgqueueBak = new HpIfaceMsgqueueBak();
		MyBeanUtils.copyBeanNotNull2Bean(hpIfaceMsgqueue,hpIfaceMsgqueueBak);
		hpIfaceMsgqueueBak.setCreatetime(DbUtil.getDate());
		hpIfaceMsgQueueDao.insertMsgQueueBak(hpIfaceMsgqueueBak);
		hpIfaceMsgQueueDao.delete(hpIfaceMsgqueue);
	}


	public void moveToBak(boolean isSuccess, HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception{
		if(isSuccess){
			this.moveToBack(hpIfaceMsgqueue);
			return;
		}
		hpIfaceMsgQueueDao.updateMsgQueueErrcount(hpIfaceMsgqueue.getPkid());
	}
}