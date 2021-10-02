package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Attendance;

public interface AttendanceService {
public List<Attendance> findAll();
	
	public Attendance findById(long theId);
	
	public Attendance save(Attendance attendance);
	
	public void deleteById(long id);

	
}
