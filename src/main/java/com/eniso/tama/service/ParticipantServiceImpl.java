package com.eniso.tama.service;
import java.util.Date;
import java.time.LocalDate; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.repository.ParticipantRepository;


@Service
@ComponentScan(basePackageClasses = ParticipantRepository.class )

public class ParticipantServiceImpl implements  ParticipantService {


		private ParticipantRepository participantRepository;

		
		@Autowired
		public ParticipantServiceImpl(ParticipantRepository theParticipantRepository) {
			participantRepository = theParticipantRepository;
		}
		
		@Override
		public List<Participant> findAll() {
			return participantRepository.findAll();
		}

		@Override
		public Participant findById(long theId) {
			Optional<Participant> result = participantRepository.findById(theId);
			
			Participant theControl = null;
			
			if (result.isPresent()) {
				theControl = result.get();
			}
			else {
				// we didn't find the participant
				throw new RuntimeException("Did not find participant id - " + theId);
			}
			
			return theControl;
		}

		//find By level 
		@Override
		public List<Participant> findByLevel(String theLevel) {
		
			
			return participantRepository.findByLevel(theLevel);

		}
		
		//find by Company
		@Override
		
		public List<Participant>  findByEntreprise (Participant theParticipant) {
         
			List<Participant> p1= null ;
			
			

			for(Participant theP:participantRepository.findAll()) {
				
				
			if  (theP.getEntreprise()!=null) {
        	  
				p1.add(theP) ;
			
          }
		            	
		}
			return p1;
			
		}

		//find by abondan 
		@Override
		public 	List<Participant> findByAbonadn(boolean theAbondan ){
					
			return participantRepository.findByAbandon(theAbondan);
		}

		@Override
		public void save(Participant theParticipant) {
			participantRepository.save(theParticipant);
		}

	
		@Override
		public void deleteById(long theId) {


			participantRepository.deleteById(theId);
		}

	
	
}