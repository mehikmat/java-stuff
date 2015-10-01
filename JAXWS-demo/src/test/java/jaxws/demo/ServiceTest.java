package jaxws.demo;

import javax.xml.ws.Endpoint;

public class ServiceTest {
    public static void main(String[] args) {
        ServiceInterfaceImpl server = new ServiceInterfaceImpl();
        Endpoint endpoint = Endpoint.publish(
                "http://localhost:9191/message", server);
        System.out.println("Service published:>>>>> "+endpoint.toString());
    }
}
