package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eniso.tama.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {

}
