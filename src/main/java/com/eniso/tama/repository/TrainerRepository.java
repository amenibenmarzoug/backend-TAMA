package com.eniso.tama.repository;




import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Trainer;



@Repository

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
	List<Trainer> findByEmail(String email);
	Boolean existsByEmail(String email);
	List<Trainer> findByFirstName(String firstName);

	Boolean existsByFirstName(String firstName);
}
