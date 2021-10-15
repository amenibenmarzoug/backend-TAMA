package com.eniso.tama.service;
import java.util.HashMap;
import java.util.List;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;




public interface ParticipantService {
	

		public List<Participant> findAll();
		
		public Participant findById(long theId);
		public List<Participant>findByLevel(String theLevel);
		public List<Participant> findByEntreprise(Participant theParticipant);
		public List<Participant> findByAbonadn(boolean theAbondan );
		public List<Participant> findParticipantsByClass(long programInstId );
		
		public void save(Participant theParticipant);
		public List<Participant> getParticipantPerClass(long id);
		public float percentMascPart();
		public void deleteById(long theId);
		
	}