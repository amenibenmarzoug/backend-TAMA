package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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
import com.eniso.tama.entity.Entreprise;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.service.ClassRoomService;
import com.eniso.tama.service.ClassRoomService;

@RestController
@ComponentScan(basePackageClasses = ClassRoomService.class )
@RequestMapping(value="/api")
public class ClassRoomController {
	@Autowired
	InstitutionRepository institutionRepository;
	
	private ClassRoomService classRoomService;
	
	@Autowired
	public ClassRoomController(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	} 
	
	


	@GetMapping("/classroom")
	public List<ClassRoom> findAll() {
		return classRoomService.findAll();
	}
	@GetMapping("/classroom/institution")
	public List<ClassRoom> getClassroomsInstitution(@RequestParam("id") long id) {
		List<ClassRoom> classroomsPerInstitution = new ArrayList<ClassRoom>();
		for (ClassRoom theC : classRoomService.findAll()) {
		if(theC.getInstitution()!=null) {
			if (id == theC.getInstitution().getId()) {

				classroomsPerInstitution.add(theC);
				
			}
		}

		}

		return classroomsPerInstitution;
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
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		classRoomService.save(classRoom);
		return classRoom;
	}
	@PostMapping("/classroomInstitution")
	public ResponseEntity<?> addClassRoomByInstitution(@Valid @RequestBody ClassRoom classRoom,@RequestParam("id") long id ) {
	
		
		Institution institution = new Institution();
		for (Institution i : institutionRepository.findAll()) {
			if (id == i.getId()) {
				institution = i;
			}
		}
		
		ClassRoom c = new ClassRoom();
		c.setClassRoomName(classRoom.getClassRoomName());
		c.setCapacity(classRoom.getCapacity());
		c.setInstitution(institution);
		
		classRoomService.save(c);
		return ResponseEntity.ok(new MessageResponse("Class added successfully!"));

		
	}
	
	
	// add mapping for PUT /classRoom - update existing classRoom
	
		@PutMapping("/classroom")
		public ClassRoom updateClassRoom(@RequestBody ClassRoom classRoom) {
			
			classRoomService.save(classRoom);
			
			return classRoom;
		}

		@DeleteMapping("/classroom/{classroomId}")
		public String deleteClassRoom(@PathVariable long  classroomId) {
			
			ClassRoom classRoom = classRoomService.findById(classroomId);
			
			// throw exception if null
			
			if (classRoom == null) {
				throw new RuntimeException("the classRoom id is not found - " + classroomId);
			}
			
			classRoomService.deleteById(classroomId);
			
			return "Deleted classRoom id - " + classroomId;
		}

}
