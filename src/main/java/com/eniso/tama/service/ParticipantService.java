package com.eniso.tama.service;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.eniso.tama.entity.Participant;




public interface ParticipantService {
	
		
		

		public List<Participant> findAll();
		
		public Participant findById(long theId);
		
		public void save(Participant theParticipant);
		
		public void deleteById(long theId);
		
	}


