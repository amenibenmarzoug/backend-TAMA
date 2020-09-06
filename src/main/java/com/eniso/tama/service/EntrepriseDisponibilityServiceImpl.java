package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.EntrepriseDisponibility;
import com.eniso.tama.repository.EntrepriseDisponibilityRepository;


@Service
@ComponentScan(basePackageClasses = EntrepriseDisponibilityRepository.class )
public class EntrepriseDisponibilityServiceImpl implements EntrepriseDisponibilityService {
	

	private   EntrepriseDisponibilityRepository  entrepriseDisponibilityRepository;

	
	@Autowired
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
	
		
		

			
	
	@Override
	public  EntrepriseDisponibility save(EntrepriseDisponibility theControl) {
		return entrepriseDisponibilityRepository.save(theControl) ;
	
	}


	@Override

	public void deleteById(long    theId) {

		entrepriseDisponibilityRepository.deleteById(theId);
	}

}
