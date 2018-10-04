import java.net.*;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {

        URL jw = new URL("http://www.nextproject.ca/");
        BufferedReader in = new BufferedReader(new InputStreamReader(jw.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}