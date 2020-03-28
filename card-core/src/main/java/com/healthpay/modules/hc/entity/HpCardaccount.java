package com.healthpay.modules.hc.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**
 * 卡帐关系Entity
 * @author yyl
 * @version 2016-05-25
 */
public class HpCardaccount extends DataEntity<HpCardaccount> {
	
	private static final long serialVersionUID = 1L;
	private String pkid;		// 主键 自增
	private String cardPkid;		// card_pkid
	private String cardId;		// 卡号（即身份证号码（看国标还是厂标））
	private Long paysystem;		// 支付系统类型（1 银行 2 银联 3 APPLEPAY...建立数据字典）
	private String name;        // 姓名
	private String account;		// 账号
	private String accountname;		// 户名
	private String mobile;		// 预留手机号
	private String bankid;		// 支付类型为银行时 ，记录银行
	private String bankname;		// bankname
	private Long accounttype;		// 账号类型 0 借记卡 1 信用卡
	private String backnum;		// 卡背后号码
	private Date validDate;		// 卡有效期
	private Long flag00;		// flag00
	private Long flag01;		// flag01
	private Long flag02;		// flag02
	private Long flag03;		// flag03
	private Long flag04;		// flag04
	private Long flag05;		// flag05
	private String str00;		// str00
	private String str01;		// str01
	private String str02;		// str02
	private String str03;		// str03
	private String str04;		// str04
	private String str05;		// str05
	
	public HpCardaccount() {
		super();
	}

	public HpCardaccount(String id){
		super(id);
	}

	@ExcelField(title="主键 自增", align=2, sort=0)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	@Length(min=1, max=30, message="card_pkid长度必须介于 1 和 30 之间")
	@ExcelField(title="card_pkid", align=2, sort=1)
	public String getCardPkid() {
		return cardPkid;
	}

	public void setCardPkid(String cardPkid) {
		this.cardPkid = cardPkid;
	}
	
	@Length(min=1, max=30, message="卡号（即身份证号码（看国标还是厂标））长度必须介于 1 和 30 之间")
	@ExcelField(title="卡号（即身份证号码（看国标还是厂标））", align=2, sort=2)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	@ExcelField(title="支付系统类型（1 银行 2 银联 3 APPLEPAY...建立数据字典）", align=2, sort=3)
	public Long getPaysystem() {
		return paysystem;
	}

	public void setPaysystem(Long paysystem) {
		this.paysystem = paysystem;
	}
	
	@Length(min=0, max=30, message="账号长度必须介于 0 和 30 之间")
	@ExcelField(title="账号", align=2, sort=4)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=30, message="户名长度必须介于 0 和 30 之间")
	@ExcelField(title="户名", align=2, sort=5)
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=0, max=30, message="预留手机号长度必须介于 0 和 30 之间")
	@ExcelField(title="预留手机号", align=2, sort=6)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=30, message="支付类型为银行时 ，记录银行长度必须介于 0 和 30 之间")
	@ExcelField(title="支付类型为银行时 ，记录银行", align=2, sort=7)
	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	
	@Length(min=0, max=100, message="bankname长度必须介于 0 和 100 之间")
	@ExcelField(title="bankname", align=2, sort=8)
	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	@ExcelField(title="账号类型 0 借记卡 1 信用卡", align=2, sort=9)
	public Long getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Long accounttype) {
		this.accounttype = accounttype;
	}
	
	@Length(min=0, max=30, message="卡背后号码长度必须介于 0 和 30 之间")
	@ExcelField(title="卡背后号码", align=2, sort=10)
	public String getBacknum() {
		return backnum;
	}

	public void setBacknum(String backnum) {
		this.backnum = backnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="卡有效期", align=2, sort=11)
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	@ExcelField(title="flag00", align=2, sort=18)
	public Long getFlag00() {
		return flag00;
	}

	public void setFlag00(Long flag00) {
		this.flag00 = flag00;
	}
	
	@ExcelField(title="flag01", align=2, sort=19)
	public Long getFlag01() {
		return flag01;
	}

	public void setFlag01(Long flag01) {
		this.flag01 = flag01;
	}
	
	@ExcelField(title="flag02", align=2, sort=20)
	public Long getFlag02() {
		return flag02;
	}

	public void setFlag02(Long flag02) {
		this.flag02 = flag02;
	}
	
	@ExcelField(title="flag03", align=2, sort=21)
	public Long getFlag03() {
		return flag03;
	}

	public void setFlag03(Long flag03) {
		this.flag03 = flag03;
	}
	
	@ExcelField(title="flag04", align=2, sort=22)
	public Long getFlag04() {
		return flag04;
	}

	public void setFlag04(Long flag04) {
		this.flag04 = flag04;
	}
	
	@ExcelField(title="flag05", align=2, sort=23)
	public Long getFlag05() {
		return flag05;
	}

	public void setFlag05(Long flag05) {
		this.flag05 = flag05;
	}
	
	@Length(min=0, max=100, message="str00长度必须介于 0 和 100 之间")
	@ExcelField(title="str00", align=2, sort=24)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}
	
	@Length(min=0, max=100, message="str01长度必须介于 0 和 100 之间")
	@ExcelField(title="str01", align=2, sort=25)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}
	
	@Length(min=0, max=100, message="str02长度必须介于 0 和 100 之间")
	@ExcelField(title="str02", align=2, sort=26)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}
	
	@Length(min=0, max=100, message="str03长度必须介于 0 和 100 之间")
	@ExcelField(title="str03", align=2, sort=27)
	public String getStr03() {
		return str03;
	}

	public void setStr03(String str03) {
		this.str03 = str03;
	}
	
	@Length(min=0, max=100, message="str04长度必须介于 0 和 100 之间")
	@ExcelField(title="str04", align=2, sort=28)
	public String getStr04() {
		return str04;
	}

	public void setStr04(String str04) {
		this.str04 = str04;
	}
	
	@Length(min=0, max=100, message="str05长度必须介于 0 和 100 之间")
	@ExcelField(title="str05", align=2, sort=29)
	public String getStr05() {
		return str05;
	}

	public void setStr05(String str05) {
		this.str05 = str05;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}