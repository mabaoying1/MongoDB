package com.healthpay.common.service.proCard;


import com.healthpay.common.entity.ProRequest;
import com.healthpay.common.entity.ProResponse;

public interface IPCardService {
	
	 ProResponse process(ProRequest request,String appSecret) throws Exception;
}
