package com.healthpay.common.annotation.remote;

import java.rmi.registry.Registry;

/**
 * Created by gyp on 2016/6/27.
 */
public @interface RmiServiceProperty {
    int registryPort() default Registry.REGISTRY_PORT;
}
