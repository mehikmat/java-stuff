package jarsign;

import shiroexample.logger.MyLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.availble@gmail.com
 */
public class JarSignTest {
    public static final transient MyLogger log = new MyLogger(JarSignTest.class);

    public static void countChars(InputStream in) throws IOException {
        int count = 0;

        while (in.read() != -1)
            count++;

        System.out.println("Counted " + count + " chars.");
    }

    public static void main(String[] args) throws Exception{

        // set security manager
        System.setSecurityManager(new SecurityManager());
        SecurityManager sm = System.getSecurityManager();
        if (sm == null){
            log.info("No Security Manager Available!!!");
        }
        // end setting security manager
        if (args.length >= 1) {
            countChars(new FileInputStream(args[0]));
        }
        else {
            System.err.println("Usage: JarSignTest filename");
        }

    }
}
