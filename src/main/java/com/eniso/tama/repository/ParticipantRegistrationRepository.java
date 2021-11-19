package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.CompanyRegistration;
import com.eniso.tama.entity.ParticipantRegistration;
import com.eniso.tama.entity.ProgramInstance;

@Repository
public interface ParticipantRegistrationRepository extends JpaRepository<ParticipantRegistration, Long> {

	List<ParticipantRegistration> findAllByDeletedFalse();

	Optional<ParticipantRegistration>  findByIdAndDeletedFalse(long id);

	//@Query("SELECT cr FROM ParticipantRegistration cr WHERE cr.participant.id =?1")
	List<ParticipantRegistration> findByParticipantIdAndDeletedFalse(long partId);

	//@Query("SELECT cr FROM ParticipantRegistration cr WHERE cr.programinstance.id =?1")
	List<ParticipantRegistration> findByPrograminstanceIdAndDeletedFalse(long programInstance);

	// List<ProgramInstance> findProgramInstanceByParticipantId(long id);

}
