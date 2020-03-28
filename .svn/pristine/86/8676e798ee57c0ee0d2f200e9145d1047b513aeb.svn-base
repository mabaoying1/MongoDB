package com.healthpay.common.framework;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailAwareTrigger;

/**
 * PersistableCronTriggerFactoryBean
 *
 * @author gyp
 * @date 2016/8/2
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        // Remove the JobDetail element
        getJobDataMap().remove(JobDetailAwareTrigger.JOB_DETAIL_KEY);
    }
}
