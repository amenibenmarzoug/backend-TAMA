package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {

}
