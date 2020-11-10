package com.eniso.tama.controller;

import java.util.List;

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

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.service.ProgramInstanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ProgramInstanceService.class)
@RequestMapping(value = "/api")

public class ProgramInstanceController {

	private ProgramInstanceService programService;

	@Autowired
	public ProgramInstanceController(ProgramInstanceService programService) {
		super();
		this.programService = programService;
	}

	@GetMapping("/programsInst")
	public List<ProgramInstance> findAll() {
		return programService.findAll();
	}

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

		programService.save(theProgram);
		return theProgram;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping("/programsInst")

	public ProgramInstance updateProgram(@RequestBody ProgramInstance theProgram) {

		ProgramInstance newProgram = programService.findById(theProgram.getId());
		newProgram.setLocation(theProgram.getLocation());

		programService.save(newProgram);

		return theProgram;
	}

	@DeleteMapping("programsInst/{programId}")
	public String deleteProgram(@PathVariable int programId) {

		ProgramInstance Program = programService.findById(programId);

		// throw exception if null

		if (Program == null) {
			throw new RuntimeException("the Program id is not found - " + programId);
		}

		programService.deleteById(programId);

		return "Deleted programId- " + programId;
		
	}

}
