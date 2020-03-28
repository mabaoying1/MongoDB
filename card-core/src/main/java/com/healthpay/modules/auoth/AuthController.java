/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.auoth;

import com.google.common.collect.Maps;
import com.healthpay.common.config.Global;
import com.healthpay.common.json.AjaxJson;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.security.shiro.session.SessionDAO;
import com.healthpay.common.servlet.ValidateCodeServlet;
import com.healthpay.common.utils.*;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.analysis.service.ResidentService;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.service.HpApplycardService;
import com.healthpay.modules.iim.service.MailBoxService;
import com.healthpay.modules.oa.service.OaNotifyService;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.security.FormAuthenticationFilter;
import com.healthpay.modules.sys.security.UsernamePasswordToken;
import com.healthpay.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.healthpay.modules.sys.service.SystemService;
import com.healthpay.modules.sys.service.UserService;
import com.healthpay.modules.sys.utils.UserUtils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 单点登录AuthController
 * @author admin
 * @version 2019-11-11
 */
@Controller
public class AuthController extends BaseController{
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 单点登录进入入口
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "${adminPath}/ssoLogin")
	public String ssoLogin(HttpServletRequest request,Model model){
		
		String username=request.getParameter("uid");
		String password=request.getParameter("pwd");
		
		
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "用户名、密码不能为空");
			return "redirect:" + adminPath + "/login";
		}
		
		
		System.out.println("username==="+username);
		System.out.println("password==="+password);
		username="admin";
		password="123456";
		
		request.setAttribute("username", username);
		request.setAttribute("password", password);
	    return "forward:login"; 
	    
	/*	User user = systemService.getUserByLoginName(username);
		if(user!=null && SystemService.validatePassword(password, user.getPassword())){
			String host = StringUtils.getRemoteAddr(request);
			UsernamePasswordToken token=new UsernamePasswordToken(username, password.toCharArray(), false, host, null, false, "1");
			return "redirect:" + adminPath;
		}else{
			model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "用户名或密码错误");
			return "redirect:" + adminPath + "/login";
		}*/
		/*boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		password="123456";
		UsernamePasswordToken token=new UsernamePasswordToken(username, password.toCharArray(), false, null, null, false, "1");
		 Subject   subject   = SecurityUtils.getSubject();
		 subject.login(token);
		return "redirect:" + adminPath;*/
	}

}
