/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.dao;

import java.util.List;

import com.healthpay.common.persistence.TreeDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
	public Area getByCode(String code);
	
	public List<Area> findListByParent (String parentId) ;
}
