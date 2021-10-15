package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ParticipantRegistration;

public interface ParticipantRegistrationService {

	public List<ParticipantRegistration> findAll();

	public ParticipantRegistration findById(long theId);

	public ParticipantRegistration save(ParticipantRegistration registration);

	public List<ParticipantRegistration> findByParticipantId(long entrepriseId);

	public List<ParticipantRegistration> findByProgramInstanceId(long progranInstId);

	public void deleteById(long id);
}
