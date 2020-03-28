package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/10/12.
 */

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class StringResponseHandler extends BasicResponseHandler {
    public StringResponseHandler() {
    }

    public String handleEntity(HttpEntity entity) throws IOException {
        return EntityUtils.toString(entity, Consts.UTF_8);
    }
}
