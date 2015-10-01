package springxmpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloBean obj = (HelloBean) context.getBean("helloWorld");
        obj.getMessage();
    }
}
