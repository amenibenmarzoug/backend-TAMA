package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Attendance;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>  {

}
