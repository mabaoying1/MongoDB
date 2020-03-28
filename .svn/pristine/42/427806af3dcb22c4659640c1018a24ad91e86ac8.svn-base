package com.healthpay.iface.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthpay.iface.service.MainBusinessService;
import com.healthpay.iface.util.IBaseModel;
import com.healthpay.iface.vo.ApplycardUpdateVo;
import com.healthpay.iface.vo.ApplycardVo;
import com.healthpay.iface.vo.CardPasswordVo;
import com.healthpay.iface.vo.MerchantListVo;
import com.healthpay.iface.vo.MerchantVo;
import com.healthpay.iface.vo.OpenPayVo;
import com.healthpay.iface.vo.QueryCardHolderVo;
import com.healthpay.iface.vo.QueryCardVo;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.RealCardCancelVo;
import com.healthpay.iface.vo.RealCardVo;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
/**    
* @ClassName: A1023
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1023 extends EHCAbstractHandlerImpl{
	
	
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		HpIfaceMerchant hpIfaceMerchant = new HpIfaceMerchant();
		List<MerchantVo> merchantList = new ArrayList<>();
		MerchantListVo merchantListVo = new MerchantListVo();
		hpIfaceMerchant.setSource(1);
		// 获取类型为医院的商户
		List<HpIfaceMerchant> hpIfacemerchantList = hpIfaceMerchantService.findList(hpIfaceMerchant);
		for (HpIfaceMerchant merchant : hpIfacemerchantList) {
			MerchantVo temp = new MerchantVo();
			temp.setMerId(merchant.getMerId());
			temp.setMerName(merchant.getMerName());
			merchantList.add(temp);
		}
		merchantListVo.setMerchantVoList(merchantList);
		return merchantListVo;
	}
	
}
