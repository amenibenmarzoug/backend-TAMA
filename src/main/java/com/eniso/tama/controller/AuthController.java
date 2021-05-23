package com.eniso.tama.controller;

import java.io.IOException;
import javax.mail.PasswordAuthentication;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.configuration.jwt.JwtUtils;
import com.eniso.tama.entity.Day;
import com.eniso.tama.entity.Days;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;

import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.User;
import com.eniso.tama.payload.JwtResponse;
import com.eniso.tama.payload.LoginRequest;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.payload.SignupRequestEnterprise;
import com.eniso.tama.payload.SignupRequestInstitution;
import com.eniso.tama.payload.SignupRequestParticipant;
import com.eniso.tama.payload.SignupRequestTrainer;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.repository.ParticipantRepository;
import com.eniso.tama.repository.RoleRepository;
import com.eniso.tama.repository.TrainerRepository;
import com.eniso.tama.repository.UserRepository;
import com.eniso.tama.service.EntrepriseService;
import com.eniso.tama.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	TrainerRepository trainerRepository;

	@Autowired
	InstitutionRepository institutionRepository;

	@Autowired
	ParticipantRepository participantRepository;

	@Autowired
	EnterpriseRepository enterpriseRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getPassword());
		System.out.println(loginRequest.getEmail());

		User u = new User();
		u = userRepository.findByEmail(loginRequest.getEmail());

		// System.out.println(u.toString());
		if (u == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Cet email n'existe pas!"));
		} else {
			if (u.isValidated()) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
				// System.out.println("USER") ;
				// System.out.println(userDetails.getEmail()) ;
				// System.out.println(userDetails.getPassword()) ;
				SecurityContextHolder.getContext().setAuthentication(authentication);

				String jwt = jwtUtils.generateJwtToken(authentication);

				List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
						.collect(Collectors.toList());

				return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
						userDetails.getEmail(), roles));
			} else {

				return ResponseEntity.badRequest().body(new MessageResponse("Votre compte n'est pas encore activé !"));
			}
		}

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestTrainer signupRequestTrainer) {

		if (trainerRepository.existsByEmail(signupRequestTrainer.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Role roleTrainer = new Role();

		Trainer trainer = new Trainer(signupRequestTrainer.getEmail(),
				encoder.encode(signupRequestTrainer.getPassword()),
				// signupRequestTrainer.getAddress(),
				signupRequestTrainer.getStreet(), signupRequestTrainer.getCity(), signupRequestTrainer.getPostalCode(),

				signupRequestTrainer.getPhoneNumber(), null, signupRequestTrainer.getFirstName(),
				signupRequestTrainer.getLastName(), signupRequestTrainer.getGender(),
				signupRequestTrainer.getDisponibilityDays(), signupRequestTrainer.getSpecifications());
		trainer.setValidated(false);

//		User user = new User(signupRequestTrainer.getEmail(),
//				 encoder.encode(signupRequestTrainer.getPassword()),
//				 //signupRequestTrainer.getAddress(),
//				 signupRequestTrainer.getStreet(),
//				 signupRequestTrainer.getCity(),
//				 signupRequestTrainer.getPostalCode(),
//				 signupRequestTrainer.getPhoneNumber(),null);

		// User user=trainer;
		Set<String> strRoles = signupRequestTrainer.getRole();
		// Set<String> strDisponibilities=signupRequestTrainer.getDisponibilityDays();
		Set<Role> roles = new HashSet<>();

		/*
		 * Set<Day> disponibilities= new HashSet<>(); for (String day :
		 * strDisponibilities) { Day d=new Day();
		 * 
		 * switch (day) { case "LUNDI": d.setDay(Days.LUNDI); break;
		 * 
		 * case "MARDI": d.setDay(Days.MARDI); break; case "MERCREDI":
		 * d.setDay(Days.MERCREDI); break; case "JEUDI": d.setDay(Days.JEUDI); break;
		 * case "VENDREDI": d.setDay(Days.VENDREDI); break; case "SAMEDI":
		 * d.setDay(Days.SAMEDI); break; case "DIMANCHE": d.setDay(Days.DIMANCHE);
		 * break; } disponibilities.add(d); System.out.println(Days.valueOf(day)); }
		 */
		Role modRole = roleRepository.findByRole(Roles.TRAINER)
				.orElseThrow(() -> new RuntimeException("Error: Role Trainer is not found."));
		roles.add(modRole);
		// trainer.setDisponibilityDays(disponibilities);
		trainer.setRoles(roles);
		// User.setRoles(roles);
		// userRepository.save(user);
		trainerRepository.save(trainer);

		System.out.println(trainer.getPhoneNumber());

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signupInstitution")
	public ResponseEntity<?> registerInstitution(
			@Valid @RequestBody SignupRequestInstitution signupRequestInstitution) {

		if (institutionRepository.existsByEmail(signupRequestInstitution.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account

		Institution institution = new Institution(signupRequestInstitution.getEmail(),
				encoder.encode(signupRequestInstitution.getPassword()),
				// signupRequestInstitution.getAddress(),
				signupRequestInstitution.getStreet(), signupRequestInstitution.getCity(),
				signupRequestInstitution.getPostalCode(), signupRequestInstitution.getPhoneNumber(), null,
				signupRequestInstitution.getInstitutionName());
		institution.setValidated(false);
		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByRole(Roles.INSTITUTION)
				.orElseThrow(() -> new RuntimeException("Error: Role INSTITUTION is not found."));
		roles.add(modRole);

		institution.setRoles(roles);

		// userRepository.save(user);
		institutionRepository.save(institution);

		// System.out.println(institution.getPhoneNumber()) ;

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@Transactional 
	@PostMapping("/signupEnterprise")
	public ResponseEntity<?> registerEnterprise(@Valid @RequestBody SignupRequestEnterprise signupRequestEnterprise) {

		if (enterpriseRepository.existsByEmail(signupRequestEnterprise.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Erreur: Veuillez donner un email valide. Cet email existe déjà"));
		}
		if (enterpriseRepository.existsByPhoneNumber(signupRequestEnterprise.getPhoneNumber())) {
			return ResponseEntity.badRequest().body(new MessageResponse(
					"Erreur: Veuillez donner un numéro de téléphone valide. Ce numéro existe déjà"));
		}

		// Create new user's account

		Entreprise enterprise = new Entreprise(signupRequestEnterprise.getEmail(),
				encoder.encode(signupRequestEnterprise.getPassword()),
				// signupRequestEnterprise.getAddress(),
				signupRequestEnterprise.getStreet(), signupRequestEnterprise.getCity(),
				signupRequestEnterprise.getPostalCode(),

				signupRequestEnterprise.getPhoneNumber(), null, signupRequestEnterprise.getEnterpriseName(),
				signupRequestEnterprise.getWebsite(), signupRequestEnterprise.getManagerFirstName(),
				signupRequestEnterprise.getManagerLastName(),signupRequestEnterprise.getManagerPosition(),signupRequestEnterprise.getNbMinParticipants());

		enterprise.setProgramInstance(signupRequestEnterprise.getProgramInstance());

		enterprise.setValidated(false);

//		User user = new User(signupRequestEnterprise.getEmail(),
//				 encoder.encode(signupRequestEnterprise.getPassword()),
//				 signupRequestEnterprise.getStreet(),
//				 signupRequestEnterprise.getCity(),
//				 signupRequestEnterprise.getPostalCode(),
//				 signupRequestEnterprise.getPhoneNumber(),null);
//		
//		
		Set<String> strRoles = signupRequestEnterprise.getRole();

		Set<Role> roles = new HashSet<>();

		Role modRole = roleRepository.findByRole(Roles.ENTREPRISE)
				.orElseThrow(() -> new RuntimeException("Error: Role ENTREPRISE is not found."));
		roles.add(modRole);

		enterprise.setRoles(roles);

		// userRepository.save(user);
		enterpriseRepository.save(enterprise);
		try {
			sendmail(enterprise.getEmail());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	// Send an email to the manager to validate the account
	@GetMapping("/sendMailToManager")
	private void sendmail(@RequestParam("email") String email)
			throws AddressException, MessagingException, IOException {

		Entreprise t = entrepriseService.findByEmail(email);
		//System.out.println(t.getEnterpriseName());
		//System.out.println(t.isValidated());
		// t.setValidated(true);
		//System.out.println(t.isValidated());
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
		msg.setContent("An enterprise wants to participate in your program \" "+ t.getProgramInstance().getProgramInstName()+ " "+ t.getProgramInstance().getLocation()
				+ " \" :<br>" + "Enterprise Name :"
				+ t.getEnterpriseName() + "<br>" + "Enterprise :" + t.getPhoneNumber() + "", "text/html");
		msg.setSentDate(new Date(0));

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("An enterprise wants to participate in your program:<br>" + "Enterprise Name :"
				+ t.getEnterpriseName() + "<br>" + "Enterprise Phone Number :" + t.getPhoneNumber() + "", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// MimeBodyPart attachPart = new MimeBodyPart();

		// attachPart.attachFile("/var/tmp/image19.png");
		// multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		// t.setValidated(true);
		//entrepriseService.save(t);
		// System.out.println(t.isValidated()) ;
		Transport.send(msg);
	}

	@PostMapping("/signupParticipant")
	public ResponseEntity<?> ji(@Valid @RequestBody SignupRequestParticipant signupRequestParticipant) {

		if (participantRepository.existsByEmail(signupRequestParticipant.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account

		Participant participant = new Participant(signupRequestParticipant.getEmail(),
				encoder.encode(signupRequestParticipant.getPassword()),
				// signupRequestParticipant.getAddress(),
				signupRequestParticipant.getStreet(),

				signupRequestParticipant.getCity(), signupRequestParticipant.getPostalCode(),

				signupRequestParticipant.getPhoneNumber(), null, signupRequestParticipant.getFirstName(),
				signupRequestParticipant.getLastName(), signupRequestParticipant.getGender(),
				signupRequestParticipant.getBirthday());
		participant.setValidated(false);

//		User user = new User(signupRequestParticipant.getEmail(),
//				 encoder.encode(signupRequestParticipant.getPassword()),
//				 signupRequestParticipant.getStreet(),
//				 signupRequestParticipant.getCity(),
//				 signupRequestParticipant.getPostalCode(),
//				 signupRequestParticipant.getPhoneNumber(),null);

		Set<String> strRoles = signupRequestParticipant.getRole();

		Set<Role> roles = new HashSet<>();

		Role modRole = roleRepository.findByRole(Roles.PARTICIPANT)
				.orElseThrow(() -> new RuntimeException("Error: Role PARTICIPANT is not found."));
		roles.add(modRole);

		participant.setRoles(roles);

		// userRepository.save(user);
		participantRepository.save(participant);

		// System.out.println(institution.getPhoneNumber()) ;

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
