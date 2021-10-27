package com.eniso.tama.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.eniso.tama.entity.Institution;

public interface InstitutionService {

public List<Institution> findAll();
	
	public Institution findById(long theId);
	
	public void save(Institution theinstitution);
	
	public void deleteById(long theId);
	
	public Institution getInstitution(long institutionId);
	
	public ResponseEntity<?> registerInstitutionParManager(Institution theI);
	
	public Institution updateParticipant(Institution theInstitution) ;
	
	public String deleteInstitution( long id) ;
	
	 public void resetPassword (long id, String newPassword); 
	    
	   public void resetPasswordAutomatically(long id); 

}
