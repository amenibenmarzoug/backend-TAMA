package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.HibernateOperations;
import org.springframework.stereotype.Service;


import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.repository.ProgramInstanceRepository;

@Service
@ComponentScan(basePackageClasses = ProgramInstanceRepository.class)
public class ProgramInstanceServiceImpl implements ProgramInstanceService {

	private ProgramInstanceRepository programInstanceRepository;

	@Autowired
	public ProgramInstanceServiceImpl(ProgramInstanceRepository theProgramInstanceRepository) {
		programInstanceRepository = theProgramInstanceRepository;
	}

	@Override
	public List<ProgramInstance> findAll() {
		return programInstanceRepository.findAll();
	}

	@Override
	public ProgramInstance findById(long theId) {
		Optional<ProgramInstance> result = programInstanceRepository.findById(theId);

		ProgramInstance theProgramInstance = null;

		if (result.isPresent()) {
			theProgramInstance = result.get();
		} else {
			// we didn't find the participant
			throw new RuntimeException("Did not find program id - " + theId);
		}

		return theProgramInstance;
	}

	@Override
	public ProgramInstance save(ProgramInstance theProgramInstance) {
		ProgramInstance p=programInstanceRepository.save(theProgramInstance);
		return(p);
	}

	@Override

	public void deleteById(long theId) {
		programInstanceRepository.deleteById(theId);
		
	}
	
	public class ObjectRepositoryImpl {

	    @PersistenceContext
	    private EntityManager em;

	}
	
}