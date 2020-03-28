package com.healthpay.modules.hc.web;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.hc.entity.HpRealCardStatistics;
import com.healthpay.modules.hc.entity.HpRealCardStockBill;
import com.healthpay.modules.hc.service.HpRealCardStatisticsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @DESC 机构发卡统计
 *
 * @author zhouwj
 * @date 2016-12-21
 */
@Controller
@RequestMapping("${adminPath}/hc/hpRealCard/statistics")
public class HpRealCardStatisticsController {
    @Autowired
    private HpRealCardStatisticsService hpRealCardStatisticsService;

    @RequiresPermissions("hc:hpRealCard:statistics:list")
    @RequestMapping(value = {"list", ""})
    public String list(@ModelAttribute HpRealCardStatistics hpRealCardStatistics, HttpServletRequest request, HttpServletResponse response, ModelMap model){
        try {
            hpRealCardStatistics.setStartDate(null);
            hpRealCardStatistics.setEndDate(null);
            if(hpRealCardStatistics.getStartDateTmp() != null && hpRealCardStatistics.getStartDateTmp().length >0){
                for(String startDate : hpRealCardStatistics.getStartDateTmp()){
                    if(StringUtils.isNotEmpty(startDate)){
                        hpRealCardStatistics.setStartDate(startDate);
                        break;
                    }
                }
                for(String endDate : hpRealCardStatistics.getEndDateTmp()){
                    if(StringUtils.isNotEmpty(endDate)){
                        hpRealCardStatistics.setEndDate(endDate);
                        break;
                    }
                }
            }
            Page<HpRealCardStatistics> page = hpRealCardStatisticsService.findPage(new Page<HpRealCardStatistics>(request, response),hpRealCardStatistics);
            model.addAttribute("page", page);
            model.addAttribute("totalC", hpRealCardStatisticsService.findListCount(hpRealCardStatistics));
            return "modules/hc/hpRealCardStatisticsList";
        }catch (Exception e){
            model.addAttribute("errorMsg","查询异常");
            return "error";
        }
    }
}
