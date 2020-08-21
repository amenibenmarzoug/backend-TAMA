package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.Group;
import com.eniso.tama.repository.GroupRepository;




@Service
@ComponentScan(basePackageClasses = GroupRepository.class )
public class GroupServiceImpl implements GroupService {
	
    private GroupRepository groupRepository;
    

    public GroupServiceImpl() {};
    
    @Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		//super();
		this.groupRepository = groupRepository;
	}

	

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
		
	}

	@Override
	public Group findById(long theId) {
		Optional<Group> result = groupRepository.findById(theId);
		
		Group theControl = null;
		
		if (result.isPresent()) {
			theControl = result.get();
		}
		else {
			// we didn't find the participant
			throw new RuntimeException("Did not find Group id - " + theId);
		}
		
		return theControl;
	}

	@Override
	public void save(Group theGroup) {
		groupRepository.save(theGroup);
			
	}

	@Override
	public void deleteById(long theId) {
		groupRepository.deleteById(theId);		
	}

	
	

}
