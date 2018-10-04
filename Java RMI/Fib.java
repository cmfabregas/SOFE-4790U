import java.rmi.*;

public interface Fib extends Remote {
   public final static String SERVICENAME="FibService";
   public int computeFib(int i) throws RemoteException;
}