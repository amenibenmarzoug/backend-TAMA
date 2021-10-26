package com.eniso.tama.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Session;
import net.sf.jasperreports.engine.JRException;

public interface AttendanceService {
public List<Attendance> findAll();
	
	public Attendance findById(long theId);
	
	public Attendance save(Attendance attendance);
	
	public Attendance createAttendance(Session session , Participant participant);
	
	
	public Attendance markPresent(Attendance attendance) ; 
	public Attendance markAbsent(Attendance attendance) ; 
	public Attendance markJustifiedAbsent(Attendance attendance) ; 
	
	public List<Attendance> findBySession(long sessionId);
	
	public Boolean existsBySession (long sessionId) ; 
	
	public void deleteById(long id);
	
	public File generateReport (long sessionId) throws JRException, IOException;
	
	public List<Attendance> findByParticipantId(long participantId);


	
}
