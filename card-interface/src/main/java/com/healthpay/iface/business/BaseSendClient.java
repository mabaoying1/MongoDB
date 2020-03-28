package com.healthpay.iface.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.iface.entity.HpIfaceAddress;
import com.healthpay.modules.iface.service.HpIfaceAddressService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component
public class BaseSendClient extends QuartzJobBean {
    private static Logger log = Logger.getLogger(BaseSendClient.class);
    @Autowired
    private HpIfaceAddressService hpIfaceAddressService;
    // 存放已经开启连接的client
    private static Map<String, Client> clientMap = new HashMap<String, Client>();
    
    private static JaxWsDynamicClientFactory dcf;

    public String send() {
//        log.info("[" + ck.getIckid() + "]锁定仓库物资开始...");
//        // 验证仓库是否开启接口
//        if (null == ck || ck.getIsckinterface().intValue() != 1) {
//            throw new BaseException("仓库不存在/仓库没有开启接口");
//        }
//        HeaderForm header = IBaseModel.getHeaderForm(ck.getIckid(), "0000", null, "1002");
//        try {
//            BillForm1002 form = lockCkSource(source, ck);
//            String sendxml = IBaseModel.parseBean2Xml(header, form);
//            log.info("发送锁定仓库物资报文：" + sendxml);
//            Client client = getClient(ck.getWebserviceurl());
//            String method = "do1002";
//            // 发送报文并获得结果
//            Object[] res = client.invoke(method, new Object[] {sendxml });
//            header = new HeaderForm();
//            // 将报文组装为对象
//            IBaseModel.loadXmlBean(header, res[0].toString());
//            // 0000 表示为成功
//            if (!"0000".equals(header.getRetCode())) {
//                throw new BaseException(header.getRetMsg());
//            }
//            // 如果成功了，保存
//            log.info("锁定仓库物资成功...");
//        } catch (BaseException e) {
//            log.info("锁定仓库物资失败：" + e.getMsg());
//            return "9999";
//        } catch (Exception e) {
//            log.info("锁定仓库物资失败：");
//            e.printStackTrace();
//            return "9999";
//        }
        return "success";
    }

    /**
     * 初始化client,并且将已经实例化的存放在MAP中
     * @param url
     * @return
     */
    @SuppressWarnings("deprecation")
    private Client getClient(String url) {
        if (null == dcf) {
           dcf = JaxWsDynamicClientFactory.newInstance();
        }
        // 根据路径转为MD5
        String urlmd5 = StringUtils.getMD5Code(url);
        // 根据key获取到已经实例化过的client
        Client client = clientMap.get(urlmd5);

        // 如果没有获取到，则需要初始化
        if (null == client) {
            try {
                client = dcf.createClient(url);
            } catch (Exception e) {
                log.info("连接" + url + "地址失败");
            }
            // 初始化后不能 null，将实例化的值存放到clientMap 中
            if (null != client) {
                clientMap.put(urlmd5, client);
            }
        }
        return client;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 获取webService  接口列表
        List<HpIfaceAddress> addressesList = hpIfaceAddressService.getAddressList("2");
        for (HpIfaceAddress address : addressesList) {
            // 校验webserviceurl不等于空则初始化
            if (StringUtils.isNotNull(address.getFuncAddress())) {
                this.getClient(address.getFuncAddress());
            }
        }
    }
}
