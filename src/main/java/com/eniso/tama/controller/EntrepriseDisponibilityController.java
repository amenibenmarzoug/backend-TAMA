package com.eniso.tama.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import com.eniso.tama.entity.EntrepriseDisponibility;
import com.eniso.tama.service.EntrepriseDisponibilityService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = EntrepriseDisponibilityService.class)
@RequestMapping(value = "/api")
public class EntrepriseDisponibilityController {

	@Autowired
	private EntrepriseDisponibilityService entrepriseDisponibilityService;
	
	public EntrepriseDisponibilityController(EntrepriseDisponibilityService entrepriseDisponibilityService) {
		super();
		this.entrepriseDisponibilityService = entrepriseDisponibilityService;
	}

	@GetMapping("/disponibilities")
	public List<EntrepriseDisponibility> findAll() {
		return entrepriseDisponibilityService.findAll();
	}

	@GetMapping("disponibilities/{disponibilitiesId}")
	public EntrepriseDisponibility getParticipant(@PathVariable long entrepriseId) {

		return entrepriseDisponibilityService.getParticipant(entrepriseId);
	}
	// add mapping for POST /participants - add new control

	@PostMapping("/disponibility")
	public EntrepriseDisponibility addDisponibility(@RequestBody EntrepriseDisponibility disponibility,
			@RequestParam("id") long id) {
		return entrepriseDisponibilityService.addDisponibility(disponibility, id);

	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping("/disponibilities")

	public EntrepriseDisponibility updateEntreprise(@RequestBody EntrepriseDisponibility theEntreprise) {

		return entrepriseDisponibilityService.updateEntreprise(theEntreprise);
	}

	@DeleteMapping("/disponibilities/{disponibilitiesId}")
	public String deleteParticipant(@PathVariable int entrepriseId) {

		EntrepriseDisponibility tempEntreprise = entrepriseDisponibilityService.findById(entrepriseId);

		// throw exception if null

		if (tempEntreprise == null) {
			throw new RuntimeException("the participant id is not found - " + entrepriseId);
		}

		entrepriseDisponibilityService.deleteById(entrepriseId);

		return "Deleted participant id - " + entrepriseId;
	}

}