package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.EntrepriseDisponibility;
import com.eniso.tama.repository.EntrepriseDisponibilityRepository;


@Service
@ComponentScan(basePackageClasses = EntrepriseDisponibilityRepository.class )
public class EntrepriseDisponibilityServiceImpl implements EntrepriseDisponibilityService {
	
	@Autowired
	private   EntrepriseDisponibilityRepository  entrepriseDisponibilityRepository;

	@Autowired
	private EntrepriseService entrepriseService;

	
	public EntrepriseDisponibilityServiceImpl(EntrepriseDisponibilityRepository theEnterpriseRepository) {
		entrepriseDisponibilityRepository = theEnterpriseRepository;
	}
	
	@Override
	public List<EntrepriseDisponibility> findAll() {
		return entrepriseDisponibilityRepository.findAll();
	}

	@Override
	public EntrepriseDisponibility findById(long theId) {
		Optional<EntrepriseDisponibility> result = entrepriseDisponibilityRepository.findById(theId);
		
		EntrepriseDisponibility theControl = null;
		
		if (result.isPresent()) {
			theControl = result.get();
		}
		else {
			// we didn't find the participant
			throw new RuntimeException("Did not find participant id - " + theId);
		}
		
		return theControl;
	}
	
		
		
	public EntrepriseDisponibility getParticipant(@PathVariable long entrepriseId) {

		EntrepriseDisponibility theEntreprise = findById(entrepriseId);

		if (theEntreprise == null) {
			throw new RuntimeException("Entreprise id not found - " + entrepriseId);
		}

		return theEntreprise;
	}
		
	public EntrepriseDisponibility addDisponibility(@RequestBody EntrepriseDisponibility disponibility,
			@RequestParam("id") long id) {

		Entreprise entreprise = new Entreprise();
		System.out.println("1");
		entreprise = entrepriseService.findById(id);
		System.out.println("2");

		if (disponibility == null) {
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

		return save(d);

	}
	
	@Override
	public  EntrepriseDisponibility save(EntrepriseDisponibility theControl) {
		return entrepriseDisponibilityRepository.save(theControl) ;
	
	}

	public EntrepriseDisponibility updateEntreprise(@RequestBody EntrepriseDisponibility theEntreprise) {

		EntrepriseDisponibility newEntreprise = findById(theEntreprise.getId());
		newEntreprise.setDay(theEntreprise.getDay());

		save(newEntreprise);

		return theEntreprise;
	}

	@Override

	public void deleteById(long    theId) {

		entrepriseDisponibilityRepository.deleteById(theId);
	}

}
