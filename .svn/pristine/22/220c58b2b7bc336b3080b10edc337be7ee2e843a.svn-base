/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.iim.dao;

import java.util.List;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.iim.entity.ChatHistory;

/**
 * 聊天记录DAO接口
 * @author jeeplus
 * @version 2015-12-29
 */
@MyBatisDao
public interface ChatHistoryDao extends CrudDao<ChatHistory> {
	
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<ChatHistory> findLogList(ChatHistory entity);
	
	
	public int findUnReadCount(ChatHistory chatHistory);
	
}