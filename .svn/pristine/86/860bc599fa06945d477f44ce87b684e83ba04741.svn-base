package com.healthpay.modules.iface.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.iface.entity.HpIfaceCardasync;
import com.healthpay.modules.iface.entity.HpIfaceCardasyncBak;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 健康E卡同步管理DAO接口
 * @author gaoyp
 * @version 2016-07-29
 */
@MyBatisDao
public interface HpIfaceCardasyncDao extends CrudDao<HpIfaceCardasync> {
   public List<HpIfaceCardasync> findCardasyncList();

   public void insertBak(HpIfaceCardasyncBak bak);

   public void updateHpIfaceCardasync(@Param(value = "pkid") String pkid, @Param(value = "errcode") String errCode, @Param(value ="errmsg" ) String errMsg);
}