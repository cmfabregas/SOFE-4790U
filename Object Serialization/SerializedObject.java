import java.io.*;
import java.util.*;

public class SerializedObject implements Serializable {
   private int array[] = null;

   public SerializedObject() {
   }

   public void setArray(int array[]) {
     this.array = array;
   }

   public int[] getArray() {
     return array;
   }
}