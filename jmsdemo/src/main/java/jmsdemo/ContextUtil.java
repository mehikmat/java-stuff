package jmsdemo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ContextUtil {
    public static Context getInitialContext() throws NamingException {
        Properties props = new Properties();

        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
        props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
        props.setProperty(Context.PROVIDER_URL, "localhost:1099"); //in tomee:vm://localhost

        Context context = new InitialContext(props);

        return context;
    }
}