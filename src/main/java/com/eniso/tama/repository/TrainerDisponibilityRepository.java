package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.TrainerDisponibility;

@Repository

public interface TrainerDisponibilityRepository extends JpaRepository<TrainerDisponibility, Long> {

}
