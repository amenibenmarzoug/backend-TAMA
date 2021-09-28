package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Attendance;
import com.eniso.tama.repository.AttendanceRepository;

@Service
@ComponentScan(basePackageClasses = AttendanceRepository.class) 
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public AttendanceServiceImpl() {} ; 
	
	public AttendanceServiceImpl (AttendanceRepository attendanceRepository) {
		this.attendanceRepository=attendanceRepository;
	}
	@Override
	public List<Attendance> findAll() {
		return attendanceRepository.findAll();
	}
	
	
	@Override
	public Attendance findById(long theId) {
		Optional <Attendance> result=attendanceRepository.findById(theId);
		Attendance attendance= null;
		if(result.isPresent()) {
			attendance=result.get();
		}
		else {
			//case the attendance is not found 
			throw new RuntimeException("Did not find Attendance id - " + theId);
		}
		return attendance; 
	}

	@Override
	public Attendance save(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	@Override
	public void deleteById(long id) {
		attendanceRepository.deleteById(id);
	}
	 
	 

	

}
