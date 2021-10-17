package com.eniso.tama.controller;

import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.service.InstitutionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = InstitutionService.class)
@RequestMapping(value = "/api")
public class InstitutionController {
	
	@Autowired
	InstitutionRepository institutionRepository;

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