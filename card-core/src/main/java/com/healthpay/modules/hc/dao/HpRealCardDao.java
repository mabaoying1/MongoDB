package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.hc.search.HpRealCardSearch;

import java.util.List;
import java.util.Map;

/**
 * 健康卡实体卡绑定DAO接口
 * @author gaoyp
 * @version 2016-07-22
 */
@MyBatisDao
public interface HpRealCardDao extends CrudDao<HpRealCard> {
    public HpRealCard getHealthCardId(String icCardId, long status, long type);

    public HpRealCard getIcCardId(String healthCardId, long status, long type);

    public int updateRealCardStatus(String healthCardId, long status,long oldstatus, long type);

    public int getCount(HpRealCard hpRealCard);

    public HpRealCard getHealthCardIdTwo(String icCardId,long type);

    public int updateRealCardStatusByPkid(String pkid, long status);

    /**
     * 通过某些条件获取实体卡列表
     * @param hpRealCardSearch
     * @return
     */
    public List<HpRealCard> findListByCondition(HpRealCardSearch hpRealCardSearch);

    public HpRealCard getHealthCardIdNotCancel(String icCardId,int type);

    public HpRealCard getIcCardNotCancel(String healthCardId,int type);
    // 根据实体卡号和类型查询所有状态实体卡
    public HpRealCard getRealCard(String healthCardId,int type);

}