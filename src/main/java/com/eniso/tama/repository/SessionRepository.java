package com.eniso.tama.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
	
	List<Session> findAllByDeletedFalse();
	
	Optional<Session> findByIdAndDeletedFalse(long id);
	
	List<Session> findByTrainerIdAndDeletedFalse(long id);
	
	List<Session> findByThemeDetailInstanceIdAndDeletedFalse(long id);
	
	List<Session> findByClassRoomIdAndDeletedFalse(long id);
	

}
