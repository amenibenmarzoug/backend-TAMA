package com.eniso.tama.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.repository.EnterpriseRepository;


@Service
@ComponentScan(basePackageClasses = EnterpriseRepository.class )

public class EntrepriseServiceImpl implements  EntrepriseService {



		private EnterpriseRepository enterpriseRepository;

		
		@Autowired
		public EntrepriseServiceImpl(EnterpriseRepository theEnterpriseRepository) {
			enterpriseRepository = theEnterpriseRepository;
		}
		
		@Override
		public List<Entreprise> findAll() {
			return enterpriseRepository.findAll();
		}

		@Override
		public Entreprise findById(long theId) {
			Optional<Entreprise> result = enterpriseRepository.findById(theId);
			
			Entreprise theControl = null;
			
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
		public Entreprise findByEmail(String email) {
			Optional<Entreprise> result = enterpriseRepository.findByEmail(email);
			
			Entreprise entreprise = null;
			
			if (result.isPresent()) {
				entreprise = result.get();
			}
			else {
				// we didn't find the participant
				throw new RuntimeException("Did not find - " + email);
			}
			
			return entreprise;
		}
		
			
		@Override
		public Entreprise findByPhoneNumber(String phoneNumber) {
			Optional<Entreprise> result = enterpriseRepository.findByPhoneNumber(phoneNumber);
			
			Entreprise entreprise = null;
			
			if (result.isPresent()) {
				entreprise = result.get();
			}
			else {
				// we didn't find the entreprise
				throw new RuntimeException("Did not find - " + phoneNumber);
			}
			
			return entreprise;
		}	
	
				
		
		@Override
		public void save(Entreprise theControl) {
			enterpriseRepository.save(theControl);
		}

	
		@Override

		public void deleteById(long    theId) {

			enterpriseRepository.deleteById(theId);
		}
}
