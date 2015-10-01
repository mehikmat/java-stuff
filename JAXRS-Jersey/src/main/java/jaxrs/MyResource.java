package jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 *
 * Root resource (exposed at "myresource" path)
 *
 * A JAX-RS resource is an annotated POJO that provides so-called
 * resource methods that are able to handle HTTP requests for
 * URI paths that the resource is bound to.
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}