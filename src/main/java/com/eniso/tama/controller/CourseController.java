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

import com.eniso.tama.entity.Course;
import com.eniso.tama.service.CourseService;



@RestController
@ComponentScan(basePackageClasses = CourseService.class )
@RequestMapping(value="/api")
public class CourseController {
	
	private CourseService courseService;
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	} 
	
	


	@GetMapping("/course")
	public List<Course> findAll() {
		return courseService.findAll();
	}
	
	@GetMapping("course/{courseId}")
	public Course getCourse(@PathVariable int  courseId) {
		
		Course course = courseService.findById(courseId);
		
		if (course == null) {
			throw new RuntimeException("Course id not found - " + courseId);
		}
		
		return course;
	}
	// add mapping for POST /course - add new control

	@PostMapping("/course")
	public  Course addCourse(@RequestBody Course course) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		courseService.save(course);
		return course;
	}
	
	
	// add mapping for PUT /employees - update existing employee
	
		@PutMapping("/course")
		public Course updateCourse(@RequestBody Course course) {
			
			courseService.save(course);
			
			return course;
		}

		@DeleteMapping("/course/{courseId}")
		public String deleteCourse(@PathVariable int  courseId) {
			
			Course course = courseService.findById(courseId);
			
			// throw exception if null
			
			if (course == null) {
				throw new RuntimeException("the course id is not found - " + courseId);
			}
			
			courseService.deleteById(courseId);
			
			return "Deleted course id - " + courseId;
		}

}
