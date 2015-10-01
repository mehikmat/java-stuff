package org.hacker;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    public static void send(final String senderEmailAddress, final String senderEmailPassword,
                            String receiverEmailAddress, String messageTitle) {
        send(senderEmailAddress,senderEmailPassword,receiverEmailAddress,messageTitle,null);
    }

    public static void send(final String senderEmailAddress, final String senderEmailPassword,
                            String receiverEmailAddress) {
        send(senderEmailAddress,senderEmailPassword,receiverEmailAddress,null,null);
    }

    public static void send(final String senderEmailAddress, final String senderEmailPassword,
                            String receiverEmailAddress, String messageTitle, String messageBody) {
        // SMTP host
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmailAddress, senderEmailPassword);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(senderEmailAddress));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmailAddress));

            // Set Subject: header field
            message.setSubject(messageTitle);

            // Now set the actual message
            message.setText(messageBody);

            // Send message
            Transport.send(message);

            System.out.println("Message sent successfully....");

        } catch (MessagingException ex) {
            System.out.println("Mail not sent due to "+ ex.getLocalizedMessage());
        }catch (Exception ex){
            System.out.println("Mail not sent due to "+ ex.getLocalizedMessage());
        }
    }
}