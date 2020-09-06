package com.eniso.tama.controller;

import java.util.List;
import java.util.Optional;

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

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.EntrepriseDisponibility;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.EntrepriseDisponibilityRepository;
import com.eniso.tama.service.EntrepriseDisponibilityService;
import com.eniso.tama.service.EntrepriseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = EntrepriseService.class)
@RequestMapping(value = "/api")
public class EntrepriseDisponibilityController {

	private EntrepriseDisponibilityService entrepriseDisponibilityService;
	private EntrepriseDisponibilityRepository entrepriseDisponibilityRepository;
	private EnterpriseRepository enterpriseRepository;
	private EntrepriseService entrepriseService;

	@Autowired
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

		EntrepriseDisponibility theEntreprise = entrepriseDisponibilityService.findById(entrepriseId);

		if (theEntreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + entrepriseId);
		}

		return theEntreprise;
	}
	// add mapping for POST /participants - add new control

	@PostMapping("/disponibility")
	public EntrepriseDisponibility addcontrol(@RequestBody EntrepriseDisponibility disponibility,
			@RequestParam("id") long id) {

		Entreprise entreprise = new Entreprise();
		entreprise = entrepriseService.findById(id);
		if ( disponibility== null) {
			throw new RuntimeException("hell no");
		}
		if (entreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + id);
		}

		System.out.println(entreprise.toString());

		EntrepriseDisponibility d = new EntrepriseDisponibility();
		d.setEntreprise(entreprise);

		d.setDay(disponibility.getDay());
		d.setTime(disponibility.getTime());
		// System.out.println(enterpriseRepository.findById(1L));

		return entrepriseDisponibilityService.save(d);

	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping("/disponibilities")

	public EntrepriseDisponibility updateEntreprise(@RequestBody EntrepriseDisponibility theEntreprise) {

		EntrepriseDisponibility newEntreprise = entrepriseDisponibilityService.findById(theEntreprise.getId());
		newEntreprise.setDay(theEntreprise.getDay());

		entrepriseDisponibilityService.save(newEntreprise);

		return theEntreprise;
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
