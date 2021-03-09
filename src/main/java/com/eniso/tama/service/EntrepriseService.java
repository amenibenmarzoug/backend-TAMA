package com.eniso.tama.service;
import java.util.List;

import com.eniso.tama.entity.Entreprise;




public interface EntrepriseService {
	

		public List<Entreprise> findAll();
		
		public Entreprise findById(long theId);
		
		public Entreprise findByEmail(String email) ;
		
		
		public Entreprise findByPhoneNumber(String email) ;

		public void save(Entreprise theEntreprise);
		
		public void deleteById(long theId);
		
	}
