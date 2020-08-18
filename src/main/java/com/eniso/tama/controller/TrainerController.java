package com.eniso.tama.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.exception.ResourceNotFoundException;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.repository.TrainerRepository;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class TrainerController {
	@Autowired
	private TrainerRepository trainerRepository;
	
	@GetMapping("/trainer")
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }
	
	@GetMapping("/trainer/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable(value = "id") Long trainerId)
        throws ResourceNotFoundException {
        Trainer trainer = trainerRepository.findById(trainerId)
          .orElseThrow(() -> new ResourceNotFoundException("Trainer not found for this id :: " + trainerId));
        return ResponseEntity.ok().body(trainer);
    }
	
	 @PostMapping("/trainer")
	    public Trainer createTrainer(@Valid @RequestBody Trainer trainer) {
	        return trainerRepository.save(trainer);
	    }
	 
	 @PutMapping("/trainer/{id}")
	    public ResponseEntity<Trainer> updateTrainer(@PathVariable(value = "id") Long trainerId,
	         @Valid @RequestBody Trainer trainerDetails) throws ResourceNotFoundException {
		 	Trainer trainer = trainerRepository.findById(trainerId)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + trainerId));

	        trainer.setEmail(trainerDetails.getEmail());
	        trainer.setLastName(trainerDetails.getLastName());
	        trainer.setFirstName(trainerDetails.getFirstName());
	        final Trainer updatedTrainer = trainerRepository.save(trainer);
	        return ResponseEntity.ok(updatedTrainer);
	    }

	    @DeleteMapping("/trainer/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long trainerId)
	         throws ResourceNotFoundException {
	        Trainer trainer = trainerRepository.findById(trainerId)
	       .orElseThrow(() -> new ResourceNotFoundException("Trainer not found for this id :: " + trainerId));

	        trainerRepository.delete(trainer);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	

}
