/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.sys.entity.SystemConfig;

/**
 * 系统配置DAO接口
 * @author liugf
 * @version 2016-02-07
 */
@MyBatisDao
public interface SystemConfigDao extends CrudDao<SystemConfig> {
	
}