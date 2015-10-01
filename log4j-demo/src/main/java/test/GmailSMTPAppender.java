package test;

import com.sun.mail.smtp.SMTPTransport;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * Extension of Log4j {@link SMTPAppender} for Gmail support
 * Copyright (c) 2010 tgerm.com
 * All rights reserved.
 *
 * @author abhinav@tgerm.com
 */
public class GmailSMTPAppender extends SMTPAppender {
    /**
     * Cached session for later use i.e. while sending emails
     */
    protected Session session;

    public GmailSMTPAppender() {
        super();
    }

    /**
     * Create mail session.
     *
     * @return mail session, may not be null.
     */
    protected Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtps.host", getSMTPHost());
        props.put("mail.smtps.auth", "true");
        props.put("mail.debug","true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class

        Authenticator auth = null;
        if (getSMTPPassword() != null && getSMTPUsername() != null) {
            auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(getSMTPUsername(),
                            getSMTPPassword());
                }
            };
        }
        session = Session.getInstance(props, auth);
        if (getSMTPProtocol() != null) {
            session.setProtocolForAddress("rfc822", getSMTPProtocol());
        }
        if (getSMTPDebug()) {
            session.setDebug(getSMTPDebug());
        }
        return session;
    }

    /**
     * Send the contents of the cyclic buffer as an e-mail message.
     */
    protected void sendBuffer() {
        try {
            MimeBodyPart part = new MimeBodyPart();

            StringBuffer sbuf = new StringBuffer();
            String t = layout.getHeader();
            if (t != null)
                sbuf.append(t);
            int len = cb.length();
            for (int i = 0; i < len; i++) {
                LoggingEvent event = cb.get();
                sbuf.append(layout.format(event));
                if (layout.ignoresThrowable()) {
                    String[] s = event.getThrowableStrRep();
                    if (s != null) {
                        for (int j = 0; j < s.length; j++) {
                            sbuf.append(s[j]);
                            sbuf.append(Layout.LINE_SEP);
                        }
                    }
                }
            }
            t = layout.getFooter();
            if (t != null)
                sbuf.append(t);
            part.setContent(sbuf.toString(), layout.getContentType());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(part);
            msg.setContent(mp);

            msg.setSentDate(new Date());
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            send(msg);
        } catch (Exception e) {
            LogLog.error("Error occured while sending e-mail notification.", e);
        }
    }

    /**
     * Pulled email send stuff i.e. Transport.send()/Transport.sendMessage(). So
     * that on required this logic can be enhanced.
     *
     * @param msg Email Message
     * @throws MessagingException
     */
    protected void send(Message msg) throws MessagingException {
        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        try {
            t.connect(getSMTPHost(), getSMTPUsername(), getSMTPPassword());
            t.sendMessage(msg, msg.getAllRecipients());
        } finally {
            System.out.println("Response: " + t.getLastServerResponse());
            t.close();
        }
    }
}
