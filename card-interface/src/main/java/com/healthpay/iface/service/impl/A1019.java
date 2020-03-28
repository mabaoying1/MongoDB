package com.healthpay.iface.service.impl;

import java.util.Date;

import com.healthpay.modules.hc.entity.*;
import com.healthpay.modules.hc.service.HpYkjlXnkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthpay.common.exception.BusException;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.QueryCardHolderVo;
import com.healthpay.iface.vo.ResResultCardHolderVo;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
/**    
* @ClassName: A1019 
* @Description: 查询持卡人档案信息
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1019 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	QueryCardHolderVo vo;

	@Autowired
	private HpYkjlXnkService hpYkjlXnkService;

	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String nationality = vo.getNationality();//国籍
		String docuType = vo.getDocuType();//证件类型  (国籍不为空时，不能为空)
		String docuId = vo.getDocuId();//证件号码(国籍,证件类型不为空时，不能为空)
		String healthCardId = vo.getHealthCardId();//健康e卡号（虚拟卡号）  (国籍、证件类型、证件号码为空时不能为空)
		String icCardId = vo.getIcCardId();//实体卡卡号(国籍、证件类型、证件号码、健康e卡号为空时不能为空)
		Integer type = vo.getType();//实体卡类型 (0 居民健康卡  1 社保卡（社保卡的参保号 前8位作为实体卡卡号）)
		String reason = "";
		HpHealthcard card = null;
		HpCardholder cardholder = null;
		HpRealCard hpRealCard = null;
		if ((StringUtils.isEmpty(nationality) || StringUtils.isEmpty(docuId) || null == docuType)) {
			if (StringUtils.isEmpty(healthCardId) && StringUtils.isEmpty(icCardId)) {
				reason = "(国籍，证件类型，证件号码)或健康卡号或实体卡卡号不能同时为空";
				throw new BusException(reason);
			} else {
				if (!(StringUtils.isEmpty(nationality) && StringUtils.isEmpty(docuId) && null == docuType)) {
					reason = "(国籍，证件类型，证件号码)全为空或全不为空";
					throw new BusException(reason);
				}
			}
		}

		// 1.如果虚拟卡卡号不为空，根据虚拟卡号查询实体卡信息
		if (StringUtils.isNotEmpty(healthCardId)) {
			card = hpHealthcardService.get(healthCardId);
			if (null != card) {
				cardholder = hpCardholderService.get(card.getIdentityId());

				hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 0L);
				if (null == hpRealCard) {
					hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 1L);
				}
			} else {
				reason = "健康卡卡号错误";
				throw new BusException(reason);
			}
		}
		// 2.如果实体卡号不为空，查出实体卡信息；通过证件号查出持卡者信息，通过证件查出健康卡信息
		else if (StringUtils.isNotEmpty(icCardId)) {
			if (null == type) {
				reason = "实体卡类型不能为空";
				throw new BusException(reason);
			}
			if (type == 0) {
				hpRealCard = hpRealCardService.getRealCard(icCardId, type);
			}
			if (type == 1) {
				hpRealCard = hpRealCardService.getHealthCardIdNotCancel(icCardId, type);
			}
			// 查不出实体卡
			if (null == hpRealCard) {
				if (StringUtils.isEmpty(docuId)) {
					reason = "不存在的实体卡卡号";
					throw new BusException(reason);
				} else {
					String identityId =  docuType + docuId;
					cardholder=hpCardholderService.get(identityId);
					card = hpHealthcardService.getByIdentityIdAndStatus(identityId, "2");// 已激活
					healthCardId = card.getPkid();
					if (null == card || null == cardholder) {
						reason = "没有找到有效的健康卡信息";
						throw new BusException(reason);
					}
				}
			}
			// 查出实体卡
			else {
				if (0 == hpRealCard.getStatus().intValue()) {
					reason = "该实体卡卡号已注销";
					throw new BusException(reason);
				}
				if (2 == hpRealCard.getStatus().intValue()) {
					reason = "该实体卡卡号已挂失";
					throw new BusException(reason);
				}
				card = hpHealthcardService.get(hpRealCard.getCardPkid());
				cardholder = hpCardholderService.get(card.getIdentityId());
				healthCardId = card.getPkid();
				// 首先判断商户类型是否为HIS
				HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.getFormCache(appId);
				if (null != hpIfaceMerchant && 1 == hpIfaceMerchant.getSource().intValue()) {
					if (null == hpMerCardlistService.getpkId(appId, hpRealCard.getIccardid())) {
						// 写HIS系统绑定记录
						HpMerCardlist hpMerCardlist = new HpMerCardlist();
						hpMerCardlist.setMerId(appId);
						hpMerCardlist.setIccardId(hpRealCard.getIccardid());
						hpMerCardlist.setHealthCardId(healthCardId);
						hpMerCardlist.setDate0(new Date());
						// flag0：1为第一次创建实体卡的标记，2为通过1019接口访问实体卡的标记
						hpMerCardlist.setFlag0(2);
						hpMerCardlistService.save(hpMerCardlist);
					}
				}
			}

		}
		// 3.健康卡、实体卡号都为空时，通过证件查出持卡者信息，通过证件查出健康卡信息
		else {
			String identityId =  docuType + docuId;
			cardholder = hpCardholderService.get(identityId);
			card = hpHealthcardService.getByIdentityIdAndStatus(identityId, "2");// 已激活
			if (null == card || null == cardholder) {
				reason = "没有找到有效的健康卡信息";
				throw new BusException(reason);
			}
			healthCardId = card.getPkid();
			hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 0L);
			if (null == hpRealCard) {
				hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 1L);
			}
		}
		ResResultCardHolderVo ret = new ResResultCardHolderVo();
		ret.setNationality(nationality);
		ret.setDocuId(docuId);
		ret.setDocuType(docuType);
		if (null != hpRealCard) {
			ret.setIcCardId(hpRealCard.getIccardid());
			ret.setType(hpRealCard.getType());
		}
		if (null != card) {
			MyBeanUtils.copyBeanNotNull2Bean(card, ret);
		}
		if (null != cardholder) {
			MyBeanUtils.copyBeanNotNull2Bean(cardholder, ret);
		}
		ret.setHealthCardId(healthCardId);
		return ret;
	}
	
}
