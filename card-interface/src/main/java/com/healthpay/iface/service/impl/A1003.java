package com.healthpay.iface.service.impl;

import org.springframework.stereotype.Service;

import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.QueryCardVo;
/**    
* @ClassName: A1003 
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1003 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	QueryCardVo vo;
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String healthCardId = vo.getHealthCardId();
		return checkIdentity(healthCardId);
	}
	
}
