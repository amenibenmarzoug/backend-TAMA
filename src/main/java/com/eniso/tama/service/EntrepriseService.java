package com.eniso.tama.service;
import java.util.List;

import com.eniso.tama.entity.Entreprise;




public interface EntrepriseService {
	

		public List<Entreprise> findAll();
		
		public Entreprise findById(long theId);
		
		//public List<Entreprise>findByLevel(String theLevel);
		//public List<Entreprise> findByEntreprise(Entreprise theParticipant);
		
		//public 	List<Entreprise> findByAbonadn(boolean theAbondan );
		
		
		

		public void save(Entreprise theEntreprise);
		
		public void deleteById(long theId);
		
	}
