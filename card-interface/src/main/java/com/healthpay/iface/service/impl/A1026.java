package com.healthpay.iface.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.healthpay.common.entity.ProRequest;
import com.healthpay.common.entity.ProResponse;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.service.proCard.IPCardService;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.RandomUtils;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.QueryParamVO;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.service.HpHealthcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: A1026
* @Description:  获取电子健康卡二维码
* @author mabaoying
* @date 2019年8月13日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1026 extends EHCAbstractHandlerImpl{

	@BsoftCustomField
	QueryParamVO vo;
	@Autowired
	HpHealthcardService hpHealthcardService;
	@Autowired
	private IPCardService proCardService;

	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {

		String healthCardId=vo.getHealthCardId();//电子健康卡id
		String ewmbs=vo.getEwmbs();//二维码标识
		String token=vo.getToken();//金融支付数据

		if(StringUtils.isBlank(healthCardId)){
			throw new BusException("电子健康卡id不能为空!");
		}

		if(StringUtils.isBlank(ewmbs)){
			throw new BusException("二维码标识不能为空!");
		}

		try{
			//省卡管获取电子健康卡二维码
			logger.info("省卡管获取电子健康卡二维码");
			Map<String, Object> content = new HashMap<>();
			content.put("ehealth_card_id", healthCardId);//电子健康卡ID
			content.put("ewmbs", ewmbs);//二维码标识
			content.put("token", token);//金融支付数据
			content.put("generate_no", RandomUtils.getAlterNo());//外部生成流水
			content.put("generate_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));//外部生成时间
			ProRequest proRequest = new ProRequest();
			proRequest.setApp_id(appId);//应用编号
			proRequest.setMethod("ehc.ehealthcode.generate");//接口名称
			proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
			ProResponse proResponse = proCardService.process(proRequest,appSecret);//发送请求
			//请求失败
			if(!"10".equals(proResponse.getCode())){  //10成功   00失败
				throw new BusException(proResponse.getMessage());
			}
			JSONObject jsonObject = JSONObject.parseObject(proResponse.getBiz_content());
			String qrCode=jsonObject.getString("ehealth_code");//二维码字符串
			ResResultVo vo=new ResResultVo();
			vo.setQrCode(qrCode);
			return vo;
		}catch(Exception e){
			throw new BusException(e.getMessage());
		}
	}
}
