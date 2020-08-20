package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.service.ClassRoomService;
import com.eniso.tama.service.ClassRoomService;

@RestController
@ComponentScan(basePackageClasses = ClassRoomService.class )
@RequestMapping(value="/api")
public class ClassRoomController {
	
	private ClassRoomService classRoomService;
	@Autowired
	public ClassRoomController(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	} 
	
	


	@GetMapping("/classRoom")
	public List<ClassRoom> findAll() {
		return classRoomService.findAll();
	}
	
	@GetMapping("classRoom/{classRoomId}")
	public ClassRoom getClassRoom(@PathVariable int  classRoomId) {
		
		ClassRoom classRoom = classRoomService.findById(classRoomId);
		
		if (classRoom == null) {
			throw new RuntimeException("classRoom id not found - " + classRoomId);
		}
		
		return classRoom;
	}
	// add mapping for POST /classRoom - add new control

	@PostMapping("/classRoom")
	public  ClassRoom addClassRoom(@RequestBody ClassRoom classRoom) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		classRoomService.save(classRoom);
		return classRoom;
	}
	
	
	// add mapping for PUT /classRoom - update existing classRoom
	
		@PutMapping("/classRoom")
		public ClassRoom updateClassRoom(@RequestBody ClassRoom classRoom) {
			
			classRoomService.save(classRoom);
			
			return classRoom;
		}

		@DeleteMapping("/classRoom/{classRoomId}")
		public String deleteClassRoom(@PathVariable int  classRoomId) {
			
			ClassRoom classRoom = classRoomService.findById(classRoomId);
			
			// throw exception if null
			
			if (classRoom == null) {
				throw new RuntimeException("the classRoom id is not found - " + classRoomId);
			}
			
			classRoomService.deleteById(classRoomId);
			
			return "Deleted classRoom id - " + classRoomId;
		}

}
