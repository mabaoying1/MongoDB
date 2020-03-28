package com.healthpay.modules.sys.web;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healthpay.common.exception.BusException;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.sys.entity.SsoUser;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.security.Des;
import com.healthpay.modules.sys.security.SystemAuthorizingRealm;
import com.healthpay.modules.sys.security.UsernamePasswordToken;
import com.healthpay.modules.sys.service.SsoUserService;
import com.healthpay.modules.sys.service.UserService;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * Created by admin on 2018/3/28.
 */
@Controller
public class IDLoginController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private String ssoLoginEncrptAccount = "cardmanager";
	private String ssoLoginEncrptPrivateKey = "YyZ4h4O4H9";
	@Autowired
	UserService userService;
	@Autowired
	private SsoUserService ssoUserService;

	@RequestMapping(value = "/sso/login", method = RequestMethod.GET)
	public String login(String appName, String data, String sign, Long timeStamp, String unique,
			HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
		String line = null;
		String idNum = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			/*
			 * SecurityModel s = new SecurityModel(); s.setAppName(appName);
			 * s.setData(data); s.setSign(sign); s.setTimeStamp(timeStamp);
			 * s.setUnique(unique); idNum=new
			 * CommonSecurity(ssoLoginEncrptAccount,ssoLoginEncrptPrivateKey).
			 * decrypt(s);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		SsoUser ssoUser = ssoUserService.getByIdNumber(idNum.substring(8, 26));
		if (ssoUser == null) {
			throw new BusException("身份证号为" + idNum + "的用户不存在或已被禁用");
		}
		User user = userService.getUserByUsername(ssoUser.getLoginName());
		// 创建subject实例
		Subject subject = SecurityUtils.getSubject();
		// 判断当前用户是否登录
		if (subject.isAuthenticated() == false) {
			// 将用户名及密码封装交个UsernamePasswordToken
			String username = user.getLoginName();
			Des desObj = new Des();
			String password = "123456";
			if (password == null) {
				password = "";
			}
			boolean rememberMe = false;
			String host = StringUtils.getRemoteAddr((HttpServletRequest) request);
			UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), password.toCharArray(),
					rememberMe, host, null, false, "2");
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				System.out.println("验证不通过，无法登录！");
				return "error";
			}
		}
		return "redirect:/hp";
	}

	@RequestMapping(value = "/sso/xxxx", method = RequestMethod.GET)
	public void haha() {
		System.out.println("123123123123123123");
	}

	@RequestMapping(value = "/sso/ssoRegister", method = RequestMethod.GET)
	public String ssoRegister() {
		return "modules/sys/ssoRegister";
	}

	@RequestMapping(value = "${adminPath}/sso/ssoRegisterSend", method = RequestMethod.POST)
	public void ssoRegister(String username, String password, String idNum) {
		boolean rememberMe = false;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), rememberMe, null,
				null, false, "2");
		// 创建subject实例
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		subject.isAuthenticated();
		if (principal != null) {
			/*
			 * SecurityModel sm = new CommonSecurity("cardmanager",
			 * "YyZ4h4O4H9").encrypt(idNum);
			 * HttpUtil.sendJsonPost("http://55.0.4.61:8381/api/mangageUser",
			 * JSONUtils.toJSONString(sm));
			 */
		}
	}

}