package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.eniso.tama.entity.ThemeInstance;

@Repository
public interface ThemeInstanceRepository extends JpaRepository<ThemeInstance, Long> {

	
	 List<ThemeInstance> findAllByDeletedFalse();
		
		Optional<ThemeInstance> findByIdAndDeletedFalse(long id);
		
}
