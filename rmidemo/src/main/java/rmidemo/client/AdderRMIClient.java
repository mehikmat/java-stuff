package rmidemo.client;

import rmidemo.server.Adder;

import java.rmi.Naming;

/**
 * Created by hdhamee on 6/24/16.
 */

// 6) Create and run the client application
public class AdderRMIClient {

    public static void main(String args[]){
        try{
            Adder stub = (Adder) Naming.lookup("rmi://localhost:1099/calculator");
            System.out.println(stub.add(34,4));
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}

