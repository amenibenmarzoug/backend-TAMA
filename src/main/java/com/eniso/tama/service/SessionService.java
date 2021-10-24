package com.eniso.tama.service;

import java.util.Date;
import java.util.List;

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;

public interface SessionService {


	public List<Session> findAll();

    //public List<Session> findAllByCourseId(long id);

    public Session findById(long theId);
    public List<Session> findByTrainerId (long trainerId);
    public ProgramInstance getProgramInstance (long sessionId);
    public Session save(Session courseSession);

    public void deleteById(long id);
    List<Session> getAttendanceMarkedSessions();
}
