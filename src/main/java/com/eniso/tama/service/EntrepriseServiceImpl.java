package com.eniso.tama.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.RoleRepository;

@Service
@ComponentScan(basePackageClasses = EnterpriseRepository.class)

public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	private EnterpriseRepository enterpriseRepository;

	@Autowired
	RoleRepository roleRepository;

	public EntrepriseServiceImpl(EnterpriseRepository theEnterpriseRepository) {
		enterpriseRepository = theEnterpriseRepository;
	}

	@Override
	public List<Entreprise> findAll() {
		return enterpriseRepository.findAll();
	}

	@Override
	public Entreprise findById(long theId) {
		Optional<Entreprise> result = enterpriseRepository.findById(theId);

		Entreprise theControl = null;

		if (result.isPresent()) {
			theControl = result.get();
		} else {
			// we didn't find the participant
			throw new RuntimeException("Did not find participant id - " + theId);
		}

		return theControl;
	}

	@Override
	public Entreprise findByEmail(String email) {
		Optional<Entreprise> result = enterpriseRepository.findByEmail(email);

		Entreprise entreprise = null;

		if (result.isPresent()) {
			entreprise = result.get();
		} else {
			// we didn't find the participant
			// throw new RuntimeException("Did not find - " + email);
		}

		return entreprise;
	}

	@Override
	public Entreprise findByPhoneNumber(String phoneNumber) {
		Optional<Entreprise> result = enterpriseRepository.findByPhoneNumber(phoneNumber);

		Entreprise entreprise = null;

		if (result.isPresent()) {
			entreprise = result.get();
		} else {
			// we didn't find the entreprise
			// throw new RuntimeException("Did not find - " + phoneNumber);
		}

		return entreprise;
	}

	@Override
	public void save(Entreprise theControl) {
		enterpriseRepository.save(theControl);
	}

	@Override

	public void deleteById(long theId) {

		enterpriseRepository.deleteById(theId);
	}

	public Entreprise getParticipant(@PathVariable long entrepriseId) {

		Entreprise theEntreprise = findById(entrepriseId);

		if (theEntreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + entrepriseId);
		}

		return theEntreprise;
	}

	public ResponseEntity<?> updateEntreprise(@RequestBody Entreprise theEntreprise) {
		Entreprise newEntreprise = findById(theEntreprise.getId());
		Entreprise verifEmailEntreprise = findByEmail(theEntreprise.getEmail());
		System.out.println(verifEmailEntreprise);
		Entreprise verifPhoneNumberEntreprise = findByPhoneNumber(theEntreprise.getPhoneNumber());

		if (((verifEmailEntreprise != null) && (verifEmailEntreprise.getId() == theEntreprise.getId()))
				|| (verifEmailEntreprise == null)) {
			if (((verifPhoneNumberEntreprise != null) && (verifPhoneNumberEntreprise.getId() == theEntreprise.getId()))
					|| (verifPhoneNumberEntreprise == null)) {
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
				newEntreprise.setProvider(theEntreprise.isProvider());
				newEntreprise.setNbMinParticipants(theEntreprise.getNbMinParticipants());
				newEntreprise.setManagerPosition(theEntreprise.getManagerPosition());
				/*Set<Role> roles = new HashSet<>();

				Role modRole = roleRepository.findByRole(Roles.ENTREPRISE)
						.orElseThrow(() -> new RuntimeException("Error: Role ENTREPRISE is not found."));
				roles.add(modRole);

				newEntreprise.setRoles(roles);*/
				save(newEntreprise);
				return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(
						"Erreur: Veuillez donner un numéro de téléphone valide. Ce numéro existe déjà"));
			}
		} else {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Erreur: Veuillez donner un email valide. Cet email existe déjà"));
		}

	}

	public List<Entreprise> getNonValid() {

		List<Entreprise> Entreprises = new ArrayList<Entreprise>();

		for (Entreprise e : findAll()) {

			if (!e.isValidated()) {

				Entreprises.add(e);

			}

		}
		return Entreprises;

	}

	public void sendmail(@RequestParam("id") long id) throws AddressException, MessagingException, IOException {

		Entreprise t = findById(id);

		t.setValidated(true);
		/*
		 * System.out.println(t.isValidated()); Properties props = new Properties();
		 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable",
		 * "true"); props.put("mail.smtp.host", "smtp.gmail.com");
		 * props.put("mail.smtp.port", "587");
		 * 
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication("noreplybaeldung@gmail.com", "0000*admin"); } });
		 * Message msg = new MimeMessage(session); msg.setFrom(new
		 * InternetAddress("noreplybaeldung@gmail.com", false));
		 * 
		 * msg.setRecipients(Message.RecipientType.TO,
		 * InternetAddress.parse(t.getEmail()));
		 * msg.setSubject("TAMA-Account Activation"); msg.
		 * setContent("Your account is successfully activated, you can log in using your settings:<br>"
		 * + "Login:" + t.getEmail() + "<br>" + "Password:" + t.getPhoneNumber() + "",
		 * "text/html"); msg.setSentDate(new Date(0));
		 * 
		 * MimeBodyPart messageBodyPart = new MimeBodyPart(); messageBodyPart.
		 * setContent("Your account is successfully activated, you can log in using your settings:<br>"
		 * + "Login:" + t.getEmail() + "<br>" + "Password:" + t.getPhoneNumber() + "",
		 * "text/html");
		 * 
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); // MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * // attachPart.attachFile("/var/tmp/image19.png"); //
		 * multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 */
		t.setValidated(true);
		save(t);
		// Transport.send(msg);
	}

	@Override
	public List<Entreprise> findEnterpriseByLocation(String location) {
		List<Entreprise> enterprises=new ArrayList<Entreprise>();
		for (Entreprise entreprise : enterpriseRepository.findAll()) {
			if(entreprise.getCity().equals(location)) {
				enterprises.add(entreprise);
			}
		}
		return enterprises;
	}
}
