package jaxws.demo;

import javax.jws.WebService;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
@WebService(endpointInterface = "jaxws.demo.ServiceInterface")
public class ServiceInterfaceImpl implements ServiceInterface {
    @Override
    public String sendMessage(String message) {
        return "Message Sent:>> "+message;
    }

    @Override
    public String receiveMessage(String message) {
        return "Message Received:>> "+message;
    }
}
