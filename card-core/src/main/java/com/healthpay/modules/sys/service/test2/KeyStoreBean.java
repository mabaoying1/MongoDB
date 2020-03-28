package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/10/12.
 */
public class KeyStoreBean {
    private String key;
    private String keyStoreType;
    private String path;
    private String password;

    public KeyStoreBean() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyStoreType() {
        return this.keyStoreType;
    }

    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
