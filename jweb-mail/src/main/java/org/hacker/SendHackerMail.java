package org.hacker;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Servlet to send mail anonymously
 * User: Hikmat Dhamee
 */
public class SendHackerMail extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sender's email ID needs to be mentioned
        String from = request.getParameter("from");
        // Recipient's email ID needs to be mentioned.
        String to = request.getParameter("to");
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        SendMail.send("email_address","password",to,title,message);
        response.getWriter().write("Message sent successfully!!! Have fun!!!");

    }

    public void destroy() {
        // do nothing.
    }


}
