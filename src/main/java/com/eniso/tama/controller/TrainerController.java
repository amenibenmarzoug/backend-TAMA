package com.eniso.tama.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Days;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.helpers.RandomPasswordGenerator;
import com.eniso.tama.service.TrainerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@ComponentScan(basePackageClasses = TrainerService.class)
@RequestMapping("/api")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;
    
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
    RandomPasswordGenerator randomPassword;
    

    public TrainerController(TrainerService theTrainerService) {
        trainerService = theTrainerService;
    }

	
	@GetMapping("/trainers")
	//@PreAuthorize("hasAuthority('MANAGER')")
	public List<Trainer> getAllTrainers() {
		return trainerService.findAll();
	}


    @GetMapping("trainers/{trainerId}")
    public Trainer getTrainer(@PathVariable int trainerId) {

        Trainer theTrainer = trainerService.findById(trainerId);

        if (theTrainer == null) {
            throw new RuntimeException("Trainer id not found - " + trainerId);
        }

        return theTrainer;
    }

    @GetMapping("trainerDisponi/{trainerId}")
    public Set<Days> getTrainerDisponibilities(@PathVariable int trainerId) {

        Trainer theTrainer = trainerService.findById(trainerId);

        if (theTrainer == null) {
            throw new RuntimeException("Trainer id not found - " + trainerId);
        }

        return theTrainer.getDisponibilityDays();
    }
    // add mapping for POST /participants - add new control

    @PostMapping("/trainers")
    public Trainer addTrainer(@RequestBody Trainer theTrainer) {
    	theTrainer.setPassword(encoder.encode(randomPassword.generateSecureRandomPassword()));
        trainerService.save(theTrainer);
        return theTrainer;
    }



    @PutMapping("/trainers")
    public Trainer updateTrainer(@RequestBody Trainer theTrainer) {

        Trainer newTrainer = trainerService.updateTrainer(theTrainer);
        return theTrainer;
    }

    @DeleteMapping("/trainers/{trainerId}")
    public String deleteTrainer(@PathVariable int trainerId) {

        Trainer tempTrainer = trainerService.findById(trainerId);

        // throw exception if null

        if (tempTrainer == null) {
            throw new RuntimeException("the trainer id is not found - " + trainerId);
        }

        trainerService.deleteById(trainerId);

        return "Deleted trainer id - " + trainerId;
    }

    @GetMapping("/sendMailToTrainer")
    private void sendmail(@RequestParam("id") long id) throws AddressException, MessagingException, IOException {

        trainerService.validateTrainer(id);
    }
    
    @GetMapping("specialization/trainers")
	public List<Trainer> findTrainersBySpecialization(@RequestParam String specialization) {
    	return trainerService.findTrainersBySpecialization(specialization);
    }
}