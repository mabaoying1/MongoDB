/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.oa.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.oa.entity.OaNotify;

/**
 * 通知通告DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify);
	
}