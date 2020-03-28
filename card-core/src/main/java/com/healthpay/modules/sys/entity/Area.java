/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */



package com.healthpay.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.healthpay.common.persistence.TreeEntity;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import com.healthpay.modules.sys.utils.DictUtils;

/**
 * 区域Entity
 * @author jeeplus
 * @version 2013-05-15
 */
public class Area extends TreeEntity<Area> {
    private static final long serialVersionUID = 1L;

//  private Area parent;    // 父级编号
//  private String parentIds; // 所有父级编号
    private String code;    // 区域编码

//  private String name;    // 区域名称
//  private Integer sort;           // 排序
    private String  type;    // 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
    private String  allName;
    private boolean isParent;
    private String  typeStr;

    public Area() {
        super();
        this.sort = 30;
    }

    public Area(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getAllName() {
        return allName;
    }

    /**
     * Method description
     *
     *
     * @param allName
     */
    public void setAllName(String allName) {
        this.allName = allName;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Length(
        min   = 0,
        max   = 100
    )
    @ExcelField(
        title = "区域编码",
        align = 2,
        sort  = 10
    )
    public String getCode() {
        return code;
    }

    /**
     * Method description
     *
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean getIsParent() {
        return isParent;
    }

    /**
     * Method description
     *
     *
     * @param isParent
     */
    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

//
//  public String getParentId() {
//          return parent != null && parent.getId() != null ? parent.getId() : "0";
//  }
//  @JsonBackReference
//  @NotNull

    /**
     * Method description
     *
     *
     * @return
     */
    public Area getParent() {
        return parent;
    }

    /**
     * Method description
     *
     *
     * @param parent
     */
    public void setParent(Area parent) {
        this.parent = parent;
    }

//
//  @Length(min=1, max=2000)
//  public String getParentIds() {
//          return parentIds;
//  }
//
//  public void setParentIds(String parentIds) {
//          this.parentIds = parentIds;
//  }
//
//  @Length(min=1, max=100)
//  public String getName() {
//          return name;
//  }
//
//  public void setName(String name) {
//          this.name = name;
//  }
//
//  public Integer getSort() {
//          return sort;
//  }
//
//  public void setSort(Integer sort) {
//          this.sort = sort;
//  }

    /**
     * Method description
     *
     *
     * @return
     */
    @Length(
        min = 1,
        max = 1
    )
    public String getType() {
        return type;
    }

    /**
     * Method description
     *
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;

        if (StringUtils.isNotEmpty(type)) {
            this.setTypeStr(DictUtils.getDictLabel(type, "sys_area_type", null));
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getTypeStr() {
        return typeStr;
    }

    /**
     * Method description
     *
     *
     * @param typeStr
     */
    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
