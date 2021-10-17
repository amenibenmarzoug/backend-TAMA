package com.eniso.tama.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.ProgramInstance;
@Repository
public interface ProgramInstanceRepository extends JpaRepository<ProgramInstance, Long> {
	

	@Query
	(value = "SELECT * FROM program_instance p WHERE p.location = :location and p.validated = :validated", nativeQuery = true)
	List<ProgramInstance> findProgramInstByLocationAndValidated(@Param("location") String location, @Param("validated") boolean validated);


}
