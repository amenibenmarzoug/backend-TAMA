package com.eniso.tama.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
	Optional<Institution> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<Institution> findByInstitutionName(String institutionName);
	Boolean existsByInstitutionName(String institutionName);
}
