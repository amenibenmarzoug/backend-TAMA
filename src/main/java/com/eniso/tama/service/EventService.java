package com.eniso.tama.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.eniso.tama.entity.Event;

public interface EventService {
	public List<Event> findAll();
	
	public Event findById(long theId);
	
	public Event findBySessionId(long id);
	
	public void save(Event event);
	
	public void deleteById(long id);
	
	public Event updateEvent(Event event);
	
	public Event addFreeDay(Event event);
}
