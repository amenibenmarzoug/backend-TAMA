package com.eniso.tama.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import com.eniso.tama.entity.Session;
import com.eniso.tama.entity.Trainer;

public interface SessionRepository extends JpaRepository<Session, Long> {
	
	List<Session> findBySessionBeginDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date sessionBeginDate);
	List<Session> findBySessionBeginDateBetween(Date dateStart, Date dateEnd);

	List<Session> findByTrainer(Trainer trainer);

}
