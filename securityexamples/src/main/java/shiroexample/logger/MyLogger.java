package shiroexample.logger;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class MyLogger {
    Class aClass ;
    public MyLogger(Class cls) {
        this.aClass = cls;
    }
    public void info(String msg){
        System.out.println( aClass.getCanonicalName() + ": " + msg);
    }
}
