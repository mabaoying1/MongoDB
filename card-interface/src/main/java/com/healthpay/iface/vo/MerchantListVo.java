package com.healthpay.iface.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */

public class MerchantListVo {

    private List<MerchantVo> merchantVoList;

    public List<MerchantVo> getMerchantVoList() {
        return merchantVoList;
    }

    public void setMerchantVoList(List<MerchantVo> merchantVoList) {
        this.merchantVoList = merchantVoList;
    }
}
