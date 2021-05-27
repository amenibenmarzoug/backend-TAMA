package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.eniso.tama.entity.Theme;
import com.eniso.tama.repository.ProgramInstanceRepository;

@Service
@ComponentScan(basePackageClasses = ProgramInstanceRepository.class)
public class ProgramInstanceServiceImpl implements ProgramInstanceService {

	private ProgramInstanceRepository programInstanceRepository;

	@Autowired
	public ProgramInstanceServiceImpl(ProgramInstanceRepository theProgramInstanceRepository) {
		programInstanceRepository = theProgramInstanceRepository;
	}
	
	@PersistenceContext
	EntityManager entityManager;


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
	public List<ProgramInstance> findByBeginDate(Date date){
		return programInstanceRepository.findByBeginDate(date); 
	}

	@Override
	public ProgramInstance save(ProgramInstance theProgramInstance) {
		ProgramInstance p=programInstanceRepository.save(theProgramInstance);
		return(p);
	}

	@Override

	public void deleteById(long theId) {
		//programInstanceRepository.deleteById(theId);
		Optional<ProgramInstance> p= programInstanceRepository.findById(theId);
		entityManager.remove(p);
		
		
		
	}
	
	public class ObjectRepositoryImpl {

	    @PersistenceContext
	    private EntityManager em;

	}

	@Override
	public ProgramInstance update(ProgramInstance theProgramInstance) {
		ProgramInstance a=entityManager.merge(theProgramInstance);
		return (a);
		
	}

	@Override
	public  void delete(ProgramInstance theProgramInstance) {

	//	entityManager.getTransaction().begin();
		//entityManager.refresh(theProgramInstance);
		 entityManager.remove(theProgramInstance);
		// entityManager.getTransaction().commit();
		
	}
	
	
	@Override
	public List<ProgramInstance> findByProgramId(long id) {
		List<ProgramInstance> list= programInstanceRepository.findAll();
		List<ProgramInstance> list1= new ArrayList<>();
		for (ProgramInstance prInst : list ) {
			if (prInst.getProgram().getId()== id) {
				 list1.add(prInst);
			}
			
		}
		return (list1);
	}
}