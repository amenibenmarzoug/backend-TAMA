package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.repository.EnterpriseRepository;
import com.eniso.tama.repository.CompanyRegistrationRepository;

@Service
public class CompanyRegistrationServiceImpl implements CompanyRegistrationService {

	@Autowired
	private CompanyRegistrationRepository registrationRepository;
	@Autowired
	private EnterpriseRepository enterpriseRepository;

	@Override
	public List<CompanyRegistration> findAll() {

		return registrationRepository.findAll();
	}

	@Override
	public CompanyRegistration findById(long theId) {
		
		Optional<CompanyRegistration> result = registrationRepository.findById(theId);
		CompanyRegistration registration = null;
		if (result.isPresent()) {
			registration = result.get();
		} else {
			// case the attendance is not found
			throw new RuntimeException("Did not find Registration id - " + theId);
		}
		return registration;
	}

	@Override
	public CompanyRegistration save(CompanyRegistration registration) {
		
		return registrationRepository.save(registration);
	}

	@Override
	public List<CompanyRegistration> findByEntreprise(long entrepriseId) {
		
		return registrationRepository.findByEntrepriseId(entrepriseId);
	}
	

	@Override
	public void deleteById(long id) {
		registrationRepository.deleteById(id);

	}

	@Override
	public List<CompanyRegistration> findByProgramInstance(long progranInstId) {
	
		return registrationRepository.findByProgramInstanceId(progranInstId);
	}

}
