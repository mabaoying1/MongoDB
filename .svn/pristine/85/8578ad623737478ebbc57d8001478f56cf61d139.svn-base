package com.healthpay.modules.analysis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.analysis.service.ResidentService;
import com.healthpay.modules.hc.entity.HpCardholder;

@Controller
@RequestMapping(value = "${adminPath}/analysis/resident")
public class ResidentController extends BaseController {
    @Autowired
    private ResidentService residentService;

    @RequiresPermissions("analysis:resident:list")
    @RequestMapping(value = { "list", "" })
    public String list(HpCardholder hpCardholder, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        Page<HpCardholder> page = residentService.findPage(new Page<HpCardholder>(request, response), hpCardholder);

        model.addAttribute("page", page);

        return "modules/analysis/residentList";
    }
}
