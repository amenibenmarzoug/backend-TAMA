package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Course;

public interface CourseService {


	public List<Course> findAll();
	
	public Course findById(long theId);
	
	public void save(Course course);
	
	public void deleteById(long id);
	
}
