package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Attendance;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Session;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>  {
	
	List<Attendance> findByParticipant(Participant participant);
	List<Attendance> findBySession(Session session);
	Boolean existsBySession (Session session ) ; 

	public List<Attendance> findByParticipantId(long participantId);

}
