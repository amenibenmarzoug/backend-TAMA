package com.eniso.tama.service;

import java.util.Date;
import java.util.List;

import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Trainer;

public interface ProgramInstanceService {

	public List<ProgramInstance> findAll();
	public List<ProgramInstance> getConfirmedClasses();
	//public List<ProgramInstance> getClassesByTrainer  (Trainer trainer );

	public ProgramInstance findById(long theId);
	

	public List<ProgramInstance> findByProgramId(long id);

	public ProgramInstance save(ProgramInstance theProgramInstance);
	
	
	public ProgramInstance update(ProgramInstance theProgramInstance);
	
	public ProgramInstance confirm(ProgramInstance theProgramInstance);
	
	public ProgramInstance cancel(ProgramInstance theProgramInstance);

	public void delete(ProgramInstance theProgramInstance);

	public void deleteById(long theId);

	public ProgramInstance addClass(ProgramInstance programInst);

	public ProgramInstance updateProgramInst(ProgramInstance programInst);
	
	public List<ProgramInstance> findByLocationAndValidated(String location , boolean validated);

}