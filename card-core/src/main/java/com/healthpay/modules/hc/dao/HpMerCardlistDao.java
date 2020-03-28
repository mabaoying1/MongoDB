package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpMerCardlist;
import com.healthpay.modules.hc.entity.HpRealCard;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyp
 * @version 2016-07-26
 */
@MyBatisDao
public interface HpMerCardlistDao extends CrudDao<HpMerCardlist> {
    public HpMerCardlist getHpMerCardlist(String merId,String iccardId);

    public Long getpkId(String merId,String iccardId);

    public List<String> getHpMerCardlisByIccardId(String iccardId,String healthCardId);

    //根据实体卡卡号查询实体卡在哪些医院被使用过
    public List<Map<String,String>> getMerNameByIccardId(String iccardId);

}