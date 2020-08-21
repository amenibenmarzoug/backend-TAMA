package com.eniso.tama.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Group;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	
	//Optional<Group> findById(long id);
	//Boolean existsByEmail(String email);
	//Optional<Participant> findByFirstNameP(String firstNameP);
	//Boolean existsByFirstNameP(String firstNameP);

}
