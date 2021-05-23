package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ProgramRepository;
import com.eniso.tama.service.ProgramInstanceService;
import com.eniso.tama.service.ThemeInstanceService;
import com.eniso.tama.service.ThemeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ThemeService.class )
@RequestMapping(value="/api")
public class ThemeController {
	
	@Autowired
	ProgramRepository programRepository;
	
	@Autowired
	private ProgramInstanceService programInsService;

	@Autowired
	private ThemeInstanceService themeInstService;
	
	@Autowired
	private ThemeService themeService;
	
	public ThemeController(ThemeService themeService) {
		super();
		this.themeService = themeService;
	}
	
	@GetMapping("/themes")
	public List<Theme> findAll() {
		return themeService.findAll();
	}
	@GetMapping("/program/themes")
	public List<Theme> getProgramThemes(@RequestParam("id") long id) {
		List<Theme> themesPerProgram = new ArrayList<Theme>();
		for (Theme theT : themeService.findAll()) {
		if(theT.getProgram()!=null) {
			if (id == theT.getProgram().getId()) {

				themesPerProgram.add(theT);			
			}
		}
		}
		return themesPerProgram;
	}
	
	
	@GetMapping("theme/{themeId}")
	public Theme getTheme(@PathVariable int  themeId) {
		
		Theme theTheme = themeService.findById(themeId);
		
		if (theTheme == null) {
			throw new RuntimeException("theme id not found - " + themeId);
		}
		
		return theTheme;
	}
	
	@PostMapping("/theme")
	public  Theme addTheme(@RequestBody Theme theTheme) {
		
		themeService.save(theTheme);
		return theTheme;
	}
	
	@Transactional 
	@PostMapping("/themeProgram")
	public ResponseEntity<?> addThemeProgram(@Valid @RequestBody Theme theme,@RequestParam("id") long id ) {

		List<ProgramInstance> list = programInsService.findByProgramId(id);
		System.out.println("chnw lid" + id);
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
		
		themeService.save(t);
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
	
	@Transactional 
	@PutMapping("/theme")
	public Theme updateTheme (@RequestBody Theme theTheme) {
		long id=theTheme.getId();
		List<ThemeInstance> list= themeInstService.findByThemeId(id);

		Theme newTheme = themeService.findById(id);
		newTheme.setThemeName(theTheme.getThemeName());
		newTheme.setNbDaysTheme(theTheme.getNbDaysTheme());
		newTheme.setProgram(theTheme.getProgram());
		for (ThemeInstance themeInstance : list) {
			themeInstance.setThemeInstName(theTheme.getThemeName());
			themeInstance.setNbDaysthemeInst(theTheme.getNbDaysTheme());
			themeInstService.save(themeInstance);
		}
		themeService.save(theTheme);
		
		return theTheme;
	}
	
	@DeleteMapping("/theme/{themeId}")
	public String deleteTheme(@PathVariable int  themeId) {
		
		Theme theme = themeService.findById(themeId);
		
		// throw exception if null
		
		if (theme == null) {
			throw new RuntimeException("the theme id is not found - " + themeId);
		}
		
		themeService.deleteById(themeId);
		
		return "Deleted theme id - " + themeId;
	}

}
