import java.io.*;
import java.util.Date;

public class ReadInfo {

  public static void main(String argv[]) throws Exception {
    FileInputStream fis = new FileInputStream("name.out");
    ObjectInputStream ois = new ObjectInputStream(fis);
    // read the objects from the input stream (the file name.out)
    UserInfo user1 = (UserInfo) ois.readObject();
    UserInfo user2 = (UserInfo) ois.readObject();
    // invoke a method on the constructed object
    user1.printInfo();
    user2.printInfo();
    ois.close();
    fis.close();
  }
}