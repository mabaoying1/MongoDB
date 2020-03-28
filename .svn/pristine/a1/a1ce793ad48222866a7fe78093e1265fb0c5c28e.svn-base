package com.healthpay.modules.hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.dao.HpCardaccountDao;
import com.healthpay.modules.hc.entity.HpCardaccount;

/**
 * 卡帐关系Service
 * 
 * @author yyl
 * @version 2016-05-25
 */
@Service
@Transactional(readOnly = true)
public class HpCardaccountService extends CrudService<HpCardaccountDao, HpCardaccount> {
	@Autowired
	private HpCardaccountDao hpCardaccountDao;

	public HpCardaccount get(String id) {
		return super.get(id);
	}

	public List<HpCardaccount> findList(HpCardaccount hpCardaccount) {
		return super.findList(hpCardaccount);
	}

	public Page<HpCardaccount> findPage(Page<HpCardaccount> page, HpCardaccount entity) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", ""));
		// 设置分页参数
		entity.setPage(page);
		// 执行分页查询
		page.setList(hpCardaccountDao.findList(entity));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(HpCardaccount hpCardaccount) {
		super.save(hpCardaccount);
	}

	@Transactional(readOnly = false)
	public void delete(HpCardaccount hpCardaccount) {
		super.delete(hpCardaccount);
	}

}