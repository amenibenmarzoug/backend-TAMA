package com.eniso.tama.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eniso.tama.dto.EntrepriseDto;
import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.repository.CompanyRegistrationRepository;
import com.eniso.tama.repository.EnterpriseRepository;

@Service
public class CompanyRegistrationServiceImpl implements CompanyRegistrationService {

	@Autowired
	private CompanyRegistrationRepository registrationRepository;
	
	@Autowired
	EntrepriseService entrepriseService ; 

	@Override
	public List<CompanyRegistration> findAll() {

		return registrationRepository.findAllByDeletedFalse();
	}

	@Override
	public CompanyRegistration findById(long theId) {
		
		Optional<CompanyRegistration> result = registrationRepository.findByIdAndDeletedFalse(theId);
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
		
		return registrationRepository.findByEntrepriseIdAndDeletedFalse(entrepriseId);
	}
	

	@Override
	public void deleteById(long id) {
		registrationRepository.deleteById(id);

	}

	@Override
	public List<CompanyRegistration> findByProgramInstance(long progranInstId) {
	
		return registrationRepository.findByProgramInstanceIdAndDeletedFalse(progranInstId);
	}

	@Override
	public Entreprise registerEntrep(EntrepriseDto enterprisedto, Long enterpriseId) {
		Entreprise entreprise = entrepriseService.findById(enterpriseId);
		entreprise.setEnterpriseName(enterprisedto.getEnterpriseName());
		entreprise.setCity(enterprisedto.getCity());
		entreprise.setEmail(enterprisedto.getEmail());
		entreprise.setManagerFirstName(enterprisedto.getManagerFirstName());
		entreprise.setManagerLastName(enterprisedto.getManagerLastName());
		entreprise.setManagerPosition(enterprisedto.getManagerPosition());
		entreprise.setPhoneNumber(enterprisedto.getPhoneNumber());
		entreprise.setStreet(enterprisedto.getStreet());
		entreprise.setPostalCode(enterprisedto.getPostalCode());
		entreprise.setNbMinParticipants(enterprisedto.getNbMinParticipants());
		entreprise.setProvider(enterprisedto.isProvider());
		CompanyRegistration registration = new CompanyRegistration();
		List<CompanyRegistration> companyRegistartions= new ArrayList<>() ;
		for (ProgramInstance p :  enterprisedto.getProgramInstance()) {
			registration.setEntreprise(entreprise);
			registration.setPrograminstance(p);
			registration.setRegistrationDate(LocalDate.now());
			//save registration
			companyRegistartions.add(registration);
		}
		//entreprise.setRegistration(companyRegistartions);
		
		entrepriseService.save(entreprise);
		return entreprise;
	}

	@Override
	public List<ProgramInstance> findEntrepPrograms(long entrepriseId) {
		List<ProgramInstance> programs = new ArrayList<>() ;
		for (CompanyRegistration registration : registrationRepository.findByEntrepriseIdAndDeletedFalse(entrepriseId)) {
			programs.add(registration.getPrograminstance());
		}
		return programs;
	}

	@Override
	public void deleteCompanyRegistation(long id) {
		CompanyRegistration companyRegistration=findById(id);
		companyRegistration.setDeleted(true);
		registrationRepository.save(companyRegistration);
		
	}
	
	

}
