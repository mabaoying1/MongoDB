/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.service;

import java.util.List;

import com.healthpay.common.utils.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.service.CrudService;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.modules.sys.dao.DictDao;
import com.healthpay.modules.sys.entity.Dict;

/**
 * 字典Service
 * @author jeeplus
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

	@Autowired
	private DictDao dictDao;
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(CacheConstants.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(CacheConstants.CACHE_DICT_MAP);
	}

	public List<Dict> findByType(String type){
		List<Dict> dictList =(List<Dict>) CacheUtils.get(CacheConstants.CACHE_DICT_MAP+"_"+type);
		if(dictList==null){
			dictList = dictDao.findByType(type);
			CacheUtils.put(CacheConstants.CACHE_DICT_MAP+"_"+type,dictList);
		}

		return dictList;
	}

}
