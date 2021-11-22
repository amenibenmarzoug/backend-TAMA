package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Module;
import com.eniso.tama.entity.ThemeDetail;

@Repository
public interface ThemeDetailRepository extends JpaRepository<ThemeDetail, Long> {
	
	List<ThemeDetail> findAllByDeletedFalse();
	
	Optional<ThemeDetail> findByIdAndDeletedFalse(long id);
	

}
