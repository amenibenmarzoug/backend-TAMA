package com.eniso.tama.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.helpers.RandomPasswordGenerator;
import com.eniso.tama.service.InstitutionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = InstitutionService.class)
@RequestMapping(value = "/api")
public class InstitutionController {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
    RandomPasswordGenerator randomPassword;
	
	@Autowired
	private InstitutionService institutionService;
	
	
	public InstitutionController(InstitutionService theInstitutionService) {
		institutionService = theInstitutionService;
	}
	
	@GetMapping("/institutions")
	public List<Institution> findAll() {
		return institutionService.findAll();
	}
	
	@GetMapping("institutions/{institutionId}")
	public Institution getInstitution(@PathVariable long institutionId) {

		return institutionService.getInstitution(institutionId);
	}
	
	@PostMapping("/signupInstituionManag")
	public ResponseEntity<?> registerInstitutionParManager(@Valid @RequestBody Institution theI) {
		theI.setPassword(encoder.encode(randomPassword.generateSecureRandomPassword()));
		return institutionService.registerInstitutionParManager(theI);
	}
	@PutMapping("/institution")
	public Institution updateParticipant(@RequestBody Institution theInstitution) {
		return institutionService.updateParticipant(theInstitution);
	}
	@DeleteMapping("/institution/{id}")
	public String deleteInstitution(@PathVariable long id) {

		return institutionService.deleteInstitution(id);
}
}