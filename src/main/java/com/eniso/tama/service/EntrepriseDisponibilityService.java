package com.eniso.tama.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.EntrepriseDisponibility;

public interface EntrepriseDisponibilityService {

	public List<EntrepriseDisponibility> findAll();

	public EntrepriseDisponibility findById(long theId);
	// public List<Entreprise>findByLevel(String theLevel);
	// public List<Entreprise> findByEntreprise(Entreprise theParticipant);

	// public List<Entreprise> findByAbonadn(boolean theAbondan );

	public EntrepriseDisponibility getParticipant(@PathVariable long entrepriseId);

	public EntrepriseDisponibility save(EntrepriseDisponibility theEntrepriseDisponibility);

	public void deleteById(long theId);

	public EntrepriseDisponibility addDisponibility(@RequestBody EntrepriseDisponibility disponibility,
			@RequestParam("id") long id);

	public EntrepriseDisponibility updateEntreprise(@RequestBody EntrepriseDisponibility theEntreprise);

}
