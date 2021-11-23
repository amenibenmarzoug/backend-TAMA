package com.eniso.tama.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Status;

public interface ParticipantService {

	public List<Participant> findAll();

	public Participant findById(long theId);

	public List<Participant> findByLevel(String theLevel);

	public List<Participant> getValidatedParticipantsByEntreprise(Entreprise company);

	public List<Participant> findByAbonadn(boolean theAbondan);

	public Set<Participant> findParticipantsByRegistrationStatus(Status status);

	public List<Participant> findParticipantsWithoutRegistration();

	public Set<Participant> findParticipantsWithRegistration();

	public List<Participant> findValidatedParticipants();

	public Participant refuseParticipant(Participant participant);

	public void save(Participant theParticipant);

	public List<Participant> getParticipantPerClass(long id);

	public List<Participant> getValidatedParticipantsPerClass(long id);

	public float percentMascPart();

	public void deleteById(long theId);

	public void deleteParticipant(long id);
	
	public void omitParticipant(long id);

	public void resetPassword(long id, String newPassword);

	public void resetPasswordAutomatically(long id);

}