import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class FibImpl extends UnicastRemoteObject implements Fib {
   
   
   public FibImpl() throws RemoteException {
      super();
   }

   public int computeFib(int n) {
     int fact = 1;
     for(int i=n;i>=1;i--) {
       fact = fact * i;
     }
     return fact;
   }
}