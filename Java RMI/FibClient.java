import java.io.*;
import java.rmi.Naming;

public class FibClient {
   public static void main(String[] args) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Enter a number: ");
      String s = null;
      try {
         s = br.readLine();
      } catch(IOException ioe) {
         System.err.println(ioe.getMessage());
      }
      int x = Integer.parseInt(s);
      try {
        Fib f = (Fib) Naming.lookup(Fib.SERVICENAME);
        int fn = f.computeFib(x);
        System.out.println("The factorial of "+ x + " is: "+fn);
      } catch(Exception e) {
        System.err.println("Remote exception: "+e.getMessage());
        e.printStackTrace();
      }
   }
}
