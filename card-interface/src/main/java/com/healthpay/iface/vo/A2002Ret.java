package com.healthpay.iface.vo;

import javax.xml.bind.annotation.*;

/**
 * A2002Ret
 *
 * @author gyp
 * @date 2016/8/1
 */
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class A2002Ret {
    @XmlElement
    private String code;
    @XmlElement
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}