package com.healthpay.modules.sys.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.healthpay.common.utils.CacheConstants;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.common.utils.SpringContextHolder;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.sys.dao.AreaDao;
import com.healthpay.modules.sys.entity.Area;

public class AreaUtils {
	
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

	
	
	public static Area getArea(String code){
		@SuppressWarnings("unchecked")
		Map<String, Area> areaMap = (Map<String, Area>) CacheUtils.get(CacheConstants.CACHE_AREA_ALL_MAP);
		Area area = null;
		if(null != areaMap &&  null != areaMap.get(CacheConstants.CACHE_AREA_MAP+"_"+code)){
			return areaMap.get(CacheConstants.CACHE_AREA_MAP+"_"+code);
		}
		if(null == areaMap)
			areaMap = Maps.newHashMap();

		area = areaDao.getByCode(code);
		if(null != area){
			areaMap.put(CacheConstants.CACHE_AREA_MAP+"_"+area.getCode(), area);
			CacheUtils.remove(CacheConstants.CACHE_AREA_ALL_MAP);
			CacheUtils.put(CacheConstants.CACHE_AREA_ALL_MAP,areaMap);
		}
        return area;
	}
	
	
	
	public static List<Area> getAreaListByParentId(String parentId){
		@SuppressWarnings("unchecked")
		Map<String, List<Area>> areaMap = (Map<String, List<Area>>)CacheUtils.get(CacheConstants.CACHE_AREA_ALL_MAP);
		if (StringUtils.isNull(parentId)) {
			parentId = "0";
		}
		if (null == areaMap){
			areaMap = Maps.newHashMap();
			List<Area> list = areaDao.findListByParent(parentId);
			if (null != list && !list.isEmpty()) {
				areaMap.put(CacheConstants.CACHE_AREA_MAP+"_"+parentId, list);
			}
			CacheUtils.put(CacheConstants.CACHE_AREA_ALL_MAP, areaMap);
		}else {
			List<Area> areaList = areaMap.get(CacheConstants.CACHE_AREA_MAP+"_"+parentId);
			if (null == areaList) {
				List<Area> list = areaDao.findListByParent(parentId);
				if (null != list && !list.isEmpty()) {
					areaMap.put(CacheConstants.CACHE_AREA_MAP+"_"+parentId, list);
					CacheUtils.remove(CacheConstants.CACHE_AREA_ALL_MAP);
					CacheUtils.put(CacheConstants.CACHE_AREA_ALL_MAP, areaMap);
				}
			}
		}
		List<Area> areaList = areaMap.get(CacheConstants.CACHE_AREA_MAP+"_"+parentId);
		if (areaList == null){
			areaList = Lists.newArrayList();
		}
		return areaList;
	}
	
}
