package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.repository.ThemeDetailInstanceRepository;

@Service
@ComponentScan(basePackageClasses = ThemeDetailInstanceRepository.class)
public class ThemeDetailInstanceServiceImpl implements ThemeDetailInstanceService{
	
	@Autowired
	private ThemeDetailInstanceRepository themeDetailInstanceRepository;
	
	@Autowired
	private SessionService sessionService;

	
	public ThemeDetailInstanceServiceImpl(ThemeDetailInstanceRepository theThemeDetailInstanceRepository) {
		themeDetailInstanceRepository = theThemeDetailInstanceRepository;
	}
	
	@Override
	public List<ThemeDetailInstance> findAll() {
		return themeDetailInstanceRepository.findAllByDeletedFalse();
	}

	@Override
	public ThemeDetailInstance findById(long theId) {
		Optional<ThemeDetailInstance> result = themeDetailInstanceRepository.findByIdAndDeletedFalse(theId);

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

	@Override
	public List<ThemeDetailInstance> findByThemeDetId(long id) {
		List<ThemeDetailInstance> list= this.findAll();
		List<ThemeDetailInstance> list1= new ArrayList<>();
		for (ThemeDetailInstance thInst : list ) {
			if (thInst.getThemeDetail().getId()== id) {
				 list1.add(thInst);
			}
			
		}
		return (list1);
	}

	@Override
	public List<ThemeDetailInstance> getModuleThemeDetails(long id) {
		List<ThemeDetailInstance> themeDetailsPerModule = new ArrayList<ThemeDetailInstance>();
		for (ThemeDetailInstance theTD : findAll()) {
		if(theTD.getModuleInstance()!=null) {
			if (id == theTD.getModuleInstance().getId()) {

				themeDetailsPerModule.add(theTD);			
			}
		}
		}
		return themeDetailsPerModule;
	}

	@Override
	@Transactional 
	public void deleteThemeDetailInstance(long theId) {
		List<Session> sessions=sessionService.findByThemeDetailInstanceId(theId);
		if(sessions!=null) {
			for (Session session : sessions) {
				sessionService.deleteSession(session.getId());
			}
		}
		ThemeDetailInstance themeDetailInstance=findById(theId);
		themeDetailInstance.setDeleted(true);
		themeDetailInstanceRepository.save(themeDetailInstance);
		
	}

	@Override
	@Transactional 
	public void omitThemeDetailInstance(long theId) {
		List<Session> sessions=sessionService.findByThemeDetailInstanceId(theId);
		if(sessions!=null) {
			for (Session session : sessions) {
				sessionService.omitSession(session.getId());
			}
		}
		deleteById(theId);
		
	}
}
