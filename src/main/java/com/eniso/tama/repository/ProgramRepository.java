package com.eniso.tama.repository;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Program;
import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.Session;
@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    // @PersistenceContext EntityManager entityManager ;
//	
//	@Modifying
//	@Query(value="DELETE program , module , theme   FROM program  INNER JOIN module  INNER JOIN theme WHERE messages.messageid= usersmessages.messageid and messages.messageid = '1'")
//			
//	void deleteBooks(@Param("id") Long id);

	
    List<Program> findAllByDeletedFalse();
	
	Optional<Program> findByIdAndDeletedFalse(long id);
	
	List<Program> findByProgramNameContainingIgnoreCase(String name);
	
	@Query(value="select * from program p2 where p2.program_name not like %:category1% and p2.program_name not like %:category2%  and p2.program_name not like %:category3%  ", nativeQuery = true)
	List<Program> findByProgramNameNotLike(String category1,String category2,String category3);



}
