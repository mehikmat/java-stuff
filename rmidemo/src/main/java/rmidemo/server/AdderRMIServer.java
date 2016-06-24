package rmidemo.server;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

// Step (3) create the stub and skeleton objects using the rmic tool. NOT NEEDED for JAVA 6 +
// rmic AdderRemote -- used to create stub and skeleton.

// Step (4) Start the registry service by the rmiregistry tool.
// rmiregistry 5000 -- starts rmi registry service in localhost where server app is hosted.

// Step(5) Create and run the server application

/**
 * Created by hdhamee on 6/24/16.
 */
public class AdderRMIServer {

    // Now rmi services need to be hosted in a server process.
    // The Naming class provides methods to get and store the remote object.
    public static void main(String args[]) throws InterruptedException {

        //step4 // starts rmi registry in 1099 port
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        LocateRegistry.createRegistry(1099);
                    } catch (IOException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            });
            thread.start();

        // wait for
        Thread.sleep(1000);

        // step5
        Thread runnable = new Thread(new Runnable() {
           public void run() {
               try{
                   Adder stub = new AdderRemote();
                   System.out.println("Starting rmi service... ");
                   Naming.rebind("rmi://localhost:1099/calculator", stub);

               }catch(Exception e){System.out.println(e);}
           }
       });
       runnable.start();
    }
}
