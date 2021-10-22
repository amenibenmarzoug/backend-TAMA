package com.eniso.tama.controller;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Session;

import com.eniso.tama.entity.AttendanceStates;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.service.AttendanceService;
import com.eniso.tama.service.ParticipantService;
import com.eniso.tama.service.ProgramInstanceService;
import com.eniso.tama.service.SessionService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan(basePackageClasses = AttendanceService.class)
@RequestMapping(value = "/api")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ProgramInstanceService programInstanceService;


    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendance")
    public List<Attendance> findAll() {
        return attendanceService.findAll();
    }

    @GetMapping("attendance/{attendanceId}")
    public Attendance getParticipantSession(@PathVariable long attendanceId) {

        Attendance attendance = attendanceService.findById(attendanceId);

        if (attendance == null) {
            throw new RuntimeException("attendance id not found - " + attendanceId);
        }

        return attendance;
    }

    @GetMapping("attendance/session/{sessionId}")
    public List<Attendance> getAttendancesBySession(@PathVariable long sessionId) {

        List<Attendance> attendances = attendanceService.findBySession(sessionId);
        if (attendances == null) {
            throw new RuntimeException("attendance id not found - " + sessionId);
        }
        return attendances;
    }
    
    //check whether the attendance of session is marked or not
    @GetMapping("attendanceMarked/{sessionId}")
    public Boolean attendanceMarked(@PathVariable long sessionId) {
        return attendanceService.existsBySession(sessionId);
    }
    
    

    // add mapping for POST /attendance - add

    @PostMapping("/addAttendance")
    public Attendance addAttendance(@RequestBody Attendance attendance) {

        System.out.println("attendance called");
        AttendanceStates present = AttendanceStates.PRESENT;

        attendance.setAttendanceState(present);
        attendanceService.save(attendance);
        return attendance;
    }
    
    @PostMapping("/attendance/createAttendance/{sessionId}")
    public Attendance createAttendance(@PathVariable long sessionId, @RequestBody Participant participant) {
    	Session session = sessionService.findById(sessionId);    	
        return attendanceService.createAttendance(session, participant);
    }


    // add mapping for POST /attendance - update
    
    @PutMapping("/attendance")
    public Attendance updateAttendance(@RequestBody Attendance attendance) {

        Attendance updatedAttendance = attendanceService.findById(attendance.getId());

        updatedAttendance.setParticipant(attendance.getParticipant());
        updatedAttendance.setSession(attendance.getSession());
        updatedAttendance.setAttendanceState(attendance.getAttendanceState());
        attendanceService.save(updatedAttendance);
        return updatedAttendance;
    }
    
    @PutMapping("attendance/markPresent")
	public Attendance markPresent(@RequestBody Attendance attendance) {
		return attendanceService.markPresent(attendance);
	}
    
    @PutMapping("attendance/markAbsent")
	public Attendance markAbsent(@RequestBody Attendance attendance) {
		return attendanceService.markAbsent(attendance);
	}
    
    @PutMapping("attendance/markNotifiedAbsent")
	public Attendance markNotifiedAbsent(@RequestBody Attendance attendance) {
		return attendanceService.markNotifiedAbsent(attendance);
	}
    
    
   
    
    
    

    @GetMapping("/attendance/generate")
    public void genereateReport() throws JRException, IOException {
        attendanceService.generateReport();
    }
    


}
