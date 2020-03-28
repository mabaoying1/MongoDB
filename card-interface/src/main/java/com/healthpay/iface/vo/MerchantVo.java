package com.healthpay.iface.vo;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/6.
 */

public class MerchantVo {

    private String merId  ; //商户Id
    private String merName  ; //商户名称

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }
}
