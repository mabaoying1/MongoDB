package com.healthpay.iface.scheduler;

import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.iface.util.IBaseModel;
import com.healthpay.iface.vo.*;
import com.healthpay.modules.iface.entity.HpIfaceCardasync;
import com.healthpay.modules.iface.service.HpIfaceCardasyncService;
import com.healthpay.modules.tools.utils.HttpPostTest;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * HpIfaceCardasyncJob
 *
 * @author gyp
 * @date 2016/7/31
 */
@Component
public class HpIfaceCardasyncJob extends QuartzJobBean {
    private static boolean isRun = false;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HpIfaceCardasyncService hpIfaceCardasyncService;


    private String doECardSys(HpIfaceCardasync hpIfaceCardasync) throws Exception {
        HeaderBean1001 header = new HeaderBean1001();
        header.setAppID(hpIfaceCardasync.getAppid());
        header.setPassword(StringUtils.getMD5Code(hpIfaceCardasync.getPassword()));
        BodyBean1001 bodyBean = new BodyBean1001();
        MyBeanUtils.copyBean2Bean(bodyBean, hpIfaceCardasync);
        if(null != hpIfaceCardasync.getGender()){
            bodyBean.setGender(hpIfaceCardasync.getGender().toString());
        }

        String xml = IBaseModel.parseBean2Xml(header, bodyBean);
        logger.info("发送报文:" + xml);
        String body = HttpPostTest.post(hpIfaceCardasync.getUrl(), xml);
        return body;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        if (isRun) {
            logger.info("服务正在执行");

            return;
        }

        isRun = true;
        String errCode = null;
        String errMsg = null;
        boolean isSuccess = true;
        List<HpIfaceCardasync> list = hpIfaceCardasyncService.findList();
        String body = null;
        for (HpIfaceCardasync hpIfaceCardasync : list) {
            if (hpIfaceCardasync.getAppid().equals("ECardSys")) {
                try {
                    body = doECardSys(hpIfaceCardasync);
                    ReturnBean1001 returnBean = new ReturnBean1001();
                    IBaseModel.loadXml2Bean(returnBean, body);
                    errCode = returnBean.getStatus();
                    errMsg = returnBean.getMessage();
                    if (!"0".equals(errCode))
                        isSuccess = false;
                    hpIfaceCardasyncService.moveCardasyncToBak(hpIfaceCardasync, errCode, errMsg, isSuccess);
                } catch (Exception e) {
                    logger.error("数据发送失败",e);
                }
            }
        }
        isRun = false;
    }
}
