package com.eniso.tama.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.configuration.jwt.JwtUtils;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.entity.Status;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.User;
import com.eniso.tama.payload.JwtResponse;
import com.eniso.tama.payload.LoginRequest;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.payload.SignupRequestEnterprise;
import com.eniso.tama.payload.SignupRequestInstitution;
import com.eniso.tama.payload.SignupRequestParticipant;
import com.eniso.tama.payload.SignupRequestTrainer;
import com.eniso.tama.repository.CompanyRegistrationRepository;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.repository.ParticipantRegistrationRepository;
import com.eniso.tama.repository.ParticipantRepository;
import com.eniso.tama.repository.RoleRepository;
import com.eniso.tama.repository.TrainerRepository;
import com.eniso.tama.repository.UserRepository;
import com.eniso.tama.service.CompanyRegistrationService;
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
	CompanyRegistrationService companyRegistrationService;
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private CompanyRegistrationRepository registrationRepository;
	@Autowired
	private ParticipantRegistrationRepository regPartRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(encoder.encode(loginRequest.getPassword()));
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
				encoder.encode(signupRequestTrainer.getPassword()), signupRequestTrainer.getStreet(),
				signupRequestTrainer.getCity(), signupRequestTrainer.getPostalCode(),
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

		List<CompanyRegistration> entrepRegistration = new ArrayList<>();
		LocalDate now = LocalDate.now();
		Entreprise enterprise = new Entreprise(signupRequestEnterprise.getEmail(),
				encoder.encode(signupRequestEnterprise.getPassword()),
				// signupRequestEnterprise.getAddress(),
				signupRequestEnterprise.getStreet(), signupRequestEnterprise.getCity(),
				signupRequestEnterprise.getPostalCode(),

				signupRequestEnterprise.getPhoneNumber(), null, signupRequestEnterprise.getEnterpriseName(),
				signupRequestEnterprise.getWebsite(), signupRequestEnterprise.getManagerFirstName(),
				signupRequestEnterprise.getManagerLastName(), signupRequestEnterprise.getManagerPosition(),
				signupRequestEnterprise.getNbMinParticipants(), signupRequestEnterprise.isProvider());

		System.out.println(enterprise.isProvider());
		enterprise.setValidated(false);

		Set<String> strRoles = signupRequestEnterprise.getRole();

		Set<Role> roles = new HashSet<>();

		Role modRole = roleRepository.findByRole(Roles.ENTREPRISE)
				.orElseThrow(() -> new RuntimeException("Error: Role ENTREPRISE is not found."));
		roles.add(modRole);

		enterprise.setRoles(roles);

		enterpriseRepository.save(enterprise);

		// enterprise.setProgramInstance(signupRequestEnterprise.getProgramInstance());
	
		for (ProgramInstance prog : signupRequestEnterprise.getProgramInstance()) {

			List<ProgramInstance> list1 = companyRegistrationService.findEntrepPrograms(enterprise.getId())
					.stream().filter(x -> x.getProgramInstName().equals(prog.getProgramInstName()))
					.collect(Collectors.toList());
			//System.out.println(list1);
			if (prog != null && list1.isEmpty()) {
				CompanyRegistration registration = new CompanyRegistration();
				registration.setEntreprise(enterprise);
				registration.setPrograminstance(prog);
				registration.setRegistrationDate(now);
				entrepRegistration.add(registration);
				// enterprise.setRegistration(entrepRegistration);
				registrationRepository.save(registration);

			}
		}

		////////////////

		/*
		 * try { mailService.sendmail(enterprise.getEmail()); } catch (AddressException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (MessagingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signupParticipant")
	public ResponseEntity<?> ji(@Valid @RequestBody SignupRequestParticipant signupRequestParticipant) {

		if (participantRepository.existsByEmail(signupRequestParticipant.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account

		List<ParticipantRegistration> partRegistration = new ArrayList<>();
		LocalDate now = LocalDate.now();
		Participant participant = new Participant(signupRequestParticipant.getEmail(),
				encoder.encode(signupRequestParticipant.getPassword()), signupRequestParticipant.getStreet(),
				signupRequestParticipant.getCity(), signupRequestParticipant.getPostalCode(),
				signupRequestParticipant.getPhoneNumber(), null, signupRequestParticipant.getFirstName(),
				signupRequestParticipant.getLastName(), signupRequestParticipant.getGender(),
				signupRequestParticipant.getBirthday());
		participant.setValidated(false);
		participant.setStatus(Status.WAITING);

		participant.setEducationLevel(signupRequestParticipant.getEducationLevel());
		participant.setLevel(signupRequestParticipant.getLevel());
		participant.setCurrentPosition(signupRequestParticipant.getCurrentPosition());
		participant.setExperience(signupRequestParticipant.getExperience());
		for (ProgramInstance p : signupRequestParticipant.getProgramInstance()) {

			if (p != null) {
				ParticipantRegistration registration = new ParticipantRegistration();
				registration.setParticipant(participant);
				registration.setPrograminstance(p);
				registration.setRegistrationDate(now);
				regPartRepository.save(registration);
				partRegistration.add(registration);
				// participant.setParticipantRegistrations(partRegistration);

			}
		}

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

		participantRepository.save(participant);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
