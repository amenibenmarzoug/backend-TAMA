package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.repository.ParticipantRegistrationRepository;
@Service
public class ParticipantRegistrationServiceImpl implements ParticipantRegistrationService {
	@Autowired
	private ParticipantRegistrationRepository participantRegistrationRepository;

	@Override
	public List<ParticipantRegistration> findAll() {

		return participantRegistrationRepository.findAll();
	}

	@Override
	public ParticipantRegistration findById(long theId) {
		Optional<ParticipantRegistration> result = participantRegistrationRepository.findById(theId);
		ParticipantRegistration registration = null;
		if (result.isPresent()) {
			registration = result.get();
		} else {
			// case the attendance is not found
			throw new RuntimeException("Did not find Registration id - " + theId);
		}
		return registration;
	}

	@Override
	public ParticipantRegistration save(ParticipantRegistration registration) {
		
		return participantRegistrationRepository.save(registration);
	}

	@Override
	public List<ParticipantRegistration> findByParticipantId(long partId) {
		
		return participantRegistrationRepository.findByParticipantId(partId);
	}

	@Override
	public List<ParticipantRegistration> findByProgramInstanceId(long partId) {
		
		return participantRegistrationRepository.findByParticipantId(partId) ;
	}

	@Override
	public void deleteById(long id) {
		
		participantRegistrationRepository.deleteById(id);
	}

}
