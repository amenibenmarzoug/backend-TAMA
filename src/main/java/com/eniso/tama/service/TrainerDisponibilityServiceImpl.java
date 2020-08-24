package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.TrainerDisponibility;

import com.eniso.tama.repository.TrainerDisponibilityRepository;

@Service
@ComponentScan(basePackageClasses = TrainerDisponibilityRepository.class )
public class TrainerDisponibilityServiceImpl implements TrainerDisponibilityService {
private TrainerDisponibilityRepository trainerDisponibilityRepository;
	
	public TrainerDisponibilityServiceImpl() {}

	@Autowired
	public TrainerDisponibilityServiceImpl(TrainerDisponibilityRepository trainerDisponibilityRepository) {
		this.trainerDisponibilityRepository = trainerDisponibilityRepository;
	}
	
	@Override
	public List<TrainerDisponibility> findAll() {
		return trainerDisponibilityRepository.findAll();
	}

	@Override
	public TrainerDisponibility findById(long theId) {
		Optional<TrainerDisponibility> result = trainerDisponibilityRepository.findById(theId);
		
		TrainerDisponibility trainerDisponibility = null;
		
		if (result.isPresent()) {
			trainerDisponibility = result.get();
		}
		else {
			// we didn't find the TrainerDisponibility
			throw new RuntimeException("Did not find TrainerDisponibility id - " + theId);
		}
		
		return trainerDisponibility;
	}

	@Override
	public void save(TrainerDisponibility trainerDisponibility) {
		trainerDisponibilityRepository.save(trainerDisponibility);
	}

	@Override
	public void deleteById(long    theId) {
		trainerDisponibilityRepository.deleteById(theId);
	}
}
