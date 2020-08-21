package com.eniso.tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eniso.tama.entity.Group;
import com.eniso.tama.service.GroupService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ComponentScan(basePackageClasses = GroupService.class )
@RequestMapping(value="/api")
public class GroupController {
	private GroupService groupService;
	@Autowired
	public GroupController(GroupService groupService) {
		//super();
		this.groupService = groupService;
	}
	@GetMapping("/groups")
	public List<Group> findAll() {
		return groupService.findAll();
	}
	
	@GetMapping("groups/{groupId}")
	public Group getParticipant(@PathVariable long  groupId) {
		
		Group theGroup = groupService.findById(groupId);
		
		if (theGroup == null) {
			throw new RuntimeException("Group id not found - " + groupId);
		}
		
		return theGroup;
	}
	
	@PostMapping("/groups")
	public  Group addcontrol(@RequestBody Group theGroup) {
	
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		//stheControl.setId(0);
		
		groupService.save(theGroup);
		return theGroup;
	}
	@PutMapping("/groups")
	public Group updateGroup (@RequestBody Group theGroup) {
		
		groupService.save(theGroup);
		
		return theGroup;
	}
	
	@DeleteMapping("/groups/{groupId}")
	public String deleteParticipant(@PathVariable long  groupId) {
		
		Group tempGroup = groupService.findById(groupId);
		
		// throw exception if null
		
		if (tempGroup == null) {
			throw new RuntimeException("the Group id is not found - " + groupId);
		}
		
		groupService.deleteById(groupId);
		
		return "Deleted group id - " + groupId;
	}

}
