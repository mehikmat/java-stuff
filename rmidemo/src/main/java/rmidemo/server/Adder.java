package rmidemo.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hdhamee on 6/24/16.
 */

//Step (1) create the remote interface
public interface Adder extends Remote {
    public int add(int x,int y)throws RemoteException;
}
