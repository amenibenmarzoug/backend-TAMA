package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.CourseSessionParticipant;

public interface CourseSessionParticipantService {

	public List<CourseSessionParticipant> findAll();
	
	public CourseSessionParticipant findById(long theId);
	
	public void save(CourseSessionParticipant courseSessionParticipant);
	
	public void deleteById(long id);
}
