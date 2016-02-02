

package servers.singlethreaded;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        SingleThreadedServer server = new SingleThreadedServer(9000);
        Thread  mainThread = new Thread(server);
        mainThread.start();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping Server");
        server.stop();
    }
}
