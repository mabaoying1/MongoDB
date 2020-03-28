/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.test.dao.note;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.test.entity.note.TestNote;

/**
 * 富文本测试DAO接口
 * @author liugf
 * @version 2016-05-06
 */
@MyBatisDao
public interface TestNoteDao extends CrudDao<TestNote> {

	
}