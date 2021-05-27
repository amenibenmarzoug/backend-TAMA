package com.eniso.tama.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.orm.hibernate5.HibernateOperations;
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

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ModuleInstance;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Theme;
import com.eniso.tama.entity.ThemeDetail;
import com.eniso.tama.entity.ThemeDetailInstance;
import com.eniso.tama.entity.ThemeInstance;
import com.eniso.tama.service.ModuleInstanceService;
import com.eniso.tama.service.ModuleService;
import com.eniso.tama.service.ProgramInstanceService;
import com.eniso.tama.service.ThemeDetailInstanceService;
import com.eniso.tama.service.ThemeDetailService;
import com.eniso.tama.service.ThemeInstanceService;
import com.eniso.tama.service.ThemeService;

@CrossOrigin(origins = "*")
@RestController
@ComponentScan(basePackageClasses = ProgramInstanceService.class)
@RequestMapping(value = "/api")

public class ProgramInstanceController {

	private ProgramInstanceService programService;
	private ThemeService themeService;
	private ModuleService moduleService;
	private ThemeDetailService themeDetailService;
	private ThemeInstanceService themeInstanceService;
	private ModuleInstanceService moduleInstanceService;
	private ThemeDetailInstanceService themeDetailInstanceService;
	@Autowired
	/*public ProgramInstanceController(ProgramInstanceService programService) {
		super();
		this.programService = programService;
	}*/
	public ProgramInstanceController(ProgramInstanceService programService, ThemeService themeService,ModuleService moduleService,ThemeDetailService themeDetailService,ThemeInstanceService themeInstanceService,ModuleInstanceService moduleInstanceService,ThemeDetailInstanceService themeDetailInstanceService) {
		super();
		this.programService = programService;
		this.themeService = themeService;
		this.moduleService=moduleService;
		this.themeDetailService=themeDetailService;
		this.themeInstanceService=themeInstanceService;
		this.moduleInstanceService=moduleInstanceService;
		this.themeDetailInstanceService=themeDetailInstanceService;
	}
	

	@GetMapping("/programsInst")
	public List<ProgramInstance> findAll() {
		return programService.findAll();
	}
	/*
	@GetMapping("/programsInst/date")
	public List<ProgramInstance> findByBeginDate(@RequestParam("Date") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date  date) {
		return programService.findByBeginDate(date);
	}
	*/

	@GetMapping("programsInst/{programId}")
	public ProgramInstance getProgram(@PathVariable long programId) {

		ProgramInstance theProgram = programService.findById(programId);

		if (theProgram == null) {
			throw new RuntimeException("programId not found - " + programId);
		}

		return theProgram;
	}
	
	
	// add mapping for POST /participants - add new control

	
	
	@PostMapping("/programsInst")
	public ProgramInstance addcontrol(@RequestBody ProgramInstance theProgram) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// stheControl.setId(0);

		ProgramInstance p=programService.save(theProgram);
		//System.out.print();
		
		return theProgram;
	}
	
	
	
	
	
	
	@Transactional 
	@PostMapping("/programsInst2")
	public ProgramInstance addClass(@RequestBody ProgramInstance theProgram) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// stheControl.setId(0);

		ProgramInstance p=programService.save(theProgram);
		//System.out.print();
		long id= theProgram.getProgram().getId();
		List<Theme> listT=this.themeService.findByProgId(id);
		
		for (Theme t : listT ) {
			System.out.println(t.getThemeName());
			//traitement création ThmeInst
			ThemeInstance themeInst= new ThemeInstance();
			themeInst.setProgramInstance(p);
			themeInst.setTheme(t);
			themeInst.setThemeInstName(t.getThemeName());
			themeInst.setNbDaysthemeInst(t.getNbDaysTheme());
			ThemeInstance t1=this.themeInstanceService.save(themeInst);
			
			List<Module> listM= this.moduleService.findModulesByThemeId(t.getId());
			for (Module m :listM) {
				System.out.println(m.getModuleName());
				//traitement création moduleInst
				ModuleInstance moduleInst= new ModuleInstance();
				moduleInst.setModule(m);
				moduleInst.setModuleInstanceName(m.getModuleName());
				moduleInst.setNbDaysModuleInstance(m.getNbDaysModule());
				moduleInst.setThemeInstance(t1);
				ModuleInstance m1=this.moduleInstanceService.save(moduleInst);
				
				List<ThemeDetail> listTd= this.themeDetailService.findByModuleId(m.getId());
				for (ThemeDetail td: listTd) {
					System.out.println(td.getThemeDetailName());
					//traitement 
					ThemeDetailInstance themeDetailinst= new ThemeDetailInstance();
					themeDetailinst.setModuleInstance(m1);
					themeDetailinst.setThemeDetail(td);
					themeDetailinst.setNbDaysthemeDetailInst(td.getNbDaysThemeDetail());
					themeDetailinst.setThemeDetailInstName(td.getThemeDetailName());
					this.themeDetailInstanceService.save(themeDetailinst);
					
				}
			}
			
		}
		
		
		return theProgram;
	}
	
	
	
	
	
	

	// add mapping for PUT /employees - update existing employee

	@Transactional 
	@PutMapping("/programsInst")

	public ProgramInstance updateProgram(@RequestBody ProgramInstance theProgram) {

		ProgramInstance newProgram = programService.findById(theProgram.getId());
		newProgram.setProgramInstName(theProgram.getProgramInstName());
		newProgram.setNbDaysProgInst(theProgram.getNbDaysProgInst());;
		newProgram.setLocation(theProgram.getLocation());
		newProgram.setProgram(theProgram.getProgram());
		newProgram.setBeginDate(theProgram.getBeginDate());
		newProgram.setEndDate(theProgram.getEndDate());

		//programService.save(newProgram);
		
		programService.update(newProgram);

		return theProgram;
	}

	
	@Transactional 
	@DeleteMapping("programsInst/{programId}")
	public String deleteProgram(@PathVariable int programId) {

		ProgramInstance Program = programService.findById(programId);

		// throw exception if null

		if (Program == null) {
			throw new RuntimeException("the Program id is not found - " + programId);
		}

		//programService.deleteById(programId);
		
		programService.delete(Program);

		return "Deleted programId- " + programId;
		
	}

}
