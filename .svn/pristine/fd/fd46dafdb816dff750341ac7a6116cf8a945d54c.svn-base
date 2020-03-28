package com.healthpay.common.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 2017/12/4.
 */
public class HttpOnlyIntercepter implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object arg2, Exception Exception)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object arg2, ModelAndView arg3) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Cookie cookie = cookies[0];
            if (cookie != null) {
                // serlvet 2.5 不支持在 Cookie 上直接设置 HttpOnly 属性.
                String value = cookie.getValue();

                StringBuilder builder = new StringBuilder();
                builder.append("JSESSIONID=" + value + "; ");
                builder.append("Secure; ");
                builder.append("HttpOnly; ");

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 1);
                Date date = calendar.getTime();
                Locale locale = Locale.CHINA;

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",locale);
                builder.append("Expires=" + sdf.format(date));

                response.setHeader("Set-Cookie", builder.toString());
            }
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {

        return true;
    }
}
