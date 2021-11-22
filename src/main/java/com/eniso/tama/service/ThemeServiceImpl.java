package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ProgramRepository;
import com.eniso.tama.repository.ThemeRepository;

@Service
@ComponentScan(basePackageClasses = ThemeRepository.class )
public class ThemeServiceImpl implements ThemeService {
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private ProgramInstanceService programInsService;
	
	@Autowired
	private ThemeInstanceService themeInstService;
	
	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private ModuleService moduleService;
	
	

	public ThemeServiceImpl(ThemeRepository themeRepository
			) {
		super();
		this.themeRepository = themeRepository;
		
		
	}

	@Override
	public List<Theme> findAll() {
		
		return themeRepository.findAll();
	}

	@Override
	public Theme findById(long theId) {
		Optional<Theme> result=themeRepository.findById(theId);
         Theme theControl = null;
		
		if (result.isPresent()) {
			theControl = result.get();
		}
		else {
			// we didn't find the theme 
			throw new RuntimeException("Did not find Theme  id - " + theId);
		}
		
		return theControl;
	}

	@Override
	public void save(Theme theme) {
		themeRepository.save(theme);
		
	}

	@Override
	public void deleteById(long id) {
		themeRepository.deleteById(id);
		
	}

	@Override
	public List<Theme> findByProgId(long id) {
		List<Theme> list= themeRepository.findAll();
		List<Theme> list1= new ArrayList<>();
		for (Theme t : list ) {
			if (t.getProgram().getId()== id) {
				 list1.add(t);
			}
			
		}
		return (list1);
	}

	@Override
	@Transactional 
	public ResponseEntity<?> addThemeProgram(Theme theme, long id) {
		List<ProgramInstance> list = programInsService.findByProgramId(id);
		
		Program program = new Program();
		for (Program p : programRepository.findAll()) {
			if (id == p.getId()) {
				program = p;
			}
		}
		
		Theme t = new Theme();
		t.setThemeName(theme.getThemeName());
		t.setNbDaysTheme(theme.getNbDaysTheme());;
		t.setProgram(program);
		
	   save(t);
		for (ProgramInstance programInstance : list) {
			ThemeInstance themeInst= new ThemeInstance();
			themeInst.setProgramInstance(programInstance);
			themeInst.setTheme(t);
			themeInst.setThemeInstName(t.getThemeName());
			themeInst.setNbDaysthemeInst(t.getNbDaysTheme());
			ThemeInstance t1=themeInstService.save(themeInst);
		}
		
		return ResponseEntity.ok(new MessageResponse("Theme added successfully!"));

	}

	@Override
	@Transactional 
	public Theme updateTheme(Theme theTheme) {
		long id=theTheme.getId();
		List<ThemeInstance> list= themeInstService.findByThemeId(id);

		Theme newTheme = findById(id);
		newTheme.setThemeName(theTheme.getThemeName());
		newTheme.setNbDaysTheme(theTheme.getNbDaysTheme());
		newTheme.setProgram(theTheme.getProgram());
		for (ThemeInstance themeInstance : list) {
			themeInstance.setThemeInstName(theTheme.getThemeName());
			themeInstance.setNbDaysthemeInst(theTheme.getNbDaysTheme());
			themeInstService.save(themeInstance);
		}
		save(theTheme);
		
		return theTheme;
	}

	@Override
	public List<String> findDistinctThemeName() {
		return themeRepository.findDistinctThemeName();
		
	}

	@Override
	@Transactional 
	public void deleteTheme(long id) {
		List<ThemeInstance> themeInstances=themeInstService.findByThemeId(id);
		List<com.eniso.tama.entity.Module> modules=moduleService.findModulesByThemeId(id);
		if(themeInstances!=null) {
			for (ThemeInstance themeInstance : themeInstances) {
				themeInstService.deleteThemeInstance(themeInstance.getId());
				
			}
		}
		if(modules!=null) {
			for (Module module : modules) {
				moduleService.deleteModule(module.getId());
			}
		}
		Theme theme=findById(id);
		theme.setDeleted(true);
		themeRepository.save(theme);
	}

	@Override
	@Transactional
	public void omitTheme(long id) {
		List<ThemeInstance> themeInstances=themeInstService.findByThemeId(id);
		List<com.eniso.tama.entity.Module> modules=moduleService.findModulesByThemeId(id);
		if(themeInstances!=null) {
			for (ThemeInstance themeInstance : themeInstances) {
				themeInstService.omitThemeInstance(themeInstance.getId());
				
			}
		}
		if(modules!=null) {
			for (Module module : modules) {
				moduleService.omitModule(module.getId());
			}
		}
		deleteById(id);
		
	}

}