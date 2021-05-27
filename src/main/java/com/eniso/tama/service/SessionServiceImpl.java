package com.eniso.tama.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;
import com.eniso.tama.repository.SessionRepository;


@Service
@ComponentScan(basePackageClasses = SessionRepository.class) 
public class SessionServiceImpl implements SessionService {
	@Autowired
	private SessionRepository sessionRepository;

	public SessionServiceImpl() {
	}

	
	public SessionServiceImpl(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Override
	public List<Session> findAll() {
		return sessionRepository.findAll();
	}
	
/*	@Override
	public List<Session> findAllByCourseId(long id) {
		// TODO Auto-generated method stub
		return courseSessionRepository.findByCourseId(id);
	}*/

	@Override
	public Session findById(long theId) {
		Optional<Session> result = sessionRepository.findById(theId);

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
	public List<Session> findBySessionBeginDate(Date sessionBeginDate){
		System.out.println(sessionBeginDate); 
		System.out.println(sessionRepository.findBySessionBeginDate(sessionBeginDate));
		try {
			System.out.println(sessionRepository.findBySessionBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-21")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionRepository.findBySessionBeginDate(sessionBeginDate);
	}
	@Override
	public List<Session> findBySessionBeginDateBetween(Date dateStart, Date dateEnd){
		return sessionRepository.findBySessionBeginDateBetween(dateStart, dateEnd);
	}


	@Override
	public List<Session> findByTrainer(Trainer trainer) {

		return sessionRepository.findByTrainer(trainer);
	}
	@Override
	public ProgramInstance findProgramInstance(long sessionId  ) {
		ProgramInstance programInstance; 
		Session session = findById(sessionId);
		programInstance=session.getThemeDetailInstance().getModuleInstance().getThemeInstance().getProgramInstance(); 

		return programInstance;
	}


}
