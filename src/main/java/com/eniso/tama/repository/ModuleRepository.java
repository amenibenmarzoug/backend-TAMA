package com.eniso.tama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
	@Query(value = "select distinct module_name from module", nativeQuery = true)
	public List<String> findModulesNames();
}
