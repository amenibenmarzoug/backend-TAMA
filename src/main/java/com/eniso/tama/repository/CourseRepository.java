package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Course;

@Repository

public interface CourseRepository extends JpaRepository<Course, Long> {

}
