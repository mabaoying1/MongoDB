package com.healthpay.modules.hc.entity;

import javax.validation.constraints.NotNull;

import com.healthpay.common.utils.StringUtils;
import org.hibernate.validator.constraints.Length;
import com.healthpay.modules.sys.entity.User;

import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.excel.annotation.ExcelField;

/**.healthpay.ctionName}Entity
 * @author gaoyp
 * @version 2016-07-22
 */
public class HpRealCard extends DataEntity<HpRealCard> {
	
	private static final long serialVersionUID = 1L;
	private String pkid;		// 主键 自增
	private String cardPkid;		// 健康卡表主键
	private String iccardid;		// 实体卡卡号
	private Long status;		// 0 已注消  1 正常  2 已挂失
	private Long type;		// 0 健康卡实体卡 1 社保卡
	private User createByName;		// 创建人
	private User updateByName;		// 更新人
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
	private HpHealthcard hpHealthcard;

	public HpHealthcard getHpHealthcard() {
		return hpHealthcard;
	}

	public void setHpHealthcard(HpHealthcard hpHealthcard) {
		this.hpHealthcard = hpHealthcard;
	}

	public HpRealCard() {
		super();
	}

	public HpRealCard(String id){
		super(id);
	}

	@ExcelField(title="主键 自增", align=2, sort=0)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	@NotNull(message="健康卡卡号不能为空")
	@Length(min=1, max=30, message="健康卡表主键长度必须介于 1 和 30 之间")
	@ExcelField(title="健康卡表主键", align=2, sort=1)
	public String getCardPkid() {
		return cardPkid;
	}

	public void setCardPkid(String cardPkid) {
		this.cardPkid = cardPkid;
	}

	@NotNull(message = "实体卡卡号不能为空")
	@Length(min=0, max=30, message="实体卡卡号长度必须介于 0 和 30 之间")
	@ExcelField(title="实体卡卡号", align=2, sort=2)
	public String getIccardid() {
		return iccardid;
	}

	public void setIccardid(String iccardid) {
		this.iccardid = iccardid;
	}
	
	@ExcelField(title="0 已注消  1 正常  2 已挂失", align=2, sort=3)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	@ExcelField(title="0 健康卡实体卡 1 社保卡", align=2, sort=4)
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	@ExcelField(title="创建人", align=2, sort=7)
	public User getCreateByName() {
		return createByName;
	}

	public void setCreateByName(User createByName) {
		this.createByName = createByName;
	}
	
	@ExcelField(title="更新人", align=2, sort=10)
	public User getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(User updateByName) {
		this.updateByName = updateByName;
	}
	
	@ExcelField(title="flag00", align=2, sort=11)
	public Long getFlag00() {
		return flag00;
	}

	public void setFlag00(Long flag00) {
		this.flag00 = flag00;
	}
	
	@ExcelField(title="flag01", align=2, sort=12)
	public Long getFlag01() {
		return flag01;
	}

	public void setFlag01(Long flag01) {
		this.flag01 = flag01;
	}
	
	@ExcelField(title="flag02", align=2, sort=13)
	public Long getFlag02() {
		return flag02;
	}

	public void setFlag02(Long flag02) {
		this.flag02 = flag02;
	}
	
	@ExcelField(title="flag03", align=2, sort=14)
	public Long getFlag03() {
		return flag03;
	}

	public void setFlag03(Long flag03) {
		this.flag03 = flag03;
	}
	
	@ExcelField(title="flag04", align=2, sort=15)
	public Long getFlag04() {
		return flag04;
	}

	public void setFlag04(Long flag04) {
		this.flag04 = flag04;
	}
	
	@ExcelField(title="flag05", align=2, sort=16)
	public Long getFlag05() {
		return flag05;
	}

	public void setFlag05(Long flag05) {
		this.flag05 = flag05;
	}
	
	@Length(min=0, max=100, message="str00长度必须介于 0 和 100 之间")
	@ExcelField(title="str00", align=2, sort=17)
	public String getStr00() {
		return str00;
	}

	public void setStr00(String str00) {
		this.str00 = str00;
	}
	
	@Length(min=0, max=100, message="str01长度必须介于 0 和 100 之间")
	@ExcelField(title="str01", align=2, sort=18)
	public String getStr01() {
		return str01;
	}

	public void setStr01(String str01) {
		this.str01 = str01;
	}
	
	@Length(min=0, max=100, message="str02长度必须介于 0 和 100 之间")
	@ExcelField(title="str02", align=2, sort=19)
	public String getStr02() {
		return str02;
	}

	public void setStr02(String str02) {
		this.str02 = str02;
	}
	
	@Length(min=0, max=100, message="str03长度必须介于 0 和 100 之间")
	@ExcelField(title="str03", align=2, sort=20)
	public String getStr03() {
		return str03;
	}

	public void setStr03(String str03) {
		this.str03 = str03;
	}
	
	@Length(min=0, max=100, message="str04长度必须介于 0 和 100 之间")
	@ExcelField(title="str04", align=2, sort=21)
	public String getStr04() {
		return str04;
	}

	public void setStr04(String str04) {
		this.str04 = str04;
	}
	
	@Length(min=0, max=100, message="str05长度必须介于 0 和 100 之间")
	@ExcelField(title="str05", align=2, sort=22)
	public String getStr05() {
		return str05;
	}

	public void setStr05(String str05) {
		this.str05 = str05;
	}

	@Override
	public boolean getIsNewRecord() {
		return isNewRecord || null == pkid;
	}
}