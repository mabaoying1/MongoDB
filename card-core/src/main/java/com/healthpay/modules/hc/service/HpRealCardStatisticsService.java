package com.healthpay.modules.hc.service;

import com.healthpay.common.Constant;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.hc.dao.HpApplycardDao;
import com.healthpay.modules.hc.dao.HpRealCardStatisticsDao;
import com.healthpay.modules.hc.entity.*;
import com.healthpay.modules.iface.entity.HpIfaceCardasync;
import com.healthpay.modules.iface.entity.HpIfacePlatform;
import com.healthpay.modules.iface.service.HpIfaceCardasyncService;
import com.healthpay.modules.iface.service.HpIfacePlatformService;
import com.healthpay.modules.iface.service.HpIfacePlatformareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 健康卡统计服务
 *
 * @author zhouwj
 * @version 2016-12-21
 */
@Service
@Transactional(readOnly = true)
public class HpRealCardStatisticsService extends CrudService<HpRealCardStatisticsDao, HpRealCardStatistics> {
    @Autowired
    HpRealCardStatisticsDao hpRealCardStatisticsDao;
    /**
     * 总数量
     * @param hpRealCardStatistics
     * @return
     */
    public Long findListCount(HpRealCardStatistics hpRealCardStatistics){
        return hpRealCardStatisticsDao.findListCount(hpRealCardStatistics);
    }
}

