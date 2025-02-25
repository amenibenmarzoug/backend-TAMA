package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

}
