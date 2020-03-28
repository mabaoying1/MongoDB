package com.healthpay.modules.iface.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.iface.entity.HpIfacePlatformarea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 外部平台管辖范围DAO接口
 * @author gaoyp
 * @version 2016-07-29
 */
@MyBatisDao
public interface HpIfacePlatformareaDao extends CrudDao<HpIfacePlatformarea> {
    public int deleteHpIfacePlatformarea(HpIfacePlatformarea hpIfacePlatformarea);

    public List<Long> getByCode(@Param(value = "cityCode") String cityCode,@Param(value = "countyCode") String countyCode);

}