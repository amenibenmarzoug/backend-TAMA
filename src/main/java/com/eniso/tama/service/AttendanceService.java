package com.eniso.tama.service;

import java.io.IOException;
import java.util.List;

import com.eniso.tama.entity.Attendance;

import net.sf.jasperreports.engine.JRException;

public interface AttendanceService {
public List<Attendance> findAll();
	
	public Attendance findById(long theId);
	
	public Attendance save(Attendance attendance);
	
	public void deleteById(long id);
	
	public void generateReport () throws JRException, IOException;

	
}
