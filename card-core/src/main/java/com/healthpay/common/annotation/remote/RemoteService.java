package com.healthpay.common.annotation.remote;

import com.healthpay.common.enumerator.ServiceType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by gyp on 2016/6/27.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RemoteService {
    ServiceType serviceType() default ServiceType.HTTP;

    Class<?> serviceInterface();
}
