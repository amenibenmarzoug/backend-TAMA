package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.SessionParticipant;
import com.eniso.tama.repository.SessionParticipantRepository;


@Service
@ComponentScan(basePackageClasses = SessionParticipantRepository.class) 
public class SessionParticipantServiceImpl implements  SessionParticipantService {

	@Autowired
	private SessionParticipantRepository sessionParticipantRepository;

	public SessionParticipantServiceImpl() {
	}

	
	public SessionParticipantServiceImpl (SessionParticipantRepository sessionParticipantRepository) {
		this.sessionParticipantRepository = sessionParticipantRepository;
	}
	@Override
	public List<SessionParticipant> findAll() {
		return sessionParticipantRepository.findAll();
	}

	@Override
	public SessionParticipant findById(long theId) {
		Optional <SessionParticipant> result=sessionParticipantRepository.findById(theId);
		SessionParticipant sessionParticipant= null;
		if(result.isPresent()) {
			sessionParticipant=result.get();
		}
		else {
			//case the sessionParticipant is not found 
			throw new RuntimeException("Did not find SessionParticipant id - " + theId);
		}
		return sessionParticipant; 
	}

	@Override
	public SessionParticipant save(SessionParticipant sessionParticipant) {
		return sessionParticipantRepository.save(sessionParticipant);
	}

	@Override
	public void deleteById(long id) {
		sessionParticipantRepository.deleteById(id);
	}

}
