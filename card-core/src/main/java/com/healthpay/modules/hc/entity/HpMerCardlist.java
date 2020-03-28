package com.healthpay.modules.hc.entity;

import com.healthpay.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by DarkSunny on 2016/7/26.
 */
public class HpMerCardlist extends DataEntity<HpMerCardlist>{
    private static final long serialVersionUID = 1L;
    private String pkId; // 主键
    private String merId; //商户号
    private String healthCardId; //健康卡卡号
    private String iccardId; //
    private int status; //状态码
    private Date date0;
    private Date date1;
    private int flag0;
    private int flag1;
    private int flag2;
    private String str0;
    private String str1;
    private String str2;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getIccardId() {
        return iccardId;
    }

    public void setIccardId(String iccardId) {
        this.iccardId = iccardId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate0() {
        return date0;
    }

    public void setDate0(Date date0) {
        this.date0 = date0;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public int getFlag0() {
        return flag0;
    }

    public void setFlag0(int flag0) {
        this.flag0 = flag0;
    }

    public int getFlag1() {
        return flag1;
    }

    public void setFlag1(int flag1) {
        this.flag1 = flag1;
    }

    public int getFlag2() {
        return flag2;
    }

    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }

    public String getStr0() {
        return str0;
    }

    public void setStr0(String str0) {
        this.str0 = str0;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}
