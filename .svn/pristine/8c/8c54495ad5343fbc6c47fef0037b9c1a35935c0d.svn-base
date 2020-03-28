package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpRealCardStock;
import com.healthpay.modules.hc.search.HpRealCardStockSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 实体卡库存清单
 * @author zhouwj
 * @version 2016-11-16
 */
@MyBatisDao
public interface HpRealCardStockDao extends CrudDao<HpRealCardStock> {
    /**
     * 获取某个HpRealCardStock
     * @param hpRealCardStockSearch
     * @return
     */
    public HpRealCardStock getHpRealCardStock(HpRealCardStockSearch hpRealCardStockSearch);

    /**
     * 插入
     * @param hpRealCardStock
     * @return
     */
    public Long insertRealCardStock(HpRealCardStock hpRealCardStock);

    public int findCancel(HpRealCardStock hpRealCardStock);

    public int findNotCancel(HpRealCardStock hpRealCardStock);

    public int findTotal(HpRealCardStock hpRealCardStock);

    public List<HpRealCardStock> findWithDate(@Param("organCode" )String organCode, @Param("organName" ) String organName,@Param("startDate" ) String startDate, @Param("endDate" ) String endDate);

    public int findCancelWithDate(@Param("organCode" )String organCode, @Param("organName" ) String organName,@Param("startDate" ) String startDate, @Param("endDate" ) String endDate);

    public int findNotCancelWithDate(@Param("organCode" )String organCode, @Param("organName" ) String organName,@Param("startDate" ) String startDate, @Param("endDate" ) String endDate);

    public int findTotalWithDate(@Param("organCode" )String organCode, @Param("organName" ) String organName,@Param("startDate" ) String startDate, @Param("endDate" ) String endDate);

}
