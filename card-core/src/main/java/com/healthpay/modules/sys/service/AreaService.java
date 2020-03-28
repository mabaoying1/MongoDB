/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */



package com.healthpay.modules.sys.service;

import java.util.List;

import com.healthpay.common.utils.CacheConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.service.TreeService;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.modules.sys.dao.AreaDao;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.utils.AreaUtils;
import com.healthpay.modules.sys.utils.UserUtils;


/**
 * Class AreaService
 *
 *
 * @version        1.0, 16/06/28
 * @author         gyp
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {
	public Area getByCode(String code){
		return AreaUtils.getArea(code);
	}
	
    /**
     * Method description
     *
     *
     * @param area
     */
    @Transactional(readOnly = false)
    public void delete(Area area) {
        super.delete(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }


	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
		CacheUtils.remove(CacheConstants.CACHE_AREA_MAP);
	}

    /**
     * Method description
     *
     *
     * @param isAll
     * @param id
     *
     * @return
     */
    public List<Area> findList(Boolean isAll, String id) {
        if ((null != isAll) && isAll) {
            return UserUtils.getAreaAllList(id);
        } else {
            return UserUtils.getAreaList(id);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
