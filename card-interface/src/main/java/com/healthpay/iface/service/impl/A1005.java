package com.healthpay.iface.service.impl;

import com.healthpay.iface.service.MainBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthpay.common.Constant;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.entity.HpRealCard;
/**    
* @ClassName: A1005 
* @Description: 查询健康卡相关数据
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1005 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	QueryIdVo vo;

	@Autowired
	private MainBusinessService mainBusinessService;

	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		backSdata = mainBusinessService.doA1005(vo);
		return backSdata;
	}
	
}
