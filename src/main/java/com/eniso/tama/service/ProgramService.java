package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;

public interface ProgramService {

	public List<Program> findAll();

	public Program findById(long theId);

	List<Program> findByProgramNameContaining(String name);

	List<Program> findByProgramNameNotLike(String category1,String category2,String category3);

	public void save(Program theProgram);

	public void saveSpecificProgram(Program theProgram);

	public void deleteById(long theId);

	public void deleteProgram(long theId);

	public void omitProgram(long theId);

	public Program updateProgram(Program theProgram);

}