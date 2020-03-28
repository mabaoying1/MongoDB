package com.healthpay.iface.vo;

/**
 * A1024返回实体VO
 */
public class A1024VO {
    /**
     *健康e卡号(虚拟卡号)
     */
    private String healthCardId;
    /**
     * 实体卡卡号
     */
    private String icCardId;
    /**
     * 实体卡类型
     */
    private Long status;
    /**
     * 实体卡状态
     */
    private Long type;

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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }


}
