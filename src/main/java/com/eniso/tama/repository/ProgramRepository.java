package com.eniso.tama.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eniso.tama.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    // @PersistenceContext EntityManager entityManager ;
//	
//	@Modifying
//	@Query(value="DELETE program , module , theme   FROM program  INNER JOIN module  INNER JOIN theme WHERE messages.messageid= usersmessages.messageid and messages.messageid = '1'")
//			
//	void deleteBooks(@Param("id") Long id);


}
