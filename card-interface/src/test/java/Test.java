import com.healthpay.iface.util.JaxbUtils;
import com.healthpay.iface.vo.A2002;
import com.healthpay.iface.vo.A2002List;
import com.healthpay.iface.vo.A2002Ret;

import javax.xml.bind.JAXB;
import java.util.ArrayList;
import java.util.List;

/**
 * Test
 *
 * @author gyp
 * @date 2016/7/25
 */
public class Test {
   public static  void  main(String[] args){

//       System.out.print(System.currentTimeMillis());
//       A2002 a2002 = new A2002();
//       a2002.setFuncid("A2002");
//       a2002.setMerid("95734");
//
//       A2002List a2002List = new A2002List();
//       a2002List.setHealthCardId("0002003030");
//       List<A2002List> list = new ArrayList<A2002List>();
//       list.add(a2002List);
//       a2002.setCards(list);
//       System.out.print(JaxbUtils.convertToXml(a2002,"utf-8"));

       String xml = "<Data><code>0</code><msg>123</msg></Data>";
       A2002Ret ret = JaxbUtils.converyToJavaBean(xml,A2002Ret.class);
   }
}
