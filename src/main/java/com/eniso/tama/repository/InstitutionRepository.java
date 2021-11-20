package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.eniso.tama.entity.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
	
	List<Institution> findAllByDeletedFalse();
	
	Optional<Institution> findByIdAndDeletedFalse(long id) ; 
	
    Optional<Institution> findByEmailAndDeletedFalse(String email);

    Boolean existsByEmailAndDeletedFalse(String email);

    Optional<Institution> findByInstitutionName(String institutionName);

    Boolean existsByInstitutionNameAndDeletedFalse(String institutionName);
}
