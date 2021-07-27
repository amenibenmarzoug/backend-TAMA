package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.ThemeInstance;

@Repository
public interface ThemeInstanceRepository extends JpaRepository<ThemeInstance, Long> {

}
