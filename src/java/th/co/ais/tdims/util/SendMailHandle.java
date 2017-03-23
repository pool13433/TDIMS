/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import th.co.ais.tdims.dao.ConfigDao;
/**
 *
 * @author satan
 */
public class SendMailHandle {
    Properties props = new Properties();
    Session session;
    
    public SendMailHandle (final String username, final String password) {
        ConfigDao config = new ConfigDao();
        String port = (String)(config.getConfigMap("MAIL").get("MAIL_PORT"));
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.port", port);
	props.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                            }
                    });
    }
    
    public void sendMail (String sender, String recipient, String subject, String context) {
        try {
                Message message = new MimeMessage(session);
                //message.setFrom(new InternetAddress(emailDao.getSender()));
                message.setFrom(new InternetAddress(sender));
                message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(recipient));
                message.setSubject(subject);
                message.setText(context);

                Transport.send(message);
        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}
