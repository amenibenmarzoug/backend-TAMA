package com.eniso.tama.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.EntrepriseDisponibility;

@Repository

public interface EntrepriseDisponibilityRepository extends JpaRepository<EntrepriseDisponibility, Long> {

    Optional<EntrepriseDisponibility> findByDay(String day);

    Boolean existsByDay(String day);

    Optional<EntrepriseDisponibility> findByTime(String time);

    Boolean existsByTime(String time);

}
