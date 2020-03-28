package com.healthpay.iface.scheduler;

import com.alibaba.fastjson.JSONObject;
import com.healthpay.common.entity.ProRequest;
import com.healthpay.common.entity.ProResponse;
import com.healthpay.common.service.proCard.IPCardService;
import com.healthpay.modules.hc.service.HpYkjlXnkService;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;

import org.apache.shiro.util.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mabaoying
 * @ClassName: HpYkjlXnkJob
 * @Description: 电子健康用卡监测数据上传
 * @date: 2019/7/31
 * @最后修改人:
 * @最后修改时间:
 */
public class HpYkjlXnkJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HpYkjlXnkService hpYkjlXnkService;
    
    @Autowired
    private HpIfaceMerchantService hpIfaceMerchantService;

    @Autowired
    private IPCardService proCardService;

    private static boolean isRun = false;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        if (isRun) {
            logger.info("健康卡用卡记录上传服务正在执行.....");
            return;
        }
        isRun = true;

        try{
        	List<HpIfaceMerchant> merchantList=hpIfaceMerchantService.findAllList(null);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date = new Date();
        	String nowDate = sdf.format(date);
        	//上传每个机构的用卡记录
        	for(HpIfaceMerchant merchant : merchantList){
        		String appId=merchant.getMerId();
        		String appSecret=merchant.getDigitalKey();
        		try {
        			//每次只能上传当天的数据 批量上传数据不超过500条
                    List<Map<String,Object>> ykjlXnkList=hpYkjlXnkService.getHpYkjlXnlByMerIdAndTime(nowDate,appId);
                    logger.info(merchant.getMerName()+"用卡记录数为"+ykjlXnkList.size());
                    if(!CollectionUtils.isEmpty(ykjlXnkList)){
                        //上传省卡管
                        logger.info("上传省卡管中.....");
                        ProRequest proRequest=new ProRequest();
                        proRequest.setApp_id(appId);
                        proRequest.setMethod("ehc.ehealthcode.usecarddate");//接口名称
                        proRequest.setBiz_content(JSONObject.toJSONString(ykjlXnkList));//请求参数集合
                        ProResponse proResponse=proCardService.process(proRequest,appSecret);//发送请求
                        //请求失败
                        if(!"10".equals(proResponse.getCode())){  //10成功   00失败
                            logger.error(merchant.getMerName()+"----"+proResponse.getMessage()+"----"+proResponse.getBiz_content());
                        }else{
                            //更新上传时间
                            logger.info("更新上传时间");
                            hpYkjlXnkService.updateHpYkjlXnlList(ykjlXnkList,new Date());
                        }
                    }
				} catch (Exception e) {
					logger.error(merchant.getMerName()+"健康卡用卡记录上传异常:"+e.getMessage());
				}
        	}
        }catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            isRun = false;
        }
        logger.info("健康卡用卡记录上传结束.....");
    }
}
