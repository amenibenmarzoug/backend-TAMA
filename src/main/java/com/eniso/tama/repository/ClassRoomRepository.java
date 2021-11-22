package com.eniso.tama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.ClassRoom;

@Repository

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

	public List<ClassRoom> findAllByDeletedFalse();

	public ClassRoom findByIdAndDeletedFalse(long theId);
	
	public List<ClassRoom> findByInstitutionAndDeletedFalse(ClassRoom theClassroom);


}
