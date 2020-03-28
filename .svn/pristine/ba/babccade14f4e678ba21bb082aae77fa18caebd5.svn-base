package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/10/12.
 */

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ByteArrayResponseHandler extends AbstractResponseHandler<byte[]> {
    public ByteArrayResponseHandler() {
    }

    public byte[] handleEntity(HttpEntity httpEntity) throws IOException {
        return EntityUtils.toByteArray(httpEntity);
    }
}
