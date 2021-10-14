package com.eniso.tama.repository;


import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<Participant> findByFirstNameP(String firstNameP);

    Boolean existsByFirstNameP(String firstNameP);

    //List<Participant> findByProgramInstance(ProgramInstance  programInst);
    List<Participant> findByLevel(String level);

    //List <Participant> findByEntreprise(Participant theParticipant);
    List<Participant> findByAbandon(boolean abandon);
    @Query
	(value = "SELECT user_id FROM participant p WHERE p.program_instance_program_instance_id = :programInstId and p.status = 'ACCEPTED'", nativeQuery = true)
	List<Long> findConfirmedParticipantsByClass(@Param("programInstId") long programInstId);


}
