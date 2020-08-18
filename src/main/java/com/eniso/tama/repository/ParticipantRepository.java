package com.eniso.tama.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Participant;


public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	Optional<Participant> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<Participant> findByFirstNameP(String firstNameP);
	Boolean existsByFirstNameP(String firstNameP);
}
