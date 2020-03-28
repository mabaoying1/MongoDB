package com.healthpay.modules.hc.entity;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;
import com.healthpay.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**.
 * 实体卡统计
 * @author zhouwj
 * @version 2016-12-21
 */
public class HpRealCardStatistics extends DataEntity<HpRealCardStatistics> {

	/**
	 * 数量
	 */
	private Long count;
	/**
	 * 商户号
	 */
	private String merId;
	/**
	 * 创建时间
	 */
	private String createDates;


	/**
	 * 商户名
	 */
	private String merName;
	/**
	 * 地区号
	 */
	private String areaId;
	/**
	 * 地区名字
	 */
	private String areaName;
	/**
	 * 类型 1：日 2：月 3：年
	 */
	private Integer type;
	/**
	 * 开始日期
	 */
	private String startDate;
	private String[] startDateTmp;
	/**
	 * 结束日期
	 */
	private String endDate;
	private String[] endDateTmp;
	/**
	 * 结束日期
	 */
	private Date endDates;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getCreateDates() {
		return createDates;
	}

	public void setCreateDates(String createDates) {
		this.createDates = createDates;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getEndDates() {
		return endDates;
	}

	public void setEndDates(Date endDates) {
		this.endDates = endDates;
	}

	public String[] getEndDateTmp() {
		return endDateTmp;
	}

	public void setEndDateTmp(String[] endDateTmp) {
		this.endDateTmp = endDateTmp;
	}

	public String[] getStartDateTmp() {
		return startDateTmp;
	}

	public void setStartDateTmp(String[] startDateTmp) {
		this.startDateTmp = startDateTmp;
	}
}