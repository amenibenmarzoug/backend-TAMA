package com.eniso.tama.service;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Status;
import com.eniso.tama.repository.ParticipantRepository;
import com.eniso.tama.repository.ProgramInstanceRepository;


@Service
@ComponentScan(basePackageClasses = ParticipantRepository.class )

public class ParticipantServiceImpl implements  ParticipantService {


		private ParticipantRepository participantRepository;
		private ProgramInstanceRepository programInstanceRepository ; 

		
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

		//get Confirmed Participants by ProgramInst
		@Override
		public List<Participant> findParticipantsByClass(long programInstId) {
			/*
			Optional<ProgramInstance> result = programInstanceRepository.findById(programInstId);
			
			List<Participant> participants ; 
			ProgramInstance programInst ; 
			if (result.isPresent()) {
				programInst=result.get();
			} else {
				// we didn't find the programInst
				throw new RuntimeException("Did not find class with Id  - " + programInstId);
			}
			*/
			List<Long> partcipantsIdsList = participantRepository.findConfirmedParticipantsByClass(programInstId);
			List<Participant> participants = new ArrayList<>() ; 
			for (long ParticipantID :  partcipantsIdsList ) {
				Optional<Participant> theP = participantRepository.findById(ParticipantID);
				if(theP.isPresent()){
					participants.add(theP.get());

				}
			}
			return participants ; 
			
		}

	
		@Override
		public List<Participant> getParticipantPerClass(long id) {
			List<Participant> participantsPerClasse = new ArrayList<Participant>();
			for (Participant theP : findAll()) {
				if(theP.getProgramInstance().getId() == id && theP.getStatus().equals(Status.ACCEPTED)){
					participantsPerClasse.add(theP);

				}
			}
			return participantsPerClasse;
		}
	
}