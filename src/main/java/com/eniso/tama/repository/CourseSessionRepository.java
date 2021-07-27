package com.eniso.tama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Session;

@Repository

public interface CourseSessionRepository extends JpaRepository<Session, Long> {
    //@Query(value="SELECT * from course_session", nativeQuery = true)
    //@Query(value="SELECT * from course_session cs where cs.course_course_id = ?1", nativeQuery = true)
    // public List<Session> findByCourseId(long id);
}
