package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Session;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.SessionService;

@RestController
@ComponentScan(basePackageClasses = SessionService.class )
@RequestMapping(value="/api")
public class SessionController {

	
	private SessionService sessionService;
	
	@Autowired
	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	} 
	
	
	


	@GetMapping("/session")
	public List<Session> findAll() {
		return sessionService.findAll();
	}
	
	
	@GetMapping("session/{sessionId}")
	public Session getSession(@PathVariable long  sessionId) {
		
		Session session = sessionService.findById(sessionId);
		
		if (session == null) {
			throw new RuntimeException("session id not found - " + sessionId);
		}
		
		return session;
	}
	// add mapping for POST /session - add new control

	@PostMapping("/session")
	public  Session addSession(@RequestBody Session session) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		
		sessionService.save(session);
		return session;
	}

	
	// add mapping for PUT /session - update existing session
	
	@PutMapping("/session")
	public Session updateSession(@RequestBody Session session) {
		
		sessionService.save(session);
		
		return session;
	}
		
		@DeleteMapping("/session/{sessionId}")
		public String deleteSession(@PathVariable long  sessionId) {
			
			Session session = sessionService.findById(sessionId);
			
			// throw exception if null
			
			if (session == null) {
				throw new RuntimeException("the session id is not found - " + sessionId);
			}
			
			sessionService.deleteById(sessionId);
			
			return "Deleted session id - " + sessionId;
		}
}
