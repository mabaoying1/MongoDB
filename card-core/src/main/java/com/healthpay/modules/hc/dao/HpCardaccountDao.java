package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpCardaccount;

/**
 * 卡帐关系DAO接口
 * @author yyl
 * @version 2016-05-25
 */
@MyBatisDao
public interface HpCardaccountDao extends CrudDao<HpCardaccount> {

	
}