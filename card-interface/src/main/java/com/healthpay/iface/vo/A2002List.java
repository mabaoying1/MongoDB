package com.healthpay.iface.vo;

import javax.xml.bind.annotation.*;

/**
 * A2002List
 *
 * @author gyp
 * @date 2016/8/1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Card")
@XmlType(propOrder = {"healthCardId","icCardId","type","optType"})
public class A2002List {
    @XmlElement(name = "HealthCardId")
    private String healthCardId;
    @XmlElement(name = "IcCardId")
    private String icCardId;
    @XmlElement(name = "Type")
    private Integer type;
    @XmlElement(name = "OptType")
    private Integer optType;

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
