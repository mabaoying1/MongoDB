package com.healthpay.iface.vo;

import com.healthpay.common.annotation.NotNull;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * RealCardCancelVo
 *
 * @author gyp
 * @date 2016/7/25
 */
public class RealCardCancelVo {
    private String nationality; //国籍
    private String docuType;  //证件类型
    private String docuId;   // 证件号码
    private String healthCardId; //健康e卡号
    private String icCardId; //实体卡号
    private Integer type;   // 卡类型 0 健康卡实体卡  1 社保卡（社保卡的参保号 前8位作为实体卡卡号）
    private Integer optType;  //操作类型 0 注销 1 挂失 2 解除挂失

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDocuType() {
        return docuType;
    }

    public void setDocuType(String docuType) {
        this.docuType = docuType;
    }

    public String getDocuId() {
        return docuId;
    }

    public void setDocuId(String docuId) {
        this.docuId = docuId;
    }

    @NotNull(name="健康卡号")
    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getIcCardId() {
        return icCardId;
    }

    public void setIcCardId(String icCardId) {
        this.icCardId = icCardId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }
}
