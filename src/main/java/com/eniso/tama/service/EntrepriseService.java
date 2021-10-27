package com.eniso.tama.service;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.dto.EntrepriseDto;
import com.eniso.tama.entity.Entreprise;




public interface EntrepriseService {

	public List<Entreprise> findAll();

	public Entreprise findById(long theId);

	public Entreprise findByEmail(String email) ;


	public Entreprise findByPhoneNumber(String email) ;

	public void save(Entreprise theEntreprise);

	public void deleteById(long theId);

	public Entreprise getParticipant( long entrepriseId) ;

	public  ResponseEntity<?>   updateEntreprise(@RequestBody EntrepriseDto theEntreprise);

	public List<Entreprise> getNonValid();
	
	public List<Entreprise> findEnterpriseByLocation(String location);


	public void sendmail(@RequestParam("id") long id) throws AddressException, MessagingException, IOException;

	
	public void resetPassword (long id, String newPassword ); 
    
	public void resetPasswordAutomatically(long id); 
}