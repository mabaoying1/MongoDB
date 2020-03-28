package com.healthpay.iface.service.impl;

import com.healthpay.common.exception.BusException;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.QueryParamVO;
import com.healthpay.modules.hc.entity.HpYkjlXnk;
import com.healthpay.modules.hc.service.HpYkjlXnkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
* @ClassName: A1030
* @Description: 电子健康卡用卡记录上传
* @author mabaoying
* @date 2019年8月28日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1030 extends EHCAbstractHandlerImpl{

	@BsoftCustomField
	QueryParamVO vo;
	@Autowired
	private HpYkjlXnkService hpYkjlXnkService;


	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {

		if(StringUtils.isBlank(vo.getHealthCardId())){
			throw new BusException("电子健康卡ID不能为空!");
		}
		if(StringUtils.isBlank(vo.getMedstepCode())){
			throw new BusException("诊疗环节代码不能为空!");
		}
		if(StringUtils.isBlank(vo.getTerminalCode())){
			throw new BusException("扫码枪终端标识号不能为空!");
		}
		if(StringUtils.isBlank(vo.getChannelCode())){
			throw new BusException("刷卡终端类型编号不能为空!");
		}
		if(null==vo.getUseTime()){
			throw new BusException("用卡时间不能为空!");
		}

		try{
			//保存电子健康卡用卡记录
			HpYkjlXnk hpYkjlXnk=new HpYkjlXnk();
			hpYkjlXnk.seteHealthCardId(vo.getHealthCardId());
			hpYkjlXnk.setMedstepCode(vo.getMedstepCode());
			hpYkjlXnk.setTerminalCode(vo.getTerminalCode());
			hpYkjlXnk.setChannelCode(vo.getChannelCode());
			hpYkjlXnk.setUseTime(vo.getUseTime());
			hpYkjlXnk.setMerId(appId);
			hpYkjlXnkService.addYkjlXnk(hpYkjlXnk);
		}catch (Exception e){
			throw new BusException("服务异常!");
		}
		return null;
	}
	
}
