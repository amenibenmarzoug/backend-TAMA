package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Group;

public interface GroupService {
	public List<Group> findAll();
	
	public Group findById(long theId);
	
	public void save(Group theGroup);
	
	public void deleteById(long theId);
}
