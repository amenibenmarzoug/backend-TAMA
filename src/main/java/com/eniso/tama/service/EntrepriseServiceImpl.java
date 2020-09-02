package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Group;
import com.eniso.tama.repository.EnterpriseRepository;


@Service
@ComponentScan(basePackageClasses = EnterpriseRepository.class )
public class EntrepriseServiceImpl implements EntrepriseService {
	
	
    private EnterpriseRepository entrepriseRepository;

    public EntrepriseServiceImpl() {};
	public EntrepriseServiceImpl(EnterpriseRepository entrepriseRepository) {
		super();
		this.entrepriseRepository = entrepriseRepository;
	}

	@Override
	public List<Entreprise> findAll() {
		return entrepriseRepository.findAll();
		}

	@Override
	public Entreprise findById(long theId) {
Optional<Entreprise> result = entrepriseRepository.findById(theId);
		
		Entreprise theControl = null;
		
		if (result.isPresent()) {
			theControl = result.get();
		}
		else {
			// we didn't find the participant
			throw new RuntimeException("Did not find Entreprise id - " + theId);
		}
		
		return theControl;
	}

	@Override
	public void save(Entreprise theEntreprise) {
		entrepriseRepository.save(theEntreprise);		
	}

	@Override
	public void deleteById(long theId) {
		entrepriseRepository.deleteById(theId);		
	}

}
