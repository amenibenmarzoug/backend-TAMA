package com.eniso.tama.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Cursus;

@Repository

public interface CursusRepository extends JpaRepository<Cursus, Long> {


}
