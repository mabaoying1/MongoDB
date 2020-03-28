/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.dao;

import com.healthpay.common.persistence.TreeDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * 
 * @author jeeplus
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {

	public Office getOfficeByCode(String code);

	public Office getByCode(String code);

	public Office getById(String id);

	public String getAreaIdById(String officeId);
}
