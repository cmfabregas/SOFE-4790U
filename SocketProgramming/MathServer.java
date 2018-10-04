/**
 * @author Qusay H. Mahmoud
 */

import java.io.*;
import java.net.*;

public class MathServer {

   ServerSocket hi;
   Socket client;
   DataInputStream br;
   DataOutputStream dos;
 
   public static int add(int a, int b) {
      return a+b;
   }

   public static void main(String argv[]) throws Exception {
     new MathServer();
   }

   public MathServer() throws Exception {
     hi = new ServerSocket(3500);
     System.out.println("Server Listening on port 3500....");
     client = hi.accept();
     br = new DataInputStream(client.getInputStream());
     dos = new DataOutputStream(client.getOutputStream());
  
     int x = br.readInt();
     System.out.println("I got: "+x);
     int y = br.readInt();
     System.out.println("I got: "+y);
     int ans = add(x,y);
     System.out.println("I am sending the answer...");
     dos.writeBytes("the sum is: "+ans+"\n");     
   }
}
