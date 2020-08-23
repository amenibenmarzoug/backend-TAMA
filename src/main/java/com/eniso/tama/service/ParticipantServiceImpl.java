package com.eniso.tama.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.repository.ParticipanRepository;


@Service
@ComponentScan(basePackageClasses = ParticipanRepository.class )

public class ParticipantServiceImpl implements  ParticipantService {

	



		private ParticipanRepository participanRepository;
		
		public ParticipantServiceImpl() {}

		@Autowired
		public ParticipantServiceImpl(ParticipanRepository theParticipantRepository) {
			participanRepository = theParticipantRepository;
		}
		
		@Override
		public List<Participant> findAll() {
			return participanRepository.findAll();
		}

		@Override
		public Participant findById(long theId) {
			Optional<Participant> result = participanRepository.findById(theId);
			
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
			//List <Participant> result = participanRepository.findByLevel(theLevel);
			
//			Optional <Participant>  theParticipant = null;
//			
//			if (result.isPresent()) {
//				theParticipant = result.get();
//			}
//			else {
//				// we didn't find the participants
//				throw new RuntimeException("Did not find participants with level - " + theLevel);
//			}
			
			return participanRepository.findByLevel(theLevel);
		}
		//find by Company
		@Override
		
		public List<Participant>  findByEntreprise (Participant theParticipant) {
			
			
			
			//List<Participant> result = participanRepository.findByEntreprise(theParticipant);
         
			List<Participant> p1= null ;
			
			
			for(Participant theP:participanRepository.findAll()) {
				
				
			if  (theP.getEntreprise()!=null) {
        	  
				p1.add(theP) ;
			
          }
            
           
//			else {
//				p2.add(theP);
//				return p2 ;
//			}
//			
			
		}
			return p1;
			
		}
		
		
		//find by abondan 
		@Override
		public 	List<Participant> findByAbonadn(boolean theAbondan ){
//			Optional<Participant> result = participanRepository.findByAbandon(theAbondan);
//			
//			Participant theParticipant = null;
//			
//			if (result.isPresent()) {
//				theParticipant = result.get();
//			}
//			else {
//				// we didn't find the participant
//				throw new RuntimeException("Did not find participant level - " );
//			}
//			
			return participanRepository.findByAbandon(theAbondan);
		}


		@Override
		public void save(Participant theControl) {
			participanRepository.save(theControl);
		}

		@Override
		public void deleteById(long    theId) {
			participanRepository.deleteById(theId);
		}
}
