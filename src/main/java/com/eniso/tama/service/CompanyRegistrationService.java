package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.dto.EntrepriseDto;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.ProgramInstance;

public interface CompanyRegistrationService {

	public List<CompanyRegistration> findAll();

	public CompanyRegistration findById(long theId);

	public CompanyRegistration save(CompanyRegistration registration);

	public List<CompanyRegistration> findByEntreprise(long entrepriseId);

	public List<ProgramInstance> findEntrepPrograms(long entrepriseId);

	public List<CompanyRegistration> findByProgramInstance(long progranInstId);

	public void deleteById(long id);
	
	public void deleteCompanyRegistation(long id);

	public Entreprise registerEntrep(EntrepriseDto e, Long id);

}
