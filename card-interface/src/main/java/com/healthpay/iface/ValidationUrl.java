package com.healthpay.iface;

import com.healthpay.common.web.BaseController;
import com.healthpay.iface.util.SHA1;
import com.healthpay.iface.util.WEIXIN;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017-01-05.
 */
@Controller
@RequestMapping(value = "${adminPath}/http")
public class ValidationUrl extends BaseController {

    @ResponseBody
    @RequestMapping(value = "validationUrl")
    public String validationUrl(String signature,long timestamp,long nonce,String echostr) {
        String data = WEIXIN.token+Long.toString(timestamp)+Long.toString(nonce);
        String digest = new SHA1().getDigestOfString(data.getBytes());
        if(!signature.equals(digest)){
            return "接入失败";
        }
            return echostr;
    }
}
