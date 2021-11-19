package com.eniso.tama.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Event;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.repository.SessionRepository;
import com.eniso.tama.repository.TrainerRepository;

@Service
@ComponentScan(basePackageClasses = SessionRepository.class)
public class SessionServiceImpl implements SessionService {
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private TrainerService trainerService;

	@Autowired
	EventService eventService;

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private TrainerRepository trainerRepository;

	public SessionServiceImpl() {
	}

	public SessionServiceImpl(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Override
	public List<Session> findAll() {
		return sessionRepository.findAllByDeletedFalse();
	}

	/*
	 * @Override public List<Session> findAllByCourseId(long id) { // TODO
	 * Auto-generated method stub return courseSessionRepository.findByCourseId(id);
	 * }
	 */

	@Override
	public Session findById(long theId) {
		Optional<Session> result = sessionRepository.findByIdAndDeletedFalse(theId);

		Session courseSession = null;

		if (result.isPresent()) {
			courseSession = result.get();
		} else {
			// we didn't find the courseSession
			throw new RuntimeException("Did not find courseSession id - " + theId);
		}

		return courseSession;
	}

	@Override
	public Session save(Session courseSession) {
		return sessionRepository.save(courseSession);
	}

	@Override
	public void deleteById(long theId) {
		sessionRepository.deleteById(theId);
	}

	@Override
	public List<Session> findByTrainerId(long trainerId) {

		/*
		 * if (result.isPresent()) { trainer=result.get();
		 * 
		 * } else { // we didn't find the trainer throw new
		 * RuntimeException("Did not find trainer with ID  - " + trainerId); }
		 */
		// TODO Auto-generated method stub
		return sessionRepository.findByTrainerIdAndDeletedFalse(trainerId);

	}

	public Set<ProgramInstance> findProgramInstByTrainer(long trainerId) {

		List<Session> listSession = findByTrainerId(trainerId);

		Set<ProgramInstance> programs = new HashSet<>();
		for (Session session : listSession) {

			ProgramInstance p1 = session.getThemeDetailInstance().getModuleInstance().getThemeInstance()
					.getProgramInstance();
			if (p1.isDeleted() == false) {
				programs.add(p1);
			}

		}

		return programs;
	}

	@Override
	public ProgramInstance getProgramInstance(long sessionId) {
		Optional<Session> result = sessionRepository.findByIdAndDeletedFalse(sessionId);
		Session session;
		ProgramInstance programInst;
		if (result.isPresent()) {
			session = result.get();
			return session.getThemeDetailInstance().getModuleInstance().getThemeInstance().getProgramInstance();

		} else {
			// we didn't find the trainer
			throw new RuntimeException("Did not find session with ID  - " + sessionId);
		}
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

	}

	@Override
	public List<Session> getAttendanceMarkedSessions() {
		List<Session> allSessions = this.findAll();
		List<Session> markedSessions = new ArrayList<Session>();
		for (Session session : allSessions) {

			if (attendanceService.existsBySession(session.getId())) {

				markedSessions.add(session);

			}
		}

		return markedSessions;

	}

	@Override
	@Transactional
	public void deleteSession(long id) {
		Event event = eventService.findBySessionId(id);
		List<Attendance> attendanceList = attendanceService.findBySession(id);
		if (attendanceList != null) {
			for (Attendance attendance : attendanceList) {
				attendanceService.deleteAttendance(attendance.getId());
			}
		}
		if (event != null) {
			eventService.deleteEvent(event.getId());
		}

		Session session = findById(id);
		session.setDeleted(true);
		save(session);

	}

	@Override
	public List<Session> findByThemeDetailInstanceId(long id) {
		// TODO Auto-generated method stub
		return sessionRepository.findByThemeDetailInstanceIdAndDeletedFalse(id);
	}

	@Override
	public List<Session> findByClassroomId(long classroomId) {
		// TODO Auto-generated method stub
		return sessionRepository.findByClassRoomIdAndDeletedFalse(classroomId);
	}

	@Override
	@Transactional
	public void omitSession(long id) {
		Event event = eventService.findBySessionId(id);
		List<Attendance> attendanceList = attendanceService.findBySession(id);
		if (attendanceList != null) {
			for (Attendance attendance : attendanceList) {
				attendanceService.deleteById(attendance.getId());
			}
		}
		if (event != null) {
			eventService.deleteById(event.getId());
		}

		deleteById(id);
	}

}
