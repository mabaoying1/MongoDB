/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */



package com.healthpay.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolationException;

import com.healthpay.modules.sys.utils.AreaUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.healthpay.common.config.Global;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.common.utils.excel.ExportExcel;
import com.healthpay.common.utils.excel.ImportExcel;
import com.healthpay.common.web.BaseController;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.service.AreaService;
import com.healthpay.modules.sys.service.OfficeService;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * Class AreaController
 *
 *
 * @version        1.0, 16/06/28
 * @author         gyp
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {
    @Autowired
    private AreaService   areaService;
    @Autowired
    private OfficeService officeService;

    /**
     * Method description
     *
     *
     * @param parentId
     * @param response
     *
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "ajaxList")
    public List<Area> ajaxList(@RequestParam(required = false) String parentId, HttpServletResponse response) {
        List<Area> areaList = areaService.findList(false, parentId);

        return areaList;
    }

    /**
     * Method description
     *
     *
     * @param area
     * @param redirectAttributes
     *
     * @return
     */
    @RequiresPermissions("sys:area:del")
    @RequestMapping(value = "delete")
    public String delete(Area area, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");

            return "redirect:" + adminPath + "/sys/area";
        }

        // if (Area.isRoot(id)){
        // addMessage(redirectAttributes, "删除区域失败, 不允许删除顶级区域或编号为空");
        // }else{
        areaService.delete(area);
        addMessage(redirectAttributes, "删除区域成功");


        // }
        return "redirect:" + adminPath + "/sys/area/";
    }


    /**
     * Method description
     *
     *
     * @param area
     * @param request
     * @param response
     * @param redirectAttributes
     *
     * @return
     */
    @RequiresPermissions("sys:area:export")
    @RequestMapping(
        value  = "export",
        method = RequestMethod.POST
    )
    public String exportFile(Area area, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        try {
            if (Global.isDemoMode()) {
                addMessage(redirectAttributes, "演示模式，不允许操作！");

                return "redirect:" + adminPath + "/sys/area";
            }

            String     fileName = "区域设置" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<Area> page     = areaService.findPage(new Page<Area>(request, response, -1), area);

            new ExportExcel("区域设置", Area.class).setDataList(page.getList()).write(response, fileName).dispose();

            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出区域设置记录失败！失败信息：" + e.getMessage());
        }

        return "redirect:" + Global.getAdminPath() + "/sys/area/?repage";
    }

    /**
     * Method description
     *
     *
     * @param area
     * @param model
     *
     * @return
     */
    @RequiresPermissions(
        value   = { "sys:area:view", "sys:area:add", "sys:area:edit" },
        logical = Logical.OR
    )
    @RequestMapping(value = "form")
    public String form(Area area, Model model) {
        if ((area.getParent() == null) || (area.getParent().getId() == null)) {
            area.setParent(UserUtils.getUser().getArea());
        } else {
            area.setParent(areaService.get(area.getParent().getId()));
        }

        //// 自动获取排序号
        // if (StringUtils.isBlank(area.getId())){
        // int size = 0;
        // List<Area> list = areaService.findAll();
        // for (int i=0; i<list.size(); i++){
        // Area e = list.get(i);
        // if (e.getParent()!=null && e.getParent().getId()!=null
        // && e.getParent().getId().equals(area.getParent().getId())){
        // size++;
        // }
        // }
        // area.setCode(area.getParent().getCode() +
        // StringUtils.leftPad(String.valueOf(size > 0 ? size : 1), 4, "0"));
        // }
        model.addAttribute("area", area);

        return "modules/sys/areaForm";
    }

    /**
     * Method description
     *
     *
     * @param file
     * @param redirectAttributes
     *
     * @return
     */
    @RequiresPermissions("sys:area:import")
    @RequestMapping(
        value  = "import",
        method = RequestMethod.POST
    )
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            if (Global.isDemoMode()) {
                addMessage(redirectAttributes, "演示模式，不允许操作！");

                return "redirect:" + adminPath + "/sys/area";
            }

            int           successNum = 0;
            int           failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel   ei         = new ImportExcel(file, 1, 0);
            List<Area>    list       = ei.getDataList(Area.class);

            for (Area area : list) {
                try {
                    areaService.save(area);
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureNum++;
                } catch (Exception ex) {
                    failureNum++;
                }
            }

            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条区域设置记录。");
            }

            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条区域设置记录" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入区域设置失败！失败信息：" + e.getMessage());
        }

        return "redirect:" + Global.getAdminPath() + "/sys/area/?repage";
    }

    /**
     * Method description
     *
     *
     * @param response
     * @param redirectAttributes
     *
     * @return
     */
    @RequiresPermissions("sys:area:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String     fileName = "区域设置数据导入模板.xlsx";
            List<Area> list     = Lists.newArrayList();

            new ExportExcel("区域设置数据", Area.class, 1).setDataList(list).write(response, fileName).dispose();

            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }

        return "redirect:" + Global.getAdminPath() + "/sys/area/?repage";
    }

    /**
     * Method description
     *
     *
     * @param area
     * @param model
     *
     * @return
     */
    @RequiresPermissions("sys:area:list")
    @RequestMapping(value = { "list", "" })
    public String list(Area area, Model model) {
        List<Area> areaList = areaService.findList(false, null);

//
//      model.addAttribute("topId", areaList.get(0).getParentId());
        model.addAttribute("list", areaList);

        return "modules/sys/areaList";
    }

    /**
     * Method description
     *
     *
     * @param area
     * @param model
     * @param redirectAttributes
     *
     * @return
     */
    @RequiresPermissions(
        value   = { "sys:area:add", "sys:area:edit" },
        logical = Logical.OR
    )
    @RequestMapping(value = "save")
    public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");

            return "redirect:" + adminPath + "/sys/area";
        }

        if (!beanValidator(model, area)) {
            return form(area, model);
        }

        if (area.getIsNewRecord()) {
            area.setId(area.getCode());

            Area bean = areaService.get(area.getId());

            if (null != bean) {
                addMessage(redirectAttributes, "区域代码已经存在");

                return "redirect:" + adminPath + "/sys/area/";
            }
        }

        areaService.save(area);
        addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");

        return "redirect:" + adminPath + "/sys/area/";
    }

    /**
     * Method description
     *
     *
     * @param extId
     * @param officeId
     * @param isAll
     * @param id
     * @param response
     *
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
                                              @RequestParam(required = false) String officeId,
                                              @RequestParam(required = false) Boolean isAll,
                                              @RequestParam(required = false) String id, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        String                    areaId  = null;
        if (StringUtils.isNotBlank(officeId) && !"undefined".equals(officeId)) {
            areaId = officeService.getAreaIdById(officeId);
            id = areaId;
        }
        List<Area> list = AreaUtils.getAreaListByParentId(id);
        for (int i = 0; i < list.size(); i++) {
            Area    e    = list.get(i);
            boolean flag = (StringUtils.isBlank(areaId)
                            || (StringUtils.isNotBlank(areaId)
                                && ((e.getParentIds().indexOf("," + areaId + ",") != -1) || e.getId().equals(areaId))));

            if ((StringUtils.isBlank(extId)
                    || ((extId != null) &&!extId.equals(e.getId())
                        && (e.getParentIds().indexOf("," + extId + ",") == -1)))
                    && flag) {
                Map<String, Object> map = Maps.newHashMap();

                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("name", e.getName());
                map.put("isParent", e.getIsParent());

                //map.put("type", e.getType());
                mapList.add(map);
            }
        }

        return mapList;
    }

    /**
     * Method description
     *
     *
     * @param id
     *
     * @return
     */
    @ModelAttribute("area")
    public Area get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return areaService.get(id);
        } else {
            return new Area();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
