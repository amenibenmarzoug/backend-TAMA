package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ProgramInstanceRepository;
import com.eniso.tama.repository.ThemeInstanceRepository;


@Service
@ComponentScan(basePackageClasses = ThemeInstanceRepository.class)
public class ThemeInstanceServiceImpl implements ThemeInstanceService{

	@Autowired
	private ThemeInstanceRepository themeInstanceRepository;
	
	@Autowired
	ProgramInstanceRepository programInstRepository;
	
	@Autowired
	private ModuleInstanceService moduleInstanceService;

	@Override
	public List<ThemeInstance> findAll() {
		return themeInstanceRepository.findAllByDeletedFalse();
	}

	
	public ThemeInstanceServiceImpl(ThemeInstanceRepository themeInstanceRepository,
			ProgramInstanceRepository programInstRepository) {
		super();
		this.themeInstanceRepository = themeInstanceRepository;
		this.programInstRepository = programInstRepository;
	}


	@Override
	public ThemeInstance findById(long theId) {
		Optional<ThemeInstance> result = themeInstanceRepository.findByIdAndDeletedFalse(theId);

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
	public ThemeInstance save(ThemeInstance theThemeInstance) {
		return(themeInstanceRepository.save(theThemeInstance));	
	}

	@Override
	public void deleteById(long theId) {
		themeInstanceRepository.deleteById(theId);
		
	}
	
	@Override
	public List<ThemeInstance> findByThemeId(long id) {
		List<ThemeInstance> list= this.findAll();
		List<ThemeInstance> list1= new ArrayList<>();
		for (ThemeInstance thInst : list ) {
			if (thInst.getTheme().getId()== id) {
				 list1.add(thInst);
			}
			
		}
		return (list1);
	}


	@Override
	public List<ThemeInstance> getProgramThemesInst(long id) {
		List<ThemeInstance> themesPerProgram = new ArrayList<ThemeInstance>();
		for (ThemeInstance theT : findAll()) {
		if(theT.getProgramInstance()!=null) {
			if (id == theT.getProgramInstance().getId()) {

				themesPerProgram.add(theT);			
			}
		}
		}
		return themesPerProgram;
	}


	@Override
	public ResponseEntity<?> addThemeProgram(ThemeInstance theme, long id) {
		ProgramInstance program = new ProgramInstance();
		for (ProgramInstance p : programInstRepository.findAllByDeletedFalse()) {
			if (id == p.getId()) {
				program = p;
			}
		}
		
		ThemeInstance t = new ThemeInstance();
		t.setThemeInstName(theme.getThemeInstName());;
		t.setNbDaysthemeInst(theme.getNbDaysthemeInst());
		t.setTheme(theme.getTheme());
		t.setProgramInstance(program);
		
		save(t);
		return ResponseEntity.ok(new MessageResponse("Theme Instance added successfully!"));
	}


	@Override
	public ThemeInstance updateThemeInst(ThemeInstance theThemeInst) {
		
		ThemeInstance newthemeInst = findById(theThemeInst.getId());
		newthemeInst.setThemeInstName(theThemeInst.getThemeInstName());
		newthemeInst.setNbDaysthemeInst(theThemeInst.getNbDaysthemeInst());
		newthemeInst.setTheme(theThemeInst.getTheme());
		save(newthemeInst);

		return newthemeInst;
	}


	@Override
	@Transactional 
	public void deleteThemeInstance(long theId) {
		List<ModuleInstance> moduleInstances=moduleInstanceService.getThemeModules(theId);
		if(moduleInstances!=null) {
			for (ModuleInstance moduleInstance : moduleInstances) {
				moduleInstanceService.deleteModuleInstance(moduleInstance.getId());
			}
		}
		ThemeInstance themeInstance=findById(theId);
		themeInstance.setDeleted(true);
		themeInstanceRepository.save(themeInstance);
	}


	@Override
	@Transactional
	public void omitThemeInstance(long theId) {
		List<ModuleInstance> moduleInstances=moduleInstanceService.getThemeModules(theId);
		if(moduleInstances!=null) {
			for (ModuleInstance moduleInstance : moduleInstances) {
				moduleInstanceService.omitModuleInstance(moduleInstance.getId());
			}
		}
		deleteById(theId);
		
	}

}