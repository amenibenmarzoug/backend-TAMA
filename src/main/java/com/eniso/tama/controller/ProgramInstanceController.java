package com.eniso.tama.controller;

import java.util.List;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.ProgramInstanceService;

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
	
	//get confirmed classes
	@GetMapping("/confirmedClasses")
	public List<ProgramInstance> getConfirmedClasses() {
		return programInstService.getConfirmedClasses();
	}

	@GetMapping("/programsInst/private")
	public List<ProgramInstance> getPrivateClasses() {
		return programInstService.findAllByPrivateProgramInstanceTrue();
	}
	
	
	@GetMapping("/programsInst/public")
	public List<ProgramInstance> getPublicClasses() {
		return programInstService.findAllByPrivateProgramInstanceFalse();
	}

	@GetMapping("programsInst/{programId}")
	public ProgramInstance getProgram(@PathVariable long programId) {

		ProgramInstance theProgram = programInstService.findById(programId);

		if (theProgram == null) {
			throw new RuntimeException("programId not found - " + programId);
		}

		return theProgram;
	}
	
	@GetMapping("programsInst/{location}/{validated}")
	public List<ProgramInstance> findClassesByLocationAndValidated(@PathVariable String location, @PathVariable boolean validated) {

		List<ProgramInstance> thePrograms = programInstService.findByLocationAndValidated(location, validated);

		if (thePrograms == null) {
			throw new RuntimeException("No confirmed classes found in this location :  " + location);
		}

		return thePrograms;
	}
	// add mapping for POST /participants - add new control
	



	@PostMapping("/programsInst")
	public ProgramInstance addcontrol(@RequestBody ProgramInstance theProgram) {

		programInstService.save(theProgram);

		return theProgram;
	}




	@PostMapping("/programsInst2")
	public ProgramInstance addClass(@RequestBody ProgramInstance theProgram) {

		return(programInstService.addClass(theProgram));


	}





	// add mapping for PUT /employees - update existing employee


	
	@PutMapping("/programsInst")
	public ProgramInstance updateProgram(@RequestBody ProgramInstance theProgram) {

		return(programInstService.updateProgramInst(theProgram));


	}


	@PutMapping("/programsInst/confirm")
	public ProgramInstance confirm(@RequestBody ProgramInstance theProgram) {

		return(programInstService.confirm(theProgram));


	}

	@PutMapping("/programsInst/cancel")
	public ProgramInstance cancel(@RequestBody ProgramInstance theProgram) {

		return(programInstService.cancel(theProgram));


	}

	@Transactional
	@DeleteMapping("programsInst/{programId}")
	public ResponseEntity<?> deleteProgram(@PathVariable int programId) {

		ProgramInstance Program = programInstService.findById(programId);

		// throw exception if null

		if (Program == null) {
			throw new RuntimeException("the Program id is not found - " + programId);
		}

		//programService.deleteById(programId);

		programInstService.deleteProgramInstance(programId);
		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}
	
	@Transactional
	@DeleteMapping("programsInst/omit/{programId}")
	public ResponseEntity<?> omitProgram(@PathVariable int programId) {

		ProgramInstance Program = programInstService.findById(programId);

		// throw exception if null

		if (Program == null) {
			throw new RuntimeException("the Program id is not found - " + programId);
		}

		//programService.deleteById(programId);

		programInstService.omitProgramInstance(programId);
		return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
	}

}