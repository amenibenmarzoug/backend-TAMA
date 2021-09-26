package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.SessionParticipant;

public interface SessionParticipantRepository  extends JpaRepository<SessionParticipant, Long> {
	

}
