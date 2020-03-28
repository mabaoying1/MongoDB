/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.test.dao.tree;

import com.healthpay.common.persistence.TreeDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.test.entity.tree.TestTree;

/**
 * 组织机构DAO接口
 * @author liugf
 * @version 2016-05-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}