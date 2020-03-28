package com.healthpay.iface.service.impl;


import org.springframework.stereotype.Service;
import com.healthpay.common.exception.BusException;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.vo.RealCardVo;
import com.healthpay.iface.vo.ReturnVo;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpRealCard;
/**    
* @ClassName: A1025
* @Description: 
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1025 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	RealCardVo vo ;
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		// 先查询有效的实体卡卡号
		HpRealCard realCard = hpRealCardService.getIcCardId(vo.getHealthCardId(), 1L, vo.getType());
		HpRealCard hpRealCard = new HpRealCard();
		ReturnVo returnVo = new ReturnVo();
		if (null != realCard) {
			if (realCard.getIccardid().equals(vo.getIcCardId())) {
				// 清空绑定的健康卡数据
				hpRealCard.setCardPkid(null);
				hpRealCard.setPkid(realCard.getPkid());
				hpRealCard.setIccardid(vo.getIcCardId());
				hpRealCard.setType(0L);
				hpRealCard.setStatus(0L);
				hpRealCard.setStr03(null);// str03存放商户id
				hpRealCardService.save(hpRealCard);
				returnVo.setStatus(1);
			} else {
				throw new BusException("绑定的实体卡信息有误");
			}
		} else {
			throw new BusException("该卡不存在获未绑定实体卡");
		}
		// 向区域平台发送数据
		HpCardholder hpCardholder = new HpCardholder();
		hpApplycardService.doSendAreaPlatform(hpCardholder, hpRealCard.getCardPkid(), hpRealCard);

		// --------------------银联异步--------------------
		// 把传送给网银的绑定记录写入队列表
		// HpIfaceMerchant gateway =
		// hpIfaceMerchantService.getGateway("支付网关",99);
		// if(gateway!=null){
		// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
		// hpIfaceMsgqueue.setMerId(null);
		// hpIfaceMsgqueue.setFuncid("P1004");
		// hpIfaceMsgqueue.setIcCardId(hpRealCard.getIccardid());
		// hpIfaceMsgqueue.setType(0);
		// hpIfaceMsgqueue.setHealthcardid(null);
		// hpIfaceMsgqueue.setDocuid(null);
		// hpIfaceMsgqueue.setDocutype(null);
		// hpIfaceMsgqueue.setCreatetime(new Date());
		// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
		// }
		return returnVo;
	}
	
}
