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

import com.eniso.tama.dto.AttendanceDTO;
import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.AttendanceStates;
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Event;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Status;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.AttendanceStates;

import com.eniso.tama.repository.AttendanceRepository;
import com.eniso.tama.repository.ParticipantRepository;
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

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private ParticipantRepository participantRepository;

	public AttendanceServiceImpl() {
	};

	public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
		this.attendanceRepository = attendanceRepository;
	}

	@Override
	public List<Attendance> findAll() {
		return attendanceRepository.findAllByDeletedFalse();
	}

	@Override
	public Attendance findById(long theId) {
		Optional<Attendance> result = attendanceRepository.findByIdAndDeletedFalse(theId);
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

	// create attendance (Session, Participant) - Exceptions not treated : check
	// participant existing in that session
	@Override
	public Attendance createAttendance(Session session, Participant participant) {

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
		List<AttendanceDTO> attendanceList = new ArrayList<AttendanceDTO>();
		List<Session> sessionList = new ArrayList<Session>();
		Session session = null;
		for (Attendance attendance : findAll()) {
			if (attendance.getSession().getId() == sessionId) {
				session = attendance.getSession();
				AttendanceDTO attendanceDTO=new AttendanceDTO();
				attendanceDTO.setParticipant(attendance.getParticipant());
				attendanceDTO.setSession(attendance.getSession());
				switch (attendance.getAttendanceState()) {
				case ABSENT:
					if(attendance.getParticipant().getGender().equals("Masculin")) {
						attendanceDTO.setAttendanceState("absent");
					}
					else {
						attendanceDTO.setAttendanceState("absente");

					}
					break;

				case PRESENT:
					if(attendance.getParticipant().getGender().equals("Masculin")) {
						attendanceDTO.setAttendanceState("présent");
					}
					else {
						attendanceDTO.setAttendanceState("présente");

					}
					break;

				case JUSTIFIEDABSENT:
					if(attendance.getParticipant().getGender().equals("Masculin")) {
						attendanceDTO.setAttendanceState("absent justifié");
					}
					else {
						attendanceDTO.setAttendanceState("absente justifiée");

					}
					break;

				default:
					break;
				}
				
				attendanceList.add(attendanceDTO);

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
		Optional<Session> result = sessionRepository.findByIdAndDeletedFalse(sessionId);
		Session session;
		if (result.isPresent()) {
			session = result.get();
			return attendanceRepository.findBySessionAndDeletedFalse(session);

		} else {
			// we didn't find the trainer
			throw new RuntimeException("Did not find session with ID  - " + sessionId);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean existsBySession(long sessionId) {
		Optional<Session> result = sessionRepository.findByIdAndDeletedFalse(sessionId);
		Session session;
		if (result.isPresent()) {
			session = result.get();
			return attendanceRepository.existsBySessionAndDeletedFalse(session);

		} else {
			// we didn't find the trainer
			throw new RuntimeException("Did not find session with ID  - " + sessionId);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public Attendance markPresent(Attendance attendance) {
		if (attendance != null) {
			attendance.setAttendanceState(AttendanceStates.PRESENT);
		}
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance markAbsent(Attendance attendance) {
		if (attendance != null) {
			attendance.setAttendanceState(AttendanceStates.ABSENT);
		}
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance markJustifiedAbsent(Attendance attendance) {
		if (attendance != null) {
			attendance.setAttendanceState(AttendanceStates.JUSTIFIEDABSENT);
		}
		return attendanceRepository.save(attendance);
	}

	@Override
	public List<Attendance> findByCompany(Entreprise company) {
		List<Attendance> companyParticipantsAttendances= new ArrayList<Attendance>() ; 
		List <Participant> participantsOfCompany = participantRepository.findByEntrepriseAndValidatedTrueAndDeletedFalse(company); 
		for (Participant theParticipant : participantsOfCompany) {
			List<Attendance> theParticipantAttendances = attendanceRepository.findByParticipantAndDeletedFalse(theParticipant);
			if (theParticipantAttendances != null) {
				companyParticipantsAttendances.addAll(theParticipantAttendances);
			}
		}
		 		return companyParticipantsAttendances;
	}

	@Override
	public List<Attendance> findByTrainer(Trainer trainer) {
		List<Attendance> trainerAttendances= new ArrayList<Attendance>() ; 
		List<Attendance> allAttendances= findAll() ; 
		
		for (Attendance attendance : allAttendances) {
			if (attendance.getSession().getTrainer() == trainer) {
				trainerAttendances.add(attendance);
			}
		}
		
		return trainerAttendances;
	}


	@Override
	public List<Attendance> findByParticipantId(long participantId) {
		return attendanceRepository.findByParticipantIdAndDeletedFalse(participantId);
	}

	@Override
	public int getPresencesNumber(Participant participant) {
		
		List<Attendance> attendances = findByParticipantId(participant.getId());
		int presencesNumber =0 ; 
		for (Attendance attendance : attendances) {
			if (attendance.getAttendanceState() == AttendanceStates.PRESENT) {
				presencesNumber++;
			}
		}
		
		return presencesNumber;
	}

	@Override
	public int getAbsencesNumber(Participant participant) {
		List<Attendance> attendances = findByParticipantId(participant.getId());
		int absencesNumber =0 ; 
		for (Attendance attendance : attendances) {
			if (attendance.getAttendanceState() == AttendanceStates.ABSENT) {
				absencesNumber++;
			}
		}
		
		return absencesNumber;
	}

	@Override
	public int getJustifiedAbsencesNumber(Participant participant) {
		List<Attendance> attendances = findByParticipantId(participant.getId());
		int justifiedAbsencesNumber =0 ; 
		for (Attendance attendance : attendances) {
			if (attendance.getAttendanceState() == AttendanceStates.JUSTIFIEDABSENT) {
				justifiedAbsencesNumber++;
			}
		}
		
		return justifiedAbsencesNumber;
	}

	@Override
	public List<Attendance> findByParticipantIdAndTrainerId(long participantId, long trainerId) {
		List<Session> sessions=sessionService.findByTrainerId(trainerId);
		List<Attendance> attendanceList=new ArrayList<>();
		for (Attendance attendance : findByParticipantId(participantId)) {
			if(sessions.contains(attendance.getSession())) {
				attendanceList.add(attendance);
			}
		}
		return attendanceList;
	}

	@Override
	public int getPresencesNumberByParticipantAndTrainer(long participantId, long trainerId) {
		List<Attendance> attendanceList=new ArrayList<>();
		for (Attendance attendance : findByParticipantIdAndTrainerId( participantId,  trainerId)) {
			if(attendance.getAttendanceState()==AttendanceStates.PRESENT) {
				attendanceList.add(attendance);
			}
		}
		return attendanceList.size();
	}

	@Override
	public int getAbsencesNumberByParticipantAndTrainer(long participantId, long trainerId) {
		List<Attendance> attendanceList=new ArrayList<>();
		for (Attendance attendance : findByParticipantIdAndTrainerId( participantId,  trainerId)) {
			if(attendance.getAttendanceState()==AttendanceStates.ABSENT) {
				attendanceList.add(attendance);
			}
		}
		return attendanceList.size();
	}

	@Override
	public int getJustifiedAbsencesNumberByParticipantAndTrainer(long participantId, long trainerId) {
		List<Attendance> attendanceList=new ArrayList<>();
		for (Attendance attendance : findByParticipantIdAndTrainerId( participantId,  trainerId)) {
			if(attendance.getAttendanceState()==AttendanceStates.JUSTIFIEDABSENT) {
				attendanceList.add(attendance);
			}
		}
		return attendanceList.size();
	}

	@Override
	public void deleteAttendance(long id) {
		
		Attendance attendance = this.findById(id);
		attendance.setDeleted(true);
		attendanceRepository.save(attendance);
		
	}

}
