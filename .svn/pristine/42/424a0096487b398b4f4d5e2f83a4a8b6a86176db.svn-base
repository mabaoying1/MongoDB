package com.healthpay.modules.hc.service;

import java.util.Date;
import java.util.List;

import com.healthpay.common.exception.BusException;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.search.HpRealCardSearch;
import com.healthpay.modules.iface.entity.HpIfaceAddress;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.service.HpIfaceAddressService;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.hc.dao.HpRealCardDao;

/**
 * 健康卡实体卡绑定Service
 * @author gaoyp
 * @version 2016-07-22
 */
@Service
@Transactional(readOnly = true)
public class HpRealCardService extends CrudService<HpRealCardDao, HpRealCard> {
	@Autowired
	private HpRealCardDao hpRealCardDao;
	@Autowired
	private HpHealthcardService hpHealthcardService;
	@Autowired
	private HpCardholderService hpCardholderService;
	@Autowired
	private HpApplycardService hpApplycardService;
	@Autowired
	private HpIfaceMsgqueueService hpIfaceMsgqueueService;
	@Autowired
	private HpMerCardlistService hpMerCardlistService;
	@Autowired
	private HpIfaceAddressService hpIfaceAddressService;
	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;

	public HpRealCard get(String id) {
		return super.get(id);
	}
	
	public List<HpRealCard> findList(HpRealCard hpRealCard) {
		return super.findList(hpRealCard);
	}
	
	public Page<HpRealCard> findPage(Page<HpRealCard> page, HpRealCard hpRealCard) {
		return super.findPage(page, hpRealCard);
	}

	public List<HpRealCard> findListByCondition(HpRealCardSearch hpRealCardSearch){
		return hpRealCardDao.findListByCondition(hpRealCardSearch);
	}

	
	@Transactional(readOnly = false)
	public void save(HpRealCard hpRealCard) {
		super.save(hpRealCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpRealCard hpRealCard) {
		super.delete(hpRealCard);
	}


	public HpRealCard getHealthCardId(String icCardId, long status, long type){
		return hpRealCardDao.getHealthCardId(icCardId,status,type);
	}

	public HpRealCard getIcCardId(String healthCardId, long status, long type) {
		return hpRealCardDao.getIcCardId(healthCardId,status,type);
	}

	public int updateRealCardStatus(String healthCardId, long status,long oldstatus, long type) {
		return hpRealCardDao.updateRealCardStatus(healthCardId,status,oldstatus,type);
	}

	public int getCount(HpRealCard hpRealCard) {
		return hpRealCardDao.getCount(hpRealCard);
	}

	public HpRealCard getHealthCardIdTwo(String icCardId,  long type){
		return hpRealCardDao.getHealthCardIdTwo(icCardId,type);
	}

	public int updateRealCardStatusByPkid(String pkid, long status) {
		return hpRealCardDao.updateRealCardStatusByPkid(pkid,status);
	}

	@Transactional
	public void deleteAndSysn(HpRealCard hpRealCard){
		int ret = this.updateRealCardStatusByPkid(hpRealCard.getPkid().toString(),hpRealCard.getStatus());
		if (ret > 0) {
			HpRealCard realCard = this.get(String.valueOf(hpRealCard.getPkid()));
			HpHealthcard hpHealthcard = hpHealthcardService.get(realCard.getCardPkid());
			HpCardholder hpCardholder = hpCardholderService.get(hpHealthcard.getIdentityId());
			//保存到消息队列
			syncHisDeleteToMsgqueue(realCard,hpHealthcard,hpCardholder,0);
			//保存到区域平台队列
			hpApplycardService.doSendAreaPlatform(hpCardholder,hpHealthcard.getPkid(),null);
			//--------------------银联异步--------------------
			//把传送给网银的绑定记录写入队列表
//			HpIfaceMerchant gateway = hpIfaceMerchantService.getGateway("支付网关",99);
//			if(gateway!=null){
//				HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
//				hpIfaceMsgqueue.setMerId(gateway.getMerId());
//				hpIfaceMsgqueue.setFuncid("P1004");
//				hpIfaceMsgqueue.setIcCardId(realCard.getIccardid());
//				hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCard.getType())));
//				hpIfaceMsgqueue.setHealthcardid(hpHealthcard.getPkid());
//				hpIfaceMsgqueue.setDocutype(hpCardholder.getDocuType());
//				hpIfaceMsgqueue.setDocuid(hpCardholder.getDocuId());
//				hpIfaceMsgqueue.setCreatetime(new Date());
//				hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
//			}
		}
	}

	/**
	 * 同步注销到his
	 */
	private void syncHisDeleteToMsgqueue(HpRealCard realCard,HpHealthcard hpHealthcard,HpCardholder hpCardholder,int optType){
		try {
//			HpHealthcard hpHealthcard = hpHealthcardService.get(realCard.getCardPkid());
//			HpCardholder hpCardholder = hpCardholderService.get(hpHealthcard.getIdentityId());

			//			需要向已绑定的医院发送注销/挂失/解挂指令
			String icCardid = realCard.getIccardid();
			String healthCardId = realCard.getCardPkid();
			List<String> list = hpMerCardlistService.getHpMerCardlisByIccardId(icCardid, healthCardId);
			String funcId = "A2002";
			for (String merid : list) {
				HpIfaceAddress hpIfaceAddress = hpIfaceAddressService.getIfaceAddressByMerid(merid, funcId);
				if (null == hpIfaceAddress) continue;
				HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
				hpIfaceMsgqueue.setMerId(merid);
				hpIfaceMsgqueue.setPosturl(hpIfaceAddress.getFuncAddress());
				hpIfaceMsgqueue.setHealthcardid(healthCardId);
				hpIfaceMsgqueue.setIcCardId(icCardid);
				hpIfaceMsgqueue.setDocuid(hpHealthcard.getCardId());
				hpIfaceMsgqueue.setDocutype(hpCardholder.getDocuType());
				if(hpHealthcard.getCountrycode2()==null){
					hpIfaceMsgqueue.setNationality("0");
					hpIfaceMsgqueue.setParam0("中国");
				}else {
					hpIfaceMsgqueue.setNationality(hpHealthcard.getCountrycode2());
					hpIfaceMsgqueue.setParam0(hpHealthcard.getCountryname2());
				}
				hpIfaceMsgqueue.setCreatetime(new Date());
				hpIfaceMsgqueue.setFuncid(funcId);
				hpIfaceMsgqueue.setType(Integer.valueOf(realCard.getType().toString()));
				hpIfaceMsgqueue.setOptType(optType);//注销0挂失1解挂2
				hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			}
		}catch (Exception e) {
			logger.error("保存注销同步队列失败",e);
			throw new BusException("保存注销同步队列失败");
		}
	}

	public HpRealCard getHealthCardIdNotCancel(String icCardId,int type){
		return hpRealCardDao.getHealthCardIdNotCancel(icCardId,type);
	}

	public HpRealCard getIcCardNotCancel(String healthCardId,int type){
		return hpRealCardDao.getIcCardNotCancel(healthCardId,type);
	}

	public HpRealCard getRealCard(String icCardId,int type){
		return hpRealCardDao.getRealCard(icCardId,type);
	}

	public HpRealCard getRealCardByHealthCardIdAndType(String healthCardId,int type){
		return hpRealCardDao.getIcCardNotCancel(healthCardId,type);
	}
}