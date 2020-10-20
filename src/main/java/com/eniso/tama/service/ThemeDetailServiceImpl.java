package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Group;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.repository.GroupRepository;
import com.eniso.tama.repository.ThemeDetailRepository;


@Service
@ComponentScan(basePackageClasses = ThemeDetailRepository.class )
public class ThemeDetailServiceImpl implements ThemeDetailService {
	
	private ThemeDetailRepository themeDetailRepository;
    
    
    @Autowired
	public ThemeDetailServiceImpl(ThemeDetailRepository themeDetailRepository) {
		//super();
		this.themeDetailRepository = themeDetailRepository;
	}
    
	@Override
	public List<ThemeDetail> findAll() {
		return themeDetailRepository.findAll();
	}

	@Override
	public ThemeDetail findById(long theId) {
		Optional<ThemeDetail> result = themeDetailRepository.findById(theId);
		
		ThemeDetail theControl = null;
		
		if (result.isPresent()) {
			theControl = result.get();
		}
		else {
			// we didn't find the theme Detail
			throw new RuntimeException("Did not find THeme Detail id - " + theId);
		}
		
		return theControl;
	}

	@Override
	public void save(ThemeDetail themeDetail) {
		themeDetailRepository.save(themeDetail);

	}

	@Override
	public void deleteById(long id) {
		themeDetailRepository.deleteById(id);

	}

}
