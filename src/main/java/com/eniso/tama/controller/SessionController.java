package com.eniso.tama.controller;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Event;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.service.EventService;
import com.eniso.tama.service.SessionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ComponentScan(basePackageClasses = SessionService.class)
@RequestMapping(value = "/api")
public class SessionController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SessionService sessionService;


    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @GetMapping("/session")
    public List<Session> findAll() {
        return sessionService.findAll();
    }


    @GetMapping("session/{sessionId}")
    public Session getSession(@PathVariable long sessionId) {

        Session session = sessionService.findById(sessionId);

        if (session == null) {
            throw new RuntimeException("session id not found - " + sessionId);
        }

        return session;
    }

    @GetMapping("/session/themeDetail")
    public List<Session> findSessionByThemeDatail(@RequestParam("themeDetailId") long themeDetailId) {
        List<Session> listSession = sessionService.findAll();
        List<Session> result = new ArrayList<>();
        for (Session session : listSession) {
            if (session.getThemeDetailInstance().getId() == themeDetailId) {
                result.add(session);
            }
        }
        return result;
    }

    @GetMapping("/session/programInst")
    public List<Session> findSessionByProgramInst(@RequestParam("programId") long programId) {
        List<Session> listSession = sessionService.findAll();
        //System.out.println("by theme");
        List<Session> result = new ArrayList<>();
        for (Session session : listSession) {
            if (session.getThemeDetailInstance().getModuleInstance().getThemeInstance().getProgramInstance().getId() == programId) {
                result.add(session);
            }
        }
        return result;
    }
    
    //Get Sessions of the specified Trainer
    @GetMapping("/session/trainerId/{trainerId}")
    public List<Session> findSessionByTrainer (@PathVariable("trainerId") long trainerId){
    	List<Session> sessions = sessionService.findByTrainerId(trainerId);
    	

        if (sessions == null) {
            throw new RuntimeException("trainernot found with id - " + trainerId);
        }

    	return sessions ; 
    }
    //Get the ProgramInstance of a specified Session
    @GetMapping("/session/getClass/{sessionId}")
    public ProgramInstance getProgramInstance (@PathVariable("sessionId") long sessionId){
    	ProgramInstance programInst = sessionService.getProgramInstance(sessionId);
    	
    	//List<Session> sessions = sessionService.findByTrainerId(trainerId);
    	

        if (programInst == null) {
            throw new RuntimeException("cannot find ProgramInstance ");
        }

    	return programInst ; 
    }

    @GetMapping("/session/trainer")
    public List<Session> calculateAllSessionsDurationByTrainer(@RequestParam("trainerId") long trainerId) {
        List<Session> listSession = sessionService.findAll();
        List<Session> result = new ArrayList<>();
        long sum = 0;
        //we should verify when the calculation will happen (at the end of the training?)
        for (Session session : listSession) {
            if (session.getTrainer().getId() == trainerId) {
                Instant begin = session.getSessionBeginDate().toInstant();
                Instant end = session.getSessionEndDate().toInstant();
                Duration duration = Duration.between(end, begin);
                long diff = Math.abs(duration.toMinutes());
                sum += diff;
                System.out.println(sum);
                result.add(session);
            }
        }
        return result;
    }

    @GetMapping("/session/trainerAndProgram/")
    public List<Session> calculateAllSessionsDurationByTrainerAndProgram(@RequestParam("trainerId") long trainerId, @RequestParam("programId") long programId) {
        List<Session> listSession = sessionService.findAll();
        List<Session> result = new ArrayList<>();
        long sum = 0;
        //we should verify when the calculation will happen (at the end of the training?)
        for (Session session : listSession) {
            if ((session.getTrainer().getId() == trainerId) && (session.getThemeDetailInstance().getModuleInstance().getThemeInstance().getProgramInstance().getId() == programId)) {
                Instant begin = session.getSessionBeginDate().toInstant();
                Instant end = session.getSessionEndDate().toInstant();
                Duration duration = Duration.between(end, begin);
                long diff = Math.abs(duration.toMinutes());
                sum += diff;
                System.out.println(sum);
                result.add(session);
            }
        }
        return result;
    }


    @Transactional
    @PostMapping("/session")
    public Session addSession(@RequestBody Session session) {


        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update


        Session newSession = sessionService.save(session);
        Event event = new Event();
        event.setTitle(session.getSessionName());
        event.setStart(session.getSessionBeginDate());
        event.setEnd(session.getSessionEndDate());
        event.setSession(newSession);
        eventService.save(event);
        return session;
    }

    // add mapping for PUT /session - update existing session

    @Transactional
    @PutMapping("/session")
    public Session updateSession(@RequestBody Session session) {

        Session updatedSession = sessionService.findById(session.getId());
        Event event = eventService.findBySessionId(session.getId());
        event.setTitle(session.getSessionName());
        event.setStart(session.getSessionBeginDate());
        event.setEnd(session.getSessionEndDate());
        updatedSession.setSessionName(session.getSessionName());
        updatedSession.setSessionBeginDate(session.getSessionBeginDate());
        updatedSession.setSessionEndDate(session.getSessionEndDate());
        updatedSession.setClassRoom(session.getClassRoom());
        updatedSession.setTrainer(session.getTrainer());
        sessionService.save(updatedSession);
        eventService.save(event);
        return updatedSession;
    }


    @DeleteMapping("/session/{sessionId}")
    public String deleteSession(@PathVariable long sessionId) {

        Session session = sessionService.findById(sessionId);

        // throw exception if null

        if (session == null) {
            throw new RuntimeException("the session id is not found - " + sessionId);
        }

        sessionService.deleteById(sessionId);

        return "Deleted session id - " + sessionId;
    }
}