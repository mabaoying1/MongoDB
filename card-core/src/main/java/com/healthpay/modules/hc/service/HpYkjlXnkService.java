package com.healthpay.modules.hc.service;

import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.dao.HpYkjlXnkDao;
import com.healthpay.modules.hc.entity.HpYkjlXnk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author mabaoying
 * @ClassName: HpYkjlXnkService
 * @Description: 虚拟卡用卡记录业务逻辑层
 * @date: 2019/7/31
 * @最后修改人:
 * @最后修改时间:
 */
@Service
@Transactional(readOnly = true)
public class HpYkjlXnkService extends CrudService<HpYkjlXnkDao, HpYkjlXnk> {

    @Autowired
    private HpYkjlXnkDao hpYkjlXnkDao;

    /**
     * 新增电子健康卡用卡记录
     * @param hpYkjlXnk
     * @return
     */
    public int addYkjlXnk(HpYkjlXnk hpYkjlXnk){
       return hpYkjlXnkDao.addHpYkjlXnk(hpYkjlXnk);
    }

    /**
     * 通过日期字符串查询健康卡用卡记录
     * @param date
     * @param merId 
     * @return
     */
    public List<Map<String,Object>> getHpYkjlXnlByMerIdAndTime(String nowDate, String merId){

        return hpYkjlXnkDao.getHpYkjlXnlByMerIdAndTime(nowDate,merId);
    }

    /**
     *  批量更新用卡上传时间
     * @param updateHpYkjlXnlList
     * @return
     */
    public int updateHpYkjlXnlList(List<Map<String,Object>> ykjlXnkList, Date date){

        return hpYkjlXnkDao.updateHpYkjlXnlList(ykjlXnkList,date);
    }
}
