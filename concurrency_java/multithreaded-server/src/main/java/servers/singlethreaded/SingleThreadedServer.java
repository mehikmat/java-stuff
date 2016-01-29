package servers.singlethreaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class SingleThreadedServer implements Runnable{
    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    Socket clientSocket                 = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;

    public SingleThreadedServer(int port){
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
            try {
                if (!isStopped()){
                    processClientRequest(clientSocket);
                }
            } catch (IOException e) {
                //log exception and go on to next request.
                System.out.println("Error: " + e.getLocalizedMessage());
            }
        }
        // Server loop end
        System.out.println("Server Stopped.");
    }

    private void processClientRequest(Socket clientSocket) throws IOException {
        InputStream  input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        StringBuilder userReq = new StringBuilder();
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(input));
        String headerLine = stdIn.readLine();
        StringTokenizer tokenizer = new StringTokenizer(headerLine);
        String httpMethod = tokenizer.nextToken();
        String httpQueryString = tokenizer.nextToken();

        while (stdIn.ready()){
            userReq.append(stdIn.readLine());
        }

        if (httpMethod.equals("GET")) {
            if (httpQueryString.equals("/date")) {
                output.write(("HTTP/1.1 200 OK\n\n<html><body>" +
                        "SingleThreaded Server: \n" +
                        "<p><b> Today: " + convertTime(time) + "</b></p>" +
                        "</body></html>").getBytes());
            }else {
                output.write(("<p>URL: " + httpQueryString + " but need to be /date</p>").getBytes());
                output.write(("Header: " + userReq.toString()).getBytes());
            }
        }else {
            output.write("This server supports only the GET method!!!".getBytes());
        }

        output.close();
        input.close();
        System.out.println("Request processed: " + time);
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

    public String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
}