package code.mail;

import code.mail.cli.CmdOptions;
import org.apache.commons.cli.CommandLine;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Command line mail sender class using javax.mail
 * @author <a href="mailto:me.hemant.available@gmail.com">Hikmat Dhamee</a>
 * @since 0.1
 * @version 0.1
 */
public class SendMail
{
    /**
     * Send mail
     * @param args
     */
    public static void main(String [] args) {
        String from=null;
        String to=null;
        String smtp=null;

        CmdOptions options = CmdOptions.createOptions();
        CommandLine commandLine = options.parse(args);

        if (commandLine != null) {
            if (commandLine.hasOption(options.to.getOpt()) && commandLine.hasOption(options.from.getOpt())
                    && commandLine.hasOption(options.smtp.getOpt())) {
                from = commandLine.getOptionValue(options.from.getOpt());
                to = commandLine.getOptionValue(options.to.getOpt());
                smtp = commandLine.getOptionValue(options.smtp.getOpt());
                SendMail.sendMail(from,to,smtp);
            }else {
                options.printHelp();
            }
        }
    }

    /**
     * Sneds mail
     * @param from
     * @param to
     * @param smtp
     */
    public static void sendMail(String from,String to, String smtp){
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        // properties.put("mail.smtp.auth", "false");
        // properties.put("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.smtp.host", smtp);
        properties.put("mail.debug", "false");
        // properties.setProperty("mail.smtp.port", "21");
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            message.setSentDate(new Date());
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from,"Chem Jong"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to,"You"));

            // Set Subject: header field
            message.setSubject("Hey, How are your. Please contact me");

            // Now set the actual message
            message.setText(":-----)\n....:...)");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}