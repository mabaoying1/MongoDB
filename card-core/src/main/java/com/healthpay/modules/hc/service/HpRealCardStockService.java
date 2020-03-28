package com.healthpay.modules.hc.service;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.dao.HpRealCardStockBillDao;
import com.healthpay.modules.hc.dao.HpRealCardStockDao;
import com.healthpay.modules.hc.entity.HpRealCardStock;
import com.healthpay.modules.hc.entity.HpRealCardStockBill;
import com.healthpay.modules.hc.search.HpRealCardStockSearch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouwj on 2016/11/16.
 */
@Service
@Transactional(readOnly = true)
public class HpRealCardStockService extends CrudService<HpRealCardStockDao, HpRealCardStock> {
    @Autowired
    private HpRealCardStockDao hpRealCardStockDao;
    @Autowired
    private HpRealCardStockBillDao hpRealCardStockBillDao;
    /**
     * 获取某个HpRealCardStock
     * @param hpRealCardStockSearch
     * @return
     */
    public HpRealCardStock getHpRealCardStock(HpRealCardStockSearch hpRealCardStockSearch){
        return  hpRealCardStockDao.getHpRealCardStock(hpRealCardStockSearch);
    }

    /**
     * 新增库存的商户信息。
     * @param hpRealCardStock
     * @return
     */
    public Long insert(HpRealCardStock hpRealCardStock){//插入到库存清单表的同时也要在库存表中插入数据
        return hpRealCardStockDao.insertRealCardStock(hpRealCardStock);
    }

    public int findCancel(HpRealCardStock hpRealCardStock){
        return hpRealCardStockDao.findCancel(hpRealCardStock);
    }

    public int findNotCancel(HpRealCardStock hpRealCardStock){
        return hpRealCardStockDao.findNotCancel(hpRealCardStock);
    }

    public int findTotal(HpRealCardStock hpRealCardStock){
        return hpRealCardStockDao.findTotal(hpRealCardStock);
    }

    //根据时间条件查询实体卡台账
    public List<HpRealCardStock> findWithDate(String sendOrganiCode, String sendOrganiName, String  startDate, String endDate){
        return hpRealCardStockDao.findWithDate(sendOrganiCode,sendOrganiName, startDate, endDate);
    }

    //根据时间条件查询总取消数量
    public int findCancelWithDate(String sendOrganiCode, String sendOrganiName, String  startDate, String endDate){
        return hpRealCardStockDao.findCancelWithDate(sendOrganiCode,sendOrganiName, startDate, endDate);
    }

    //根据时间条件查询未取消数量
    public int  findNotCancelWithDate(String sendOrganiCode, String sendOrganiName, String  startDate, String endDate){
        return hpRealCardStockDao.findNotCancelWithDate(sendOrganiCode,sendOrganiName, startDate, endDate);
    }

    //根据时间条件查询总数量
    public int findTotalWithDate(String sendOrganiCode, String sendOrganiName, String  startDate, String endDate){
        return hpRealCardStockDao.findTotalWithDate(sendOrganiCode,sendOrganiName, startDate, endDate);
    }


}
