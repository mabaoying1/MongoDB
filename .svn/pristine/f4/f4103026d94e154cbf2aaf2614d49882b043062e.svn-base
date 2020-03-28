package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpApplycard;

/**
 * 健康卡申请记录DAO接口
 * @author yyl
 * @version 2016-05-25
 */
@MyBatisDao
public interface HpApplycardDao extends CrudDao<HpApplycard> {

	public HpApplycard getByIdentityId(String identityId);
	
	public HpApplycard getByPhone(String phone);
	
	public HpApplycard getByNationAndDocuTypeAndDocuId(String nationality, String docuType, String docuId);

	public int getWaitAuditCount(HpApplycard entity);
}