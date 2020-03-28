package com.healthpay.modules.hc.service;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.dao.HpMerCardlistDao;
import com.healthpay.modules.hc.dao.HpRealCardDao;
import com.healthpay.modules.hc.entity.HpMerCardlist;
import com.healthpay.modules.hc.entity.HpRealCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyp
 * @version 2016-07-26
 */
@Service
@Transactional(readOnly = true)
public class HpMerCardlistService extends CrudService<HpMerCardlistDao, HpMerCardlist> {
	@Autowired
	private HpMerCardlistDao hpMerCardlistDao;

	public HpMerCardlist getHpMerCardlist(String merId,String idcardId){
		return this.hpMerCardlistDao.getHpMerCardlist(merId,idcardId);
	}

	public Long getpkId(String merId,String idcardId){
		return this.hpMerCardlistDao.getpkId(merId,idcardId);
	}

	public List<String> getHpMerCardlisByIccardId(String iccardId,String healthCardId){
		return this.hpMerCardlistDao.getHpMerCardlisByIccardId(iccardId,healthCardId);
	}

	public List<Map<String,String>> getMerNameByIccardId(String iccardId){
		return this.hpMerCardlistDao.getMerNameByIccardId(iccardId);
	}
}