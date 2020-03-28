package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by think on 2016/6/15.
 */
public class ResResultCardHolderVo extends ResResultVo {
    private String healthId;
    private String newFarmid;
    private String name;
    private Date birth;
    private String profession;
    private Integer sex;
    private String nation;
    private String maritalStatus;
    private Integer educationLevel;
    private String provcode;
    private String provname;
    private String citycode;
    private String cityname;
    private String countycode;
    private String countyname;
    private String towncode;
    private String townname;
    private String villagecode;
    private String villagename;
    private String postcode;
    private String provcode2;
    private String provname2;
    private String citycode2;
    private String cityname2;
    private String countycode2;
    private String countyname2;
    private String towncode2;
    private String townname2;
    private String villagecode2;
    private String villagename2;
    private String address2;
    private String phone;
    private String tel;
    private String icCardId;
    private String attnName;		// 联系人姓名
    private String attnPhone;		// 联系人手机
    private String attnRela;		// 联系人关系
    private String email;		// 本人Email

    public String getAttnName() {
        return attnName;
    }

    public void setAttnName(String attnName) {
        this.attnName = attnName;
    }

    public String getAttnPhone() {
        return attnPhone;
    }

    public void setAttnPhone(String attnPhone) {
        this.attnPhone = attnPhone;
    }

    public String getAttnRela() {
        return attnRela;
    }

    public void setAttnRela(String attnRela) {
        this.attnRela = attnRela;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getNewFarmid() {
        return newFarmid;
    }

    public void setNewFarmid(String newFarmid) {
        this.newFarmid = newFarmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getProvcode() {
        return provcode;
    }

    public void setProvcode(String provcode) {
        this.provcode = provcode;
    }

    public String getProvname() {
        return provname;
    }

    public void setProvname(String provname) {
        this.provname = provname;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCountycode() {
        return countycode;
    }

    public void setCountycode(String countycode) {
        this.countycode = countycode;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public String getTowncode() {
        return towncode;
    }

    public void setTowncode(String towncode) {
        this.towncode = towncode;
    }

    public String getTownname() {
        return townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getProvcode2() {
        return provcode2;
    }

    public void setProvcode2(String provcode2) {
        this.provcode2 = provcode2;
    }

    public String getProvname2() {
        return provname2;
    }

    public void setProvname2(String provname2) {
        this.provname2 = provname2;
    }

    public String getCitycode2() {
        return citycode2;
    }

    public void setCitycode2(String citycode2) {
        this.citycode2 = citycode2;
    }

    public String getCityname2() {
        return cityname2;
    }

    public void setCityname2(String cityname2) {
        this.cityname2 = cityname2;
    }

    public String getCountycode2() {
        return countycode2;
    }

    public void setCountycode2(String countycode2) {
        this.countycode2 = countycode2;
    }

    public String getCountyname2() {
        return countyname2;
    }

    public void setCountyname2(String countyname2) {
        this.countyname2 = countyname2;
    }

    public String getTowncode2() {
        return towncode2;
    }

    public void setTowncode2(String towncode2) {
        this.towncode2 = towncode2;
    }

    public String getTownname2() {
        return townname2;
    }

    public void setTownname2(String townname2) {
        this.townname2 = townname2;
    }

    public String getVillagecode2() {
        return villagecode2;
    }

    public void setVillagecode2(String villagecode2) {
        this.villagecode2 = villagecode2;
    }

    public String getVillagename2() {
        return villagename2;
    }

    public void setVillagename2(String villagename2) {
        this.villagename2 = villagename2;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIcCardId() {
        return icCardId;
    }

    public void setIcCardId(String icCardId) {
        this.icCardId = icCardId;
    }
    
}
