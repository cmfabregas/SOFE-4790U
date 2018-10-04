/**
 * @author Qusay H. Mahmoud
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Arith extends Remote {
    int[] addArrays(int a[], int b[]) throws RemoteException;
}
