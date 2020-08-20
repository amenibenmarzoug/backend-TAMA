package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Course;
import com.eniso.tama.repository.CourseRepository;

@Service
@ComponentScan(basePackageClasses = CourseRepository.class )
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepository;
	
	public CourseServiceImpl() {}

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course findById(long theId) {
		Optional<Course> result = courseRepository.findById(theId);
		
		Course course = null;
		
		if (result.isPresent()) {
			course = result.get();
		}
		else {
			// we didn't find the course
			throw new RuntimeException("Did not find course id - " + theId);
		}
		
		return course;
	}

	@Override
	public void save(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void deleteById(long    theId) {
		courseRepository.deleteById(theId);
	}

}
