package com.healthpay.iface.scheduler;

import com.healthpay.common.mapper.JsonMapper;
import com.healthpay.common.utils.DateUtils;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.common.utils.StringUtils;
import com.healthpay.iface.util.IBaseModel;
import com.healthpay.iface.util.JaxbUtils;
import com.healthpay.iface.vo.*;
import com.healthpay.iface.vo.gateway.HealthCardVo;
import com.healthpay.iface.vo.gateway.RealCardForGatewayVo;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.service.HpHealthcardService;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;
import com.healthpay.modules.tools.utils.HttpPostTest;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Created by gyp on 2016/6/17.
 */
@Component
public class HpIfaceMsgQueueJob extends QuartzJobBean {
    private static boolean isRun = false;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HpIfaceMsgqueueService hpIfaceMsgqueueService;
    @Autowired
    private HpIfaceMerchantService hpIfaceMerchantService;
    @Autowired
    HpHealthcardService hpHealthcardService;

    private void doA2002(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
        A2002 head = new A2002();
        head.setFuncid(hpIfaceMsgqueue.getFuncid());
        head.setMerid(hpIfaceMsgqueue.getMerId());

        List<A2002List> list = new ArrayList<A2002List>();
        A2002List bodybean = new A2002List();
        bodybean.setHealthCardId(hpIfaceMsgqueue.getHealthcardid());
        bodybean.setIcCardId(hpIfaceMsgqueue.getIcCardId());
        bodybean.setOptType(hpIfaceMsgqueue.getOptType());
        bodybean.setType(hpIfaceMsgqueue.getType());
        list.add(bodybean);
        head.setCards(list);
        String xml = JaxbUtils.convertToXml(head, "UTF-8");
        String retXml = HttpPostTest.post(hpIfaceMsgqueue.getPosturl(), xml);
        boolean isSuccess = false;
        if (StringUtils.isNotEmpty(retXml)) {
            A2002Ret ret = JaxbUtils.converyToJavaBean(retXml, A2002Ret.class);
            if (null != ret && "0".equals(ret.getCode())) {
                isSuccess = true;
            }
        }
        hpIfaceMsgqueueService.moveToBak(isSuccess, hpIfaceMsgqueue);
    }

    private void doA2001(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
        HeaderVo headerVo = new HeaderVo();
        headerVo.setFuncid(hpIfaceMsgqueue.getFuncid());
        headerVo.setMerid(hpIfaceMsgqueue.getMerId());
        headerVo.setTimer(String.valueOf(System.currentTimeMillis()));

        QueryCardHolderVo vo1 = new QueryCardHolderVo();

        MyBeanUtils.copyBeanNotNull2Bean(hpIfaceMsgqueue, vo1);
        vo1.setDocuId(hpIfaceMsgqueue.getDocuid());
        vo1.setDocuType(hpIfaceMsgqueue.getDocutype());
        vo1.setHealthCardId(hpIfaceMsgqueue.getHealthcardid());
        vo1.setIcCardId(null);

        HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.get(hpIfaceMsgqueue.getMerId());

        if (null == hpIfaceMerchant) {
            logger.error("商户[" + hpIfaceMsgqueue.getMerId() + "]发送审核结果失败,该商户不存在");

            return;
        }

        String sign = StringUtils.getMD5Code(hpIfaceMerchant.getDigitalKey() + headerVo.getFuncid() + headerVo.getTimer()
                + JsonMapper.toJsonString(vo1));

        Map<String, String> map = new HashMap<String, String>();
        String xml = IBaseModel.parseBean2Xml(headerVo, vo1);
        map.put("xml", xml);
        map.put("sign", sign);

        String body = HttpPostTest.post(hpIfaceMsgqueue.getPosturl(), map);

        boolean isSuccess = false;
        if ("hpcheckok".equals(body)) {
            isSuccess = true;
        }
        hpIfaceMsgqueueService.moveToBak(isSuccess, hpIfaceMsgqueue);
    }

    private void doGateway(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {

        HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.get(hpIfaceMsgqueue.getMerId());
        if (null == hpIfaceMerchant) {
            logger.error("商户[" + hpIfaceMsgqueue.getMerId() + "]卡管发送网关失败,该商户不存在");
            return;
        }

        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);

        Object vo;
        String funid = hpIfaceMsgqueue.getFuncid();
        if("P1001".equals(funid)|| "P1007".equals(funid)) {
            vo = this.setHealthCardVo(hpIfaceMsgqueue, headerVo);
        }else{
            vo = this.setRealCardVo(hpIfaceMsgqueue, headerVo);
        }

//        HpHealthcard hpHealthcard = hpHealthcardService.getByPkid(hpIfaceMsgqueue.getHealthcardid());
//        HpCardholder hpCardholder = hpHealthcard.getHpCardholder();
//        vo.setHealthCardId(hpIfaceMsgqueue.getHealthcardid());
//        vo.setCardType(hpHealthcard.getCardType());
//        vo.setName(hpCardholder.getName());
//        vo.setDocuType(hpCardholder.getDocuType());
//        vo.setDocuId(hpCardholder.getDocuId());
//        vo.setPhone(hpHealthcard.getPhone());
//        Date birth =hpCardholder.getBirth();
//        vo.setBirth(DateUtils.formatDate(birth,"yyyyMMdd"));
//        vo.setProfession(hpCardholder.getProfession());
//        vo.setSex(hpCardholder.getSex());
//        Date transDate = new Date(Long.valueOf(headerVo.getTimer()));
//        vo.setTransDate(DateUtils.formatDate(transDate,"yyyy-MM-dd hh:mm:ss"));
//        String addr = hpCardholder.getCountryname2() + hpCardholder.getProvname2() + hpCardholder.getCityname2()
//                + hpCardholder.getCountyname2() + hpCardholder.getTownname2() + hpCardholder.getVillagename2() + hpCardholder.getAddress2();
//        vo.setAddr(addr);
//        vo.setOrganization(hpHealthcard.getOffice().getName());



        String sign = StringUtils.getMD5Code(hpIfaceMerchant.getDigitalKey() + headerVo.getFuncid() + headerVo.getTimer()
                + JsonMapper.toJsonString(vo));

        Map<String, String> map = new HashMap<String, String>();
        String xml = IBaseModel.parseBean2Xml(headerVo, vo);
        map.put("xml", xml);
        map.put("sign", sign);

        String body = HttpPostTest.post(hpIfaceMsgqueue.getPosturl(), map);

        boolean isSuccess = false;
        if ("hpcheckok".equals(body)) {
            isSuccess = true;
        }
        hpIfaceMsgqueueService.moveToBak(isSuccess, hpIfaceMsgqueue);
    }

//    private void doP1002(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
//        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);
//    }
//
//    private void doP1003(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
//        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);
//    }
//
//    private void doP1004(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
//        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);
//    }
//
//    private void doP1005(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
//        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);
//    }
//
//    private void doP1006(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
//        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);
//    }
//
//    private void doP1007(HpIfaceMsgqueue hpIfaceMsgqueue) throws Exception {
//        HeaderVo headerVo = this.setHeader(hpIfaceMsgqueue);
//    }
    //设置头部
    private HeaderVo setHeader(HpIfaceMsgqueue hpIfaceMsgqueue){
        HeaderVo headerVo = new HeaderVo();
        headerVo.setFuncid(hpIfaceMsgqueue.getFuncid());
        headerVo.setMerid(hpIfaceMsgqueue.getMerId());
        headerVo.setTimer(String.valueOf(System.currentTimeMillis()));
        return headerVo;
    }
    //设置消息体(P1001和P1007)
    private HealthCardVo setHealthCardVo(HpIfaceMsgqueue hpIfaceMsgqueue,HeaderVo headerVo){
        HealthCardVo vo = new HealthCardVo();

        HpHealthcard hpHealthcard = hpHealthcardService.getByPkid(hpIfaceMsgqueue.getHealthcardid());
        HpCardholder hpCardholder = hpHealthcard.getHpCardholder();
        vo.setHealthCardId(hpIfaceMsgqueue.getHealthcardid());
        vo.setCardType(hpHealthcard.getCardType());
        vo.setName(hpCardholder.getName());
        vo.setDocuType(hpCardholder.getDocuType());
        vo.setDocuId(hpCardholder.getDocuId());
        vo.setPhone(hpHealthcard.getPhone());
        Date birth =hpCardholder.getBirth();
        if(birth!=null) {
            vo.setBirth(DateUtils.formatDate(birth, "yyyyMMdd"));
        }
        vo.setProfession(hpCardholder.getProfession());
        vo.setSex(hpCardholder.getSex());
        Date transDate = new Date(Long.valueOf(headerVo.getTimer()));
        vo.setTransDate(DateUtils.formatDate(transDate,"yyyy-MM-dd hh:mm:ss"));
        String addr = hpHealthcard.getCountryname2() + hpHealthcard.getProvname2() + hpHealthcard.getCityname2()
                + hpHealthcard.getCountyname2() + hpHealthcard.getTownname2() + hpHealthcard.getVillagename2();
        vo.setAddr(addr);
        vo.setOrganization(hpHealthcard.getOffice().getName());

        return vo;
    }
    //设置消息体(P1002,P1003,P1004,P1005,P1006)
    private RealCardForGatewayVo setRealCardVo(HpIfaceMsgqueue hpIfaceMsgqueue,HeaderVo headerVo){
        RealCardForGatewayVo vo = new RealCardForGatewayVo();

        HpHealthcard hpHealthcard = hpHealthcardService.getByPkid(hpIfaceMsgqueue.getHealthcardid());
        HpCardholder hpCardholder = hpHealthcard.getHpCardholder();

        vo.setHealthCardId(hpIfaceMsgqueue.getHealthcardid());
        vo.setCardType(hpHealthcard.getCardType());
        vo.setName(hpCardholder.getName());
        vo.setDocuType(hpCardholder.getDocuType());
        Date transDate = new Date(Long.valueOf(headerVo.getTimer()));
        vo.setTransDate(DateUtils.formatDate(transDate,"yyyy-MM-dd hh:mm:ss"));

        return vo;
    }
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        if (isRun) {
            logger.info("服务正在执行");

            return;
        }

        try {
            isRun = true;

            List<HpIfaceMsgqueue> hpIfaceMsgqueueList = hpIfaceMsgqueueService.findMsgQueueList();

            for (HpIfaceMsgqueue hpIfaceMsgqueue : hpIfaceMsgqueueList) {
                if ("A2001".equals(hpIfaceMsgqueue.getFuncid())) {
                    this.doA2001(hpIfaceMsgqueue);
                }
                if ("A2002".equals(hpIfaceMsgqueue.getFuncid())) {
                    this.doA2002(hpIfaceMsgqueue);
                }
//                if ("P1001".equals(hpIfaceMsgqueue.getFuncid())
//                        ||"P1002".equals(hpIfaceMsgqueue.getFuncid())
//                        ||"P1003".equals(hpIfaceMsgqueue.getFuncid())
//                        ||"P1004".equals(hpIfaceMsgqueue.getFuncid())
//                        ||"P1005".equals(hpIfaceMsgqueue.getFuncid())
//                        ||"P1006".equals(hpIfaceMsgqueue.getFuncid())
//                        ||"P1007".equals(hpIfaceMsgqueue.getFuncid()) ){
//                    this.doGateway(hpIfaceMsgqueue);
//                }
            }
        } catch (Exception e) {
            logger.error("审核结果发送失败", e);
        } finally {
            isRun = false;
        }
    }


}


//~ Formatted by Jindent --- http://www.jindent.com
