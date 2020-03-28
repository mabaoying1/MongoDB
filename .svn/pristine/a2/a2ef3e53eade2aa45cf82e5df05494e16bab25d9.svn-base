package com.healthpay.modules.sys.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.google.common.collect.Lists;
import com.healthpay.common.utils.CacheConstants;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.common.utils.SpringContextHolder;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.entity.HpIfacePlatform;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfacePlatformService;
import com.healthpay.modules.sys.dao.DictDao;
import com.healthpay.modules.sys.entity.Dict;

public class WebContextListener extends ContextLoaderListener {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		loadCache();
	}

	private void loadCache() {
		// 开始加载缓存
		logger.info("================开始加载数据字典===========================");
		DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
		Map<String, List<Dict>> dictMap = new HashMap<String, List<Dict>>();
		List<Dict> dictList = dictDao.findAllList(new Dict());
		for (Dict dict : dictList) {
			List<Dict> list = dictMap.get(dict.getType());
			if (null == list) {
				list = Lists.newArrayList();
				dictMap.put(dict.getType(), list);
			}
			list.add(dict);
		}
		CacheUtils.put(CacheConstants.CACHE_DICT_MAP, dictMap);
		logger.info("================加载数据字典结束===========================");

		logger.info("================开始加载商户数据===========================");
		HpIfaceMerchantService hpIfaceMerchantService = SpringContextHolder.getBean(HpIfaceMerchantService.class);
		Map<String, HpIfaceMerchant> merchantMap = new HashMap<String, HpIfaceMerchant>();
		List<HpIfaceMerchant> merchantList = hpIfaceMerchantService.findAllList(new HpIfaceMerchant());
		for (HpIfaceMerchant hpIfaceMerchant : merchantList) {
			merchantMap.put(hpIfaceMerchant.getMerId(), hpIfaceMerchant);
		}
		CacheUtils.put(CacheConstants.CACHE_MERCHANT_MAP, merchantMap);
		logger.info("================加载商户数据结束===========================");

		logger.info("================开始区域平台数据===========================");
		HpIfacePlatformService hpIfacePlatformService = SpringContextHolder.getBean(HpIfacePlatformService.class);
		Map<String, HpIfacePlatform> platformMap = new HashMap<>();
		List<HpIfacePlatform> platformList = hpIfacePlatformService.findAllList(new HpIfacePlatform());
		for (HpIfacePlatform hpIfacePlatform : platformList) {
			platformMap.put(hpIfacePlatform.getPkid(), hpIfacePlatform);
		}
		CacheUtils.put(CacheConstants.CACHE_PLATFORM_MAP, platformMap);
		logger.info("================加载区域平台数据结束===========================");
	}

}
