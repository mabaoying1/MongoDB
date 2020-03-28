package com.healthpay.iface.service.impl;

import com.healthpay.common.utils.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单httpclient实例 
 *
 * @author arron
 * @date 2015年11月11日 下午6:36:49  
 * @version 1.0
 */
public class test {

    /**
     * 模拟请求 
     *
     * @param url       资源地址 
     * @param map   参数列表 
     * @param encoding  编码 
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String send(String url, Map<String,String> map, String encoding) throws ParseException, IOException {
        String body = "";

        //创建httpclient对象  
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象  
        HttpPost httpPost = new HttpPost(url);

        //装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //设置header信息  
        //指定报文头【Content-type】、【User-Agent】  
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）  
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体  
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型  
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接  
        response.close();
        return body;
    }

    public static void main(String[] args) throws ParseException, IOException {
        long time = System.currentTimeMillis();
        String url = "http://55.5.0.7:81/card-interface/hp/http/doHealth";
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><RetCode/><Timer>"+time+"</Timer><Funcid>A1005</Funcid><Merid>95734</Merid></Head><Body><Nationality>123123123123</Nationality><DocuType>10002</DocuType><DocuId>张三</DocuId></Body></Data>";
        Map<String,String> params = new HashMap<String,String>();
        String key ="6d4a14rY3M5BgcXT9aL24ah1343l8WC124XhVy3V5ZL1xd3113v2K0zm85A888V8";
        String funid = "A1005";

        String all = key + funid + time + xml;
        String cardsign = com.healthpay.common.utils.StringUtils.getMD5Code("vm12r1W3847CA0i70ebL68Ww092787533xdT5K53k1nd5os42bC6u0B1Go61MNcmA10011517989047409<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data><Head><Merid>85853</Merid><Funcid>A1001</Funcid><RetCode></RetCode><Errmsg></Errmsg><Timer>1517989047409</Timer></Head><Body><Nationality>01</Nationality><DocuType>01</DocuType><DocuId>440883198811062217</DocuId><HealthId></HealthId><IcCardId>27207585</IcCardId><Type>0</Type><NewFarmid></NewFarmid><Name>李康荣</Name><Birth></Birth><Profession>1</Profession><Sex>1</Sex><Nation>01</Nation><MaritalStatus></MaritalStatus><EducationLevel>10</EducationLevel><Provcode></Provcode><Provname></Provname><Citycode></Citycode><Cityname></Cityname><Countycode></Countycode><Tountyname></Tountyname><Towncode></Towncode><Townname></Townname><Villagecode></Villagecode><Villagename></Villagename><Postcode></Postcode><Provcode2></Provcode2><Provname2></Provname2><Citycode2></Citycode2><Cityname2></Cityname2><Countycode2></Countycode2><Countyname2></Countyname2><Towncode2></Towncode2><Townname2></Townname2><Villagecode2></Villagecode2><Villagename2></Villagename2><Address2>广东省中山市-</Address2><Paytype></Paytype><Phone>13823941202</Phone><Tel></Tel><Email></Email><AttnName></AttnName><AttnPhone></AttnPhone><AttnRela></AttnRela><AppliDate>2018-02-07 15:37:27</AppliDate><SecurityCode></SecurityCode><ChipSerialid></ChipSerialid><CardType></CardType><Version></Version><StartDate></StartDate><ValidDate></ValidDate><Password></Password><Reserved></Reserved><Img1></Img1><Img2></Img2></Body></Data>");

        Map<String, String> map = new HashMap<String, String>();
         map.put("xml", xml);
              map.put("sign", cardsign);
        String body = send(url, map,"utf-8");
        System.out.println("交易响应结果：");
        System.out.println(body);

        System.out.println("-----------------------------------");

//        map.put("city", "北京");
//        body = send(url, map, "utf-8");
//        System.out.println("交易响应结果：");
//        System.out.println(body);
    }
} 