import java.io.*;
import java.util.Date;

public class SaveInfo {

  public static void main(String argv[]) throws Exception {
    FileOutputStream fos = new FileOutputStream("name.out");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    // create two objects
    UserInfo user1 = new UserInfo("Java Duke");
    UserInfo user2 = new UserInfo("Java Blue");
    // write the objects to the output stream
    oos.writeObject(user1);
    oos.writeObject(user2);
    oos.flush();
    oos.close();
    fos.close();
  }
}