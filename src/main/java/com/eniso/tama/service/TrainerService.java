package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Trainer;


public interface TrainerService {
    public List<Trainer> findAll();

    public Trainer findById(long theId);

    public void save(Trainer thetrainer);

    public void deleteById(long theId);
}
