package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.CompanyRegistration;


public interface CompanyRegistrationService {

	public List<CompanyRegistration> findAll();

	public CompanyRegistration findById(long theId);

	public CompanyRegistration save(CompanyRegistration registration);

	public List<CompanyRegistration> findByEntreprise(long entrepriseId);

	public List<CompanyRegistration> findByProgramInstance(long progranInstId);

	public void deleteById(long id);
	



}
