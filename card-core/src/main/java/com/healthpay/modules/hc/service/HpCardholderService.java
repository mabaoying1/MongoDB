package com.healthpay.modules.hc.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.dao.HpCardholderDao;
import com.healthpay.modules.hc.dao.HpHealthcardDao;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * 持卡人档案Service
 * 
 * @author yyl
 * @version 2016-05-25
 */
@Service
@Transactional(readOnly = true)
public class HpCardholderService extends CrudService<HpCardholderDao, HpCardholder> {
	@Autowired
	private HpCardholderDao hpCardholderDao;
	@Autowired
	private HpHealthcardDao hpHealthcardDao;

	public HpCardholder get(String id) {
		return super.get(id);
	}

	public HpCardholder getByPhone(String phone) {
		return this.dao.getByPhone(phone);
	}

	public HpCardholder getByNationAndDocuTypeAndDocuId(String nationality, String docuType, String docuId) {
		return this.dao.getByNationAndDocuTypeAndDocuId(nationality, docuType, docuId);
	}

	public List<HpCardholder> findList(HpCardholder hpCardholder) {
		return super.findList(hpCardholder);
	}

	public Page<HpCardholder> findPage(Page<HpCardholder> page, HpCardholder entity) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", ""));
		// 设置分页参数
		entity.setPage(page);
		// 执行分页查询
		List<HpCardholder> list = this.dao.findList(entity).stream().peek(hpCardholder -> {
			hpCardholder.setDocuId(hpCardholder.getDocuId().replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2"));
			hpCardholder.setPhone((hpCardholder.getPhone() != null ? hpCardholder.getPhone() : "")
					.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
		}).collect(Collectors.toList());
		// list.parallelStream().forEach(hpCardholder->{hpCardholder.setDocuId(hpCardholder.getDocuId().replaceAll("(?<=\\\\d{4})\\\\d(?=\\\\d{4})","*"));});
		page.setList(list);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(HpCardholder hpCardholder) {
		super.save(hpCardholder);
	}

	@Transactional(readOnly = false)
	public void delete(HpCardholder hpCardholder) {
		super.delete(hpCardholder);
	}

	@Transactional(readOnly = false)
	public void update(HpCardholder hpCardholder) {
		hpCardholder.setId(hpCardholder.getIdentityId());
		hpCardholder.preUpdate();
		hpCardholderDao.update(hpCardholder);
	}

	@Transactional(readOnly = false)
	public void closeHpCardHoler(HpCardholder hpCardholder) {
		this.update(hpCardholder);
		// 更新健康卡数据
		HpHealthcard hpHealthCard = new HpHealthcard();
		hpHealthCard.setStatus("0");
		hpHealthCard.setIdentityId(hpCardholder.getIdentityId());
		hpHealthCard.setUpdateDate(new Date());
		hpHealthCard.setUpdateBy(UserUtils.getUser());
		hpHealthcardDao.updateByIdentityId(hpHealthCard);
	}

	public void defriendHpCardHoler(HpCardholder hpCardholder) {
		this.closeHpCardHoler(hpCardholder);
		// 写黑名单记录表
	}
}