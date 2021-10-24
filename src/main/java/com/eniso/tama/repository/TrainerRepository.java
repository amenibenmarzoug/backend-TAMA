package com.eniso.tama.repository;


import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Trainer;


@Repository

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
	
	//Trainer findById(long id);
    List<Trainer> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<Trainer> findByFirstName(String firstName);

    Boolean existsByFirstName(String firstName);
    
    @Query(value="select distinct ts.user_id from trainer_specifications ts  "
    		+ "join module m  on m.module_name=ts.specifications " + 
    		"join theme th on th.theme_id= m.theme_theme_id " + 
    		"where th.theme_name like %:specialization%", nativeQuery = true)
    List<Long> findTrainersBySpecialization(String specialization);
}
