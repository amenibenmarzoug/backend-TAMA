package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
