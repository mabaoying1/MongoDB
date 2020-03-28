import java.util.Random;

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
      Random random = new Random();
      random.setSeed(random.nextLong() + System.currentTimeMillis());
      System.out.print((int)(random.nextDouble() * 1000000000));
   }
}
