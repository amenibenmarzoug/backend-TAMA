package com.eniso.tama.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

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

		@Override
		public void save(Participant theControl) {
			participanRepository.save(theControl);
		}

		@Override
		public void deleteById(long    theId) {
			participanRepository.deleteById(theId);
		}
}
