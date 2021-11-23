package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

import com.eniso.tama.entity.Event;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.EventService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ComponentScan(basePackageClasses = EventService.class )
@RequestMapping(value="/api")
public class EventController {
	@Autowired
	private EventService eventService;
	
	public EventController(EventService eventService) {
		this.eventService = eventService;
	} 


	@GetMapping("/event")
	public List<Event> findAll() {
		return eventService.findAll();
	}
	
	@GetMapping("event/{eventId}")
	public Event getCourse(@PathVariable int  eventId) {
		
		Event event = eventService.findById(eventId);
		
		if (event == null) {
			throw new RuntimeException("Event id not found - " + eventId);
		}
		
		return event;
	}
	// add mapping for POST /Event - add new Event

	@PostMapping("/event")
	public  Event addEvent(@RequestBody Event event) {
	
		
		//stheControl.setId(0);
		
		eventService.save(event);
		return event;
	}
	
	
	// add mapping for PUT /event - update existing event
	
		@PutMapping("/event")
		public Event updateEvent(@RequestBody Event event) {
			Event updatedEvent=eventService.updateEvent(event);
			
			return updatedEvent;
		}

		@DeleteMapping("/event/{eventId}")
		public ResponseEntity<?> deleteEvent(@PathVariable int  eventId) {
			
			Event event = eventService.findById(eventId);
			
			// throw exception if null
			
			if (event == null) {
				throw new RuntimeException("the event id is not found - " + eventId);
			}
			
			eventService.deleteEvent(eventId);
			
			return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
		}
		
		
		@DeleteMapping("/event/omit/{eventId}")
		public ResponseEntity<?> omitEvent(@PathVariable int  eventId) {
			
			Event event = eventService.findById(eventId);
			
			// throw exception if null
			
			if (event == null) {
				throw new RuntimeException("the event id is not found - " + eventId);
			}
			
			eventService.deleteById(eventId);
			
			return ResponseEntity.ok(new MessageResponse("Suppression faite avec succès!"));
		}
		
		@PostMapping("/event/freeDay")
		public  Event addFreeDay(@RequestBody Event event) {
		
			
			//stheControl.setId(0);
			
			eventService.addFreeDay(event);
			return event;
		}

}