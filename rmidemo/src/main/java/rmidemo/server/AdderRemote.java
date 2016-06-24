package rmidemo.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by hdhamee on 6/24/16.
 */

// Step (2) Provide the implementation of the remote interface.
public class AdderRemote extends UnicastRemoteObject implements Adder{

    public AdderRemote() throws RemoteException {
        super();
    }

    public int add(int x,int y){return x+y;}
}
