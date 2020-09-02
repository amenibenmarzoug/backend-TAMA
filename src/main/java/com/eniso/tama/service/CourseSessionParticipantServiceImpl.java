package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.CourseSessionParticipant;
import com.eniso.tama.repository.CourseSessionParticipantRepository;


@Service
@ComponentScan(basePackageClasses = CourseSessionParticipantRepository.class) 
public class CourseSessionParticipantServiceImpl implements CourseSessionParticipantService{

	private CourseSessionParticipantRepository courseSessionParticipantRepository;

	public CourseSessionParticipantServiceImpl() {
	}

	@Autowired
	public CourseSessionParticipantServiceImpl(CourseSessionParticipantRepository courseSessionParticipantRepository) {
		this.courseSessionParticipantRepository = courseSessionParticipantRepository;
	}

	@Override
	public List<CourseSessionParticipant> findAll() {
		return courseSessionParticipantRepository.findAll();
	}

	@Override
	public CourseSessionParticipant findById(long theId) {
		Optional<CourseSessionParticipant> result = courseSessionParticipantRepository.findById(theId);

		CourseSessionParticipant courseSessionParticipant = null;

		if (result.isPresent()) {
			courseSessionParticipant = result.get();
		} else {
			// we didn't find the courseSessionParticipant
			throw new RuntimeException("Did not find courseSessionParticipant id - " + theId);
		}

		return courseSessionParticipant;
	}

	@Override
	public void save(CourseSessionParticipant courseSessionParticipant) {
		courseSessionParticipantRepository.save(courseSessionParticipant);
	}

	@Override
	public void deleteById(long theId) {
		courseSessionParticipantRepository.deleteById(theId);
	}
}
