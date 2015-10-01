package jaxws.demo;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
@WebService
public interface ServiceInterface {

    @WebMethod
    public String sendMessage(String message);
    @WebMethod
    public String receiveMessage(String message);
}