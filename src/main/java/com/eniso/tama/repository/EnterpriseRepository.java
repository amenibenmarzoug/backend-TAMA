package com.eniso.tama.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Entreprise;

public interface EnterpriseRepository extends JpaRepository<Entreprise, Long> {
	
	Optional<Entreprise> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<Entreprise> findByEnterpriseName(String entrepriseName);
	Boolean existsByEnterpriseName(String entrepriseName);
}


