package com.eniso.tama.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import com.eniso.tama.entity.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private EntrepriseService entrepriseService;

    // Send an email to the manager to validate the account

    @Override
    public void sendmail(@RequestParam("email") String email)
            throws AddressException, MessagingException, IOException {

        Entreprise t = entrepriseService.findByEmail(email);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("noreplybaeldung@gmail.com", "0000*admin");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("noreplybaeldung@gmail.com", false));
        //il faut changer l email par celui d'un manager!!!!!!
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("noreplybaeldung@gmail.com"));
        msg.setSubject("Program-Registration");
        msg.setContent("An enterprise wants to participate in your program \" " + t.getProgramInstance().getProgramInstName() + " " + t.getProgramInstance().getLocation()
                + " \" :<br>" + "Enterprise Name :"
                + t.getEnterpriseName() + "<br>" + "Enterprise :" + t.getPhoneNumber() + "", "text/html");
        msg.setSentDate(new java.sql.Date(0));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("An enterprise wants to participate in your program:<br>" + "Enterprise Name :"
                + t.getEnterpriseName() + "<br>" + "Enterprise Phone Number :" + t.getPhoneNumber() + "", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // MimeBodyPart attachPart = new MimeBodyPart();

        // attachPart.attachFile("/var/tmp/image19.png");
        // multipart.addBodyPart(attachPart);
        msg.setContent(multipart);

        Transport.send(msg);
    }

}
