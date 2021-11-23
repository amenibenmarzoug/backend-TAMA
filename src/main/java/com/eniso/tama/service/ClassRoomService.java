package com.eniso.tama.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Participant;

public interface ClassRoomService {

	public List<ClassRoom> findAll();

	public ClassRoom findById(long theId);

	public void save(ClassRoom classRoom);

	public void deleteById(long id);

	public List<ClassRoom> findByInstitution(ClassRoom theClassroom);

	public List<ClassRoom> getClassroomsInstitution(long id);

	public ResponseEntity<?> addClassRoomByInstitution(ClassRoom classRoom, long id);

	public ClassRoom updateClassRoomInstit(ClassRoom classRoom);

	public void deleteClassroom(long id);

	public void omitClassroom(long id);
}
