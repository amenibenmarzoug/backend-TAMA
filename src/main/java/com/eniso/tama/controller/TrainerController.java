package com.eniso.tama.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.service.TrainerService;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
@ComponentScan(basePackageClasses = TrainerService.class )
@RequestMapping("/api")
public class TrainerController {
	
	
	
	private TrainerService trainerService;
	
	@Autowired
	public TrainerController(TrainerService theTrainerService) {
		trainerService = theTrainerService;
	}
	
	@GetMapping("/trainers")
    public List<Trainer> getAllTrainers() {
        return trainerService.findAll();
    }
	
	@GetMapping("trainers/{trainerId}")
	public Trainer getTrainer(@PathVariable int  trainerId) {
		
		Trainer theTrainer = trainerService.findById(trainerId);
		
		if (theTrainer == null) {
			throw new RuntimeException("Trainer id not found - " + trainerId);
		}
		
		return theTrainer;
	}
	// add mapping for POST /participants - add new control

	@PostMapping("/trainers")
	public  Trainer addTrainer(@RequestBody Trainer theTrainer) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		trainerService.save(theTrainer);
		return theTrainer;
	}
	
	
	// add mapping for PUT /employees - update existing employee
	
		@PutMapping("/trainers")
		public Trainer updateTrainer(@RequestBody Trainer theTrainer) {
			
			trainerService.save(theTrainer);
			
			return theTrainer;
		}

		@DeleteMapping("/trainers/{trainerId}")
		public String deleteTrainer(@PathVariable int  trainerId) {
			
			Trainer tempTrainer = trainerService.findById(trainerId);
			
			// throw exception if null
			
			if (tempTrainer == null) {
				throw new RuntimeException("the trainer id is not found - " + trainerId);
			}
			
			trainerService.deleteById(trainerId);
			
			return "Deleted trainer id - " + trainerId;
		}
	
	

}
