package com.market.servicemarket.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMailUtil {

    @Autowired
    ConfigurationUtil configurationUtil;

    public  static void sendMailToAdmin(String recipient, StringWriter errors) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");

        /************** Please put email and password ************************************/
        /************** Your email security should be configured as: (1) Allow less securing app to access turned on (2) Turn off 2FA ************************************/

        String myAccountEmail = ""; //service market email
        String password="";    //service market email password

        String[] admin_emails = recipient.split(",");

        Session session = Session.getInstance(prop, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message= prepareMessage(session,myAccountEmail,admin_emails,errors);

        Transport.send(message);
        System.out.println("Message send Successfully");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String[] recipient, StringWriter errors) {
    try {
        for(int i = 0 ; i<recipient.length; i++) {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient[i]));
            message.setSubject("Service Market Exception Log");
            message.setText(String.valueOf(errors));
            return message;
        }
    }catch (Exception ex){
        Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,ex);
    }
        return  null;
    }

}
