package com.healthpay.modules.iface.service;

import java.util.List;
import java.util.Map;

import com.healthpay.common.exception.BusException;
import com.healthpay.common.utils.CacheConstants;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.modules.iface.dao.HpIfacePlatformareaDao;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.entity.HpIfacePlatformarea;
import com.healthpay.modules.sys.dao.AreaDao;
import com.healthpay.modules.sys.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.iface.entity.HpIfacePlatform;
import com.healthpay.modules.iface.dao.HpIfacePlatformDao;

/**
 * 外部平台管理Service
 * @author gaoyp
 * @version 2016-07-29
 */
@Service
@Transactional(readOnly = true)
public class HpIfacePlatformService extends CrudService<HpIfacePlatformDao, HpIfacePlatform> {

	@Autowired
	private AreaDao areaDao;
	@Autowired
	private HpIfacePlatformareaDao hpIfacePlatformareaDao;
	@Autowired
	private HpIfacePlatformDao hpIfacePlatformDao;

	public HpIfacePlatform get(String id) {
		return super.get(id);
	}
	
	public List<HpIfacePlatform> findList(HpIfacePlatform hpIfacePlatform) {
		return super.findList(hpIfacePlatform);
	}
	
	public Page<HpIfacePlatform> findPage(Page<HpIfacePlatform> page, HpIfacePlatform hpIfacePlatform) {
		return super.findPage(page, hpIfacePlatform);
	}
	
	@Transactional(readOnly = false)
	public void save(HpIfacePlatform hpIfacePlatform) {
		super.save(hpIfacePlatform);
	}
	
	@Transactional(readOnly = false)
	public void delete(HpIfacePlatform hpIfacePlatform) {
		super.delete(hpIfacePlatform);
	}


	public void saveHpIfacePlatform(HpIfacePlatform hpIfacePlatform)throws BusException {
		try {
			if(!hpIfacePlatform.getIsNewRecord()){//编辑表单保存
                HpIfacePlatform t = dao.get(hpIfacePlatform.getId());//从数据库取出记录的值
                MyBeanUtils.copyBeanNotNull2Bean(hpIfacePlatform, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
                this.save(t);//保存

				HpIfacePlatformarea model = new HpIfacePlatformarea();
				model.setPlatformid(hpIfacePlatform.getPkid());
				hpIfacePlatformareaDao.deleteHpIfacePlatformarea(model);
            }else{//新增表单保存
                this.save(hpIfacePlatform);//保存
            }
			if(null != hpIfacePlatform.getHpIfacePlatformareaList() && hpIfacePlatform.getHpIfacePlatformareaList().size() > 0){
                for(HpIfacePlatformarea hpIfacePlatformarea :hpIfacePlatform.getHpIfacePlatformareaList()){
                    Area area = areaDao.get(hpIfacePlatformarea.getAreaid());
                    if(null != area){
						hpIfacePlatformarea.setCode(area.getCode());
                        hpIfacePlatformarea.setName(area.getName());
                        hpIfacePlatformarea.setPlatformid(hpIfacePlatform.getPkid());
                        hpIfacePlatformarea.setPlatformtype(hpIfacePlatform.getPlatformtype());
                        hpIfacePlatformareaDao.insert(hpIfacePlatformarea);
                    }
                }
            }
		} catch (Exception e) {
			throw new BusException("保存失败");
		}
	}

	public List<HpIfacePlatform> findAllList(HpIfacePlatform hpIfacePlatform) {
		return hpIfacePlatformDao.findAllList(hpIfacePlatform);
	}

	public HpIfacePlatform getFormCache(Long id) {
		Map<Long,HpIfacePlatform> map = (Map<Long, HpIfacePlatform>) CacheUtils.get(CacheConstants.CACHE_PLATFORM_MAP);
		if(null != map)
			return map.get(id);
		return null;
	}
}