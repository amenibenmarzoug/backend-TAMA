package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Program;
import com.eniso.tama.service.ProgramService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ProgramService.class)
@RequestMapping(value = "/api")
public class ProgramController {

	
	@Autowired
	private ProgramService programService;

	public ProgramController(ProgramService programService) {
		super();
		this.programService = programService;
	}

	@GetMapping("/programs")
	public List<Program> findAll() {
		return programService.findAll();
	}

	@GetMapping("programs/{programId}")
	public Program getProgram(@PathVariable long programId) {

		Program theProgram = programService.findById(programId);

		if (theProgram == null) {
			throw new RuntimeException("programId not found - " + programId);
		}

		return theProgram;
	}
// add mapping for POST /participants - add new control

	@PostMapping("/program")
	public Program addcontrol(@RequestBody Program theProgram) {

		programService.save(theProgram);
		return theProgram;
	}
	// add program specific
	@PostMapping("/specificProgram")
	public Program addSpecificProgram(@RequestBody Program theProgram) {

		programService.saveSpecificProgram(theProgram);
		return theProgram;
	}

// add mapping for PUT /employees - update existing employee

	@PutMapping("/programEdit")
	public Program updateProgram(@RequestBody Program theProgram) {
		
		return(programService.updateProgram(theProgram));
		
	}

	@DeleteMapping("programs/{programId}")
	public String deleteProgram(@PathVariable int programId) {

		Program Program = programService.findById(programId);

		// throw exception if null

		if (Program == null) {
			throw new RuntimeException("the Program id is not found - " + programId);
		}

		programService.deleteById(programId);

		return "Deleted programId- " + programId;
	}

}