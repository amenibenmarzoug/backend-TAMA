package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Event;
import com.eniso.tama.repository.EventRepository;


@Service
@ComponentScan(basePackageClasses = EventRepository.class )
public class EventServiceImpl implements EventService{

private EventRepository eventRepository;
	
	public EventServiceImpl() {}

	@Autowired
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Event findById(long theId) {
		Optional<Event> result = eventRepository.findById(theId);
		
		Event event = null;
		
		if (result.isPresent()) {
			event = result.get();
		}
		else {
			// we didn't find the event
			throw new RuntimeException("Did not find event id - " + theId);
		}
		
		return event;
	}

	@Override
	public void save(Event event) {
		eventRepository.save(event);
	}

	@Override
	public void deleteById(long    theId) {
		eventRepository.deleteById(theId);
	}

}
