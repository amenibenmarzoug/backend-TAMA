package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	Optional<Participant> findByIdAndDeletedFalse(long id);

	List<Participant> findAllByDeletedFalse();

	Optional<Participant> findByEmailAndDeletedFalse(String email);

	Boolean existsByEmail(String email);

	Optional<Participant> findByFirstNamePAndDeletedFalse(String firstNameP);

	Boolean existsByFirstNamePAndDeletedFalse(String firstNameP);

	// List<Participant> findByProgramInstance(ProgramInstance programInst);
	List<Participant> findByLevelAndDeletedFalse(String level);

	List<Participant> findByValidatedTrueAndDeletedFalse();

	List<Participant> findByEntrepriseAndValidatedTrueAndDeletedFalse(Entreprise company);

	// List <Participant> findByEntreprise(Participant theParticipant);<<<<<<< HEAD

	List<Participant> findByAbandonAndDeletedFalse(boolean abandon);
//    @Query
//	(value = "SELECT user_id FROM participant p WHERE p.program_instance_program_instance_id = :programInstId and p.status = 'ACCEPTED'", nativeQuery = true)
//	List<Long> findConfirmedParticipantsByClassAndDeletedFalse( long programInstId);

	@Query(value = ("select sum(case when p.gender = 'Masculin' then 1 else 0 end) * 100 / count(*) from participant p;"), nativeQuery = true)
	float getMaleParticipants();

}
