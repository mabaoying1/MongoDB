package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpRealCardStock;
import com.healthpay.modules.hc.entity.HpRealCardStockBill;
import com.healthpay.modules.hc.search.HpRealCardStockBillSearch;

/**
 * 实体卡库存清单
 * @author zhouwj
 * @version 2016-11-16
 */
@MyBatisDao
public interface HpRealCardStockBillDao extends CrudDao<HpRealCardStockBill> {
    /**
     * 插入
     * @param hpRealCardStockBill
     * @return
     */
    public Long insertRealCardStockBill(HpRealCardStockBill hpRealCardStockBill);

    /**
     * 获取某个HpRealCardStockBill
     * @param hpRealCardStockBillSearch
     * @return
     */
    public HpRealCardStockBill getRealCardStockBill(HpRealCardStockBillSearch hpRealCardStockBillSearch);

    /**
     * 修改
     * @param hpRealCardStockBill
     * @return
     */
    public Integer updateRealCardStockBill(HpRealCardStockBill hpRealCardStockBill);

}
