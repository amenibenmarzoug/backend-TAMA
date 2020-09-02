package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.repository.ClassRoomRepository;


@Service
@ComponentScan(basePackageClasses = ClassRoomRepository.class )
public class ClassRoomServiceImpl  implements ClassRoomService{
private ClassRoomRepository classRoomRepository;
	
	public ClassRoomServiceImpl() {}

	@Autowired
	public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository) {
		this.classRoomRepository = classRoomRepository;
	}
	
	@Override
	public List<ClassRoom> findAll() {
		return classRoomRepository.findAll();
	}

	@Override
	public ClassRoom findById(long theId) {
		Optional<ClassRoom> result = classRoomRepository.findById(theId);
		
		ClassRoom classRoom = null;
		
		if (result.isPresent()) {
			classRoom = result.get();
		}
		else {
			// we didn't find the classRoom
			throw new RuntimeException("Did not find classRoom id - " + theId);
		}
		
		return classRoom;
	}

	@Override
	public void save(ClassRoom classRoom) {
		classRoomRepository.save(classRoom);
	}

	@Override
	public void deleteById(long    theId) {
		classRoomRepository.deleteById(theId);
	}
}
