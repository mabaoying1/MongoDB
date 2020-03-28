package com.healthpay.iface.service.impl;

import org.springframework.stereotype.Service;

import com.healthpay.common.Constant;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.CardPasswordVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpHealthcard;
/**    
* @ClassName: A1018 
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1018 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	CardPasswordVo vo;
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String status = Constant.IfaceStatus.FAILURE;
		// 先身份认证
		ResResultVo resultVo = checkIdentity(healthCardId);
		if (Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			card.setPassword(vo.getPassword());
			hpHealthcardService.update(card);
			status = Constant.IfaceStatus.SUCCESS;
		}
		resultVo.setStatus(status);
		return resultVo;
	}
	
}
