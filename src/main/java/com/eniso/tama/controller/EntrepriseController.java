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
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.service.EntrepriseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = EntrepriseService.class)
@RequestMapping(value = "/api")
public class EntrepriseController {
	private EntrepriseService entrepriseService;

	@Autowired
	public EntrepriseController(EntrepriseService entrepriseService) {
		super();
		this.entrepriseService = entrepriseService;
	}

	@GetMapping("/entreprises")
	public List<Entreprise> findAll() {
		return entrepriseService.findAll();
	}

	@GetMapping("entreprises/{entrepriseId}")
	public Entreprise getParticipant(@PathVariable long entrepriseId) {

		Entreprise theEntreprise = entrepriseService.findById(entrepriseId);

		if (theEntreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + entrepriseId);
		}

		return theEntreprise;
	}
// add mapping for POST /participants - add new control

	@PostMapping("/entreprises")
	public Entreprise addcontrol(@RequestBody Entreprise theParticipant) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// stheControl.setId(0);

		entrepriseService.save(theParticipant);
		return theParticipant;
	}

// add mapping for PUT /employees - update existing employee

	@PutMapping("/entreprises")

	public Entreprise updateEntreprise(@RequestBody Entreprise theEntreprise) {

		
		Entreprise newEntreprise = entrepriseService.findById(theEntreprise.getId());
		newEntreprise.setEnterpriseName(theEntreprise.getEnterpriseName());
		newEntreprise.setEmail(theEntreprise.getEmail());
		newEntreprise.setWebsite(theEntreprise.getWebsite());
		newEntreprise.setCity(theEntreprise.getCity());
		newEntreprise.setStreet(theEntreprise.getStreet());
		newEntreprise.setPhoneNumber(theEntreprise.getPhoneNumber());
		newEntreprise.setPostalCode(theEntreprise.getPostalCode());
		newEntreprise.setProgramInstance(theEntreprise.getProgramInstance());
		newEntreprise.setManagerFirstName(theEntreprise.getManagerFirstName());
		newEntreprise.setManagerLastName(theEntreprise.getManagerLastName());
		entrepriseService.save(newEntreprise);

		return theEntreprise;
	}

	/*@PutMapping("/entreprises")

	public  ResponseEntity<?>   updateEntreprise(@RequestBody Entreprise theEntreprise) {
		if (enterpriseRepository.existsByEmail(theEntreprise.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Veuillez donner un email valide. Cet email existe déjà"));
		}
		if (enterpriseRepository.existsByPhoneNumber(theEntreprise.getPhoneNumber())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Veuillez donner un numéro de téléphone valide. Ce numéro existe déjà"));
		}
		
		Entreprise newEntreprise = entrepriseService.findById(theEntreprise.getId());
		newEntreprise.setEnterpriseName(theEntreprise.getEnterpriseName());
		newEntreprise.setEmail(theEntreprise.getEmail());
		newEntreprise.setWebsite(theEntreprise.getWebsite());
		newEntreprise.setCity(theEntreprise.getCity());
		newEntreprise.setStreet(theEntreprise.getStreet());
		newEntreprise.setPhoneNumber(theEntreprise.getPhoneNumber());
		newEntreprise.setPostalCode(theEntreprise.getPostalCode());
		newEntreprise.setProgramInstance(theEntreprise.getProgramInstance());
		newEntreprise.setManagerFirstName(theEntreprise.getManagerFirstName());
		newEntreprise.setManagerLastName(theEntreprise.getManagerLastName());
		entrepriseService.save(newEntreprise);

		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}*/

	
	@DeleteMapping("/entreprises/{entrepriseId}")
	public String deleteParticipant(@PathVariable int entrepriseId) {

		Entreprise tempEntreprise = entrepriseService.findById(entrepriseId);

		// throw exception if null

		if (tempEntreprise == null) {
			throw new RuntimeException("the participant id is not found - " + entrepriseId);
		}

		entrepriseService.deleteById(entrepriseId);

		return "Deleted participant id - " + entrepriseId;
	}

}
