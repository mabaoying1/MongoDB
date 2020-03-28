package com.healthpay.modules.hc.web;

import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.hc.dao.HpRealCardStockDao;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.hc.entity.HpRealCardStock;
import com.healthpay.modules.hc.search.HpRealCardStockSearch;
import com.healthpay.modules.hc.service.HpRealCardStockService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhouwj on 2016/11/17.
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/realCard/stock")
public class HpRealCardStockController extends BaseController {
    @Autowired
    private HpRealCardStockService hpRealCardStockService;
    @Autowired
    HpRealCardStockDao hpRealCardStockDao;

    @RequiresPermissions(value = {"hc:realCard:stock:list"})
    @RequestMapping({"/list", ""})
    public String list(@ModelAttribute HpRealCardStock hpRealCardStock, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Page<HpRealCardStock> page = hpRealCardStockService.findPage(new Page<HpRealCardStock>(request, response), hpRealCardStock);
            model.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "modules/hc/hpRealCardStockList";
    }

    @RequiresPermissions(value = {"hc:send:realCard:list"})
    @RequestMapping({"/sendlist"})
    public String sendLlist(@ModelAttribute HpRealCardStock hpRealCardStock, String startDate, String endDate, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Page<HpRealCardStock> page =null;
            int cancel;
            int notCancel;
            int total;
            if ((!"".equals(startDate) && startDate != null) || (!"".equals(endDate) && endDate != null)) {
                //无时间条件 去查询
                List<HpRealCardStock> listWithoutDate = hpRealCardStockService.findList(hpRealCardStock);

                page = new Page<HpRealCardStock>(request, response);
                hpRealCardStock.setPage(page);
                List<HpRealCardStock> list =  hpRealCardStockService.findWithDate(hpRealCardStock.getSendOrganiCode(),hpRealCardStock.getSendOrganiName(), startDate, endDate);
                for(HpRealCardStock h:list){
                    for(HpRealCardStock s:listWithoutDate){
                        if(s.getSendOrganiCode().equals(h.getSendOrganiCode())){
                            long sendCardCount = s.getRealSendCardCount()==null?0:s.getRealSendCardCount();
                            long totalMount = s.getReceiveTotalMount()==null?0:s.getReceiveTotalMount();
                            h.setRemainingMount(totalMount-sendCardCount);
                        }
                    }
                }
                page.setList(hpRealCardStockService.findWithDate(hpRealCardStock.getSendOrganiCode(),hpRealCardStock.getSendOrganiName(), startDate, endDate));
                cancel = hpRealCardStockService.findCancelWithDate(hpRealCardStock.getSendOrganiCode(),hpRealCardStock.getSendOrganiName(), startDate, endDate);
                notCancel = hpRealCardStockService.findNotCancelWithDate(hpRealCardStock.getSendOrganiCode(),hpRealCardStock.getSendOrganiName(), startDate, endDate);
                total = hpRealCardStockService.findTotalWithDate(hpRealCardStock.getSendOrganiCode(),hpRealCardStock.getSendOrganiName(), startDate, endDate);
            } else {
                page = hpRealCardStockService.findPage(new Page<HpRealCardStock>(request, response), hpRealCardStock);
                List<HpRealCardStock> list = page.getList();
                for(HpRealCardStock h:list){
                    long sendCardCount = h.getRealSendCardCount()==null?0:h.getRealSendCardCount();
                    long totalMount = h.getReceiveTotalMount()==null?0:h.getReceiveTotalMount();
                    h.setRemainingMount(totalMount-sendCardCount);
                }
                page.setList(list);
                cancel = hpRealCardStockService.findCancel(hpRealCardStock);
                notCancel = hpRealCardStockService.findNotCancel(hpRealCardStock);
                total = hpRealCardStockService.findTotal(hpRealCardStock);
            }
            model.addAttribute("page", page);
            model.addAttribute("startDate",startDate==null?"":startDate);
            model.addAttribute("endDate",endDate==null?"":endDate);
            request.setAttribute("cancel", cancel);
            request.setAttribute("notCancel", notCancel);
            request.setAttribute("total", total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "modules/hc/hpSendRealCardList";
    }

}
