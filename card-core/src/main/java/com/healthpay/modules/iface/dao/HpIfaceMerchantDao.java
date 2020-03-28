/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.dao;


import org.apache.ibatis.annotations.Param;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;

/**
 * 商户管理DAO接口
 * @author gyp
 * @version 2016-06-14
 */
@MyBatisDao
public interface HpIfaceMerchantDao extends CrudDao<HpIfaceMerchant> {

    public HpIfaceMerchant getGateway(String merName,int source);

	public HpIfaceMerchant getMerchantByOrgCode(@Param("orgCode")String orgCode);

}