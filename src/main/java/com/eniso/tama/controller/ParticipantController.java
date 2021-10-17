package com.eniso.tama.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.entity.Status;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.ParticipantRegistrationRepository;
import com.eniso.tama.repository.ParticipantRepository;
import com.eniso.tama.repository.RoleRepository;
import com.eniso.tama.service.MailService;
import com.eniso.tama.service.ParticipantService;
import com.eniso.tama.service.ProgramInstanceService;

@CrossOrigin(origins = "*")
@RestController
@ComponentScan(basePackageClasses = ParticipantService.class)
@RequestMapping(value = "/api")
public class ParticipantController {


	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ParticipantRepository participantRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	EnterpriseRepository enterpriseRepository;

	@Autowired
	ProgramInstanceService classeService;
	
	@Autowired
	private ParticipantService participantService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ParticipantRegistrationRepository participantRegistrationRepository;

	
	public ParticipantController(ParticipantService theParticipantService) {
		participantService = theParticipantService;
	}
	// public ControlController () {}

	@GetMapping("/participants")
	public List<Participant> findAll() {
//		for (Participant par : participantService.findAll()) {
//			//System.out.println("AGE"+par.getAge());
//		}
		return participantService.findAll();
	}

	@GetMapping("participants/{participantId}")
	public Participant getParticipant(@PathVariable long participantId) {

		Participant theParticipant = participantService.findById(participantId);
		
		if (theParticipant == null) {
			throw new RuntimeException("Participant id not found - " + participantId);
		}

		return theParticipant;
	}

	
	
	
	
	
	// get participants by level

	@GetMapping("participants/level/{participantLevel}")
	public List<Participant> getParticipant(@PathVariable String participantLevel) {

		List<Participant> theParticipant = participantService.findByLevel(participantLevel);

		if (theParticipant == null) {
			throw new RuntimeException("Participant id not found - " + participantLevel);
		}

		return theParticipant;
	}

	// les participants du pilier1
	@GetMapping("participants/pilier1")
	public List<Participant> getParticipantPilier1() {

		List<Participant> theParticipant1 = new ArrayList<Participant>();

		for (Participant theP : participantService.findAll()) {

			if (theP.getEntreprise() != null) {

				theParticipant1.add(theP);
			}

		}
		return theParticipant1;

	}

	// les participants du pilier2
	@GetMapping("participants/pilier2")
	public List<Participant> getParticipantPilier2() {

		List<Participant> theParticipant1 = new ArrayList<Participant>();

		for (Participant theP : participantService.findAll()) {

			if (theP.getEntreprise() == null) {

				theParticipant1.add(theP);

			}



		}
		return theParticipant1;

	}


		//POST FOR ADDING PARTICIPANTS IN CRUD , IT REQUERS THE ID OF THE ENTERPRISE
		
		
		@PostMapping("/participants")
		public  Participant addParticipant(@RequestBody Participant theParticipant) {
		
			
			participantService.save(theParticipant);
			return theParticipant;
		}
		

	// get participants by abandon
	@GetMapping("participants/isAbandon/{abandon}")
	public List<Participant> getParticipantByAbandon(@PathVariable boolean abandon) {

		List<Participant> theParticipant = participantService.findByAbonadn(abandon);

		if (theParticipant == null) {
			throw new RuntimeException("Theres no abandon");
		}

		return theParticipant;
	}

	//this api generates an error :)
//	@GetMapping("participants/class/{id}")
//	public List<Participant> getParticipantPerClass(@PathVariable("id") long id) {
//		List<Participant> participantsPerClasse = new ArrayList<Participant>();
//		for (Participant theP : findAll()) {
//			if(theP.getParticipantRegistrations().getProgramInstance().getId() == id && theP.getStatus().equals(Status.ACCEPTED)){
//				participantsPerClasse.add(theP);
//
//			}
//		}
//		return participantsPerClasse;
//	}
	//Get Participants (status-Validated) By Class  (same as the method above : to be corrected) 
	@GetMapping("participants/classId/{id}")
	public List<Participant> getParticipantsPerClass(@PathVariable("id") long classId) {
		List<Participant> participantsPerClasse = participantService.findParticipantsByClass(classId);

		if (participantsPerClasse == null) {
			throw new RuntimeException("No participants found in the class with id  " + classId);
		}
		return participantsPerClasse;
	}
	

	@GetMapping("participants/entreprise")
	public List<Participant> getParticipantEntreprise(@RequestParam("id") long id) {

		List<Participant> participantsPerEntr = new ArrayList<Participant>();

		for (Participant theP : getParticipantPilier1()) {

			if (id == theP.getEntreprise().getId()) {

				participantsPerEntr.add(theP);
				
			}
		}

		return participantsPerEntr;
	}
	
	
	

	// POST FOR ADDING PARTICIPANTS IN CRUD , IT REQUERS THE ID OF THE ENTERPRISE

	@PostMapping("/signupParticipantManag")
	public ResponseEntity<?> registerParticipantParManager(@Valid @RequestBody Participant theP) {
		 System.out.println("participant" + theP) ;

		if (participantRepository.existsByEmail(theP.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
	
		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByRole(Roles.PARTICIPANT)
				.orElseThrow(() -> new RuntimeException("Error: Role PARTICIPANT is not found."));
		roles.add(modRole);

		Participant p = new Participant();
		p.setEmail(theP.getEmail());
		p.setPassword(encoder.encode(theP.getPassword()));
		p.setStreet(theP.getStreet());
		p.setCity(theP.getCity());
		p.setPostalCode(theP.getPostalCode());
		p.setPhoneNumber(theP.getPhoneNumber());
		p.setRoles(roles);
		p.setFirstNameP(theP.getFirstNameP());
		p.setLastNameP(theP.getLastNameP());
		p.setGender(theP.getGender());
		p.setBirthday(theP.getBirthday());
		p.setEntreprise(theP.getEntreprise());
		p.setEducationLevel(theP.getEducationLevel());
		p.setLevel(theP.getLevel());
		p.setCurrentPosition(theP.getCurrentPosition());
		p.setExperience(theP.getExperience());
		//p.setProgramInstance(theP.getProgramInstance());
		p.setStatus(Status.WAITING);

		participantRepository.save(p);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/signupParticipantEntre")
	public ResponseEntity<?> registerParticipantPerEntr(@Valid @RequestBody Participant theP,
			@RequestParam("id") long id ) {

		if (participantRepository.existsByEmail(theP.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		Entreprise entreprise = new Entreprise();
		for (Entreprise e : enterpriseRepository.findAll()) {
			if (id == e.getId()) {
				entreprise = e;
			}
		}

		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByRole(Roles.PARTICIPANT)
				.orElseThrow(() -> new RuntimeException("Error: Role PARTICIPANT is not found."));
		roles.add(modRole);

		Participant p = new Participant();
		p.setEmail(theP.getEmail());
		p.setPassword(encoder.encode(theP.getPassword()));
		p.setStreet(theP.getStreet());
		p.setCity(theP.getCity());
		p.setPostalCode(theP.getPostalCode());
		p.setPhoneNumber(theP.getPhoneNumber());
		p.setRoles(roles);
		p.setFirstNameP(theP.getFirstNameP());
		p.setLastNameP(theP.getLastNameP());
		p.setGender(theP.getGender());
		p.setBirthday(theP.getBirthday());
		p.setEducationLevel(theP.getEducationLevel());
		p.setLevel(theP.getLevel());
		p.setCurrentPosition(theP.getCurrentPosition());
		p.setEntreprise(entreprise);
		p.setExperience(theP.getExperience());
		//p.setProgramInstance(entreprise.getRegistration().getPrograminstance());
		p.setStatus(Status.WAITING);
		p.setValidated(false);
		participantRepository.save(p);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	// add mapping for PUT /employees - update existing employee

	@PutMapping("/participants")
	public Participant updateParticipant(@RequestBody Participant theParticipant) {
		Participant newParticipant = participantService.findById(theParticipant.getId());
		newParticipant.setFirstNameP(theParticipant.getFirstNameP());
		newParticipant.setLastNameP(theParticipant.getLastNameP());
		newParticipant.setBirthday(theParticipant.getBirthday());
		newParticipant.setCity(theParticipant.getCity());
		newParticipant.setCity(theParticipant.getStreet());
		newParticipant.setCity(theParticipant.getPostalCode());
		newParticipant.setGender(theParticipant.getGender());
		newParticipant.setCurrentPosition(theParticipant.getCurrentPosition());
		newParticipant.setEducationLevel(theParticipant.getEducationLevel());
		newParticipant.setEmail(theParticipant.getEmail());
		newParticipant.setLevel(theParticipant.getLevel());
		newParticipant.setEntreprise(theParticipant.getEntreprise());
		newParticipant.setParticipantRegistrations(theParticipant.getParticipantRegistrations());
		participantService.save(newParticipant);

		return theParticipant;
	}
	
	@PutMapping("/classeParticipant/{id}")
	public Participant updateClasse(@RequestBody Participant theParticipant,@PathVariable long id) {
		Participant newParticipant = participantService.findById(theParticipant.getId());
		ParticipantRegistration registration = new ParticipantRegistration();
		registration.setParticipant(newParticipant);
		registration.setPrograminstance(classeService.findById(id));
		registration.setRegistrationDate(LocalDate.now());
		participantRegistrationRepository.save(registration) ;
		newParticipant.setStatus(Status.ACCEPTED);
		theParticipant.getParticipantRegistrations().add(registration);
		newParticipant.setParticipantRegistrations(theParticipant.getParticipantRegistrations()); //À revoir		
		participantService.save(newParticipant);

		return theParticipant;
	}

	@PutMapping("/updatePartEntr")
	public Participant updateParticipantEntr(@RequestBody Participant theParticipant) {

		Participant newParticipant = participantService.findById(theParticipant.getId());
		newParticipant.setFirstNameP(theParticipant.getFirstNameP());
		newParticipant.setLastNameP(theParticipant.getLastNameP());
		newParticipant.setBirthday(theParticipant.getBirthday());
		newParticipant.setCity(theParticipant.getStreet());
		newParticipant.setCity(theParticipant.getPostalCode());
		newParticipant.setCity(theParticipant.getCity());
		newParticipant.setGender(theParticipant.getGender());
		newParticipant.setCurrentPosition(theParticipant.getCurrentPosition());
		newParticipant.setEducationLevel(theParticipant.getEducationLevel());
		newParticipant.setEmail(theParticipant.getEmail());
		newParticipant.setLevel(theParticipant.getLevel());
		participantService.save(newParticipant);

		return theParticipant;
	}
			
			
	
	@PutMapping("/participants/validate")
	public Participant ValidateParticipant(@RequestBody Participant theParticipant) throws AddressException, MessagingException {
		Participant newParticipant = participantService.findById(theParticipant.getId());
		newParticipant.setValidated(true);
		participantService.save(newParticipant);
		//mailService.sendParticipantValidationMail(newParticipant);

		//participantService.save(newParticipant);


		return theParticipant;
	}

	@DeleteMapping("/participants/{participantId}")
	public String deleteParticipant(@PathVariable long participantId) {

		Participant tempParticipant = participantService.findById(participantId);

		// throw exception if null

		if (tempParticipant == null) {
			throw new RuntimeException("the participant id is not found - " + participantId);
		}

		participantService.deleteById(participantId);

		return "Deleted participant id - " + participantId;
	}

}


