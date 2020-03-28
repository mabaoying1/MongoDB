package com.healthpay.modules.sys.service.test2;

/**
 * Created by admin on 2018/1/24.
 */
public class test {
    public static void main(String[] args){

        try {
            String data = "<Data>\n" +
                    "<order_no>8</order_no>\n" +
                    "<merchant_no>1001004</merchant_no>\n"+
                    "</Data>";
            String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAtQEXHeIvIZLnMno9ynkL6GddJKIpfRwi3sKd/lnYtaFqMvFuH5sLJSv+lRkWvqEn9FOZmS2QUBEo2g83fPrt6wIDAQABAkAeapl83xZ2eN47IEmj7aVvn3cKgemg6Yp+jovIQ0bkdntGcV1ypO/1LeAPguNvTxGxP/gBP8GVC0nvEkSXV3JBAiEA/CBD/FZh4BBzYqhqWlcKCuSWKl1yjajDUmP0zM2SPiECIQC3yRFytzMEfblNBWqs02PbknqrD8GL1BPRbpcctNjyiwIhAJwsFFCy5U+YgRCkh7RL4+GZdFbrqXCMD+jyF4ng8GxhAiAn24ZaL5rz3WRDbOpOouWkJ+dMk/UwowTNRcOl6R6nswIgdPjfYpdYlX9XPpS37npQcfQbfYJvvaSMFXzr8ScNocs=";

            byte[] sign = RSAEncrypt.encrypt(privateKey,data.getBytes("utf-8"));
            String desKey = "O65SqJJSkr8=";
            byte[] content = EncryptUtil.encrypt(data.getBytes("utf-8"),desKey.getBytes());
            String xml = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                    "<Root>\n" +
                    "\t<Head>\n" +
                    "\t\t<MerchantNo>97494</MerchantNo>\n" +
                    "\t\t<FuncId>P2005</FuncId>\n" +
                    "\t\t<Timestamp>12312312312324</Timestamp>\n" +
                    "\t\t<Signature>"+new String(sign)+"</Signature>\n" +
                    "\t</Head>\n" +
                    "\t<Body>"+new String(content)+"</Body>\n" +
                    "</Root>";
            String retXml = HttpClientUtil.httpPost("http://116.7.255.40:53502/gatewayOnline/gateway/portal/execute",xml,"application/xml");

        } catch (Exception e) { }


    }
}
