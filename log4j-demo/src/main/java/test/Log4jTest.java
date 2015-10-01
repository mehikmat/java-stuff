package test;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Log4jTest {
    /* Get actual class name to be printed on */
    static Logger logger = Logger.getLogger(Log4jTest.class.getName());

    public static void main(String[] args) {
        try {
            // Generate exception
            throw new Exception("Generating exception to test Log4j mail notification...");
        } catch (Exception ex) {
            logger.error("Test Result : ", ex);
        }
    }
}