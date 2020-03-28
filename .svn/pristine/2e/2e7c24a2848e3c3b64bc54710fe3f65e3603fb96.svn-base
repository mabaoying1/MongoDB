/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.iface.entity.HpIfaceAddress;

import java.util.List;

/**
 * 接口地址管理DAO接口
 * @author gyp
 * @version 2016-06-16
 */
@MyBatisDao
public interface HpIfaceAddressDao extends CrudDao<HpIfaceAddress> {
    public HpIfaceAddress getIfaceAddressByMerid(String merId, String funcid);

    public List<HpIfaceAddress> getAddressList(String funcType);
}