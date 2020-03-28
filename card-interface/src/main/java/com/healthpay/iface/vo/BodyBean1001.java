package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * BodyBean1001
 *
 * @author gyp
 * @date 2016/7/31
 */
public class BodyBean1001 {
    private String iDType;
    private String iDCardNum;
    private String name;
    private String phone;
    private String healthECardNum;
    private String cardType;
    private String cardNum;
    private String gender;
    private Date dateOfBirth;

    public String getIDType() {
        return iDType;
    }

    public void setIDType(String iDType) {
        this.iDType = iDType;
    }

    public String getIDCardNum() {
        return iDCardNum;
    }

    public void setIDCardNum(String iDCardNum) {
        this.iDCardNum = iDCardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHealthECardNum() {
        return healthECardNum;
    }

    public void setHealthECardNum(String healthECardNum) {
        this.healthECardNum = healthECardNum;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonFormat(pattern = "yyyy/MM/dd")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
