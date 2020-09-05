package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.payload.SignupRequestPartEntr;
import com.eniso.tama.payload.SignupRequestParticipant;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.ParticipantRepository;
import com.eniso.tama.repository.RoleRepository;
import com.eniso.tama.service.ParticipantService;

@CrossOrigin(origins = "http://localhost:4200")
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

	private ParticipantService participantService;

	@Autowired
	public ParticipantController(ParticipantService theParticipantService) {
		participantService = theParticipantService;
	}
	// public ControlController () {}

	@GetMapping("/participants")
	public List<Participant> findAll() {
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

		// List <Participant> theParticipant=
		// participantService.findByEntreprise(participant);
		List<Participant> theParticipant1 = new ArrayList<Participant>();

		for (Participant theP : participantService.findAll()) {

			System.out.println(theP.getEntreprise());
			if (theP.getEntreprise() != null) {

				theParticipant1.add(theP);
			}
//
//				if (theParticipant1 == null) {
//					throw new RuntimeException("oops");
//				}
//				
		}
		return theParticipant1;

	}

	// les participants du pilier2
	@GetMapping("participants/pilier2")
	public List<Participant> getParticipantPilier2() {

		// List <Participant> theParticipant=
		// participantService.findByEntreprise(participant);
		List<Participant> theParticipant1 = new ArrayList<Participant>();

		for (Participant theP : participantService.findAll()) {

			// System.out.println(theP.getEntreprise()) ;
			if (theP.getEntreprise() == null) {

				theParticipant1.add(theP);

			}

			if (theParticipant1 == null) {
				throw new RuntimeException("oops");
			}

		}
		return theParticipant1;
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
		// System.out.println("participant") ;

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
		// System.out.println(p.toString()) ;
		participantRepository.save(p);


//			
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	@PostMapping("/signupParticipantEntre")
	public ResponseEntity<?> registerParticipantPerEntr(@Valid @RequestBody Participant theP,
			@RequestParam("id") long id) {
		// System.out.println("participant") ;

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
		p.setEntreprise(entreprise);
		// System.out.println(p.toString()) ;
		participantRepository.save(p);


//			
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
		newParticipant.setGender(theParticipant.getGender());
		newParticipant.setCurrentPosition(theParticipant.getCurrentPosition());
		newParticipant.setEducationLevel(theParticipant.getEducationLevel());
		newParticipant.setEmail(theParticipant.getEmail());
		newParticipant.setLevel(theParticipant.getLevel());
		newParticipant.setEntreprise(theParticipant.getEntreprise());
		participantService.save(newParticipant);

		return theParticipant;
	}
	@PutMapping("/updatePartEntr")
	public Participant updateParticipantEntr(@RequestBody Participant theParticipant) {

		Participant newParticipant = participantService.findById(theParticipant.getId());
		newParticipant.setFirstNameP(theParticipant.getFirstNameP());
		newParticipant.setLastNameP(theParticipant.getLastNameP());
		newParticipant.setBirthday(theParticipant.getBirthday());
		newParticipant.setCity(theParticipant.getCity());
		newParticipant.setGender(theParticipant.getGender());
		newParticipant.setCurrentPosition(theParticipant.getCurrentPosition());
		newParticipant.setEducationLevel(theParticipant.getEducationLevel());
		newParticipant.setEmail(theParticipant.getEmail());
		newParticipant.setLevel(theParticipant.getLevel());
		//newParticipant.setEntreprise(theParticipant.getEntreprise());
		participantService.save(newParticipant);

		return theParticipant;
	}

	@PutMapping("/participants/validate")
	public Participant ValidateParticipant(@RequestBody Participant theParticipant) {
		Participant newParticipant = participantService.findById(theParticipant.getId());
		newParticipant.setValidated(true);
		// newParticipant.setValidated(theParticipant.isValidated());

		participantService.save(newParticipant);

		if (theParticipant == null) {
			throw new RuntimeException("oops");
		}

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
	//les participants du groupe
	
	@GetMapping("participants/group")
	public List <Participant> getGroupParticipant(@RequestParam("id") long  id) {
	 
		//List <Participant> theParticipant= participantService.findByEntreprise(participant);
		 List<Participant> groupParticipants= new ArrayList<Participant>();
		
		
		for(Participant theP:participantService.findAll()) {
			
			//System.out.println(theP.getEntreprise()) ;
			 if  (theP.getGroup()!=null) {
   	  
				if(id == theP.getGroup().getId()) {
					System.out.println(id) ;
					groupParticipants.add(theP);
					System.out.println(groupParticipants.isEmpty()) ;
				}
				else {
					System.out.println(id) ;

				}
				}
		
		}
		return groupParticipants;
	}
	
	@DeleteMapping("group/participants/{participantId}")
	public String deleteParticipantFromGroup(@PathVariable long  participantId) {

		
		Participant tempParticipant = participantService.findById(participantId);
		System.out.println("participant id" + tempParticipant.getId()) ;
		tempParticipant.setGroup(null);
	
		participantService.save(tempParticipant);
		return "Deleted participant id - " + participantId;
	}
	@GetMapping("participants/group/{id}")
	public List<Participant> findByGroup(@PathVariable long id) {
		return participantService.findByGroup(id);
	}
	
	
}


