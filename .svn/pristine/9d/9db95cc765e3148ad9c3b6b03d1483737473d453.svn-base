package com.healthpay.modules.sys.security;

import com.healthpay.common.utils.SpringContextHolder;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.service.SystemService;
import com.healthpay.modules.sys.service.UserService;
import com.healthpay.modules.sys.utils.UserUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by admin on 2018/8/15.
 */
public class PasswordExpiredFilter extends AccessControlFilter {
    SystemService systemService;
    @Autowired
    UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if(null == UserUtils.getPrincipal()){
            return true;
        }
        String username = UserUtils.getPrincipal().getLoginName();
        User user = userService.getUserByUsername(username);
        if(user.getPasswordExpiredTime().getTime()<System.currentTimeMillis()){
            return false;
        }else return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        org.apache.shiro.web.util.WebUtils.issueRedirect(request, response,"/webpage/error/kickOut.jsp");
        return false;
    }

        /**
         * 获取系统业务对象
         */
        public SystemService getSystemService() {
            if (systemService == null){
                systemService = SpringContextHolder.getBean(SystemService.class);
            }
            return systemService;
        }
}