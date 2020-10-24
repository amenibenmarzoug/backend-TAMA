package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


import com.eniso.tama.entity.ThemeInstance;

import com.eniso.tama.repository.ThemeInstanceRepository;


@Service
@ComponentScan(basePackageClasses = ThemeInstanceRepository.class)
public class ThemeInstanceServiceImpl implements ThemeInstanceService{

	
	private ThemeInstanceRepository themeInstanceRepository;

	@Autowired
	public ThemeInstanceServiceImpl(ThemeInstanceRepository themeInstanceRepository) {
		super();
		this.themeInstanceRepository = themeInstanceRepository;
	}


	@Override
	public List<ThemeInstance> findAll() {
		return themeInstanceRepository.findAll();
	}

	
	@Override
	public ThemeInstance findById(long theId) {
		Optional<ThemeInstance> result = themeInstanceRepository.findById(theId);

		ThemeInstance theThemeInstance = null;

		if (result.isPresent()) {
			theThemeInstance = result.get();
		} else {
			// we didn't find the participant
			throw new RuntimeException("Did not find theme id - " + theId);
		}

		return theThemeInstance;
	}

	@Override
	public void save(ThemeInstance theThemeInstance) {
		themeInstanceRepository.save(theThemeInstance)	;	
	}

	@Override
	public void deleteById(long theId) {
		themeInstanceRepository.deleteById(theId);
		
	}

}
