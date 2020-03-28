package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpRealCardStatistics;

/**
 * Created by zhouwj on 2016/12/21.
 */
@MyBatisDao
public interface HpRealCardStatisticsDao extends CrudDao<HpRealCardStatistics> {
    /**
     * 总数量
     * @param hpRealCardStatistics
     * @return
     */
    public Long findListCount(HpRealCardStatistics hpRealCardStatistics);
}
