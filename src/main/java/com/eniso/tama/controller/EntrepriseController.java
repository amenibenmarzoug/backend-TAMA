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
import com.eniso.tama.service.EntrepriseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = EntrepriseService.class )
@RequestMapping(value="/api")
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

@GetMapping("entreprises/{entreprisesId}")
public Entreprise getParticipant(@PathVariable int  entrepriseId) {
	
	Entreprise theEntreprise = entrepriseService.findById(entrepriseId);
	
	if (theEntreprise == null) {
		throw new RuntimeException("Entreprise id not found - " + entrepriseId);
	}
	
	return theEntreprise;
}
// add mapping for POST /participants - add new control

@PostMapping("/entreprises")
public  Entreprise addcontrol(@RequestBody Entreprise theParticipant) {

	
	// also just in case they pass an id in JSON ... set id to 0
	// this is to force a save of new item ... instead of update
	
	//stheControl.setId(0);
	
	entrepriseService.save(theParticipant);
	return theParticipant;
}


// add mapping for PUT /employees - update existing employee

	@PutMapping("/entreprises")
	public Entreprise updateParticipant (@RequestBody Entreprise theParticipant) {
		
		entrepriseService.save(theParticipant);
		
		return theParticipant;
	}

	@DeleteMapping("/entreprises/{entrepriseId}")
	public String deleteParticipant(@PathVariable int  entrepriseId) {
		
		Entreprise tempEntreprise = entrepriseService.findById(entrepriseId);
		
		// throw exception if null
		
		if (tempEntreprise == null) {
			throw new RuntimeException("the participant id is not found - " + entrepriseId);
		}
		
		entrepriseService.deleteById(entrepriseId);
		
		return "Deleted participant id - " + entrepriseId;
	}

}
