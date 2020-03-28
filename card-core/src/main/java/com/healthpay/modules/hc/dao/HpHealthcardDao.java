package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpHealthcard;
import org.apache.ibatis.annotations.Param;

/**
 * 健康卡数据DAO接口
 * 
 * @author yyl
 * @version 2016-05-25
 */
@MyBatisDao
public interface HpHealthcardDao extends CrudDao<HpHealthcard> {

	 HpHealthcard getByIdentityId(String identityId);

	 int updateByIdentityId(HpHealthcard hpHealthCard);
	
	 HpHealthcard getByCardIdAndStatus(String cardId ,String status);
	
	 HpHealthcard getByIdentityIdAndStatus(String identityId ,String status);

	/**
	 * 通过电子健康卡id和状态查询电子健康卡
	 * @param pkid
	 * @param status
	 * @return
	 */
	 HpHealthcard getByPkidAndStatus(@Param("pkid")String pkid ,@Param("status") String status);

	 String geteMaxId(String s);

	 HpHealthcard getByPkid(String pkid);


}