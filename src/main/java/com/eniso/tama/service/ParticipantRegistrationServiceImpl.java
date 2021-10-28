package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Status;
import com.eniso.tama.repository.ParticipantRegistrationRepository;
import com.eniso.tama.repository.ProgramInstanceRepository;
import com.eniso.tama.repository.SessionRepository;

@Service
public class ParticipantRegistrationServiceImpl implements ParticipantRegistrationService {
	@Autowired
	private ParticipantRegistrationRepository participantRegistrationRepository;

	@Autowired
	private ProgramInstanceRepository programInstanceRepository;

	@Autowired
	private SessionService sessionService;
	
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

		return participantRegistrationRepository.findByProgramInstanceId(partId);
	}

	@Override
	public void deleteById(long id) {

		participantRegistrationRepository.deleteById(id);
	}

	@Override
	public List<ProgramInstance> findParticipantPrograms(long participantId) {
		List<ProgramInstance> programs = new ArrayList<>();
		for (ParticipantRegistration registration : participantRegistrationRepository
				.findByParticipantId(participantId)) {
			programs.add(registration.getPrograminstance());
		}
		return programs;

	}

	@Override
	public ParticipantRegistration validateRegistration(long registrationId) {
		ParticipantRegistration registration = findById(registrationId);
		if (registration != null) {
			registration.setStatus(Status.ACCEPTED);
			participantRegistrationRepository.save(registration);

		}
		return registration;
	}

	@Override
	public ParticipantRegistration refuseRegistration(long registrationId) {
		ParticipantRegistration registration = findById(registrationId);
		if (registration != null) {
			registration.setStatus(Status.REFUSED);
			participantRegistrationRepository.save(registration);
		}
		return registration;
	}

	@Override
	public List<ProgramInstance> findParticipantValidatedPrograms(long participantId) {
		List<ProgramInstance> programs = new ArrayList<>();
		for (ParticipantRegistration registration : participantRegistrationRepository
				.findByParticipantId(participantId)) {
			if (registration.getStatus() == Status.ACCEPTED) {
				programs.add(registration.getPrograminstance());
			}
		}
		return programs;
	}

	@Override
	public Set<Participant> findParticipantsByTrainerId(long trainerId) {

		Set<ProgramInstance> programs=sessionService.findProgramInstByTrainer(trainerId);
		
		Set<Participant> participantSet= new HashSet<>();
		for (ParticipantRegistration registration : findAll()) {
			if(programs.contains( registration.getPrograminstance())&&registration.getStatus()==Status.ACCEPTED) {
				participantSet.add(registration.getParticipant());
			}
		}
		return participantSet;
	}

}
