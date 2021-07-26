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

import com.eniso.tama.entity.Event;
import com.eniso.tama.service.EventService;


@RestController
@ComponentScan(basePackageClasses = EventService.class)
@RequestMapping(value = "/api")
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
    public Event getCourse(@PathVariable int eventId) {

        Event event = eventService.findById(eventId);

        if (event == null) {
            throw new RuntimeException("Event id not found - " + eventId);
        }

        return event;
    }
    // add mapping for POST /Event - add new Event

    @PostMapping("/event")
    public Event addEvent(@RequestBody Event event) {


        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        //stheControl.setId(0);

        eventService.save(event);
        return event;
    }


    // add mapping for PUT /event - update existing event

    @PutMapping("/event")
    public Event updateEvent(@RequestBody Event event) {
        Event updatedEvent = eventService.findById(event.getId());
        updatedEvent.setSession(event.getSession());
        updatedEvent.setEnd(event.getSession().getSessionEndDate());
        updatedEvent.setStart(event.getSession().getSessionBeginDate());
        updatedEvent.setTitle(event.getSession().getSessionName());

        eventService.save(updatedEvent);

        return updatedEvent;
    }

    @DeleteMapping("/event/{eventId}")
    public String deleteEvent(@PathVariable int eventId) {

        Event event = eventService.findById(eventId);

        // throw exception if null

        if (event == null) {
            throw new RuntimeException("the event id is not found - " + eventId);
        }

        eventService.deleteById(eventId);

        return "Deleted Event id - " + eventId;
    }

}
