package com.eniso.tama.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Group;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	Optional<Group> findByGroupName(String groupName);
	Boolean existsByGroupName(String groupName);


}
