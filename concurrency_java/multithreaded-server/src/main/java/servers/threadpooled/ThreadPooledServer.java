package servers.threadpooled;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */

public class ThreadPooledServer implements Runnable{
    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    Socket clientSocket                 = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;

    // thread pool
    protected ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public ThreadPooledServer(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }

        openServerSocket();
        // server loop; serves a request in each iteration
        /**
         *   1. Wait for a client request
         *   2. Process client request
         *   3. Repeat from 1
         */
        while(!isStopped()){
            openClientSocket();

            if (!isStopped()){
                Runnable worker =   new WorkerRunnable(clientSocket, "Multithreaded Server");
                this.threadPool.execute(worker);
            }
            System.out.println("Current Active Thread Count: " + Thread.activeCount());
        }
        // Server loop end
        this.threadPool.shutdown();
        System.out.println("Server Stopped.");
    }



    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort + " ", e);
        }
    }

    private void openClientSocket() {
        try {
            clientSocket = this.serverSocket.accept();
        } catch (IOException e) {
            if(isStopped()) {
                System.out.println("Error: Server Stopped.") ;
                return;
            }
            throw new RuntimeException("Error accepting client connection ", e);
        }
    }
}