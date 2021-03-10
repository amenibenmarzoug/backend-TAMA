package com.eniso.tama.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.EntrepriseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = EntrepriseService.class)
@RequestMapping(value = "/api")
public class EntrepriseController {
	private EntrepriseService entrepriseService;

	@Autowired
	public EntrepriseController(EntrepriseService entrepriseService) {
		super();
		this.entrepriseService = entrepriseService;
	}

	@GetMapping("/entreprises")
	public List<Entreprise> findAll() {
		return entrepriseService.findAll();
	}

	@GetMapping("entreprises/{entrepriseId}")
	public Entreprise getParticipant(@PathVariable long entrepriseId) {

		Entreprise theEntreprise = entrepriseService.findById(entrepriseId);

		if (theEntreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + entrepriseId);
		}

		return theEntreprise;
	}
// add mapping for POST /participants - add new control

	@PostMapping("/entreprises")
	public Entreprise addcontrol(@RequestBody Entreprise theParticipant) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// stheControl.setId(0);

		entrepriseService.save(theParticipant);
		return theParticipant;
	}

// add mapping for PUT /employees - update existing employee

	/*@PutMapping("/entreprises")

	public Entreprise updateEntreprise(@RequestBody Entreprise theEntreprise) {

		
		Entreprise newEntreprise = entrepriseService.findById(theEntreprise.getId());
		Entreprise verifEmailEntreprise =entrepriseService.findByEmail(theEntreprise.getEmail());
		Entreprise verifPhoneNumberEntreprise =entrepriseService.findByPhoneNumber(theEntreprise.getPhoneNumber());
	
			if (((verifEmailEntreprise!=null) &&(verifEmailEntreprise.getId()==theEntreprise.getId()))||(verifEmailEntreprise==null) ) {
				if(((verifPhoneNumberEntreprise!=null) &&(verifPhoneNumberEntreprise.getId()==theEntreprise.getId())) || (verifPhoneNumberEntreprise==null)) {
					newEntreprise.setEnterpriseName(theEntreprise.getEnterpriseName());
					newEntreprise.setEmail(theEntreprise.getEmail());
					newEntreprise.setWebsite(theEntreprise.getWebsite());
					newEntreprise.setCity(theEntreprise.getCity());
					newEntreprise.setStreet(theEntreprise.getStreet());
					newEntreprise.setPhoneNumber(theEntreprise.getPhoneNumber());
					newEntreprise.setPostalCode(theEntreprise.getPostalCode());
					newEntreprise.setProgramInstance(theEntreprise.getProgramInstance());
					newEntreprise.setManagerFirstName(theEntreprise.getManagerFirstName());
					newEntreprise.setManagerLastName(theEntreprise.getManagerLastName());
					entrepriseService.save(newEntreprise);
				}
				else {
					System.out.println("Phone number exists");
				}}
				else {
					System.out.println("Email exists");	
				}
			
			
		
	

		return theEntreprise;
	}*/

	@PutMapping("/entreprises")

	public  ResponseEntity<?>   updateEntreprise(@RequestBody Entreprise theEntreprise) {
		Entreprise newEntreprise = entrepriseService.findById(theEntreprise.getId());
		Entreprise verifEmailEntreprise =entrepriseService.findByEmail(theEntreprise.getEmail());
		Entreprise verifPhoneNumberEntreprise =entrepriseService.findByPhoneNumber(theEntreprise.getPhoneNumber());
	
			if (((verifEmailEntreprise!=null) &&(verifEmailEntreprise.getId()==theEntreprise.getId()))||(verifEmailEntreprise==null) ) {
				if(((verifPhoneNumberEntreprise!=null) &&(verifPhoneNumberEntreprise.getId()==theEntreprise.getId())) || (verifPhoneNumberEntreprise==null)) {
					newEntreprise.setEnterpriseName(theEntreprise.getEnterpriseName());
					newEntreprise.setEmail(theEntreprise.getEmail());
					newEntreprise.setWebsite(theEntreprise.getWebsite());
					newEntreprise.setCity(theEntreprise.getCity());
					newEntreprise.setStreet(theEntreprise.getStreet());
					newEntreprise.setPhoneNumber(theEntreprise.getPhoneNumber());
					newEntreprise.setPostalCode(theEntreprise.getPostalCode());
					newEntreprise.setProgramInstance(theEntreprise.getProgramInstance());
					newEntreprise.setManagerFirstName(theEntreprise.getManagerFirstName());
					newEntreprise.setManagerLastName(theEntreprise.getManagerLastName());
					entrepriseService.save(newEntreprise);
					return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
				}
				else {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Erreur: Veuillez donner un numéro de téléphone valide. Ce numéro existe déjà"));
				}}
				else {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Erreur: Veuillez donner un email valide. Cet email existe déjà"));
				}
			
			
		
		
	}

	
	@DeleteMapping("/entreprises/{entrepriseId}")
	public String deleteParticipant(@PathVariable int entrepriseId) {

		Entreprise tempEntreprise = entrepriseService.findById(entrepriseId);

		// throw exception if null

		if (tempEntreprise == null) {
			throw new RuntimeException("the participant id is not found - " + entrepriseId);
		}

		entrepriseService.deleteById(entrepriseId);

		return "Deleted participant id - " + entrepriseId;
	}

	@GetMapping("/NonValidEntreprise")
	public List<Entreprise> getNonValid() {

		List<Entreprise> Entreprises = new ArrayList<Entreprise>();

		for (Entreprise e : entrepriseService.findAll()) {

			// System.out.println(theP.getEntreprise()) ;
			if (!e.isValidated()) {

				Entreprises.add(e);

			}

//				if (theParticipant1 == null) {
//					throw new RuntimeException("oops");
//				}

		}
		return Entreprises;

	}

	@GetMapping("/sendMailToEntrep")
	private void sendmail(@RequestParam("id") long id) throws AddressException, MessagingException, IOException {

		Entreprise t = entrepriseService.findById(id);
		System.out.println(t.getEnterpriseName());
		System.out.println(t.isValidated());
		t.setValidated(true);
		System.out.println(t.isValidated());
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

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(t.getEmail()));
		msg.setSubject("TAMA-Account Activation");
		msg.setContent("Your account is successfully activated, you can log in using your settings:<br>" + "Login:"
				+ t.getEmail() + "<br>" + "Password:" + t.getPhoneNumber() + "", "text/html");
		msg.setSentDate(new Date(0));
		

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Your account is successfully activated, you can log in using your settings:<br>"
				+ "Login:" + t.getEmail() + "<br>" + "Password:" + t.getPhoneNumber() + "", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// MimeBodyPart attachPart = new MimeBodyPart();

		// attachPart.attachFile("/var/tmp/image19.png");
		// multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		t.setValidated(true);
		entrepriseService.save(t);
		System.out.println(t.isValidated());
		Transport.send(msg);
	}

}
