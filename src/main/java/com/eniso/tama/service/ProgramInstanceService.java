package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.ProgramInstance;

public interface ProgramInstanceService {

    public List<ProgramInstance> findAll();

    public ProgramInstance findById(long theId);

    public List<ProgramInstance> findByProgramId(long id);

    public ProgramInstance save(ProgramInstance theProgramInstance);

    public ProgramInstance update(ProgramInstance theProgramInstance);

    public void delete(ProgramInstance theProgramInstance);

    public void deleteById(long theId);

}
