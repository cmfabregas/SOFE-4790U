/**
 * @author Qusay H. Mahmoud
 */

import java.io.*;
import java.net.*;

public class HiServer {
   public static void main(String argv[]) throws Exception {
     ServerSocket hi;
     Socket client;
     BufferedReader br;
     DataOutputStream dos;

     hi = new ServerSocket(5000);
     System.out.println("Server Listening on port 5000....");
     client = hi.accept();
     br = new BufferedReader(new InputStreamReader(client.getInputStream()));
     dos = new DataOutputStream(client.getOutputStream());
     String line = br.readLine();
     if(line.equals("Hi")) {
       dos.writeBytes("....hi to you...."+"\n");
     } else {
       dos.writeBytes("....you don't speak my protocol..."+"\n");
     }
  }
}
