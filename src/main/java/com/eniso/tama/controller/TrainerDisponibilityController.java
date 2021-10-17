package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.TrainerDisponibility;
import com.eniso.tama.service.TrainerDisponibilityService;

@RestController
@ComponentScan(basePackageClasses = TrainerDisponibilityService.class)
@RequestMapping(value = "/api")
public class TrainerDisponibilityController {

	@Autowired
    private TrainerDisponibilityService trainerDisponibilityService;

    
    public TrainerDisponibilityController(TrainerDisponibilityService trainerDisponibilityService) {
        this.trainerDisponibilityService = trainerDisponibilityService;
    }


    @GetMapping("/trainerDisponibility")
    public List<TrainerDisponibility> findAll() {
        return trainerDisponibilityService.findAll();
    }

    @GetMapping("trainerDisponibility/{trainerDisponibilityId}")
    public TrainerDisponibility getTrainerDisponibility(@PathVariable int trainerDisponibilityId) {

        TrainerDisponibility trainerDisponibility = trainerDisponibilityService.findById(trainerDisponibilityId);

        if (trainerDisponibility == null) {
            throw new RuntimeException("TrainerDisponibility id not found - " + trainerDisponibilityId);
        }

        return trainerDisponibility;
    }
    // add mapping for POST /trainerDisponibility - add new TrainerDisponibility

    @PostMapping("/trainerDisponibility")
    public TrainerDisponibility addTrainerDisponibility(@RequestBody TrainerDisponibility trainerDisponibility) {


        trainerDisponibilityService.save(trainerDisponibility);
        return trainerDisponibility;
    }


    // add mapping for PUT /trainerDisponibility - update existing trainerDisponibility

    @PutMapping("/trainerDisponibility")
    public TrainerDisponibility updateTrainerDisponibility(@RequestBody TrainerDisponibility trainerDisponibility) {

        trainerDisponibilityService.save(trainerDisponibility);

        return trainerDisponibility;
    }

    @DeleteMapping("/trainerDisponibility/{trainerDisponibilityId}")
    public String deleteTrainerDisponibility(@PathVariable int trainerDisponibilityId) {

        TrainerDisponibility trainerDisponibility = trainerDisponibilityService.findById(trainerDisponibilityId);

        // throw exception if null

        if (trainerDisponibility == null) {
            throw new RuntimeException("the trainerDisponibility id is not found - " + trainerDisponibilityId);
        }

        trainerDisponibilityService.deleteById(trainerDisponibilityId);

        return "Deleted trainerDisponibility id - " + trainerDisponibilityId;
    }
}
