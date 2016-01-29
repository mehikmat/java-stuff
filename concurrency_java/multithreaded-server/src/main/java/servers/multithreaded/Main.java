package servers.multithreaded;

import servers.singlethreaded.SingleThreadedServer;

/**
 * Created by hdhamee on 1/29/16.
 */
public class Main {
    public static void main(String[] args) {
        MultiThreadedServer server = new MultiThreadedServer(9000);
        Thread  mainThread = new Thread(server);
        mainThread.start();

        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping Server");
        server.stop();
    }
}
