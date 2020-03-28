package com.healthpay.modules.analysis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.CrudService;
import com.healthpay.modules.hc.dao.HpCardholderDao;
import com.healthpay.modules.hc.entity.HpCardholder;

/**
 * Class ResidentService
 *
 *
 * @version        1.0, 16/06/27
 * @author         gyp
 */
@Service
@Transactional(readOnly = true)
public class ResidentService extends CrudService<HpCardholderDao, HpCardholder> {
    @Autowired
    private HpCardholderDao hpCardholderDao;

    /**
     * Method description
     *
     *
     * @param hpCardholder
     *
     * @return
     */
    public Map<String, Integer> findChartData(HpCardholder hpCardholder) {
        hpCardholder.getSqlMap().put("dsf", dataScopeFilter(hpCardholder.getCurrentUser(), "o", ""));

        Map<String, Integer> map = hpCardholderDao.findChartData(hpCardholder);

        return map;
    }

    /**
     * Method description
     *
     *
     * @param page
     * @param entity
     *
     * @return
     */
    public Page<HpCardholder> findPage(Page<HpCardholder> page, HpCardholder entity) {

        // 加入数据权限
        entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", ""));
        entity.setPage(page);
        page.setList(hpCardholderDao.findResidentList(entity));

        HpCardholder hpCardholder = new HpCardholder();

        hpCardholder.setCreateDateStr("合计");
        entity.setPage(null);
        hpCardholder.setCount(hpCardholderDao.getTotalResident(entity));
        page.getList().add(hpCardholder);

        return page;
    }

    public Page<HpCardholder> findPag(Page<HpCardholder> page, HpCardholder entity) {

        // 加入数据权限
        entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", ""));
        entity.setPage(page);
        page.setList(hpCardholderDao.findResistatList(entity));

        HpCardholder hpCardholder = new HpCardholder();

        hpCardholder.setCreateDateStr("合计");
        entity.setPage(null);
        hpCardholder.setCount(hpCardholderDao.getTotalResistat(entity));
        page.getList().add(hpCardholder);

        return page;
    }

    public Page<HpCardholder> getTable(Page<HpCardholder> page, HpCardholder entity) {
        entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", ""));
        entity.setPage(page);
        page.setList(hpCardholderDao.getTable(entity));
        HpCardholder hpCardholder = new HpCardholder();

        hpCardholder.setCreateDateStr("合计");
        entity.setPage(null);
        hpCardholder.setCount(hpCardholderDao.getTotalResistat(entity));
        page.getList().add(hpCardholder);
        return page;
    }

}
