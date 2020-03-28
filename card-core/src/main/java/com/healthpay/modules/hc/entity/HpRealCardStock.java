package com.healthpay.modules.hc.entity;

import com.healthpay.common.persistence.DataEntity;

import java.util.Date;

/**
实体卡库存
 */
public class HpRealCardStock extends DataEntity<HpRealCardStock> {
    private String pkid;
    /**
     * 发卡机构代码
     */
    private String sendOrganiCode;
    /**
     * 发卡机构名字
     */
    private String sendOrganiName;
    /**
     * 入库最大卡号
     */
    private String cardNOMaxInStock;
    /**
     * 出库最大卡号
     */
    private String cardNOMaxOutStock;
    /**
     * 库存数量
     */
    private Long stockMount;
    /**
     * 领取卡总数量
     */
    private Long receiveTotalMount;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 修改时间
     */
    private Date gmtModifiend;
    /**
     * 1:入库 2：出库
     */
    private Integer type;
    /**
     * 已发放卡数量
     */
    private Integer realSendCardCount;

    private Integer cancel;

    private Integer notcancel;

    //实体卡剩余数量
    private Long remainingMount;

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public Integer getNotcancel() {
        return notcancel;
    }

    public void setNotcancel(Integer notcancel) {
        this.notcancel = notcancel;
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getSendOrganiCode() {
        return sendOrganiCode;
    }

    public void setSendOrganiCode(String sendOrganiCode) {
        this.sendOrganiCode = sendOrganiCode;
    }

    public String getSendOrganiName() {
        return sendOrganiName;
    }

    public void setSendOrganiName(String sendOrganiName) {
        this.sendOrganiName = sendOrganiName;
    }

    public String getCardNOMaxInStock() {
        return cardNOMaxInStock;
    }

    public void setCardNOMaxInStock(String cardNOMaxInStock) {
        this.cardNOMaxInStock = cardNOMaxInStock;
    }

    public String getCardNOMaxOutStock() {
        return cardNOMaxOutStock;
    }

    public void setCardNOMaxOutStock(String cardNOMaxOutStock) {
        this.cardNOMaxOutStock = cardNOMaxOutStock;
    }

    public Long getStockMount() {
        return stockMount;
    }

    public void setStockMount(Long stockMount) {
        this.stockMount = stockMount;
    }
    public Long getReceiveTotalMount() {
        return receiveTotalMount;
    }

    public void setReceiveTotalMount(Long receiveTotalMount) {
        this.receiveTotalMount = receiveTotalMount;
    }
    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModifiend() {
        return gmtModifiend;
    }

    public void setGmtModifiend(Date gmtModifiend) {
        this.gmtModifiend = gmtModifiend;
    }

    public Integer getRealSendCardCount() {
        return realSendCardCount;
    }

    public void setRealSendCardCount(Integer realSendCardCount) {
        this.realSendCardCount = realSendCardCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getRemainingMount() {
        return remainingMount;
    }

    public void setRemainingMount(Long remainingMount) {
       this. remainingMount = remainingMount;
    }
}
