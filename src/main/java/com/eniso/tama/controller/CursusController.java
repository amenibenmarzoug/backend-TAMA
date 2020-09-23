package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Cursus;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.service.CursusService;
import com.eniso.tama.service.ParticipantService;

@RestController
@ComponentScan(basePackageClasses = CursusService.class )
@RequestMapping(value="/api")
public class CursusController {
	
	private CursusService cursusService;
	@Autowired ParticipantService participantService;
	@Autowired
	public CursusController(CursusService cursusService) {
		this.cursusService = cursusService;
	} 
	
	


	@GetMapping("/cursus")
	public List<Cursus> findAll() {
		return cursusService.findAll();
	}
	@GetMapping("/cursusParticipant")
	public List<Cursus> findCursusPart(@RequestParam("id") long  id) {
		Participant theParticipant = participantService.findById(id);
		List<Cursus> cursusParticipants= new ArrayList<Cursus>();
		for(Cursus theC:cursusService.findAll()) {
			
   	  
				if(theParticipant.getCursus().getId() == theC.getId()) {
					System.out.println(id) ;
					cursusParticipants.add(theC);
					System.out.println(cursusParticipants.isEmpty()) ;
				}
				else {
					System.out.println(id) ;

				}
				
		
		}
		return cursusParticipants;
	}
	
	
	@GetMapping("cursus/{cursusId}")
	public Cursus getCursus(@PathVariable int  cursusId) {
		
		Cursus cursus = cursusService.findById(cursusId);
		
		if (cursus == null) {
			throw new RuntimeException("Cursus id not found - " + cursusId);
		}
		
		return cursus;
	}
	// add mapping for POST /cursus - add new control

	@PostMapping("/cursus")
	public  Cursus addCursus(@RequestBody Cursus cursus) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		cursusService.save(cursus);
		return cursus;
	}
	
	
	// add mapping for PUT /employees - update existing employee
	
		@PutMapping("/cursus")
		public Cursus updateCursus(@RequestBody Cursus cursus) {
			
			cursusService.save(cursus);
			
			return cursus;
		}

		@DeleteMapping("/cursus/{cursusId}")
		public String deleteCursus(@PathVariable int  cursusId) {
			
			Cursus cursus = cursusService.findById(cursusId);
			
			// throw exception if null
			
			if (cursus == null) {
				throw new RuntimeException("the cursus id is not found - " + cursusId);
			}
			
			cursusService.deleteById(cursusId);
			
			return "Deleted cursus id - " + cursusId;
		}

}
