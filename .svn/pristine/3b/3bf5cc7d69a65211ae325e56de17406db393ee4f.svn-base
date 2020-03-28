package com.healthpay.modules.analysis.web;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.analysis.service.ResidentService;
import com.healthpay.modules.hc.entity.HpCardholder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2016-12-23.
 */
@Controller
@RequestMapping(value = "${adminPath}/analysis/table")
public class TableController extends BaseController {

    @Autowired
    private ResidentService residentService;

    @RequiresPermissions("analysis:table:list")
    @RequestMapping(value = { "list", "" })
    public String list(HpCardholder hpCardholder, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        Page<HpCardholder> page = residentService.getTable(new Page<HpCardholder>(request, response), hpCardholder);

        model.addAttribute("page", page);

        return "modules/analysis/tableList";
    }
}
