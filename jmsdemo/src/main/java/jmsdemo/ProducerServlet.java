package jmsdemo;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/producer")
public class ProducerServlet extends GenericServlet  {
    @Resource
    ConnectionFactory connectionFactory;

    @Resource(name = "bar")
    private Queue queue;

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Connection connection = null;
        try {
            //System.out.println("Create JNDI Context");
            //Context context = ContextUtil.getInitialContext();

            //System.out.println("Get connection facory");
            //ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");

            System.out.println("Create connection");
            connection = connectionFactory.createConnection();

            System.out.println("Create session");
            Session session = connection.createSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);

            //System.out.println("Lookup queue");
            //Queue queue = (Queue) context.lookup("/queue/HelloWorldQueue");

            System.out.println("Start connection");
            connection.start();

            System.out.println("Create producer");
            MessageProducer producer = session.createProducer(queue);

            System.out.println("Create hello world message");
            Message hellowWorldText = session.createTextMessage("Hello World!");

            System.out.println("Send hello world message");
            producer.send(hellowWorldText);
            servletResponse.getWriter().write("Message Sent: " + hellowWorldText);

        } catch (JMSException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        } finally {
            if (connection != null) {
                System.out.println("close sender connection");
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getLocalizedMessage());
                }
            }

        }
    }
}