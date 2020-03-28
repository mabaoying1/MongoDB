package com.healthpay.modules.sys.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Map;

/**
 * Created by admin on 2018/3/29.
 */
public class RealmAuthenticator extends ModularRealmAuthenticator {
    /**
     * 存放realm
     */
    private Map<String, Object> definedRealms;

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }

    /**
     * 根据用户类型判断使用哪个Realm
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        super.assertRealmsConfigured();
        Realm realm = null;
        // 使用自定义Token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 判断用户类型
//        if ("1".equals(token.getUserType())) {
            realm = (Realm) this.definedRealms.get("systemAuthorizingRealm");
//        } else if ("2".equals(token.getUserType())) {
//            realm = (Realm) this.definedRealms.get("iDNumberAuthorizingRealm");
//        }
        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }
}