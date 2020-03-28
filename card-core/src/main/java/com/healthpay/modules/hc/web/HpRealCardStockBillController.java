package com.healthpay.modules.hc.web;

import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.service.ServiceException;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.hc.entity.HpRealCardStock;
import com.healthpay.modules.hc.entity.HpRealCardStockBill;
import com.healthpay.modules.hc.search.HpRealCardStockBillSearch;
import com.healthpay.modules.hc.search.HpRealCardStockSearch;
import com.healthpay.modules.hc.service.HpRealCardStockBillService;
import com.healthpay.modules.hc.service.HpRealCardStockService;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.service.OfficeService;
import com.healthpay.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存清单
 */
@Controller
@RequestMapping(value = "${adminPath}/hc/realCard/stockbill")
public class HpRealCardStockBillController extends BaseController {
    @Autowired
    private HpRealCardStockBillService hpRealCardStockBillService;
    @Autowired
    private HpRealCardStockService hpRealCardStockService;
    @Autowired
    private OfficeService officeService;

    private List<HpRealCardStockBill> listCache;

    private int stockSumCache;

    /**
     *
     * @param hpRealCardStockBill
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("hc:realCard:stockbill:list")
    @RequestMapping({"/list",""})
    public String list(@ModelAttribute HpRealCardStockBill hpRealCardStockBill, HttpServletRequest request, HttpServletResponse response, Model model){
        try{
            if(hpRealCardStockBill.getType() == null){
                hpRealCardStockBill.setType(1);
            }
            if(hpRealCardStockBill.getStatus() == null){
                hpRealCardStockBill.setStatus(1);
            }
            HpRealCardStockBill hpRealCardStockBillSearch = new HpRealCardStockBill();
            BeanUtils.copyProperties(hpRealCardStockBill,hpRealCardStockBillSearch);
            if(hpRealCardStockBill.getRegisterStartDate()!=null){
                String startDate = DateUtils.formatDate(hpRealCardStockBill.getRegisterStartDate())+" 00:00:00";
                hpRealCardStockBillSearch.setRegisterStartDate(DateUtils.parseDate(startDate));
            }
            if(hpRealCardStockBill.getRegisterEndDate()!=null){
                String endDate = DateUtils.formatDate(hpRealCardStockBill.getRegisterEndDate())+" 23:59:59";
                hpRealCardStockBillSearch.setRegisterEndDate(DateUtils.parseDate(endDate));
            }

            Page<HpRealCardStockBill> page = hpRealCardStockBillService.findPage(new Page<HpRealCardStockBill>(request, response), hpRealCardStockBillSearch);
            model.addAttribute("page", page);
            model.addAttribute("hpRealCardStockBillCon",hpRealCardStockBill);

            listCache = hpRealCardStockBillService.findList(hpRealCardStockBillSearch);
            //计算查询来的库存总数量
            int sum = 0;
            for(int i=0;i<page.getList().size();i++){
                sum += page.getList().get(i).getStockInOutMount();
            }
            model.addAttribute("stockSum",sum);

            stockSumCache = sum;

        }catch (Exception  e){
            logger.error("444444444444",e);
            e.printStackTrace();
        }

        return "modules/hc/hpRealCardStockBillList";
    }


    /**
     *
     * @param hpRealCardStockBill
     * @param sort 1:新增 2：修改
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(value = { "hc:realCard:stockbill:add", "hc:realCard:stockbill:edit" }, logical = Logical.OR)
    @RequestMapping(value = "save")
    public String save(@ModelAttribute HpRealCardStockBill hpRealCardStockBill,@RequestParam(required = true,defaultValue = "1")String sort,  HttpServletRequest request, HttpServletResponse response, Model model){
        int isNewRecord;
        //新增
        if(hpRealCardStockBill.getPkid() == null)isNewRecord=0;
        //修改
        else isNewRecord=1;
        try{
            if(!StringUtils.isNotEmpty(hpRealCardStockBill.getOfficeId())){
                throw  new ServiceException("没有对应的机构，或者机构id为空");
            }
            Office office = officeService.getById(hpRealCardStockBill.getOfficeId().split(",")[0]);
            if(office==null){
                throw  new ServiceException("没有对应的机构，或者机构代码为空");
            }
            hpRealCardStockBill.setSendOrganiCode(office.getCode());

            //查询库存对象
            HpRealCardStockSearch hpRealCardStockSearch  = new HpRealCardStockSearch();
            hpRealCardStockSearch.setSendOrganiCode(hpRealCardStockBill.getSendOrganiCode());
            HpRealCardStock hpRealCardStock = hpRealCardStockService.getHpRealCardStock(hpRealCardStockSearch);

            //执行入库或者出库的修改或者新
            if(isNewRecord==1){//修改
                //通过pkid查询清单的审核状态
                HpRealCardStockBillSearch s = new HpRealCardStockBillSearch();
                s.setPkid(hpRealCardStockBill.getPkid());
                HpRealCardStockBill h = hpRealCardStockBillService.getRealCardStockBill(s);
                //只有待审核状态的清单才能修改
                if(h!=null&&"2".equals(h.getStatus().toString())){
                    //出库校验
                    if(h.getType()==2 ){
                        //校验有无库存记录
                        if(hpRealCardStock== null) {
                            throw new ServiceException("该机构库存还没有入库，不能出库,请先入库");
                        }
                        //校验库存数量
                        if(hpRealCardStock.getStockMount()<hpRealCardStockBill.getStockInOutMount()){
                            throw  new ServiceException("库存数量小于出库数量");
                        }
                    }
                    //当入库时或者出库通过校验时 直接修改
                    hpRealCardStockBillService.updateRealCardStockBill(hpRealCardStockBill);
                }else{
                    throw  new ServiceException("非待审核状态的库存清单不能修改");
                }
            //新增
            }else{
                //出库校验
                if(hpRealCardStockBill.getType() == 2){
                    if(hpRealCardStock== null){
                        throw  new ServiceException("该机构库存还没有入库，不能出库,请先入库");
                    }
                    if(hpRealCardStock.getStockMount()<hpRealCardStockBill.getStockInOutMount()){
                        throw  new ServiceException("库存数量小于出库数量");
                    }
                }
                hpRealCardStockBill.setStatus(2);
                hpRealCardStockBill.setRegisterPerson(UserUtils.getUser().getId());
                hpRealCardStockBillService.insertRealCardStockBill(hpRealCardStockBill);
            }
        }catch (ServiceException serviceException){
            logger.error("==:"+serviceException.getMessage());
            model.addAttribute("errorMsg",serviceException.getMessage());
            return "error/error";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMsg",e.getMessage());
            return "error/error";
        }
        if(isNewRecord==0){
            return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill";
        }else{
            if(hpRealCardStockBill.getType()==1){
                return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill?status=2&type=1";
            }else{
                return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill?status=2&type=2";
            }
        }
    }

    /**
     *
     * @param hpRealCardStockBill
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(value = { "hc:realCard:stockbill:audit" }, logical = Logical.OR)
    @RequestMapping(value = "audit")
    public String audit(@ModelAttribute HpRealCardStockBill hpRealCardStockBill,  HttpServletRequest request, HttpServletResponse response, Model model){
        HpRealCardStockBill realCardStockBill;
        try{
            if(hpRealCardStockBill.getPkid() == null){//修改
                model.addAttribute("errorMsg","参数错误");
                return "error/error";
            }
            if(StringUtils.isNotEmpty(hpRealCardStockBill.getSendOrganiCode())){
                hpRealCardStockBill.setSendOrganiCode(hpRealCardStockBill.getSendOrganiCode().split(",")[0]);
            }
            HpRealCardStockBillSearch stockBillSearch = new HpRealCardStockBillSearch();
            stockBillSearch.setStatus(2);
            stockBillSearch.setPkid(hpRealCardStockBill.getPkid());
            realCardStockBill =  hpRealCardStockBillService.getRealCardStockBill(stockBillSearch);
            if(realCardStockBill == null){
                model.addAttribute("errorMsg","没有找到需要审核实体卡库存清单");
                return "error/error";
            }
            realCardStockBill.setStatus(hpRealCardStockBill.getStatus());
            hpRealCardStockBillService.audit(realCardStockBill);
        }catch (ServiceException serviceException){
            logger.error("==:"+serviceException.getMessage());
            model.addAttribute("errorMsg",serviceException.getMessage());
            return "error/error";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMsg","审核异常");
            return "error/error";
        }
        if(realCardStockBill.getType()==1){
            return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill?type=1";
        }else{
            return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill?type=2";
        }
    }

    /**
     * 删除
     * @param ids
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions(value = { "hc:realCard:stockbill:del" }, logical = Logical.OR)
    @RequestMapping(value = "del")
    public String delete(String ids, RedirectAttributes redirectAttributes){
        int type = 1;
        String idArray[] =ids.split(",");
        List<HpRealCardStockBill> list = new ArrayList<>();
        for(String pkid : idArray){
            HpRealCardStockBill search = new HpRealCardStockBill();
            search.setPkid(pkid);
            HpRealCardStockBill bill = hpRealCardStockBillService.get(search);
            if("1".equals(bill.getStatus().toString())){
                throw new ServiceException("审核成功的库存清单不能删除");
            }
            list.add(bill);
        }
        for(HpRealCardStockBill bill : list){
            hpRealCardStockBillService.delete(bill);
            type = bill.getType();
        }
        addMessage(redirectAttributes, "删除成功");
        if(type==1){
            return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill?status=2&type=1";
        }else{
            return "redirect:"+ Global.getAdminPath()+"/hc/realCard/stockbill?status=2&type=2";
        }

    }

    /**
     * 查看，增加，编辑健康卡表单页面
     */
    @RequiresPermissions(value = { "hc:realCard:stockbill:add", "hc:realCard:stockbill:edit"}, logical = Logical.OR)
    @RequestMapping(value = "form")
    public String form(@ModelAttribute HpRealCardStockBill hpRealCardStockBill, Model model) {
        HpRealCardStockBill entity = null;
        if (hpRealCardStockBill.getPkid() != null) {
            entity = hpRealCardStockBillService.get(hpRealCardStockBill);
        }
        if (entity == null) {
            entity = new HpRealCardStockBill();
        }
        model.addAttribute("hpRealCardStockBill", entity);
        return "modules/hc/hpRealCardStockBillForm";
    }

    /**
     *审核页面跳转
     */
    @RequiresPermissions(value = { "hc:realCard:stockbill:audit"}, logical = Logical.OR)
    @RequestMapping(value = "formAudit")
    public String formAudit(@ModelAttribute HpRealCardStockBill hpRealCardStockBill, Model model) {
        HpRealCardStockBill entity = null;
        if (hpRealCardStockBill.getPkid()== null) {
            model.addAttribute("errorMsg","参数错误");
            return "error/error";
        }
        hpRealCardStockBill.setStatus(2);//正在审核中
        entity = hpRealCardStockBillService.get(hpRealCardStockBill);
        if(entity == null){
            model.addAttribute("errorMsg","该状态下的订单不存在");
            return "error/error";
        }
        model.addAttribute("hpRealCardStockBill", entity);
        model.addAttribute("opeType", 3);//审核标志
        return "modules/hc/hpRealCardStockBillAuditForm";
    }

    /**
     * 导出excel文件
     */
    @RequiresPermissions("hc:realCard:stockbill:export")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(@ModelAttribute HpRealCardStockBill hpRealCardStockBill,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "实体卡库存台账"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            ExportExcel e = new ExportExcel("实体卡库存台账", HpRealCardStockBill.class).setDataList(listCache);

            //e.addCell(page.getList().size(),1,stockSumCache,0, Class.class);
            e.addCell( e.addRow(),0,"数量总计："+stockSumCache);
            e.addCell( e.addRow(),0,"说明：类型：1为入库，2为出库; 状态：1为成功，2为待审核，3为失败");
            e.write(response, fileName).dispose();
            //new ExportExcel("实体卡库存台账", HpRealCardStockBill.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出实体卡库存台账失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/hc/realCard/stockbill/?repage";
    }

}
