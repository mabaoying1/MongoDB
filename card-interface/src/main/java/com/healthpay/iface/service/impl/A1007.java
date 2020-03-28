package com.healthpay.iface.service.impl;

import org.springframework.stereotype.Service;

import com.healthpay.common.Constant;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpHealthcard;
/**    
* @ClassName: A1007
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1007 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	QueryIdVo vo;
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String nationality = vo.getNationality();
		String docuType = vo.getDocuType();
		String docuId = vo.getDocuId();
		String status = Constant.IfaceStatus.FAILURE;
		String reason = "";
		String identityId = nationality + String.valueOf(docuType) + docuId;
		HpHealthcard card = hpHealthcardService.getByIdentityIdAndStatus(identityId, "1"); // 待激活
		// 、先默认只有一个吧
		if (card != null) {
			try {
				card.setStatus("2");
				hpHealthcardService.update(card);
				status = Constant.IfaceStatus.SUCCESS;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				status = Constant.IfaceStatus.FAILURE;
			}
		}
		return new ResResultVo(nationality, docuType, docuId, status, reason);
	}
	
}
