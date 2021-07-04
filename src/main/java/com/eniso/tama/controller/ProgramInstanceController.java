package com.eniso.tama.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.HibernateOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Autowired
	private ProgramInstanceService programInstService;
	

	public ProgramInstanceController(ProgramInstanceService programService) {
		super();
		this.programInstService = programService;
	}
	
	

	@GetMapping("/programsInst")
	public List<ProgramInstance> findAll() {
		return programInstService.findAll();
	}

	

	@GetMapping("programsInst/{programId}")
	public ProgramInstance getProgram(@PathVariable long programId) {

		ProgramInstance theProgram = programInstService.findById(programId);

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

		ProgramInstance p=programInstService.save(theProgram);
		//System.out.print();
		
		return theProgram;
	}
	
	
	

	@PostMapping("/programsInst2")
	public ProgramInstance addClass(@RequestBody ProgramInstance theProgram) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// stheControl.setId(0);

		return(programInstService.addClass(theProgram));
		
		
	}
	
	
	
	

	// add mapping for PUT /employees - update existing employee


	@PutMapping("/programsInst")
    public ProgramInstance updateProgram(@RequestBody ProgramInstance theProgram) {
		
		return(programInstService.updateProgramInst(theProgram));
		
		
	}

	
	@Transactional 
	@DeleteMapping("programsInst/{programId}")
	public String deleteProgram(@PathVariable int programId) {

		ProgramInstance Program = programInstService.findById(programId);

		// throw exception if null

		if (Program == null) {
			throw new RuntimeException("the Program id is not found - " + programId);
		}

		//programService.deleteById(programId);
		
		programInstService.delete(Program);

		return "Deleted programId- " + programId;
		
	}

}
