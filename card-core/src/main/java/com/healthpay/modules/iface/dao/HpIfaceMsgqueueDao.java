/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueueBak;

import java.util.List;

/**
 * 发送队列管理DAO接口
 * @author gyp
 * @version 2016-06-16
 */
@MyBatisDao
public interface HpIfaceMsgqueueDao extends CrudDao<HpIfaceMsgqueue> {
    public List<HpIfaceMsgqueue> findMsgQueueList();

    public void insertMsgQueueBak(HpIfaceMsgqueueBak hpIfaceMsgqueueBak);

    public void updateMsgQueueErrcount(String pkid);
}