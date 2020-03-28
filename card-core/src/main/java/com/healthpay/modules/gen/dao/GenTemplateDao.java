package com.healthpay.modules.gen.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.gen.entity.GenTemplate;

@MyBatisDao
public abstract interface GenTemplateDao extends CrudDao<GenTemplate>
{
}

