package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.ProgramInstance;
import com.eniso.tama.entity.ThemeDetailInstance;

@Repository
public interface ThemeDetailInstanceRepository extends JpaRepository<ThemeDetailInstance, Long> {

	
	 List<ThemeDetailInstance> findAllByDeletedFalse();
		
		Optional<ThemeDetailInstance> findByIdAndDeletedFalse(long id);
}
