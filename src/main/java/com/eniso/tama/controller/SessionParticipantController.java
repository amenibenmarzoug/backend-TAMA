package com.eniso.tama.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Event;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.PresenceStates;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.SessionParticipant;
import com.eniso.tama.service.SessionParticipantService;

@RestController
@ComponentScan(basePackageClasses = SessionParticipantService.class )
@RequestMapping(value="/api")
public class SessionParticipantController {

	@Autowired
	private SessionParticipantService sessionParticipantService;
	
	
	public SessionParticipantController(SessionParticipantService sessionParticipantService) {
		this.sessionParticipantService = sessionParticipantService;
	} 
	
	@GetMapping("/sessionParticipant")
	public List<SessionParticipant> findAll() {
		return sessionParticipantService.findAll();
	}
	
	@GetMapping("sessionParticipant/{sessionParticipantId}")
	public SessionParticipant getParticipantSession(@PathVariable long  sessionParticipantId) {
		
		SessionParticipant sessionParticipant = sessionParticipantService.findById(sessionParticipantId);
		
		if (sessionParticipant == null) {
			throw new RuntimeException("sessionParticipant id not found - " + sessionParticipantId);
		}
		
		return sessionParticipant;
	}
	
	// add mapping for POST /sessionParticipant - add
	
	@Transactional
	@PostMapping("/sessionParticipant")
	public  SessionParticipant addSessionParticipant(@RequestBody Session session, @RequestBody Participant participant) {	
		
		PresenceStates present=PresenceStates.PRESENT;
		SessionParticipant newSessionParticipant= new SessionParticipant();
		newSessionParticipant.setParticipant(participant);
		newSessionParticipant.setSession(session);
		newSessionParticipant.setPresenceState(present);
		
		sessionParticipantService.save(newSessionParticipant);
		return newSessionParticipant;
	}
	// add mapping for POST /sessionParticipant - update
	@Transactional
	@PutMapping("/session")
	public SessionParticipant updateSessionParticipant(@RequestBody SessionParticipant sessionParticipant) {
		
		SessionParticipant updatedSessionParticipant=sessionParticipantService.findById(sessionParticipant.getId());

		updatedSessionParticipant.setParticipant(sessionParticipant.getParticipant());
		updatedSessionParticipant.setSession(sessionParticipant.getSession());
		updatedSessionParticipant.setPresenceState(sessionParticipant.getPresenceState());

		sessionParticipantService.save(updatedSessionParticipant);
		return updatedSessionParticipant;
	}
	
}
