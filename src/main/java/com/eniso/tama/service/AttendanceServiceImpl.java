package com.eniso.tama.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.AttendanceStates;
import com.eniso.tama.entity.Event;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Status;
import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.AttendanceStates;

import com.eniso.tama.repository.AttendanceRepository;
import com.eniso.tama.repository.SessionRepository;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@ComponentScan(basePackageClasses = AttendanceRepository.class)
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public AttendanceServiceImpl() {
	};

	public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
		this.attendanceRepository = attendanceRepository;
	}

	@Override
	public List<Attendance> findAll() {
		return attendanceRepository.findAll();
	}

	@Override
	public Attendance findById(long theId) {
		Optional<Attendance> result = attendanceRepository.findById(theId);
		Attendance attendance = null;
		if (result.isPresent()) {
			attendance = result.get();
		} else {
			// case the attendance is not found
			throw new RuntimeException("Did not find Attendance id - " + theId);
		}
		return attendance;
	}
	
	

	@Override
	public Attendance save(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}
	
	
	 //create attendance (Session, Participant) - Exceptions not treated : check participant existing in that session
	@Override 
	public Attendance createAttendance(Session session , Participant participant){
		
		AttendanceStates present = AttendanceStates.PRESENT;
		Attendance newAttendance = new Attendance();
	    newAttendance.setSession(session);
	    newAttendance.setParticipant(participant);
	    newAttendance.setAttendanceState(present);
	    
		return attendanceRepository.save(newAttendance);
	}

	@Override
	public void deleteById(long id) {
		attendanceRepository.deleteById(id);
	}

	@Override
	public File generateReport(long sessionId) throws JRException, IOException {

		String fileName = "src/main/resources/reports/attendance.jrxml";
		File fileToSend=null;
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		List<Session> sessionList = new ArrayList<Session>();
		Session session = null;
		for (Attendance attendance : findAll()) {
			if (attendance.getSession().getId() == sessionId) {
				session = attendance.getSession();
				attendanceList.add(attendance);

			}
		}

		Map<String, Object> parameter = new HashMap<String, Object>();

		JRBeanCollectionDataSource listCollectionTable1 = new JRBeanCollectionDataSource(attendanceList);
		if (session != null) {
			parameter.put("nomF", session.getTrainer().getLastName());
			parameter.put("prenomF", session.getTrainer().getFirstName());
			parameter.put("program", session.getThemeDetailInstance().getModuleInstance().getThemeInstance()
					.getProgramInstance().getProgramInstName());
			;
			parameter.put("themeDet", session.getThemeDetailInstance().getThemeDetailInstName());
			parameter.put("session", session.getSessionName());
			parameter.put("date", session.getSessionBeginDate());
			parameter.put("table1", listCollectionTable1);
			parameter.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);
			if (session.getClassRoom() != null) {
				parameter.put("place", session.getClassRoom().getClassRoomName());
			} else {
				if (session.getThemeDetailInstance().getModuleInstance().getThemeInstance().getProgramInstance()
						.getPlace() != null) {
					JSONObject obj = null;
					try {
						System.out.println(session.getThemeDetailInstance().getModuleInstance().getThemeInstance()
								.getProgramInstance().getPlace());
						obj = new JSONObject(session.getThemeDetailInstance().getModuleInstance().getThemeInstance()
								.getProgramInstance().getPlace());
						if (obj != null) {

							parameter.put("place", obj.getString("name"));
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter, new JREmptyDataSource());

			File file = new File(
					"src/main/resources/reports/" + session.getThemeDetailInstance().getThemeDetailInstName() + "_"
							+ session.getSessionName() + "-liste_presence.pdf");
			FileOutputStream outputSteam = null;
			
			try {
				outputSteam = new FileOutputStream(file);
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);
				System.out.println("Report Generated!");

				fileToSend=new File(file.getAbsolutePath());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				outputSteam.close();
			}

		}
		return fileToSend;

	}

	@Override
	public List<Attendance> findBySession(long sessionId) {
		Optional<Session> result = sessionRepository.findById(sessionId);
		Session session;
		if (result.isPresent()) {
			session = result.get();

		} else {
			// we didn't find the trainer
			throw new RuntimeException("Did not find session with ID  - " + sessionId);
		}
		// TODO Auto-generated method stub
		return attendanceRepository.findBySession(session);
	}
	
	@Override
	public Boolean existsBySession(long sessionId) {
		Optional<Session> result=sessionRepository.findById(sessionId);
		Session session ; 
		if (result.isPresent()) {
			 session=result.get();

		} else {
			// we didn't find the trainer
			throw new RuntimeException("Did not find session with ID  - " + sessionId);
		}
		// TODO Auto-generated method stub
		return attendanceRepository.existsBySession(session);
	}
	
	@Override
	public Attendance markPresent(Attendance attendance) {
		if(attendance!=null) {
			attendance.setAttendanceState(AttendanceStates.PRESENT);
		}
		return attendanceRepository.save(attendance);
	}
	
	@Override
	public Attendance markAbsent(Attendance attendance) {
		if(attendance!=null) {
			attendance.setAttendanceState(AttendanceStates.ABSENT);
		}
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance markJustifiedAbsent(Attendance attendance) {
		if(attendance!=null) {
			attendance.setAttendanceState(AttendanceStates.JUSTIFIEDABSENT);
		}
		return attendanceRepository.save(attendance);
	}
	
	

	

}
