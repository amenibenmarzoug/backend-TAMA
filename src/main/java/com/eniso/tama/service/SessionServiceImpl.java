package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Session;

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


}
