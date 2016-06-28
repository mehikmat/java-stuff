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
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/consumer")
public class ConsumerServlet extends GenericServlet implements MessageListener {
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

           // System.out.println("Get connection facory");
           // ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");

            System.out.println("Create connection");
            connection = connectionFactory.createConnection();

            System.out.println("Create session");
            Session session = connection.createSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);

            //System.out.println("Lookup queue");
            //Queue queue = (Queue) context.lookup("/queue/HelloWorldQueue");

            System.out.println("Start connection");
            connection.start();

            System.out.println("Create consumer");
            MessageConsumer consumer = session.createConsumer(queue);

            System.out.println("set message listener");
            consumer.setMessageListener(this);

            TextMessage message = (TextMessage) consumer.receive();
            servletResponse.getWriter().write( "Received: " + message.getText());


        } catch (JMSException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        } finally {
            if (connection != null) {
                System.out.println("close the connection");
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getLocalizedMessage());
                }
            }
        }

    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("message received");
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}