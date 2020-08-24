package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.User;

public interface ParticipanRepository extends JpaRepository<Participant, Long>{
	

}
