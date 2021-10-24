package com.eniso.tama.service;

import java.util.List;
import java.util.Set;

import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.ProgramInstance;

public interface ParticipantRegistrationService {

	public List<ParticipantRegistration> findAll();

	public ParticipantRegistration findById(long theId);

	public ParticipantRegistration save(ParticipantRegistration registration);

	public List<ParticipantRegistration> findByParticipantId(long entrepriseId);
	public List<ProgramInstance>findParticipantPrograms(long participantId);
	public List<ProgramInstance>findParticipantValidatedPrograms(long participantId);

	public List<ParticipantRegistration> findByProgramInstanceId(long progranInstId);

	public void deleteById(long id);
	
	public ParticipantRegistration validateRegistration(long registrationId);
	
	public ParticipantRegistration refuseRegistration(long registrationId);
}
