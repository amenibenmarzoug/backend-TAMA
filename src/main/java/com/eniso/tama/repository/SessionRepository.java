package com.eniso.tama.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
	List<Session> findByTrainer(Trainer trainer);
	
	List<Session> findByThemeDetailInstanceId(long id);
	
	List<Session> findByClassRoomId(long id);
	

}
