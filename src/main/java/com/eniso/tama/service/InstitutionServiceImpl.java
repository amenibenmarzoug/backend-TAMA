package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.repository.InstitutionRepository;

@Service
@ComponentScan(basePackageClasses = InstitutionRepository.class )
public class InstitutionServiceImpl implements InstitutionService {
	@Autowired
    private InstitutionRepository institutionRepository;
    
   // public InstitutionServiceImpl() {}
    
    
	public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
		super();
		this.institutionRepository = institutionRepository;
	}

	@Override
	public List<Institution> findAll() {
		return institutionRepository.findAll();
	}

	@Override
	public Institution findById(long theId) {
Optional<Institution> result = institutionRepository.findById(theId);
		
        Institution theControl = null;
		
		if (result.isPresent()) {
			theControl = result.get();
		}
		else {
			// we didn't find the participant
			throw new RuntimeException("Did not find institution id - " + theId);
		}
		
		return theControl;
	}

	@Override
	public void save(Institution theinstitution) {
		institutionRepository.save(theinstitution);
		
	}

	@Override
	public void deleteById(long theId) {
		institutionRepository.deleteById(theId);
	}

}
