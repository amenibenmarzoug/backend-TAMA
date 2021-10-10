package com.eniso.tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.ClassRoom;

@Repository

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

}
