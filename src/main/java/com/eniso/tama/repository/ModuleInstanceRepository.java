package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.ModuleInstance;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleInstanceRepository extends JpaRepository<ModuleInstance, Long> {

}
