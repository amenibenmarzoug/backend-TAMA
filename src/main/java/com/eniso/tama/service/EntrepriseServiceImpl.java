package com.eniso.tama.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.dto.EntrepriseDto;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.CompanyRegistrationRepository;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.RoleRepository;

@Service
@ComponentScan(basePackageClasses = EnterpriseRepository.class)

public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	private EnterpriseRepository enterpriseRepository;

	@Autowired
	private CompanyRegistrationRepository registrationRepository;

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
		// TODO Auto-generated method stub

		/*Optional<Entreprise> result = enterpriseRepository.findByEmail(email);

		Entreprise entreprise = null;

		if (result.isPresent()) {
			entreprise = result.get();
		} else {
			// we didn't find the participant
			// throw new RuntimeException("Did not find - " + email);
		}

		return entreprise;*/
		return null;
	}

	@Override
	public Entreprise findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub

		/*Optional<Entreprise> result = enterpriseRepository.findByPhoneNumber(phoneNumber);

		Entreprise entreprise = null;

		if (result.isPresent()) {
			entreprise = result.get();
		} else {
			// we didn't find the entreprise
			// throw new RuntimeException("Did not find - " + phoneNumber);
		}

		return entreprise;*/
return null;
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

	public ResponseEntity<?> updateEntreprise(@RequestBody EntrepriseDto theEntreprise) {
		
		Entreprise newEntreprise = findById(theEntreprise.getId());
		Entreprise verifEmailEntreprise = findByEmail(theEntreprise.getEmail());
		Entreprise verifPhoneNumberEntreprise = findByPhoneNumber(theEntreprise.getPhoneNumber());
	
		
		if (((verifEmailEntreprise != null) && (verifEmailEntreprise.getId() == theEntreprise.getId()))
				|| (verifEmailEntreprise == null)) {
			if (((verifPhoneNumberEntreprise != null) && (verifPhoneNumberEntreprise.getId() == theEntreprise.getId()))
					|| (verifPhoneNumberEntreprise == null)) {
				newEntreprise.setEnterpriseName(theEntreprise.getEnterpriseName());
				// TODO Auto-generated method stub

				//newEntreprise.setEmail(theEntreprise.getEmail());
				newEntreprise.setWebsite(theEntreprise.getWebsite());
				//newEntreprise.setCity(theEntreprise.getCity());
				//newEntreprise.setStreet(theEntreprise.getStreet());
				//newEntreprise.setPhoneNumber(theEntreprise.getPhoneNumber());
				//newEntreprise.setPostalCode(theEntreprise.getPostalCode());
				newEntreprise.setManagerFirstName(theEntreprise.getManagerFirstName());
				newEntreprise.setManagerLastName(theEntreprise.getManagerLastName());
				newEntreprise.setProvider(theEntreprise.isProvider());
				newEntreprise.setNbMinParticipants(theEntreprise.getNbMinParticipants());
				newEntreprise.setManagerPosition(theEntreprise.getManagerPosition());
				
				
				//List<CompanyRegistration> companyRegistartions= new ArrayList<>() ;
				for (ProgramInstance p :  theEntreprise.getClasses()) {
					CompanyRegistration registration = new CompanyRegistration();
					registration.setEntreprise(newEntreprise);
					registration.setPrograminstance(p);
					registration.setRegistrationDate(LocalDate.now());
					registrationRepository.save(registration);
					//companyRegistartions.add(registration);
				}
				//newEntreprise.setRegistration(companyRegistartions);
//				registration.setEntreprise(theEntreprise);
//				registration.setPrograminstance(theEntreprise.getRegistration().getPrograminstance());
				/*
				 * Set<Role> roles = new HashSet<>();
				 * 
				 * Role modRole = roleRepository.findByRole(Roles.ENTREPRISE) .orElseThrow(() ->
				 * new RuntimeException("Error: Role ENTREPRISE is not found."));
				 * roles.add(modRole);
				 * 
				 * newEntreprise.setRoles(roles);
				 */
				save(newEntreprise);
			//	registrationRepository.save(registration) ;
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
			// TODO Auto-generated method stub

			/*if (!e.isValidated()) {

				Entreprises.add(e);

			}*/

		}
		return Entreprises;

	}

	public void sendmail(@RequestParam("id") long id) throws AddressException, MessagingException, IOException {

		Entreprise t = findById(id);
		// TODO Auto-generated method stub

		//t.setValidated(true);
		
		
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
		
		// TODO Auto-generated method stub

		//t.setValidated(true);
		save(t);
		// Transport.send(msg);
	}

	@Override
	public List<Entreprise> findEnterpriseByLocation(String location) {
		List<Entreprise> enterprises = new ArrayList<Entreprise>();
		for (Entreprise entreprise : enterpriseRepository.findAll()) {
			
			// TODO Auto-generated method stub

			/*if (entreprise.getCity().equals(location)) {
				enterprises.add(entreprise);
			}*/
		}
		return enterprises;
	}
}
