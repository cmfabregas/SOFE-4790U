/** 
 * @author Qusay H. Mahmoud
 */
import java.rmi.*;
import java.net.*;

public class ArithApp {
  
    public static void main(String argv[]) {
	   System.setSecurityManager(new SecurityManager());
	   int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9};
	   int b[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
	   int result[] = new int[10];
	   try {
           Arith obj = (Arith)Naming.lookup("//127.0.0.1/ArithServer");
	       result = obj.addArrays(a, b);
	   } catch (Exception e) {
	     System.out.println("ArithApp exception:"+e.getMessage());
	     e.printStackTrace();
	   }
	   System.out.println("The sum of the arrays is: ");
	   for (int j=0; j<10; j++) {
	      System.out.println(result[j]);
	   }
    }
}

