/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */
package com.healthpay.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.sys.entity.Dict;
import com.healthpay.modules.sys.service.DictService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 字典Controller
 * @author jeeplus
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/bizFlow")
public class BizFlowController extends BaseController {

	public String form() {
		return "modules/sys/bizFlow";
	}
}
