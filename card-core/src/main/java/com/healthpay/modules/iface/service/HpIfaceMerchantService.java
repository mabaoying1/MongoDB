/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.healthpay.modules.iface.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthpay.common.utils.CacheConstants;
import com.healthpay.common.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.healthpay.common.entity.ProRequest;
import com.healthpay.common.entity.ProResponse;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.common.service.proCard.IPCardService;
import com.healthpay.common.service.proCard.impl.ProCardServiceImpl;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.dao.HpIfaceMerchantDao;

/**
 * 商户管理Service
 * @author gyp
 * @version 2016-06-14
 */
@Service
@Transactional(readOnly = true)
public class HpIfaceMerchantService extends CrudService<HpIfaceMerchantDao, HpIfaceMerchant> {
	@Autowired
	private HpIfaceMerchantDao hpIfaceMerchantDao;
	
	@Autowired
	private IPCardService proCardService;
	@Value("${appId}")
	private String appId;
	@Value("${appSecret}")
	private String appSecret;
	
	
	public HpIfaceMerchant get(String merId) {
		return super.get(merId);
	}

	public List<HpIfaceMerchant> findList(HpIfaceMerchant hpIfaceMerchant) {
		return super.findList(hpIfaceMerchant);
	}

	public Page<HpIfaceMerchant> findPage(Page<HpIfaceMerchant> page, HpIfaceMerchant hpIfaceMerchant) {
		return super.findPage(page, hpIfaceMerchant);
	}

	@Transactional(readOnly = false)
	public void saveHpIfaceMerchant(HpIfaceMerchant hpIfaceMerchant) throws Exception {
		Map<String, Object> content = new HashMap<>();
		content.put("orgname", hpIfaceMerchant.getMerName());//机构名称
		content.put("orgcode", hpIfaceMerchant.getOrgCode());//机构编码
		ProRequest proRequest = new ProRequest();
		proRequest.setApp_id(appId);
		proRequest.setMethod("ehc.ehealthcode.queryorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
		proRequest.setBiz_content(JSONObject.toJSONString(content));//请求参数集合
		ProResponse proResponse = proCardService.process(proRequest,appSecret);
		
		//如果查询失败则注册
		if(!"10".equals(proResponse.getCode())){  //10成功   00失败
			
			Map<String, Object> content1 = new HashMap<>();
			content1.put("orgname", hpIfaceMerchant.getMerName());//机构名称
			content1.put("orgcode", hpIfaceMerchant.getOrgCode());//机构编码
			content1.put("orgproperty", hpIfaceMerchant.getOrgProperty());//机构性质
			content1.put("accessmethod", hpIfaceMerchant.getAccessMethod());//接入方式
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String validtime=sdf.format(hpIfaceMerchant.getValidTime());
			content1.put("validtime", validtime);//有效时间
			content1.put("orgaddr", hpIfaceMerchant.getOrgAddr().getCode());//机构行政区划
			content1.put("orglevel", hpIfaceMerchant.getOrgLevel());//机构等级
			content1.put("orghierarchy", hpIfaceMerchant.getOrgHierarchy());//机构等次
			
			ProRequest proRequest1 = new ProRequest();
			proRequest1.setApp_id(appId);
			proRequest1.setMethod("ehc.ehealthcode.addorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
			proRequest1.setBiz_content(JSONObject.toJSONString(content1));//请求参数集合
			
			proResponse = proCardService.process(proRequest1,appSecret);
			if(!"10".equals(proResponse.getCode())){  //10成功   00失败
				throw new BusException(proResponse.getMessage());
			}
		}
	
		JSONObject jsonObject=JSONObject.parseObject(proResponse.getBiz_content());
		String merId=jsonObject.getString("app_id");//商户appid
		String digitalKey=jsonObject.getString("app_secret");//商户密钥
		
		hpIfaceMerchant.setMerId(merId);
		hpIfaceMerchant.setDigitalKey(digitalKey);
		
		//super.save(hpIfaceMerchant);
		hpIfaceMerchantDao.insert(hpIfaceMerchant);
		//新增的商户放到缓存中
//		Map<String,HpIfaceMerchant> merchantMap =null;
//		if(CacheUtils.get(CacheConstants.CACHE_MERCHANT_MAP) == null){
//			merchantMap = new HashMap<String,HpIfaceMerchant>();
//		}else{
//			merchantMap = (Map<String, HpIfaceMerchant>) CacheUtils.get(CacheConstants.CACHE_MERCHANT_MAP);
//		}
//		merchantMap.put(hpIfaceMerchant.getMerId(),hpIfaceMerchant);
//		CacheUtils.put(CacheConstants.CACHE_MERCHANT_MAP,merchantMap);
		this.saveToCache(hpIfaceMerchant);
	}

	public void updateHpIfacemerchant(HpIfaceMerchant hpIfaceMerchant) throws Exception {
		
		Map<String, Object> content1 = new HashMap<>();
		content1.put("orgname", hpIfaceMerchant.getMerName());//机构名称
		content1.put("orgcode", hpIfaceMerchant.getOrgCode());//机构编码
		content1.put("orgproperty", hpIfaceMerchant.getOrgProperty());//机构性质
		content1.put("accessmethod", hpIfaceMerchant.getAccessMethod());//接入方式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String validtime=sdf.format(hpIfaceMerchant.getValidTime());
		content1.put("validtime", validtime);//有效时间
		content1.put("orgaddr", hpIfaceMerchant.getOrgAddr().getCode());//机构行政区划
		content1.put("orglevel", hpIfaceMerchant.getOrgLevel());//机构等级
		content1.put("orghierarchy", hpIfaceMerchant.getOrgHierarchy());//机构等次
		
		ProRequest proRequest1 = new ProRequest();
		proRequest1.setApp_id(appId);
		proRequest1.setMethod("ehc.ehealthcode.updateorg");//接口名称ehc.ehealthcard.addorg  ehc.ehealthcode.addorg
		proRequest1.setBiz_content(JSONObject.toJSONString(content1));//请求参数集合
		
		ProResponse proResponse = proCardService.process(proRequest1,appSecret);
		if(!"10".equals(proResponse.getCode())){  //10成功   00失败
			throw new BusException(proResponse.getMessage());
		}
		JSONObject jsonObject=JSONObject.parseObject(proResponse.getBiz_content());
		String merId=jsonObject.getString("app_id");//商户appid
		String digitalKey=jsonObject.getString("app_secret");//商户密钥
		
		hpIfaceMerchant.setMerId(merId);
		hpIfaceMerchant.setDigitalKey(digitalKey);
		
		
		hpIfaceMerchantDao.update(hpIfaceMerchant);
		this.saveToCache(hpIfaceMerchant);
	}

	private void saveToCache(HpIfaceMerchant hpIfaceMerchant){
		Map<String,HpIfaceMerchant> merchantMap =null;
		if(CacheUtils.get(CacheConstants.CACHE_MERCHANT_MAP) == null){
			merchantMap = new HashMap<String,HpIfaceMerchant>();
		}else{
			merchantMap = (Map<String, HpIfaceMerchant>) CacheUtils.get(CacheConstants.CACHE_MERCHANT_MAP);
		}
		merchantMap.put(hpIfaceMerchant.getMerId(),hpIfaceMerchant);
		CacheUtils.put(CacheConstants.CACHE_MERCHANT_MAP,merchantMap);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpIfaceMerchant hpIfaceMerchant) {
		super.delete(hpIfaceMerchant);
	}
	
	public List<HpIfaceMerchant> findAllList(HpIfaceMerchant hpIfaceMerchant){
		return hpIfaceMerchantDao.findAllList(hpIfaceMerchant);
	}
	
	public HpIfaceMerchant getFormCache(String merid){
		Map<String,HpIfaceMerchant> merchantMap =(Map<String,HpIfaceMerchant>)CacheUtils.get(CacheConstants.CACHE_MERCHANT_MAP);

		HpIfaceMerchant merchant = null;
		if(null != merchantMap && null != merchantMap.get(merid)) {
			merchant = merchantMap.get(merid);
		}else{
			merchant = this.get(merid);
			if(merchant!=null) {
				this.saveToCache(merchant);
			}
		}
		return merchant;


	}

	public HpIfaceMerchant getGateway(String merName,int source){
		HpIfaceMerchant gateway = hpIfaceMerchantDao.getGateway(merName,source);
		return gateway;
	}

	public HpIfaceMerchant getMerchantByOrgCode(String orgCode) {
		HpIfaceMerchant merchant=hpIfaceMerchantDao.getMerchantByOrgCode(orgCode);
		return merchant;
	}


}