package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/10/12.
 */

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.Iterator;
import java.util.List;

public class IgnoreHostnameVerfier implements HostnameVerifier {
    private List<String> hostnameList;

    public boolean verify(String s, SSLSession sslSession) {
        Iterator var3 = this.hostnameList.iterator();

        String host;
        do {
            if(!var3.hasNext()) {
                return false;
            }

            host = (String)var3.next();
        } while(!s.endsWith(host));

        return true;
    }

    public IgnoreHostnameVerfier(List<String> hostnameList) {
        this.hostnameList = hostnameList;
    }
}
