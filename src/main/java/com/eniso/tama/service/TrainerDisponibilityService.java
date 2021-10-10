package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.TrainerDisponibility;

public interface TrainerDisponibilityService {

    public List<TrainerDisponibility> findAll();

    public TrainerDisponibility findById(long theId);

    public void save(TrainerDisponibility trainerdis);

    public void deleteById(long id);
}
