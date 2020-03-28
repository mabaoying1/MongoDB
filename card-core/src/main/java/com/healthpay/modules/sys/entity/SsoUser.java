package com.healthpay.modules.sys.entity;

/**
 * Created by admin on 2018/3/28.
 */
public class SsoUser {
    private static final long serialVersionUID = 1L;
    private String idNumber;
    private String loginName;
    private String isEnable;

    public String getIDNumber() {
        return idNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.idNumber = IDNumber;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}