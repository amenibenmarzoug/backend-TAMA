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
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.service.ParticipantService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = ParticipantService.class )
@RequestMapping(value="/api")

public class ParticipantController {
	


		private ParticipantService participantService;
		@Autowired
		public ParticipantController(ParticipantService theParticipantService) {
			participantService = theParticipantService;
		} 
		//public ControlController () {}
		


		@GetMapping("/participants")
		public List<Participant> findAll() {
			return participantService.findAll();
		}
		
		
		@GetMapping("participants/{participantId}")
		public Participant getParticipant(@PathVariable long participantId) {

			
			Participant theParticipant = participantService.findById(participantId);
			
			if (theParticipant == null) {
				throw new RuntimeException("Participant id not found - " + participantId);
			}
			
			return theParticipant;
		}

		//get participants by level 

		@GetMapping("participants/level/{participantLevel}")
		public List <Participant> getParticipant(@PathVariable String  participantLevel) {
			
			List<Participant> theParticipant = participantService.findByLevel(participantLevel);
			
			if (theParticipant == null) {
				throw new RuntimeException("Participant id not found - " + participantLevel);
			}
			
			return theParticipant;
		}
		
		//les participants du pilier1
		@GetMapping("participants/pilier1")
		public List <Participant> getParticipantPilier1() {
		 
			//List <Participant> theParticipant= participantService.findByEntreprise(participant);
			 List<Participant> theParticipant1= new ArrayList<Participant>();
			
			
			for(Participant theP:participantService.findAll()) {
				
				System.out.println(theP.getEntreprise()) ;
				 if  (theP.getEntreprise()!=null) {
       	  
				theParticipant1.add(theP) ;
				         } 
//
//				if (theParticipant1 == null) {
//					throw new RuntimeException("oops");
//				}
//				
		}
			return theParticipant1 ;

		}
		
		//les participants du pilier2
		@GetMapping("participants/pilier2")
		public List <Participant> getParticipantPilier2() {
		 
			//List <Participant> theParticipant= participantService.findByEntreprise(participant);
			 List<Participant> theParticipant1= new ArrayList<Participant>();
			
			
			for(Participant theP:participantService.findAll()) {
				
				//System.out.println(theP.getEntreprise()) ;
				 if  (theP.getEntreprise()==null) {
       	  
				theParticipant1.add(theP) ;
			
         } 

				if (theParticipant1 == null) {
					throw new RuntimeException("oops");
				}
			
			
			
		}
			return theParticipant1 ;
		}
		//get participants by abandon 
		@GetMapping("participants/isAbandon/{abandon}")
		public List <Participant> getParticipantByAbandon(@PathVariable boolean  abandon) {
			
			List<Participant> theParticipant = participantService.findByAbonadn(abandon);
			
			if (theParticipant == null) {
				throw new RuntimeException("Theres no abandon");
			}
			
			return theParticipant;
		}
		
		

		// add mapping for POST /participants - add new control

		@PostMapping("/participants")
		public  Participant addcontrol(@RequestBody Participant theParticipant) {
		
			
			// also just in case they pass an id in JSON ... set id to 0
			// this is to force a save of new item ... instead of update
			
			//stheControl.setId(0);
			
			participantService.save(theParticipant);
			return theParticipant;
		}
		
		
		// add mapping for PUT /employees - update existing employee
		
			@PutMapping("/participants")
			public Participant updateParticipant (@RequestBody Participant theParticipant) {
				
				participantService.save(theParticipant);
				
				return theParticipant;
			}

			@DeleteMapping("/participants/{participantId}")
			public String deleteParticipant(@PathVariable long  participantId) {

				
				Participant tempParticipant = participantService.findById(participantId);
				
				// throw exception if null
				
				if (tempParticipant == null) {
					throw new RuntimeException("the participant id is not found - " + participantId);
				}
				
				participantService.deleteById(participantId);
				
				return "Deleted participant id - " + participantId;
			}
			
	}


