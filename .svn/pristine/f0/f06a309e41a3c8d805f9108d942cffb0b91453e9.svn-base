package com.healthpay.iface.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.healthpay.common.Constant;
import com.healthpay.common.utils.DbUtil;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.OpenPayVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpCardaccount;
import com.healthpay.modules.hc.entity.HpHealthcard;
/**    
* @ClassName: A1004
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1004 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	OpenPayVo vo;
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String status = Constant.IfaceStatus.SUCCESS;
		String reason = "";
		// 先身份认证
		ResResultVo resultVo = checkIdentity(healthCardId);
		if (Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			if (card.getIsOpenpay() != null && card.getIsOpenpay().intValue() == 1) {
				status = Constant.IfaceStatus.FAILURE;
				reason = "支付功能已经开通";
			} else {
				card.setIsOpenpay(1L);
				Date date = DbUtil.getDate();
				if (null == card.getFirstOpenpayDate()) {
					card.setFirstOpenpayDate(date);
				}
				card.setOpenpayDate(date);
				card.setPaylimit(vo.getPaylimit());
				card.setPassword(vo.getPassword());
				card.setIsPwdfree(vo.getIsPwdfree());
				card.setSmalllimit(vo.getSmalllimit());
				card.setReserved(vo.getReserved());
				hpHealthcardService.update(card);
				// 添加卡帐关系
				HpCardaccount cardaccount = new HpCardaccount();
				cardaccount.setCardPkid(card.getPkid());
				cardaccount.setCardId(card.getCardId());
				cardaccount.setPaysystem(vo.getPaysystem());
				cardaccount.setAccount(vo.getAccount());
				cardaccount.setAccountname(vo.getAccountname());
				cardaccount.setBankid(vo.getBankid());
				cardaccount.setBankname(vo.getBankname());
				hpCardaccountService.save(cardaccount);
				status = Constant.IfaceStatus.SUCCESS;
				reason = "";
			}

		} else {
			status = Constant.IfaceStatus.FAILURE;
			reason = "身份认证失败";
		}

		resultVo.setStatus(status);
		resultVo.setReason(reason);
		return resultVo;
	}
	
}
