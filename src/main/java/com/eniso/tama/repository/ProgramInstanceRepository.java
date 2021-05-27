package com.eniso.tama.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import com.eniso.tama.entity.ProgramInstance;

public interface ProgramInstanceRepository extends JpaRepository<ProgramInstance, Long> {
	
	List<ProgramInstance> findByBeginDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date BeginDate);
}
