package com.healthpay.common.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.healthpay.common.exception.BusException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mabaoying
 * @ClassName: SSLClient
 * @Description: 用于进行Https请求的HttpClient
 * @date: 2019/8/21 19:40
 * @最后修改人:
 * @最后修改时间:
 */
public class SSLClient extends DefaultHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(SSLClient.class);
    public SSLClient() throws Exception {
        super();
        //传输协议需要根据自己的判断
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
    }

    /**
     *  发送Https post请求
     * @param url
     * @param map
     * @return String
     * @throws BusException
     */
    public static String httpsSendPosthttpclient(String url,String  map) throws BusException {
        org.apache.http.client.HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            //httpPost.addHeader("Accept", "application/json");
            //httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(map);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (Exception ex) {
            throw new BusException( "连接省电子健康卡系统超时");
        }
        return result;
    }

}