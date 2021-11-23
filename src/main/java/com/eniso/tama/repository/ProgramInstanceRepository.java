package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.ProgramInstance;

@Repository
public interface ProgramInstanceRepository extends JpaRepository<ProgramInstance, Long> {

	List<ProgramInstance> findByValidatedTrue();

	@Query(value = "SELECT * FROM program_instance p WHERE p.location = :location and p.validated = :validated and p.deleted = FALSE", nativeQuery = true)
	List<ProgramInstance> findProgramInstByLocationAndValidated(@Param("location") String location,
			@Param("validated") boolean validated);

	List<ProgramInstance> findAllByDeletedFalse();
	
	List<ProgramInstance> findAllByPrivateProgramInstanceTrue();
	
	List<ProgramInstance> findAllByPrivateProgramInstanceFalse();
	
	List<ProgramInstance> findByProgramInstNameContaining(String name);

	Optional<ProgramInstance> findByIdAndDeletedFalse(long id);
}
