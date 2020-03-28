package com.healthpay.iface.service.impl;

import com.healthpay.iface.service.MainBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthpay.common.Constant;
import com.healthpay.common.persistence.Page;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.entity.HpApplycardHis;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
/**    
* @ClassName: A1002 
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1002 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	public QueryIdVo vo ;
	@Autowired
	private MainBusinessService mainBusinessService;
	
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {

		return mainBusinessService.doA1002(vo);
	}
	
}
