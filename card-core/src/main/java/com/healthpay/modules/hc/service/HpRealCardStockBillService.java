package com.healthpay.modules.hc.service;

import com.healthpay.common.service.CrudService;
import com.healthpay.common.service.ServiceException;
import com.healthpay.modules.hc.dao.HpRealCardStockBillDao;
import com.healthpay.modules.hc.dao.HpRealCardStockDao;
import com.healthpay.modules.hc.entity.HpRealCardStock;
import com.healthpay.modules.hc.entity.HpRealCardStockBill;
import com.healthpay.modules.hc.search.HpRealCardStockBillSearch;
import com.healthpay.modules.hc.search.HpRealCardStockSearch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhouwj on 2016/11/16.
 */
@Service
@Transactional(readOnly = true)
public class HpRealCardStockBillService extends CrudService<HpRealCardStockBillDao, HpRealCardStockBill> {
    @Autowired
    private HpRealCardStockBillDao hpRealCardStockBillDao;
    @Autowired
    private HpRealCardStockDao hpRealCardStockDao;

    /**
     * 插入
     * @param hpRealCardStockBill
     * @return
     */
    @Transactional(readOnly = false)
    public Long insertRealCardStockBill(HpRealCardStockBill hpRealCardStockBill) throws ServiceException {
        return hpRealCardStockBillDao.insertRealCardStockBill(hpRealCardStockBill);
    }

    /**
     * 审核
     * @param hpRealCardStockBill
     */
    @Transactional(readOnly = false)
    public void audit(HpRealCardStockBill hpRealCardStockBill){
        //修改庫存流水狀態
        hpRealCardStockBillDao.updateRealCardStockBill(hpRealCardStockBill);
        if(hpRealCardStockBill.getStatus() == 1){//审核通过，修改库存表数据

            HpRealCardStockSearch hpRealCardStockSearch  = new HpRealCardStockSearch();
            hpRealCardStockSearch.setSendOrganiCode(hpRealCardStockBill.getSendOrganiCode());
            HpRealCardStock hpRealCardStock = new HpRealCardStock();
            if(hpRealCardStockDao.getHpRealCardStock(hpRealCardStockSearch)== null){//判断该机构是否已存在
                if(hpRealCardStockBill.getType() == 2){//出库
                    throw  new ServiceException("该机构在库存表中不存在");
                }
                BeanUtils.copyProperties(hpRealCardStockBill,hpRealCardStock);
                hpRealCardStock.setStockMount(hpRealCardStockBill.getStockInOutMount());
                hpRealCardStock.setCardNOMaxInStock(hpRealCardStockBill.getCardNOAbortInOutStock());
                hpRealCardStockDao.insertRealCardStock(hpRealCardStock);
            }else {//存在只修改数量
                hpRealCardStock.setSendOrganiCode(hpRealCardStockBill.getSendOrganiCode());
                hpRealCardStock.setType(hpRealCardStockBill.getType());
                if(hpRealCardStockBill.getType() == 2){//出库
                    hpRealCardStockBill.setStockInOutMount(-hpRealCardStockBill.getStockInOutMount());
                    //出库最大卡号
                    hpRealCardStock.setCardNOMaxOutStock(hpRealCardStockBill.getCardNOAbortInOutStock());
                }else{
                    //入库最大卡号
                    hpRealCardStock.setCardNOMaxInStock(hpRealCardStockBill.getCardNOAbortInOutStock());
                }
                hpRealCardStock.setStockMount(hpRealCardStockBill.getStockInOutMount());
                int re = hpRealCardStockDao.update(hpRealCardStock);
                if(re <1){
                    throw  new ServiceException("机构在库存表中数量修改失败");
                }}
        }
    }

    /**
     * 修改
     * @param hpRealCardStockBill
     * @return
     */
    public Integer updateRealCardStockBill(HpRealCardStockBill hpRealCardStockBill){
        return hpRealCardStockBillDao.updateRealCardStockBill(hpRealCardStockBill);
    }

    /**
     * 删除
     * @param hpRealCardStockBill
     * @return
     */
    public Integer deleteRealCardStockBill(HpRealCardStockBill hpRealCardStockBill){
        super.delete(hpRealCardStockBill);
        return hpRealCardStockBillDao.delete(hpRealCardStockBill);
    }

    /**
     * 获取某个HpRealCardStockBill
     * @param hpRealCardStockBillSearch
     * @return
     */
    public HpRealCardStockBill getRealCardStockBill(HpRealCardStockBillSearch hpRealCardStockBillSearch){
        return hpRealCardStockBillDao.getRealCardStockBill(hpRealCardStockBillSearch);
    }
}
