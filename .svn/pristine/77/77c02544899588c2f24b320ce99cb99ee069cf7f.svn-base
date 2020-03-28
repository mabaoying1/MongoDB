package com.healthpay.iface.vo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * A2002
 *
 * @author gyp
 * @date 2016/8/1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Data")
@XmlType(propOrder = {"merid","funcid","cards"})
public class A2002 {
    @XmlElement(name = "Merid")
    private String merid;
    @XmlElement(name = "Funcid")
    private String funcid;
    @XmlElementWrapper(name = "Cards")
    @XmlElements(
            @XmlElement(name = "Card",type = A2002List.class)
    )
    private List<A2002List> cards;
    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getFuncid() {
        return funcid;
    }

    public void setFuncid(String funcid) {
        this.funcid = funcid;
    }

    public List<A2002List> getCards() {
        return cards;
    }

    public void setCards(List<A2002List> cards) {
        this.cards = cards;
    }
}
