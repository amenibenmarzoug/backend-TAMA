package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.repository.RoleRepository;
import com.eniso.tama.service.InstitutionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = InstitutionService.class)
@RequestMapping(value = "/api")
public class InstitutionController {
	@Autowired
	InstitutionRepository institutionRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	
	private InstitutionService institutionService;
	
	@Autowired
	public InstitutionController(InstitutionService theInstitutionService) {
		institutionService = theInstitutionService;
	}
	
	@GetMapping("/institutions")
	public List<Institution> findAll() {
		return institutionService.findAll();
	}
	
	@GetMapping("institutions/{institutionId}")
	public Institution getInstitution(@PathVariable long institutionId) {

		Institution theInstitution = institutionService.findById(institutionId);

		if (theInstitution == null) {
			throw new RuntimeException("Participant id not found - " + institutionId);
		}

		return theInstitution;
	}
	
	@PostMapping("/signupInstituionManag")
	public ResponseEntity<?> registerInstitutionParManager(@Valid @RequestBody Institution theI) {

		if (institutionRepository.existsByEmail(theI.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
	
		Set<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByRole(Roles.INSTITUTION)
				.orElseThrow(() -> new RuntimeException("Error: Role Institution is not found."));
		roles.add(modRole);

		Institution inst = new Institution();
		inst.setEmail(theI.getEmail());
		inst.setPassword(encoder.encode(theI.getPassword()));
		inst.setStreet(theI.getStreet());
		inst.setCity(theI.getCity());
		inst.setPostalCode(theI.getPostalCode());
		inst.setPhoneNumber(theI.getPhoneNumber());
		inst.setRoles(roles);
		inst.setInstitutionName(theI.getInstitutionName());

		institutionRepository.save(inst);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	@PutMapping("/institution")
	public Institution updateParticipant(@RequestBody Institution theInstitution) {
		Institution newInstitution = institutionService.findById(theInstitution.getId());
		
		newInstitution.setCity(theInstitution.getCity());
		newInstitution.setStreet(theInstitution.getStreet());
		newInstitution.setPostalCode(theInstitution.getPostalCode());
		newInstitution.setPhoneNumber(theInstitution.getPhoneNumber());
		newInstitution.setInstitutionName(theInstitution.getInstitutionName());	
		institutionService.save(newInstitution);

		return theInstitution;
	}
	@DeleteMapping("/institution/{id}")
	public String deleteInstitution(@PathVariable long id) {

		Institution inst= institutionService.findById(id);

		// throw exception if null

		if (inst == null) {
			throw new RuntimeException("the institution id is not found - " + id);
		}

		institutionService.deleteById(id);

		return "Deleted institution id - " + id;
}
}