package com.eniso.tama.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Days;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.User;
import com.eniso.tama.service.TrainerService;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
@ComponentScan(basePackageClasses = TrainerService.class )
@RequestMapping("/api")
public class TrainerController {
	
	
	
	private TrainerService trainerService;
	
	@Autowired
	public TrainerController(TrainerService theTrainerService) {
		trainerService = theTrainerService;
	}
	
	@GetMapping("/trainers")
    public List<Trainer> getAllTrainers() {
        return trainerService.findAll();
    }
	
	@GetMapping("trainers/{trainerId}")
	public Trainer getTrainer(@PathVariable int  trainerId) {
		
		Trainer theTrainer = trainerService.findById(trainerId);
		
		if (theTrainer == null) {
			throw new RuntimeException("Trainer id not found - " + trainerId);
		}
		
		return theTrainer;
	}
	
	@GetMapping("trainerDisponi/{trainerId}")
	public Set<Days> getTrainerDisponibilities(@PathVariable int  trainerId) {
		
		Trainer theTrainer = trainerService.findById(trainerId);
		
		if (theTrainer == null) {
			throw new RuntimeException("Trainer id not found - " + trainerId);
		}
		
		return theTrainer.getDisponibilityDays();
	}
	// add mapping for POST /participants - add new control

	@PostMapping("/trainers")
	public  Trainer addTrainer(@RequestBody Trainer theTrainer) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		trainerService.save(theTrainer);
		return theTrainer;
	}
	
	
	// add mapping for PUT /employees - update existing employee
	
		@PutMapping("/trainers")
		public Trainer updateTrainer(@RequestBody Trainer theTrainer) {
			
			Trainer newTrainer = trainerService.findById(theTrainer.getId());
			newTrainer.setEmail(theTrainer.getEmail());
			newTrainer.setCity(theTrainer.getCity());
			newTrainer.setStreet(theTrainer.getStreet());
			newTrainer.setPhoneNumber(theTrainer.getPhoneNumber());
			newTrainer.setPostalCode(theTrainer.getPostalCode());
			newTrainer.setFirstName(theTrainer.getFirstName());
			newTrainer.setLastName(theTrainer.getLastName());
			newTrainer.setSpecifications(theTrainer.getSpecifications());
			newTrainer.setDisponibilityDays(theTrainer.getDisponibilityDays());
			System.out.println(theTrainer.getDisponibilityDays());
			System.out.println(newTrainer.getDisponibilityDays());
			trainerService.save(newTrainer);
			return theTrainer;
		}

		@DeleteMapping("/trainers/{trainerId}")
		public String deleteTrainer(@PathVariable int  trainerId) {
			
			Trainer tempTrainer = trainerService.findById(trainerId);
			
			// throw exception if null
			
			if (tempTrainer == null) {
				throw new RuntimeException("the trainer id is not found - " + trainerId);
			}
			
			trainerService.deleteById(trainerId);
			
			return "Deleted trainer id - " + trainerId;
		}
		@GetMapping( "/sendMailToTrainer" )
		private void sendmail(@RequestParam("id") long id  ) throws AddressException, MessagingException, IOException {
			
			Trainer t = trainerService.findById(id ) ;
			   Properties props = new Properties();
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true");
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.port", "587");
			   
			   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication("noreplybaeldung@gmail.com", "Admin*0000");
			      }
			   });
			   Message msg = new MimeMessage(session);
			   msg.setFrom(new InternetAddress("noreplybaeldung@gmail.com", false));

			   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(t.getEmail()));
			   msg.setSubject("Account Activation");
			   msg.setContent("Your account is successfully activated, you can log in using your settings:\n" + 
			   		"Login:"+ t.getEmail() + "\n"+
			   		"Password:"+t.getPhoneNumber() +"", "text/html");
			   msg.setSentDate(new Date(0));

			   MimeBodyPart messageBodyPart = new MimeBodyPart();
			   messageBodyPart.setContent("Your account is successfully activated, you can log in using your settings:\n" + 
				   		"Login:"+ t.getEmail() + "\n"+
				   		"Password:"+t.getPhoneNumber() +"", "text/html");

			   Multipart multipart = new MimeMultipart();
			   multipart.addBodyPart(messageBodyPart);
			  // MimeBodyPart attachPart = new MimeBodyPart();

			  // attachPart.attachFile("/var/tmp/image19.png");
			   //multipart.addBodyPart(attachPart);
			   msg.setContent(multipart);
			   Transport.send(msg);   
			}
	
	

}
