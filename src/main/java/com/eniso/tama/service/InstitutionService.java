package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Institution;

public interface InstitutionService {
    public List<Institution> findAll();

    public Institution findById(long theId);

    public void save(Institution theinstitution);

    public void deleteById(long theId);
}
