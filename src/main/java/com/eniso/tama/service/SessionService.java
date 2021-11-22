package com.eniso.tama.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;

public interface SessionService {

	public List<Session> findAll();

	// public List<Session> findAllByCourseId(long id);

	public Session findById(long theId);

	List<Session> findByThemeDetailInstanceId(long id);

	public List<Session> findByTrainerId(long trainerId);

	public List<Session> findByClassroomId(long classroomId);

	public ProgramInstance getProgramInstance(long sessionId);

	public Session save(Session courseSession);

	public Set<ProgramInstance> findProgramInstByTrainer(long trainerId);

	public void deleteById(long id);

	public void deleteSession(long id);

	public void omitSession(long id);

	List<Session> getAttendanceMarkedSessions();
}
