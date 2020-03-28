package com.healthpay.modules.gen.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.gen.entity.GenTable;
import com.healthpay.modules.gen.entity.GenTableColumn;
import java.util.List;

@MyBatisDao
public abstract interface GenDataBaseDictDao extends CrudDao<GenTableColumn>
{
  public abstract List<GenTable> findTableList(GenTable paramGenTable);

  public abstract List<GenTableColumn> findTableColumnList(GenTable paramGenTable);

  public abstract List<String> findTablePK(GenTable paramGenTable);
}

