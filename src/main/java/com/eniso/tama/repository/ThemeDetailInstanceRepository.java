package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.ThemeDetailInstance;

@Repository
public interface ThemeDetailInstanceRepository extends JpaRepository<ThemeDetailInstance, Long>{

}
