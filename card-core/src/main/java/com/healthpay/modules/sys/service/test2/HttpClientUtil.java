package com.healthpay.modules.sys.service.test2;

import com.google.common.collect.Lists;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.*;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by admin on 2018/10/13.
 */
public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static RequestConfig requestConfig = null;
    private static Map<String, PoolingHttpClientConnectionManager> poolingHttpClientConnectionManagerMap;
    private static PoolingHttpClientConnectionManager defaultConnectionManager = null;
    private static HttpClientBuilder httpBulder = null;
    private static int MAXCONNECTION = 1000;
    private static int DEFAULTMAXCONNECTION = 100;
    private static ResponseHandler<String> responseHandler;
    private static ResponseHandler<byte[]> byteArrayResonseHandler;

    public static String doPostMethod(String url, List<NameValuePair> list) throws Exception {
        return (String)getConnection().execute(getRequestMethod(list, url, "post"), responseHandler);
    }

    public static final String httpPost(String url, String data, String contentType) throws Exception {
        CloseableHttpClient httpclient = getConnection();
        HttpUriRequest httpUriRequest = getRequestMethod(data, url, "post", contentType);
        return (String)httpclient.execute(httpUriRequest, responseHandler);
    }

    private static HttpUriRequest getRequestMethod(List<NameValuePair> list, String url, String method) {
        return getRequestMethod(url, method, EntityBuilder.create().setParameters(list).build());
    }

    public static HttpUriRequest getRequestMethod(String url, String method, HttpEntity httpEntity) {
        return RequestBuilder.create(method.toUpperCase()).setUri(url).setConfig(requestConfig).setEntity(httpEntity).build();
    }

    public static void init(List<String> hostList, List<KeyStoreBean> keyStoreBeans) {
        try {
            ArrayList e = Lists.newArrayList();
            if(null != hostList && !hostList.isEmpty()) {
                e.add(new IgnoreHostnameVerfier(hostList));
            }

            e.add(SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout('\uea60').setConnectionRequestTimeout(5000).build();
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(new TrustAllStrategy());
            CompositeHostNameVerfier hostnameVerifier = new CompositeHostNameVerfier(e);
            if(null != keyStoreBeans && !keyStoreBeans.isEmpty()) {
                poolingHttpClientConnectionManagerMap = (Map)keyStoreBeans.parallelStream().collect(Collectors.toConcurrentMap(KeyStoreBean::getKey, (keyStoreBean) -> {
                    try {
                        KeyStore e2 = KeyStore.getInstance(keyStoreBean.getKeyStoreType());
                        e2.load(HttpClientUtil.class.getResourceAsStream(keyStoreBean.getPath()), keyStoreBean.getPassword().toCharArray());
                        Registry socketFactoryRegistry = RegistryBuilder.create().register("https", new SSLConnectionSocketFactory(sslContextBuilder.loadKeyMaterial(e2, keyStoreBean.getPassword().toCharArray()).build(), hostnameVerifier)).build();
                        return new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                    } catch (Exception var5) {
                        logger.error("Http请求失败", var5);
                        return null;
                    }
                }));
            }

            Registry socketFactoryRegistry = RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", new SSLConnectionSocketFactory(sslContextBuilder.build(), hostnameVerifier)).build();
            defaultConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpBulder = HttpClients.custom().setMaxConnPerRoute(DEFAULTMAXCONNECTION).setMaxConnTotal(MAXCONNECTION).setConnectionManager(defaultConnectionManager).setRetryHandler(new DefaultHttpRequestRetryHandler()).setKeepAliveStrategy((httpResponse, httpContext) -> {
                return 20000L;
            });
            responseHandler = new StringResponseHandler();
            byteArrayResonseHandler = new ByteArrayResponseHandler();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    private static CloseableHttpClient getConnection() {
        return getConnection(false, (String)null);
    }

    private static CloseableHttpClient getConnection(boolean useCert, String certKey) {
        if(null == httpBulder) {
            init((List)null, (List)null);
        }

        if(StringUtils.isNotEmpty(certKey) && null != poolingHttpClientConnectionManagerMap) {
            httpBulder.setConnectionManager((HttpClientConnectionManager)poolingHttpClientConnectionManagerMap.get(certKey));
        } else {
            httpBulder.setConnectionManager(defaultConnectionManager);
        }

        return httpBulder.build();
    }

    public static HttpUriRequest getRequestMethod(String data, String url, String method, String contentType) {
        RequestBuilder requestBuilder = RequestBuilder.create(method.toUpperCase());
        StringEntity entity;
        if(null != contentType) {
            entity = new StringEntity(data, ContentType.create(contentType, Consts.UTF_8));
        } else {
            entity = new StringEntity(data, Consts.UTF_8);
        }

        return requestBuilder.setUri(url).setEntity(entity).setConfig(requestConfig).build();
    }
}