package com.eniso.tama.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Entreprise;

import org.springframework.data.jpa.repository.JpaRepository;


import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.User;

public interface EnterpriseRepository extends JpaRepository<Entreprise, Long>{
	
	Boolean existsByEmail(String email) ; 


}