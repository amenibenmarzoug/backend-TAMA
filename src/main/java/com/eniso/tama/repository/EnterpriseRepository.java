package com.eniso.tama.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Entreprise;

@Repository
public interface EnterpriseRepository extends JpaRepository<Entreprise, Long> {
	
	List<Entreprise> findAllByDeletedFalse();
	Optional<Entreprise> findByIdAndDeletedFalse(long id);


    Optional<Entreprise> findByEmailAndDeletedFalse(String email);

    Optional<Entreprise> findByPhoneNumberAndDeletedFalse(String email);

    Boolean existsByEmailAndDeletedFalse(String email);

    Boolean existsByPhoneNumberAndDeletedFalse(String phoneNumber);

    Optional<Entreprise> findByEnterpriseNameAndDeletedFalse(String entrepriseName);

    Boolean existsByEnterpriseNameAndDeletedFalse(String entrepriseName);
}


