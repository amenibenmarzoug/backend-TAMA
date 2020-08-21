package com.eniso.tama.service;
import java.util.List;
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
		
		public ParticipantServiceImpl() {}

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

		@Override
		public void save(Participant theControl) {
			participantRepository.save(theControl);
		}

		@Override
		public void deleteById(long    theId) {
			participantRepository.deleteById(theId);
		}
}
