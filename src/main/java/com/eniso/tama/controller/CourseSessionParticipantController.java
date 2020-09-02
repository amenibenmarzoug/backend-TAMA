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

import com.eniso.tama.entity.CourseSessionParticipant;
import com.eniso.tama.service.CourseSessionParticipantService;


@RestController
@ComponentScan(basePackageClasses = CourseSessionParticipantService.class )
@RequestMapping(value="/api")
public class CourseSessionParticipantController {

	private CourseSessionParticipantService courseSessionParticipantService;
	@Autowired
	public CourseSessionParticipantController(CourseSessionParticipantService courseSessionParticipantService) {
		this.courseSessionParticipantService = courseSessionParticipantService;
	} 
	
	


	@GetMapping("/courseSessionParticipant")
	public List<CourseSessionParticipant> findAll() {
		return courseSessionParticipantService.findAll();
	}
	
	@GetMapping("courseSessionParticipant/{courseSessionParticipantId}")
	public CourseSessionParticipant getCourseSessionParticipant(@PathVariable int  courseSessionParticipantId) {
		
		CourseSessionParticipant courseSessionParticipant = courseSessionParticipantService.findById(courseSessionParticipantId);
		
		if (courseSessionParticipant == null) {
			throw new RuntimeException("courseSessionParticipant id not found - " + courseSessionParticipantId);
		}
		
		return courseSessionParticipant;
	}
	// add mapping for POST /courseSessionParticipant - add new courseSessionParticipant

	@PostMapping("/courseSessionParticipant")
	public  CourseSessionParticipant addCourseSessionParticipant(@RequestBody CourseSessionParticipant courseSessionParticipant) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
	
		
		courseSessionParticipantService.save(courseSessionParticipant);
		return courseSessionParticipant;
	}
	
	
	// add mapping for PUT /courseSessionParticipant - update existing courseSessionParticipant
	
		@PutMapping("/courseSessionParticipant")
		public CourseSessionParticipant updateCourseSessionPart(@RequestBody CourseSessionParticipant courseSessionParticipant) {
			
			courseSessionParticipantService.save(courseSessionParticipant);
			
			return courseSessionParticipant;
		}

		@DeleteMapping("/courseSessionParticipant/{courseSessionParticipantId}")
		public String deleteCourseSessionPart(@PathVariable int  courseSessionParticipantId) {
			
			CourseSessionParticipant courseSessionParticipant = courseSessionParticipantService.findById(courseSessionParticipantId);
			
			// throw exception if null
			
			if (courseSessionParticipant == null) {
				throw new RuntimeException("the courseSessionParticipant id is not found - " + courseSessionParticipantId);
			}
			
			courseSessionParticipantService.deleteById(courseSessionParticipantId);
			
			return "Deleted courseSessionParticipant id - " + courseSessionParticipantId;
		}
}
