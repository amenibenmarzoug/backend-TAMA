package com.eniso.tama.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Cursus;
import com.eniso.tama.repository.CursusRepository;


@Service
@ComponentScan(basePackageClasses = CursusRepository.class )

public class CursusServiceImpl implements CursusService {
	
	private CursusRepository cursusRepository;
	
	public CursusServiceImpl() {}

	@Autowired
	public CursusServiceImpl(CursusRepository cursusRepository) {
		this.cursusRepository = cursusRepository;
	}
	
	@Override
	public List<Cursus> findAll() {
		return cursusRepository.findAll();
	}

	@Override
	public Cursus findById(long theId) {
		Optional<Cursus> result = cursusRepository.findById(theId);
		
		Cursus cursus = null;
		
		if (result.isPresent()) {
			cursus = result.get();
		}
		else {
			// we didn't find the cursus
			throw new RuntimeException("Did not find cursus id - " + theId);
		}
		
		return cursus;
	}

	@Override
	public void save(Cursus cursus) {
		cursusRepository.save(cursus);
	}

	@Override
	public void deleteById(long    theId) {
		cursusRepository.deleteById(theId);
	}

}
