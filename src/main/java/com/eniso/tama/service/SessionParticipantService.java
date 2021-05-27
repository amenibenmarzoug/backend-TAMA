package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.SessionParticipant;

public interface SessionParticipantService {
	public List<SessionParticipant> findAll();
	
	public SessionParticipant findById(long theId);
	
	public SessionParticipant save(SessionParticipant sessionParticipant);
	
	public void deleteById(long id);
	

}
