package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.eniso.tama.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
	
	@Query(value = "select distinct theme_name from theme", nativeQuery = true)
	public List<String> findDistinctThemeName();

	
	 List<Theme> findAllByDeletedFalse();
		
	Optional<Theme> findByIdAndDeletedFalse(long id);
		
}
