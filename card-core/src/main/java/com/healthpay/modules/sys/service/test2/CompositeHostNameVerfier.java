package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/10/12.
 */

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.Iterator;

public class CompositeHostNameVerfier implements HostnameVerifier {
    private Iterable<HostnameVerifier> verifiers;

    public CompositeHostNameVerfier(Iterable<HostnameVerifier> verifiers) {
        this.verifiers = verifiers;
    }

    public boolean verify(String s, SSLSession sslSession) {
        Iterator iterator = this.verifiers.iterator();

        do {
            if(!iterator.hasNext()) {
                return false;
            }
        } while(!((HostnameVerifier)iterator.next()).verify(s, sslSession));

        return true;
    }
}