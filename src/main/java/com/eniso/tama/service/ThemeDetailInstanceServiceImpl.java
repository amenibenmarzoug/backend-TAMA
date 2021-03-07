package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.repository.ProgramInstanceRepository;
import com.eniso.tama.repository.ThemeDetailInstanceRepository;

@Service
@ComponentScan(basePackageClasses = ThemeDetailInstanceRepository.class)
public class ThemeDetailInstanceServiceImpl implements ThemeDetailInstanceService{
	
	private ThemeDetailInstanceRepository themeDetailInstanceRepository;

	@Autowired
	public ThemeDetailInstanceServiceImpl(ThemeDetailInstanceRepository theThemeDetailInstanceRepository) {
		themeDetailInstanceRepository = theThemeDetailInstanceRepository;
	}
	
	@Override
	public List<ThemeDetailInstance> findAll() {
		return themeDetailInstanceRepository.findAll();
	}

	@Override
	public ThemeDetailInstance findById(long theId) {
		Optional<ThemeDetailInstance> result = themeDetailInstanceRepository.findById(theId);

		ThemeDetailInstance themeDetailInstance = null;

		if (result.isPresent()) {
			themeDetailInstance = result.get();
		} else {
			// we didn't find the themeDetailInstance
			throw new RuntimeException("Did not find themeDetailInstance id - " + theId);
		}

		return themeDetailInstance;
	}

	@Override
	public void save(ThemeDetailInstance theThemeDetailInstance) {
		themeDetailInstanceRepository.save(theThemeDetailInstance);
		
	}

	@Override
	public void deleteById(long theId) {
		themeDetailInstanceRepository.deleteById(theId);
		
	}

}
