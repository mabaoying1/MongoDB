package com.healthpay.modules.hc.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.dao.HpHealthcardDao;
import com.healthpay.modules.hc.entity.HpHealthcard;

/**
 * 健康卡数据Service
 * 
 * @author yyl
 * @version 2016-05-25
 */
@Service
@Transactional(readOnly = true)
public class HpHealthcardService extends CrudService<HpHealthcardDao, HpHealthcard> {

	@Autowired
	private HpHealthcardDao hpHealthcardDao;

	public HpHealthcard get(String id) {
		return super.get(id);
	}

	public HpHealthcard getByIdentityId(String identityId) {
		return this.dao.getByIdentityId(identityId);
	}


	public HpHealthcard getByCardIdAndStatus(String cardId ,String status) {
		return this.dao.getByCardIdAndStatus(cardId, status);
	}
	
	public HpHealthcard getByIdentityIdAndStatus(String identityId ,String status) {
		return this.dao.getByIdentityIdAndStatus(identityId, status);
	}
	
	public List<HpHealthcard> findList(HpHealthcard hpHealthcard) {
		return super.findList(hpHealthcard);
	}

	public Page<HpHealthcard> findPage(Page<HpHealthcard> page, HpHealthcard entity) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
				entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", ""));
				// 设置分页参数
				entity.setPage(page);
				// 执行分页查询
				page.setList(this.dao.findList(entity));
				return page;
	}

	@Transactional(readOnly = false)
	public void save(HpHealthcard hpHealthcard) {
		super.save(hpHealthcard);
	}

	@Transactional(readOnly = false)
	public void delete(HpHealthcard hpHealthcard) {
		super.delete(hpHealthcard);
	}

	@Transactional(readOnly = false)
	public HpHealthcard newHealthCard(HpApplycard hpApplycard) throws Exception {
		HpHealthcard healthcard = new HpHealthcard();
		MyBeanUtils.copyBeanNotNull2Bean(hpApplycard, healthcard);
		healthcard.setIdentityId(hpApplycard.getCardId());
		healthcard.setSecurityCode(123);// 安全码
		healthcard.setChipSerialid("123");// 芯片序列号
		healthcard.setStartDate(new Date());
		healthcard.setIsOpenpay(0L);
		return healthcard;
	}

	public void update(HpHealthcard hpHealthcard) {
		hpHealthcard.preUpdate();
		this.dao.update(hpHealthcard);
	}

	public String getMaxId(String s) {
		String max = hpHealthcardDao.geteMaxId(s);
		if (null == max) {
			max = "0";
		} else {
			max = max.substring(6);
		}
		long lMax = Long.parseLong(max) + 1;
		DecimalFormat df = new DecimalFormat("000000000");
		return s +df.format(lMax);
	}

	public HpHealthcard getByPkid(String pkid) {
		return this.dao.getByPkid(pkid);
	}

	public int updateByIdentityId(HpHealthcard hpHealthcard){
		return this.dao.updateByIdentityId(hpHealthcard);
	}

	/**
	 * 通过电子健康卡id和状态查询健康卡信息
	 * @param healthCardId
	 * @param status
	 * @return
	 */
	public HpHealthcard getByPkidAndStatus(String healthCardId, String status) {
		return this.dao.getByPkidAndStatus(healthCardId,status);
	}
}