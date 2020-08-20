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

import com.eniso.tama.entity.CourseSession;
import com.eniso.tama.service.CourseSessionService;

@RestController
@ComponentScan(basePackageClasses = CourseSessionService.class )
@RequestMapping(value="/api")
public class CourseSessionController {
	private CourseSessionService courseSessionService;
	@Autowired
	public CourseSessionController(CourseSessionService courseSessionService) {
		this.courseSessionService = courseSessionService;
	} 
	
	


	@GetMapping("/courseSession")
	public List<CourseSession> findAll() {
		return courseSessionService.findAll();
	}
	
	@GetMapping("courseSession/{courseSessionId}")
	public CourseSession getCourseSession(@PathVariable int  courseSessionId) {
		
		CourseSession courseSession = courseSessionService.findById(courseSessionId);
		
		if (courseSession == null) {
			throw new RuntimeException("CourseSession id not found - " + courseSessionId);
		}
		
		return courseSession;
	}
	// add mapping for POST /course - add new control

	@PostMapping("/courseSession")
	public  CourseSession addCourseSession(@RequestBody CourseSession courseSession) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		courseSessionService.save(courseSession);
		return courseSession;
	}
	
	
	// add mapping for PUT /courseSession - update existing employee
	
		@PutMapping("/courseSession")
		public CourseSession updateCourseSession(@RequestBody CourseSession courseSession) {
			
			courseSessionService.save(courseSession);
			
			return courseSession;
		}

		@DeleteMapping("/courseSession/{courseSessionId}")
		public String deleteCourseSession(@PathVariable int  courseSessionId) {
			
			CourseSession courseSession = courseSessionService.findById(courseSessionId);
			
			// throw exception if null
			
			if (courseSession == null) {
				throw new RuntimeException("the courseSession id is not found - " + courseSessionId);
			}
			
			courseSessionService.deleteById(courseSessionId);
			
			return "Deleted courseSession id - " + courseSessionId;
		}
}
