package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Equipments;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.entity.Participant;
import com.eniso.tama.entity.Session;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ClassRoomRepository;
import com.eniso.tama.repository.EquipmentsRepository;
import com.eniso.tama.repository.InstitutionRepository;


@Service
@ComponentScan(basePackageClasses = ClassRoomRepository.class )
public class ClassRoomServiceImpl  implements ClassRoomService{
	
	@Autowired
    private ClassRoomRepository classRoomRepository;
	
	@Autowired
	private EquipmentsService equipmentsService ; 
	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private SessionService sessionService;

	
	
	public ClassRoomServiceImpl() {}

	
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

	@Override
	public List<ClassRoom> findByInstitution(ClassRoom theClassroom) {
		
		List<ClassRoom> classroom= null ;
		
		

		for(ClassRoom theC:classRoomRepository.findAll()) {
			
			
		if  (theC.getInstitution()!=null) {
    	  
			classroom.add(theC) ;
		
      }
           	
	}
		return classroom;
	}


	@Override
	public List<ClassRoom> getClassroomsInstitution(long id) {
		List<ClassRoom> classroomsPerInstitution = new ArrayList<ClassRoom>();
		for (ClassRoom theC : this.findAll()) {
		if(theC.getInstitution()!=null) {
			if (id == theC.getInstitution().getId()) {

				classroomsPerInstitution.add(theC);
				
			}
		}

		}

		return classroomsPerInstitution;
	}


	@Override
	public ResponseEntity<?> addClassRoomByInstitution(ClassRoom classRoom, long id) {
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
		
		save(c);
		return ResponseEntity.ok(new MessageResponse("Class added successfully!"));
	}


	@Override
	public ClassRoom updateClassRoomInstit (ClassRoom classRoom) {
		ClassRoom newClassroom = findById(classRoom.getId());
		newClassroom.setClassRoomName(classRoom.getClassRoomName());
		newClassroom.setCapacity(classRoom.getCapacity());
		newClassroom.setInstitution(classRoom.getInstitution());
		newClassroom.setFees(classRoom.getFees());
	     save(newClassroom);
		
		return classRoom;
	}


	@Override
	public void deleteClassroom(long id) {
		
		// les séances qui vont se dérouler dans cette salle
		List<Session> sessions=sessionService.findByClassroomId(id) ; 
		if(sessions!=null) {
			for (Session session : sessions) {
				session.setClassRoom(null);
			}
		}
			
		//supprimer les equipments de cette salle
		List <Equipments> equipments = equipmentsService.getClassroomsEquipments(id);
		if (equipments !=null) {
			for (Equipments equipment : equipments) {
			equipmentsService.deleteEquipment(equipment.getId());
		}
		}
		
		ClassRoom classroom = findById(id) ; 
		classroom.setDeleted(true);
		save(classroom) ; 

		// TODO Auto-generated method stub
		
	}

	}
