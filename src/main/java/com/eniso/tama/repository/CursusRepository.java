package com.eniso.tama.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Cursus;


public interface CursusRepository extends JpaRepository<Cursus, Long> {


}
