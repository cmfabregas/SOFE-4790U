/**
 * @author Qusay H. Mahmoud
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ArithImpl extends UnicastRemoteObject implements Arith {
    private String name;

    public ArithImpl(String s) throws RemoteException {
	super();
        name = s;
    }

    public int[] addArrays(int a[], int b[]) throws RemoteException {
	int c[] = new int[10];
	for (int i=0; i<10; i++) {
	   c[i] = a[i] + b[i];
	}  
        return c;
    }

    public static void main(String argv[]) {
	System.setSecurityManager(new SecurityManager()); //client and the server must decide what's allowed and not allowed

	try {
	   ArithImpl obj = new ArithImpl("ArithServer"); //implements the remote interface
           Naming.rebind("//localhost/ArithServer", obj); //Naming service, another server that we run (default port 1099))
	   System.out.println("ArithServer bound in registry");
	} catch (Exception e) {
	   System.out.println("ArithImpl err: " + e.getMessage());
	   e.printStackTrace();
	}
    }
}