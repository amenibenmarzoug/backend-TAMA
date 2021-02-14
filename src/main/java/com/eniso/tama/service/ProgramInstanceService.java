package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ProgramInstance;

public interface ProgramInstanceService {

	public List<ProgramInstance> findAll();

	public ProgramInstance findById(long theId);

	public void save(ProgramInstance theProgramInstance);

	public void deleteById(long theId);

}
