package com.eniso.tama.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;

@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private EntrepriseService entrepriseService;
    @Autowired
	private ProgramInstanceService programInstanceSerivice;

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
        
		// TODO Auto-generated method stub

        /*msg.setContent("An enterprise wants to participate in your program \" " 
        //t.getRegistration().get(t.getRegistration().size()-1).getPrograminstance().getProgramInstName()+ " " + t.getRegistration().get(t.getRegistration().size()-1).getPrograminstance().getLocation()
                + " \" :<br>" + "Enterprise Name :"
                + t.getEnterpriseName() + "<br>" + "Enterprise :" + t.getPhoneNumber() + "", "text/html");*/
        msg.setSentDate(new java.sql.Date(0));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
		// TODO Auto-generated method stub

        /*messageBodyPart.setContent("An enterprise wants to participate in your program:<br>" + "Enterprise Name :"
                + t.getEnterpriseName() + "<br>" + "Enterprise Phone Number :" + t.getPhoneNumber() + "", "text/html");*/

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // MimeBodyPart attachPart = new MimeBodyPart();

        // attachPart.attachFile("/var/tmp/image19.png");
        // multipart.addBodyPart(attachPart);
        msg.setContent(multipart);

        Transport.send(msg);
    }

    @Override
    public void sendParticipantValidationMail(Participant newParticipant) throws MessagingException {

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


        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newParticipant.getEmail()));
		msg.setSubject("TAMA-Account Activation");
		msg.setContent("Your account is successfully activated, you can log in using your settings:<br>" + "Login:"
				+ newParticipant.getEmail() + "<br>" + "Password:" + newParticipant.getPhoneNumber() + "", "text/html");
		msg.setSentDate(new Date(0));
		

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Your account is successfully activated, you can log in using your settings:<br>"
				+ "Login:" + newParticipant.getEmail() + "<br>" + "Password:" + newParticipant.getPhoneNumber() + "", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		
		Transport.send(msg);
    }
    @Override
    public void sendmailAlert(long id )
            throws AddressException, MessagingException, IOException {

    	ProgramInstance c = programInstanceSerivice.findById(id);

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
        msg.setContent("The number of participants did not reach the minimum number required for the program\" " + c.getProgramInstName() + " " + c.getLocation()
                 + "", "text/html");
        msg.setSentDate(new java.sql.Date(0));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("The number of participants did not reach the minimum number required for the program\" " + c.getProgramInstName() + " " + c.getLocation()
        + "", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // MimeBodyPart attachPart = new MimeBodyPart();

        // attachPart.attachFile("/var/tmp/image19.png");
        // multipart.addBodyPart(attachPart);
        msg.setContent(multipart);

        Transport.send(msg);
    }

}
