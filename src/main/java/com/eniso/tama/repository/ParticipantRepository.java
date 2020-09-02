package com.eniso.tama.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	Optional<Participant> findByEmail(String email);
	Boolean existsByEmail(String email);
	Optional<Participant> findByFirstNameP(String firstNameP);
	Boolean existsByFirstNameP(String firstNameP);
	@Query(value = "SELECT * from participant p", nativeQuery=true)
	public List<Participant> findByGroupId(long id);
	List<Participant> findByLevel(String level);
	
	//List <Participant> findByEntreprise(Participant theParticipant);
	List<Participant> findByAbandon(boolean abandon );
	
	//Page<Participant> findByGroupId(Long groupId, Pageable pageable);

}
