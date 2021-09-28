package com.eniso.tama.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.AttendanceStates;
import com.eniso.tama.entity.Event;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Session;
import com.eniso.tama.service.AttendanceService;
import com.eniso.tama.service.ParticipantService;
import com.eniso.tama.service.SessionService;

@RestController
@ComponentScan(basePackageClasses = AttendanceService.class )
@RequestMapping(value="/api")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	private ParticipantService participantService ; 
	private SessionService sessionService; 
	
	
	
	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	} 
	
	@GetMapping("/attendance")
	public List<Attendance> findAll() {
		return attendanceService.findAll();
	}
	
	@GetMapping("attendance/{attendanceId}")
	public Attendance getParticipantSession(@PathVariable long  attendanceId) {
		
		Attendance attendance = attendanceService.findById(attendanceId);
		
		if (attendance == null) {
			throw new RuntimeException("attendance id not found - " + attendanceId);
		}
		
		return attendance;
	}
	
	// add mapping for POST /attendance - add
	
	@PostMapping("/addAttendance")
	public  Attendance addAttendance(@RequestBody Attendance attendance) {
	
		System.out.println("attendance called");
		AttendanceStates present=AttendanceStates.PRESENT;
		
		attendance.setAttendanceState(present);
		attendanceService.save(attendance);
		return attendance;
	}
	
	
	// add mapping for POST /attendance - update
	@Transactional
	@PutMapping("/attendance")
	public Attendance updateAttendance(@RequestBody Attendance attendance) {
		
		Attendance updatedAttendance=attendanceService.findById(attendance.getId());

		updatedAttendance.setParticipant(attendance.getParticipant());
		updatedAttendance.setSession(attendance.getSession());
		updatedAttendance.setAttendanceState(attendance.getAttendanceState());

		attendanceService.save(updatedAttendance);
		return updatedAttendance;
	}

}
