package xample.javathrift;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class TimeServer {
    private void start() {
        try {
            TServerSocket serverTransport = new TServerSocket(7911);
            xample.generated.TimeServer.Processor processor = new xample.generated.TimeServer.Processor(new TimeServerImpl());
            TServer server = new TSimpleServer(
                    new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting server on port 7911 ...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TimeServer srv = new TimeServer();
        srv.start();
    }
}