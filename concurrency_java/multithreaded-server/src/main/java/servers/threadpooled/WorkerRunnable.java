/*
 * *
 *  * @author Hikmat Dhamee
 *  * @email me.hemant.available@gmail.com
 *
 */

package servers.threadpooled;

import java.io.*;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class WorkerRunnable implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            processClientRequest(clientSocket);
        } catch (IOException e) {
            //log exception and go on to next request.
            System.out.println("Error: " + e.getLocalizedMessage());
        }
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

    public String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
}