/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.echarts.dao;


import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.echarts.entity.ChinaWeatherDataBean;

/**
 * 城市气温DAO接口
 * @author lgf
 * @version 2016-06-02
 */
@MyBatisDao
public interface ChinaWeatherDataBeanDao extends CrudDao<ChinaWeatherDataBean> {

	
}