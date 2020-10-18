package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Session;

import com.eniso.tama.repository.CourseSessionRepository;


@Service
@ComponentScan(basePackageClasses = CourseSessionRepository.class) 
public class CourseSessionServiceImpl implements CourseSessionService {
	private CourseSessionRepository courseSessionRepository;

	public CourseSessionServiceImpl() {
	}

	@Autowired
	public CourseSessionServiceImpl(CourseSessionRepository courseSessionRepository) {
		this.courseSessionRepository = courseSessionRepository;
	}

	@Override
	public List<Session> findAll() {
		return courseSessionRepository.findAll();
	}
	
	@Override
	public List<Session> findAllByCourseId(long id) {
		// TODO Auto-generated method stub
		return courseSessionRepository.findByCourseId(id);
	}

	@Override
	public Session findById(long theId) {
		Optional<Session> result = courseSessionRepository.findById(theId);

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
	public void save(Session courseSession) {
		courseSessionRepository.save(courseSession);
	}

	@Override
	public void deleteById(long theId) {
		courseSessionRepository.deleteById(theId);
	}


}
