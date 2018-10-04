import java.io.*;
import java.util.Date;

//instance of the date object and save the instance
public class SaveDate {

  public static void main(String argv[]) throws Exception {
    FileOutputStream fos = new FileOutputStream("date.out"); //file where the state is saved
    ObjectOutputStream oos = new ObjectOutputStream(fos); //
    Date date = new Date();
    oos.writeObject(date); 
    oos.flush();
    oos.close();
    fos.close();
  }
}