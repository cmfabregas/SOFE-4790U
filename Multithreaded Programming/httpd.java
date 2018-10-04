/**
 * @(#)httpd.java
 * @author Qusay H. Mahmoud
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

/** 
 * This class implements a multithreaded simple minded HTTP server. This 
 * server doesn't handle CGI. All it does it listens on port 8080 and 
 * waits for connections and servers requested documents.
 */

public class httpd {
    // The port number on which the server will be listening on
    public static final int HTTP_PORT =3500;

    // constructor.
    public httpd() {
    }
    
    public ServerSocket getServer() throws Exception {
      return new ServerSocket(HTTP_PORT);
    }

    // multi-threading -- create a new connection for each request
    public void run() {
     ServerSocket listen;
    try {
       listen = getServer();
       while(true) {
         Socket client = listen.accept();
         Connects cc = new Connects(client);
     }
     } catch(Exception e) {
       System.out.println("Exception..."+e);
     }
    }

    // main program
    public static void main(String argv[]) throws Exception {
      //System.setSecurityManager(new OurHttpdSecurityManager());
      httpd ht = new httpd();
      ht.run();
    }
}

class Connects extends Thread {
    Socket client;
    BufferedReader is;
    DataOutputStream os;
   
    public Connects(Socket s) { // constructor
        client = s;
        try {
           is = new BufferedReader(new InputStreamReader(client.getInputStream()));
           os = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
           try {
             client.close();
           } catch (IOException ex) {
             System.out.println("Error while getting socket streams.."+ex);
           }
           return;
        }
        this.start(); // Thread starts here...this start() will call run()
    }
 
    public void run() {
    try {
       // get a request and parse it.
       String request = is.readLine();
       System.out.println( "Request: "+request );
       StringTokenizer st = new StringTokenizer( request );
       if ( (st.countTokens() >= 2) && st.nextToken().equals("GET") ) {
         if ( (request = st.nextToken()).startsWith("/") )
            request = request.substring( 1 );
          if ( request.endsWith("/") || request.equals("") )
            request = request + "index.html";
            os.write("HTTP/1.1 200 OK\n");
            File f = new File(request);
            shipDocument(os, f);
        } else {
            os.writeBytes( "400 Bad Request" );
        } 
        client.close();
        } catch ( IOException e ) {
            System.out.println( "I/O error " + e );
        } catch (Exception ex) {
            System.out.println("Exception: "+ex);
        }       
     }

    /**
     * Read the requested file and ships it to the browser if found.
     */
     
     public static void shipDocument(DataOutputStream out, File f) throws Exception {
       try {
          DataInputStream in = new DataInputStream(new FileInputStream(f));
          int len = (int) f.length();
          byte[] buf = new byte[len];
          in.readFully(buf);          
          out.write(buf,0,len);
          in.close();

          String s = f.toString();
          if(s.endsWith(".jpg))")) 
              out.writeBytes("Content-Type: image/jpeg\n\n");
              
       } catch (FileNotFoundException e) {
          out.writeBytes("404 Not Found");
       } catch (SecurityException e1) {
          out.writeBytes("403 Forbidden...not enough access rights"+e1);
       } 
     }
}
