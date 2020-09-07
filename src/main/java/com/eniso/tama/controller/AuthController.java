package com.eniso.tama.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.configuration.jwt.JwtUtils;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.entity.Trainer;
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
import com.eniso.tama.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository ;
	
	@Autowired
	TrainerRepository trainerRepository ;

	@Autowired
	InstitutionRepository institutionRepository ;
	
	@Autowired
	ParticipantRepository participantRepository ;
	
	@Autowired
	EnterpriseRepository enterpriseRepository ;
	
	@Autowired
	PasswordEncoder encoder ;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getPassword()) ;
		System.out.println(loginRequest.getEmail()) ;
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		//System.out.println("USER") ;
	//	System.out.println(userDetails.getEmail()) ;
		//System.out.println(userDetails.getPassword()) ;
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 	
												 roles));
	}

	@PostMapping( "/signup" )
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestTrainer signupRequestTrainer){
		
		

		if (trainerRepository.existsByEmail(signupRequestTrainer.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
		Role roleTrainer=new Role();
		
		Trainer trainer = new Trainer(
				             signupRequestTrainer.getEmail(),
							 encoder.encode(signupRequestTrainer.getPassword()),
							 //signupRequestTrainer.getAddress(),
							 signupRequestTrainer.getStreet(),
							 signupRequestTrainer.getCity(),
							 signupRequestTrainer.getPostalCode(),
							 
							 signupRequestTrainer.getPhoneNumber(),
							 null,
							 signupRequestTrainer.getFirstName(),
							 signupRequestTrainer.getLastName(),
							 signupRequestTrainer.getSpecification(),
							 signupRequestTrainer.getGender());

		
//		User user = new User(signupRequestTrainer.getEmail(),
//				 encoder.encode(signupRequestTrainer.getPassword()),
//				 //signupRequestTrainer.getAddress(),
//				 signupRequestTrainer.getStreet(),
//				 signupRequestTrainer.getCity(),
//				 signupRequestTrainer.getPostalCode(),
//				 signupRequestTrainer.getPhoneNumber(),null);
		
		//User user=trainer;
		Set<String> strRoles = signupRequestTrainer.getRole();
		
		Set<Role> roles = new HashSet<>();

		
		Role modRole = roleRepository.findByRole(Roles.TRAINER)
				.orElseThrow(() -> new RuntimeException("Error: Role Trainer is not found."));
		roles.add(modRole);
		
		trainer.setRoles(roles) ;
		//User.setRoles(roles);
		//userRepository.save(user);
		trainerRepository.save(trainer);
		
		
		System.out.println(trainer.getPhoneNumber()) ;
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping( "/signupInstitution" )
	public ResponseEntity<?> registerInstitution(@Valid @RequestBody SignupRequestInstitution signupRequestInstitution){
		
		

		if (institutionRepository.existsByEmail(signupRequestInstitution.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
	
		Institution institution = new Institution(
				signupRequestInstitution.getEmail(),
							 encoder.encode(signupRequestInstitution.getPassword()),
							 //signupRequestInstitution.getAddress(),
							 signupRequestInstitution.getStreet(),
							 signupRequestInstitution.getPostalCode(),
							 signupRequestInstitution.getCity(),
							 
							 signupRequestInstitution.getPhoneNumber(),
							null,
							 signupRequestInstitution.getInstitutionName());
		
//		User user = new User(signupRequestInstitution.getEmail(),
//				 encoder.encode(signupRequestInstitution.getPassword()),
//				 //signupRequestInstitution.getAddress(),
//				 signupRequestInstitution.getStreet(),
//				 signupRequestInstitution.getCity(),
//				 signupRequestInstitution.getPostalCode(),
//				 signupRequestInstitution.getPhoneNumber(),null);
//		
	//	Set<String> strRoles = signupRequestTrainer.getRole();
		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByRole(Roles.INSTITUTION)
				.orElseThrow(() -> new RuntimeException("Error: Role INSTITUTION is not found."));
		roles.add(modRole);

		institution.setRoles(roles) ;
		
		//userRepository.save(user);
		institutionRepository.save(institution);
		
		
		//System.out.println(institution.getPhoneNumber()) ;
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping( "/signupEnterprise" )
	public ResponseEntity<?> registerEnterprise(@Valid @RequestBody SignupRequestEnterprise signupRequestEnterprise){
		
		

		if (enterpriseRepository.existsByEmail(signupRequestEnterprise.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
	
		Entreprise enterprise = new Entreprise(
				signupRequestEnterprise.getEmail(),
							 encoder.encode(signupRequestEnterprise.getPassword()),
							 //signupRequestEnterprise.getAddress(),
							 signupRequestEnterprise.getStreet(),
							 signupRequestEnterprise.getCity(),
							 signupRequestEnterprise.getPostalCode(),
							
							 
							 signupRequestEnterprise.getPhoneNumber(),
							 null,
							 signupRequestEnterprise.getEnterpriseName(),
							 signupRequestEnterprise.getWebsite());
		
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

		enterprise.setRoles(roles) ;
		
		//userRepository.save(user);
		enterpriseRepository.save(enterprise);
		
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping( "/signupParticipant" )
	public ResponseEntity<?> registerParticipant(@Valid @RequestBody SignupRequestParticipant signupRequestParticipant)
	{
		
		

		if (participantRepository.existsByEmail(signupRequestParticipant.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
	
		Participant participant = new Participant(
				signupRequestParticipant.getEmail(),
							 encoder.encode(signupRequestParticipant.getPassword()),
							 //signupRequestParticipant.getAddress(),
							 signupRequestParticipant.getStreet(),
							 signupRequestParticipant.getPostalCode(),
							 signupRequestParticipant.getCity(),
							 
							 signupRequestParticipant.getPhoneNumber(),
							null,
							 signupRequestParticipant.getFirstName(),
							 signupRequestParticipant.getLastName(),
							 signupRequestParticipant.getGender(),
							 signupRequestParticipant.getBirthday());
		
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

		participant.setRoles(roles) ;
		
		//userRepository.save(user);
		participantRepository.save(participant);
		
		
		//System.out.println(institution.getPhoneNumber()) ;
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	
}
