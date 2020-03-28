package com.healthpay.modules.gen.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.gen.entity.GenTable;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public abstract interface GenTableDao extends CrudDao<GenTable>
{
  public abstract int buildTable(@Param("sql") String paramString);
}

