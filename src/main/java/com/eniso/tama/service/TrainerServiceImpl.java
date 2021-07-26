package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Trainer;
import com.eniso.tama.repository.TrainerRepository;

@Service
@ComponentScan(basePackageClasses = TrainerRepository.class)
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    public TrainerServiceImpl() {
    }

    @Autowired
    public TrainerServiceImpl(TrainerRepository theTrainerRepository) {
        trainerRepository = theTrainerRepository;
    }

    @Override
    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findById(long theId) {
        Optional<Trainer> result = trainerRepository.findById(theId);

        Trainer theControl = null;

        if (result.isPresent()) {
            theControl = result.get();
        } else {
            // we didn't find the trainer
            throw new RuntimeException("Did not find trainer id - " + theId);
        }

        return theControl;
    }

    @Override
    public void save(Trainer trainer) {
        trainerRepository.save(trainer);

    }

    @Override
    public void deleteById(long theId) {
        trainerRepository.deleteById(theId);

    }

}
