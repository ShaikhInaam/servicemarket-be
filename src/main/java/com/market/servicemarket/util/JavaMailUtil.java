package com.market.servicemarket.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JavaMailUtil {


    @Autowired
    ConfigurationUtil configurationUtil;

    public  void sendMailToAdmin(StringWriter errors) throws MessagingException {
        String recipient = configurationUtil.getMessage(Constants.ADMIN_EMAILS);
        Properties prop = new Properties();
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");

        String myAccountEmail = configurationUtil.getMessage(Constants.EMAIL_ACCOUNT_USERNAME_CODE); //service market email
        String password= EncryptDecryptUtil.decrypt(configurationUtil.getMessage(Constants.EMAIL_ACCOUNT_PASSWORD_CODE));    //service market email password

        String[] admin_emails;

        if(recipient.contains(",")){
            admin_emails = recipient.split(",");
        }else{
            admin_emails = new String[1];
            admin_emails[0] = recipient;
        }



        Session session = Session.getInstance(prop, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message= prepareMessage(session,myAccountEmail,admin_emails,errors);

        Transport.send(message);


    }

    private Message prepareMessage(Session session, String myAccountEmail, String[] recipient, StringWriter errors) {
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
