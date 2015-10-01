package jaas;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

/**
 * Attempt to authenticate the user.
 *
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class HelloWorld {

    public static void main(String[] args) {
        // use the configured LoginModules for the "helloWorld" entry
        LoginContext lc = null;
        try {
            lc = new LoginContext("helloWorld", new MyCallbackHandler());
        } catch (LoginException le) {
            le.printStackTrace();
            System.exit(-1);
        }

        // the user has 3 attempts to authenticate successfully
        int i;
        for (i = 0; i < 3; i++) {
            try {

                // attempt authentication
                lc.login();

                // if we return with no exception, authentication succeeded
                break;

             /* catch (FailedLoginException fle) {

                System.out.println("Authentication Failed");
                try {
                    Thread.currentThread().sleep(3000);
                } catch (Exception e) {
                    // ignore
                }*/

            } catch (Exception e) {

                System.out.println("Unexpected Exception - unable to continue");
                e.printStackTrace();
                System.exit(-1);
            }
        }

        // did they fail three times?
        if (i == 3) {
            System.out.println("Sorry");
            System.exit(-1);
        }

        // Look at what Principals we have:
        Iterator principalIterator = lc.getSubject().getPrincipals().iterator();
        System.out.println("\n\nAuthenticated user has the following Principals:");
        while (principalIterator.hasNext()) {
            Principal p = (Principal)principalIterator.next();
            System.out.println("\t" + p.toString());
        }

        // Look at some Principal-based work:
        Subject.doAsPrivileged(lc.getSubject(), new PrivilegedAction() {
            public Object run() {
                System.out.println("\nYour java.home property: "
                        + System.getProperty("java.home"));

                System.out.println("\nYour user.home property: "
                        + System.getProperty("user.home"));

                File f = new File("foo.txt");
                System.out.print("\nfoo.txt does ");
                if (!f.exists()) System.out.print("not ");
                System.out.println("exist in your current directory");

                System.out.println("\nOh, by the way ...");

                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    // ignore
                }
                System.out.println("\n\nHello World!\n");
                return null;
            }
        }, null);
        System.exit(0);
    }
}


