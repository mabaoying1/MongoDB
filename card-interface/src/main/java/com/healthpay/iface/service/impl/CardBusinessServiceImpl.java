package com.healthpay.iface.service.impl;

import com.healthpay.common.annotation.NotNull;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.utils.DbUtil;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.iface.service.CardBusinessService;
import com.healthpay.iface.service.MainBusinessService;
import com.healthpay.iface.util.Constants;
import com.healthpay.iface.util.IBaseModel;
import com.healthpay.iface.vo.*;
import com.healthpay.modules.hc.search.HpRealCardSearch;
import com.healthpay.modules.iface.entity.HpIfaceLog;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.service.HpIfaceLogService;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.sys.entity.Dict;
import com.healthpay.modules.sys.service.DictService;
import com.healthpay.modules.tools.utils.HttpPostTest;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cardBusinessService")
public class CardBusinessServiceImpl implements CardBusinessService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MainBusinessService mainBusinessService;
	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;
	@Autowired
	private HpIfaceLogService hpIfaceLogService;
	@Autowired
	private DictService dictService;

	private Object backSdata;

	public static void main(String[] args){
		String url = "http://localhost:8080/hp-cardif/hp/http/doHealth";
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><RetCode/><Timer>1479773939000</Timer><Funcid>A1001</Funcid><Merid>62853</Merid></Head><Body><Nationality>01</Nationality><DocuType>01</DocuType><DocuId>999999</DocuId><IcCardId>0999999</IcCardId><Type>0</Type><Name>E卡通</Name><Birth>20000101</Birth><Sex>1</Sex><Nation>01</Nation><Address2>工</Address2><Phone>1234</Phone><AppliDate>2016-11-22 08:18:59</AppliDate></Body></Data>";
		Map<String,String> params = new HashMap<String,String>();
		params.put("xml",xml);
		params.put("sign","3333");
		HttpPostTest.post(url,params);


	}

	@Override
	public String doBusiness(HttpServletRequest request) {
		String retcode = "0000";
		String errmsg = "";
		Object backSdata = null;
		String merid = "";
		String funcid = "";
		String retData = null;
		String xml = request.getParameter("xml");
		String sign = request.getParameter("sign");
		HeaderVo retVo = new HeaderVo();
		HpIfaceLog hpIfaceLog = new HpIfaceLog();
		hpIfaceLog.setCreateTime(DbUtil.getDate());
		try {
			String method = request.getMethod();
			if(!"POST".equals(method)){
				throw new BusException("请采用POST方式提交");
			}
			if(StringUtils.isEmpty(xml))
				throw new BusException("xml数据域不能为空");
			if(StringUtils.isEmpty(sign))
				throw new BusException("sign数据域不能为空");

			HeaderVo headerVo = new HeaderVo();
			IBaseModel.loadXml2Bean(headerVo,xml,"Head");
			hpIfaceLog.setMerId(headerVo.getMerid());
			hpIfaceLog.setFuncId(headerVo.getFuncid());
			hpIfaceLog.setSenddata(xml);
			merid = headerVo.getMerid();
			funcid = headerVo.getFuncid();
			// 验证头有效性
			this.isCheck(headerVo,xml,sign);
			// 验证非空数据

			// 业务处理
			backSdata = this.execute(merid,xml, headerVo.getFuncid(),request.getServletContext().getRealPath("/"));
		} catch (BusException e) {
			logger.error(e.getMessage(), e);
			errmsg = e.getMessage();
			retcode = "9999";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errmsg = e.getMessage();
			retcode = "9999";
		}
		// 返回消息
		retVo.setMerid(merid);
		retVo.setFuncid(funcid);
		retVo.setRetCode(retcode);
		retVo.setErrmsg(errmsg);
		retVo.setTimer(String.valueOf(System.currentTimeMillis()));// 暂时先这样吧
		try {
			retData = IBaseModel.parseBean2Xml(retVo,backSdata);
			logger.info("调用返回结果======"+retData);
		} catch (Exception e) {
			errmsg = "服务异常";
			retcode = "9999";
		}
		hpIfaceLog.setRetcode(retcode);
		hpIfaceLog.setErrmsg(errmsg);
		hpIfaceLog.setRetdata(retData);
		hpIfaceLog.setIsNewRecord(true);
		hpIfaceLogService.save(hpIfaceLog);
		return retData;
	}

	/**
	 * 验证数据有效性
	 *
	 * @param headerVo
	 * @param xml
	 * @param sign
	 * @author yyl
	 * @date 2016年6月1日 下午12:25:55
	 */
	private void isCheck(HeaderVo headerVo,String xml,String sign) {
		if (null == headerVo) {
			throw new BusException("无效数据");
		}
		// 非空校验
		if (StringUtils.isNull(headerVo.getMerid())) {
			throw new BusException("商户号为空");
		}
		if (StringUtils.isNull(headerVo.getFuncid())) {
			throw new BusException("功能码为空");
		}
		if (StringUtils.isNull(sign)) {
			throw new BusException("验签码为空");
		}
		if (StringUtils.isNull(headerVo.getTimer())) {
			throw new BusException("时间戳为空");
		}
		// 验证商户号
		// 验证商户号是否合法
		HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.getFormCache(headerVo.getMerid());
		if (null == hpIfaceMerchant) {
			throw new BusException("商户号错误");
		}
		if (null != hpIfaceMerchant.getStatus() && "0".equals(hpIfaceMerchant.getStatus())) {
			throw new BusException("商户已停用");
		}
		// 时间戳验证
		long curTime = System.currentTimeMillis();
		long sendTime = Long.valueOf(headerVo.getTimer());
		if ((curTime - sendTime) > 300000) {
			throw new BusException("时间戳有误，时间戳误差不能大于5分钟");
		}
		// 验签码验证
		String curSign = StringUtils.getMD5Code(hpIfaceMerchant.getDigitalKey() + headerVo.getFuncid() + headerVo.getTimer()
				+ xml);
		logger.error("原文为:"+ hpIfaceMerchant.getDigitalKey() + headerVo.getFuncid() + headerVo.getTimer()
				+ xml);
		logger.error("当前Sign为:"+curSign);
		logger.error("接收到的Sign为:"+sign);
		if (!curSign.equalsIgnoreCase(sign)) {
			throw new BusException("验签失败，请检查数据签名码是否正确");
		}
		// 验证数据非空
	}


	// 验证数据非空
	private void isCheckData(Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			NotNull notNullCheck = field.getAnnotation(NotNull.class);
			if (notNullCheck != null) {
				try {
					Object val = FieldUtils.readField(field, object, true);
					if (val == null || StringUtils.isBlank(String.valueOf(val))) {
						throw new BusException("字段：["+notNullCheck.name() + "]不能为空");
					}
				} catch (IllegalAccessException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		for (Method method : object.getClass().getDeclaredMethods()) {
			NotNull notNullCheck = method.getAnnotation(NotNull.class);
			if (notNullCheck != null) {
				try {
					String name = method.getName().substring(3);
					String field = name.substring(0, 1).toLowerCase() + name.substring(1);
					// Method m = object.getClass().getDeclaredMethod("get" +
					// name);
					// Object val = m.invoke(object);
					Object val = MethodUtils.invokeMethod(object, "get" + name, null);
					if (val == null || StringUtils.isBlank(String.valueOf(val))) {
						throw new BusException("字段：["+notNullCheck.name() + "]不能为空");
					}
				} catch (IllegalAccessException e) {
					logger.error(e.getMessage(), e);
				} catch (NoSuchMethodException e) {
					logger.error(e.getMessage(), e);
				} catch (SecurityException e) {
					logger.error(e.getMessage(), e);
				} catch (IllegalArgumentException e) {
					logger.error(e.getMessage(), e);
				} catch (InvocationTargetException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 业务处理
	 *
	 * @param merid
	 * @param xml
	 * @param type
	 * @return
	 * @author yyl
	 * @throws Exception
	 * @throws BusException,FileUploadException
	 * @date 2016年6月1日 下午1:33:59
	 */
	private Object execute(String merid,String xml,String type,String realPath)
			throws  Exception {
		Object backSdata = null;
		if (Constants.A1001.equals(type)) {
			ApplycardVo vo = new ApplycardVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
//			String result = this.checkApplyData(vo);
//			if(org.apache.commons.lang3.StringUtils.isNotBlank(result)){
//				throw new BusException(result);
//			}
			backSdata = mainBusinessService.doA1001(merid,vo ,realPath);
		} else if (Constants.A1002.equals(type)) {
			QueryIdVo vo = new QueryIdVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1002(vo);
		} else if (Constants.A1003.equals(type)) {
			QueryCardVo vo = new QueryCardVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1003(vo);
		} else if (Constants.A1004.equals(type)) {
			OpenPayVo vo = new OpenPayVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1004(vo);
		} else if (Constants.A1005.equals(type)) {
			QueryIdVo vo = new QueryIdVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1005(vo);
		} else if (Constants.A1006.equals(type)) {
			ApplycardUpdateVo vo = new ApplycardUpdateVo();
			IBaseModel.loadXml2Bean(vo, xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1006(vo);
		} else if (Constants.A1007.equals(type)) {
			QueryIdVo vo = new QueryIdVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1007(vo);
		} else if (Constants.A1019.equals(type)) {
			QueryCardHolderVo vo = new QueryCardHolderVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1019(vo,merid);
		} else if (Constants.A1018.equals(type)) {
			CardPasswordVo vo = new CardPasswordVo();
			IBaseModel.loadXml2Bean(vo,xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1018(vo);
		} else if (Constants.A1020.equals(type)) {
			CardPasswordVo vo = new CardPasswordVo();
			IBaseModel.loadXml2Bean(vo, xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1020(vo);
		} else if (Constants.A1021.equals(type)){
			RealCardVo vo = new RealCardVo();
			IBaseModel.loadXml2Bean(vo, xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1021(vo,merid);
		} else if (Constants.A3001.equals(type)) {
			QueryAreaVo vo = new QueryAreaVo();
			IBaseModel.loadXml2Bean(vo, xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA3001(vo);
		} else if (Constants.A1022.equals(type)){
			RealCardCancelVo realCardCancelVo = new RealCardCancelVo();
			IBaseModel.loadXml2Bean(realCardCancelVo,xml);
			this.isCheckData(realCardCancelVo);
			backSdata = mainBusinessService.doA1022(realCardCancelVo,true);
		}else if(Constants.A1023.equals(type)){
			backSdata = mainBusinessService.doA1023();
		}else if(Constants.A1024.equals(type)){
			HpRealCardSearch search = new HpRealCardSearch();
			IBaseModel.loadXml2Bean(search,xml);
			this.isCheckData(search);
			backSdata = mainBusinessService.doA1024(search);
		}else if (Constants.A1025.equals(type)){
			RealCardVo vo = new RealCardVo();
			IBaseModel.loadXml2Bean(vo, xml);
			this.isCheckData(vo);
			backSdata = mainBusinessService.doA1025(vo,merid);
		}
		return backSdata;
	}

	private String checkApplyData(ApplycardVo vo) {
		String result = "";
		boolean check = false;
		List<Dict> list = dictService.findByType("nationality");
		for(Dict dict:list){
			if(dict.getValue().equals(vo.getNationality())){
				check = true;
				break;
			}
		}
		if(check==false){
			result = "国籍输入不正确";
			return result;
		}

		check = false;
		list = dictService.findByType("std_nation");
		for(Dict dict:list){
			if(dict.getValue().equals(vo.getNation())){
				check = true;
				break;
			}
		}
		if(check==false){
			result = "民族输入不正确";
			return result;
		}

		check = false;
		list = dictService.findByType("std_id_type");
		for(Dict dict:list){
			if(dict.getValue().equals(vo.getDocuType())){
				check = true;
				break;
			}
		}
		if(check==false){
			result = "证件类型输入不正确";
			return result;
		}

		if(!org.apache.commons.lang3.StringUtils.isBlank(vo.getIcCardId())&&
				vo.getType()!=null) {
			check = false;
			list = dictService.findByType("card_type");
			for (Dict dict : list) {
				if (dict.getValue().equals(String.valueOf(vo.getType()))) {
					check = true;
					break;
				}
			}
			if (check == false) {
				result = "实体卡类型输入不正确";
				return result;
			}
		}

		check = false;
		list = dictService.findByType("sex");
		for(Dict dict:list){
			if(dict.getValue().equals(String.valueOf(vo.getSex()))){
				check = true;
				break;
			}
		}
		if(check==false){
			result = "性别输入不正确";
			return result;
		}

		return result;
	}
}
