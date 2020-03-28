package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author mabaoying
 * @ClassName: QueryParamVO
 * @Description: 查询参数封装累
 * @date: 2019/8/14 16:43
 * @最后修改人:
 * @最后修改时间:
 */
public class QueryParamVO {

    private String healthCardId;//电子健康卡id
    private String idCard; //居民身份证号
    private String docuType;		// 证件类型
    private String docuId;		//证件号码
    private String ewmbs;//二维码标识
    private String token;//金融支付数据
    private String qrCode;//二维码密文
    private String medstepCode;//诊疗环节代码
    private String terminalCode;//扫码枪终端标识号
    private String channelCode;//刷卡终端类型编号
    private String rzfs;//认证模式
    private String rzzl;//认证资料
    private Date useTime;//使用时间  yyyy-MM-dd HH:mm:ss


    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getEwmbs() {
        return ewmbs;
    }

    public void setEwmbs(String ewmbs) {
        this.ewmbs = ewmbs;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMedstepCode() {
        return medstepCode;
    }

    public void setMedstepCode(String medstepCode) {
        this.medstepCode = medstepCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getRzfs() {
        return rzfs;
    }

    public void setRzfs(String rzfs) {
        this.rzfs = rzfs;
    }

    public String getRzzl() {
        return rzzl;
    }

    public void setRzzl(String rzzl) {
        this.rzzl = rzzl;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
