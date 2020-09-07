package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.CourseSessionParticipant;
@Repository

public interface CourseSessionParticipantRepository extends JpaRepository<CourseSessionParticipant, Long>{

}
