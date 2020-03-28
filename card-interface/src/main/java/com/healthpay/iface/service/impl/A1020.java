package com.healthpay.iface.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.Constant;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.CardPasswordVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpHealthcard;
/**    
* @ClassName: A1020 
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1020 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	CardPasswordVo vo;
	@Transactional
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String password = vo.getPassword();
		String status = Constant.IfaceStatus.FAILURE;
		
		// 先身份认证
		ResResultVo resultVo = checkIdentity(healthCardId);
		if (Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			if (password.equals(card.getPassword())) {
				status = Constant.IfaceStatus.SUCCESS;
			}
		}
		resultVo.setStatus(status);
		return resultVo;
	}
	
}
