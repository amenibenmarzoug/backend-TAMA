package com.eniso.tama.controller;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.AttendanceStates;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.service.AttendanceService;
import com.eniso.tama.service.EntrepriseService;
import com.eniso.tama.service.ParticipantService;
import com.eniso.tama.service.ProgramInstanceService;
import com.eniso.tama.service.SessionService;
import com.eniso.tama.service.TrainerService;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import javax.transaction.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    
    @Autowired
    private TrainerService trainerService;
    
    @Autowired
    private EntrepriseService companyService ; 


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
    
    @GetMapping("attendance/company/{companyId}")
    public List<Attendance> getAttendancesByCompany(@PathVariable long companyId) {
        Entreprise company = companyService.findById(companyId);
		if (company == null) {
			throw new RuntimeException("error : no company with id" + companyId );
		}
		
        return attendanceService.findByCompany(company);
    }

    
    @GetMapping("attendance/participant/{participantId}")
    public List<Attendance> getAttendancesByParticipant(@PathVariable long participantId) {

        List<Attendance> attendances = attendanceService.findByParticipantId(participantId);
        if (attendances == null) {
            throw new RuntimeException("attendance id not found - " + participantId);
        }
        return attendances;
    }
    
    @GetMapping("attendance/participant/trainer")
    public List<Attendance>  findByParticipantIdAndTrainerId(@RequestParam long participantId, @RequestParam long trainerId){
    	return attendanceService.findByParticipantIdAndTrainerId(participantId, trainerId);
    }
    
    @GetMapping("attendance/participant/trainer/presence")
    public int  getPresenceByParticipantIdAndTrainerId(@RequestParam long participantId, @RequestParam long trainerId){
    	return attendanceService.getPresencesNumberByParticipantAndTrainer(participantId, trainerId);
    }
    
    @GetMapping("attendance/participant/trainer/absence")
    public int  getAbsenceByParticipantIdAndTrainerId(@RequestParam long participantId, @RequestParam long trainerId){
    	return attendanceService.getAbsencesNumberByParticipantAndTrainer(participantId, trainerId);
    }
    
    @GetMapping("attendance/participant/trainer/justified")
    public int  getJustifiedAbsenceByParticipantIdAndTrainerId(@RequestParam long participantId, @RequestParam long trainerId){
    	return attendanceService.getJustifiedAbsencesNumberByParticipantAndTrainer(participantId, trainerId);
    }
    
    //check whether the attendance of session is marked or not
    @GetMapping("attendanceMarked/{sessionId}")
    public Boolean attendanceMarked(@PathVariable long sessionId) {
        return attendanceService.existsBySession(sessionId);
    }
    
    @GetMapping("attendance/trainer/{trainerId}")
    public List<Attendance> getAttendancesByTrainer(@PathVariable long trainerId) {
        Trainer trainer = trainerService.findById(trainerId);
		if (trainer == null) {
			throw new RuntimeException("error : no trainer with id" + trainerId );
		}
		
        return attendanceService.findByTrainer(trainer);
    }
    
    @GetMapping("attendance/presencesNumber/{participantId}")
    public int getPresencesNumber(@PathVariable long participantId) {
        Participant participant = participantService.findById(participantId);
		if (participant == null) {
			throw new RuntimeException("error : no participant with id" + participantId );
		}
		
        return attendanceService.getPresencesNumber(participant);
    }
    
    @GetMapping("attendance/absencesNumber/{participantId}")
    public int getAbsencesNumber(@PathVariable long participantId) {
        Participant participant = participantService.findById(participantId);
		if (participant == null) {
			throw new RuntimeException("error : no participant with id" + participantId );
		}
		
        return attendanceService.getAbsencesNumber(participant);
    }
    @GetMapping("attendance/justifiedAbsencesNumber/{participantId}")
    public int getJustifiedAbsencesNumber(@PathVariable long participantId) {
        Participant participant = participantService.findById(participantId);
		if (participant == null) {
			throw new RuntimeException("error : no participant with id" + participantId );
		}
		
        return attendanceService.getJustifiedAbsencesNumber(participant);
    }
    

    // add mapping for POST /attendance - add

    @PostMapping("/addAttendance")
    public Attendance addAttendance(@RequestBody Attendance attendance) {

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
    
    @PutMapping("attendance/markJustifiedAbsent")
	public Attendance markNotifiedAbsent(@RequestBody Attendance attendance) {
		return attendanceService.markJustifiedAbsent(attendance);
	}
    
    
   
    
    
    

    @GetMapping("/attendance/generateReport/{sessionId}")
    public ResponseEntity<Resource> genereateReport(@PathVariable long sessionId) throws JRException, IOException {
    	File file=attendanceService.generateReport(sessionId);
    	Resource resource=null;
    	resource=new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().contentLength(file.length())
        		.contentType(MediaType.APPLICATION_OCTET_STREAM)
        		 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
        		.body(resource);
    }
    


}
