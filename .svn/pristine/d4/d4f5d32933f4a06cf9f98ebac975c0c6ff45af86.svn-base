package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpCardholder;

import java.util.List;
import java.util.Map;

/**
 * 持卡人档案DAO接口
 * 
 * @author yyl
 * @version 2016-05-25
 */
@MyBatisDao
public interface HpCardholderDao extends CrudDao<HpCardholder> {
	public List<HpCardholder> findResidentList(HpCardholder entity);

	public Integer getTotalResident(HpCardholder entity);

	public Map<String,Integer> findChartData(HpCardholder entity);
	
	public HpCardholder getByPhone(String phone);
	
	public HpCardholder getByNationAndDocuTypeAndDocuId(String nationality ,String docuType ,String docuId);

	public List<HpCardholder> findResistatList(HpCardholder entity);

	public Integer getTotalResistat(HpCardholder entity);

	public List<HpCardholder> getTable(HpCardholder entity);

}