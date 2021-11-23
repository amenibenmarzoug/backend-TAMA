package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.service.ClassRoomService;

@RestController
@ComponentScan(basePackageClasses = ClassRoomService.class)
@RequestMapping(value = "/api")
public class ClassRoomController {

	
	@Autowired
	private ClassRoomService classRoomService;
	

	public ClassRoomController(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	} 
	
	
	
	@GetMapping("/classroom")
	public List<ClassRoom> findAll() {
		return classRoomService.findAll();
	}
	
	
	@GetMapping("/classroom/institution")
	public List<ClassRoom> getClassroomsInstitution(@RequestParam("id") long id) {
		
		return (classRoomService.getClassroomsInstitution(id));
		
	}
	
	
	@GetMapping("classroom/{classroomId}")
	public ClassRoom getClassRoom(@PathVariable long  classroomId) {
		
		ClassRoom classRoom = classRoomService.findById(classroomId);
		
		if (classRoom == null) {
			throw new RuntimeException("classRoom id not found - " + classroomId);
		}
		
		return classRoom;
	}
	// add mapping for POST /classRoom - add new control

	@PostMapping("/classroom")
	public  ClassRoom addClassRoom(@RequestBody ClassRoom classRoom) {

		classRoomService.save(classRoom);
		return classRoom;
	}
	
	
	@PostMapping("/classroomInstitution")
	public ResponseEntity<?> addClassRoomByInstitution(@Valid @RequestBody ClassRoom classRoom,@RequestParam("id") long id ) {
	
		return(classRoomService.addClassRoomByInstitution(classRoom,id));
		
		
	}
	
	
	
	// add mapping for PUT /classRoom - update existing classRoom
	
		@PutMapping("/classroomInstitution")
		public ClassRoom updateClassRoomInstit(@RequestBody ClassRoom classRoom) {
			return (classRoomService.updateClassRoomInstit(classRoom));
			
			
		}
		
		@DeleteMapping("/classroom/{classroomId}")
		public ResponseEntity<?> deleteClassRoom(@PathVariable long  classroomId) {
			
			ClassRoom classRoom = classRoomService.findById(classroomId);
			
			// throw exception if null
			
			if (classRoom == null) {
				throw new RuntimeException("the classRoom id is not found - " + classroomId);
			}
			
			//classRoomService.deleteById(classroomId);
			classRoomService.deleteClassroom(classroomId);
			return ResponseEntity.ok(new MessageResponse( "Salle supprimé avec succès"));
		}
		
		@DeleteMapping("/classroom/omit/{classroomId}")
		public ResponseEntity<?> omitClassRoom(@PathVariable long  classroomId) {
			
			ClassRoom classRoom = classRoomService.findById(classroomId);
			
			// throw exception if null
			
			if (classRoom == null) {
				throw new RuntimeException("the classRoom id is not found - " + classroomId);
			}
			
			//classRoomService.deleteById(classroomId);
			classRoomService.omitClassroom(classroomId);
			return ResponseEntity.ok(new MessageResponse( "Salle supprimé avec succès"));
		}

}
