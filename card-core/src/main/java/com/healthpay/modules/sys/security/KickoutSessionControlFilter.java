package com.healthpay.modules.sys.security;

import com.healthpay.common.security.shiro.session.SessionManager;
import com.healthpay.common.utils.CacheUtils;
import com.healthpay.modules.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by admin on 2018/8/8.
 */
public class KickoutSessionControlFilter extends AccessControlFilter {
    private SessionManager sessionManager;
    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter ; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //创建subject实例
        Subject subject = SecurityUtils.getSubject();

        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        String username = UserUtils.getPrincipal().getLoginName();
        Serializable sessionId = session.getId();

        //TODO 同步控制

        Deque<Serializable> deque = (Deque<Serializable>)CacheUtils.get(username);
//        Deque<Serializable> deque = cache.get(username);
        if(deque == null) {
            deque = new LinkedList<Serializable>();
            CacheUtils.put(username, deque);
//            cache.put(username, deque);
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > 1) {
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession =
                        sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
            }
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response,kickoutUrl);
            return false;
        }
        return true;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public boolean isKickoutAfter() {
        return kickoutAfter;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }
}