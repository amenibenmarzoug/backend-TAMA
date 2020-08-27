package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.CourseSession;

public interface CourseSessionService {

	public List<CourseSession> findAll();
	
	public List<CourseSession> findAllByCourseId(long id);
	
	public CourseSession findById(long theId);
	
	public void save(CourseSession courseSession);
	
	public void deleteById(long id);
}
