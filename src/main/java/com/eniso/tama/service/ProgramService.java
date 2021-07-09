package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Program;

public interface ProgramService {
	public List<Program> findAll();

	public Program findById(long theId);

	public void save(Program theProgram);

	public void deleteById(long theId);
	
	public Program updateProgram (Program theProgram);

}
