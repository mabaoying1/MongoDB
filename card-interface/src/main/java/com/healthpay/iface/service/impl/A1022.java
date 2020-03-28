package com.healthpay.iface.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthpay.common.Constant;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.service.MainBusinessService;
import com.healthpay.iface.util.IBaseModel;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.ApplycardUpdateVo;
import com.healthpay.iface.vo.ApplycardVo;
import com.healthpay.iface.vo.CardPasswordVo;
import com.healthpay.iface.vo.OpenPayVo;
import com.healthpay.iface.vo.QueryCardHolderVo;
import com.healthpay.iface.vo.QueryCardVo;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.RealCardCancelVo;
import com.healthpay.iface.vo.RealCardVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.iface.vo.ReturnVo;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.entity.HpApplycardHis;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.iface.entity.HpIfaceAddress;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
/**    
* @ClassName: A1022
* @Description: 实体健康卡注销/挂失/解除挂失
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1022 extends EHCAbstractHandlerImpl{
	@Autowired
	private MainBusinessService mainBusinessService;
	@BsoftCustomField
	RealCardCancelVo realCardCancelVo;
	
	
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		ResResultVo resultVo = null;
		Boolean isSend=true;
		String reason = "";
		if (StringUtils.isEmpty(realCardCancelVo.getHealthCardId())
				&& StringUtils.isEmpty(realCardCancelVo.getIcCardId())) {
			if (StringUtils.isEmpty(realCardCancelVo.getNationality())
					|| StringUtils.isEmpty(realCardCancelVo.getDocuId())
					|| StringUtils.isEmpty(realCardCancelVo.getDocuType())) {
				reason = "(国籍，证件类型，证件号码)或健康卡号或实体卡卡号不能同时为空";
				throw new BusException(reason);
			}
		}
		if (null == realCardCancelVo.getOptType()) {
			throw new BusException("操作类型不能为空");
		}
		// 如果为解挂，实体卡不能为空
		if (2 == realCardCancelVo.getOptType().intValue() && StringUtils.isEmpty(realCardCancelVo.getIcCardId())) {
			throw new BusException("实体卡卡号不能为空");
		}

		if (1 == realCardCancelVo.getType().intValue()) {
			throw new BusException("社保卡不允许注销/挂失/解挂操作");
		}
		Integer type = realCardCancelVo.getType();
		String healthCardId = realCardCancelVo.getHealthCardId();
		String icCardid = realCardCancelVo.getIcCardId();
		String identityId = null;
		if (StringUtils.isEmpty(healthCardId) && StringUtils.isEmpty(icCardid)) {
			QueryIdVo vo = new QueryIdVo();
			MyBeanUtils.copyBeanNotNull2Bean(realCardCancelVo, vo);
			resultVo = mainBusinessService.doA1002(vo);
			if (null == resultVo || "2".equals(resultVo.getStatus())) {
				throw new BusException("没有找到相应的健康卡信息");
			}
			healthCardId = resultVo.getHealthCardId();
			identityId = resultVo.getDocuType() + resultVo.getDocuId();
		}
		HpRealCard hpRealCard = null;
		if (StringUtils.isNotEmpty(icCardid)) {
			if (null == type) {
				reason = "实体卡类型不能为空";
				throw new BusException(reason);
			}

			// if(realCardCancelVo.getOptType().intValue()==2){//解挂
			// hpRealCard = hpRealCardService.getHealthCardId(icCardid,
			// 2,realCardCancelVo.getType());
			// }else{
			// hpRealCard = hpRealCardService.getHealthCardIdTwo(icCardid,
			// type);
			// }
			// 获取对应健康卡数据-------注：健康卡注销即为作废，因此数据是唯一的
			if (type == 0) {
				hpRealCard = hpRealCardService.getRealCard(icCardid, type);
			}
			// 获取对应社保卡数据------注：社保卡注销后能再启用，查询时取非注销的社保卡数据
			if (type == 1) {
				hpRealCard = hpRealCardService.getHealthCardIdNotCancel(icCardid, type);
			}
			if (null == hpRealCard) {
				reason = "不存在的实体卡卡号";
				throw new BusException(reason);
			}
			// 如果解挂，需要判断该实体卡是否已经挂失
			if (2 == realCardCancelVo.getOptType().intValue() && 2 != hpRealCard.getStatus().intValue()) {
				throw new BusException("该实体卡没有挂失，不能解挂操作");
			}
			//如果挂失，需要判断该实体卡是否正常
			if (1 == realCardCancelVo.getOptType().intValue() && 1 != hpRealCard.getStatus().intValue()) {
				String statusStr = "";
				if (0 == hpRealCard.getStatus().intValue()) {
					statusStr = "已注销";
				}
				if (2 == hpRealCard.getStatus().intValue()) {
					statusStr = "已挂失";
				}
				throw new BusException("该实体卡" + statusStr);
			}
			//如果注销，需要判断是否已经注销
			if (0 == realCardCancelVo.getOptType().intValue() && 0 == hpRealCard.getStatus().intValue()) {
				throw new BusException("该实体卡已注销");
			}
			//获取健康卡记录
			HpHealthcard card = hpHealthcardService.get(hpRealCard.getCardPkid());
			healthCardId = card.getPkid();
			identityId = card.getIdentityId();
		} else {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			if (null != card) {
				// 如果是解挂，肯定会有实体卡卡号，
				// 如果是注销或挂失，先要判断该健康e卡号下是否具有有效的实体卡
				identityId = card.getIdentityId();
				HpRealCard hpRealCardTmp = hpRealCardService.getIcCardId(card.getPkid(), 1L,
						realCardCancelVo.getType());
				if (null == hpRealCardTmp) {
					throw new BusException("该健康e卡没有绑定有效的实体卡");
				}
			} else {
				reason = "健康e卡号不存在";
				throw new BusException(reason);
			}
		}
		// 判断操作类型
		Integer status = 0;
		Integer oldStatus = 0;
		switch (realCardCancelVo.getOptType()) {
		case 0:// 注销
			status = 0;//注销
			oldStatus = 1;
			break;
		case 1:// 挂失
			status = 2;//挂失
			oldStatus = 1;
			break;
		case 2:// 解挂
			status = 1;//正常
			oldStatus = 2;
			// 需要判断能否解挂
			HpRealCard hpRealCardTmp = hpRealCardService.getIcCardId(healthCardId, status, realCardCancelVo.getType());
			if (null != hpRealCardTmp) {
				throw new BusException("已经具有有效的实体卡，不能解挂");
			}
			break;
		}
		// int ret = hpRealCardService.updateRealCardStatus(healthCardId,
		// status,oldStatus, realCardCancelVo.getType());
		//更新实体卡状态
		int ret = hpRealCardService.updateRealCardStatusByPkid(hpRealCard.getPkid().toString(), status);
		Integer retStatus = 0;
		ReturnVo returnVo = new ReturnVo();
		if (ret > 0) {
			// HpIfaceMerchant gateway =
			// hpIfaceMerchantService.getGateway("支付网关",99);
			retStatus = 1;
			// --------------------银联异步--------------------
			// switch (realCardCancelVo.getOptType()) {
			// case 0://注销
			// //把传送给网银的实体卡解绑记录写入队列表
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1004");
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId(realCardCancelVo.getIcCardId());
			// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCardCancelVo.getType())));
			// hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
			// hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
			// break;
			// case 1://挂失
			// //把传送给网银的实体卡挂失记录写入队列表
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1005");
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId(realCardCancelVo.getIcCardId());
			// hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
			// hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
			// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCardCancelVo.getType())));
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
			// break;
			// case 2://解挂
			// //把传送给网银的实体卡解挂记录写入队列表
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1006");
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId(realCardCancelVo.getIcCardId());
			// hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
			// hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
			// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCardCancelVo.getType())));
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
			// break;
			// }

			// 需要向已绑定的医院发送注销/挂失/解挂指令
			List<String> list = hpMerCardlistService.getHpMerCardlisByIccardId(icCardid, healthCardId);
			String funcId = "A2002";//实体健康卡卡注销/挂失/解除挂失通知（卡管HIS）
			for (String merid1 : list) {
				//接口地址
				HpIfaceAddress hpIfaceAddress = hpIfaceAddressService.getIfaceAddressByMerid(merid1, funcId);
				if (null == hpIfaceAddress)
					continue;
				HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
				hpIfaceMsgqueue.setMerId(appId);
				hpIfaceMsgqueue.setPosturl(hpIfaceAddress.getFuncAddress());
				hpIfaceMsgqueue.setHealthcardid(healthCardId);
				hpIfaceMsgqueue.setIcCardId(icCardid);
				hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
				hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
				hpIfaceMsgqueue.setNationality(realCardCancelVo.getNationality());
				hpIfaceMsgqueue.setCreatetime(new Date());
				hpIfaceMsgqueue.setFuncid(funcId);
				hpIfaceMsgqueue.setType(realCardCancelVo.getType());
				hpIfaceMsgqueue.setOptType(realCardCancelVo.getOptType());
				//保存消息队列
				hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			}
		}
		returnVo.setStatus(retStatus);
		// 像区域平台发送数据
		if (isSend) {
			HpCardholder hpCardholder = hpCardholderService.get(identityId);
			// HpRealCard realCard = hpRealCardService.getIcCardId(healthCardId,
			// 1L, 0L);
			HpRealCard realCard = hpRealCardService.getIcCardId(healthCardId, 1L, realCardCancelVo.getType());
			hpApplycardService.doSendAreaPlatform(hpCardholder, healthCardId, realCard);
		}
		return returnVo;
	}

}
