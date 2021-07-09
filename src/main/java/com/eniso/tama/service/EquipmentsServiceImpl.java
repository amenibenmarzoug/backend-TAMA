package com.eniso.tama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Equipments;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ClassRoomRepository;
import com.eniso.tama.repository.EquipmentsRepository;

@Service
@ComponentScan(basePackageClasses = EquipmentsRepository.class)
public class EquipmentsServiceImpl implements EquipmentsService {
	@Autowired
	private EquipmentsRepository equipmentsRepository;

	@Autowired
	ClassRoomRepository classroomRepository;
	
	public EquipmentsServiceImpl() {
	}

	
	public EquipmentsServiceImpl(EquipmentsRepository equipmentsRepository) {
		this.equipmentsRepository = equipmentsRepository;
	}

	@Override
	public List<Equipments> findAll() {
		return equipmentsRepository.findAll();
	}

	@Override
	public Equipments findById(long theId) {
		Optional<Equipments> result = equipmentsRepository.findById(theId);

		Equipments equipments = null;

		if (result.isPresent()) {
			equipments = result.get();
		} else {
			// we didn't find the classRoom
			throw new RuntimeException("Did not find equipment id - " + theId);
		}

		return equipments;
	}

	@Override
	public void save(Equipments equipment) {
		equipmentsRepository.save(equipment);
	}

	@Override
	public void deleteById(long id) {
		equipmentsRepository.deleteById(id);
	}

	@Override
	public List<Equipments> findByClassroom(Equipments theEquipment) {
		List<Equipments> equipments = null;

		for (Equipments theE : equipmentsRepository.findAll()) {

			if (theE.getClassroom() != null) {

				equipments.add(theE);

			}

		}
		return equipments;
	}

	public List<Equipments> getClassroomsEquipments(@RequestParam("id") long id) {
		List<Equipments> equipmentsPerClassroom = new ArrayList<Equipments>();
		for (Equipments theE : findAll()) {
			if (theE.getClassroom() != null) {
				if (id == theE.getClassroom().getId()) {

					equipmentsPerClassroom.add(theE);

				}
			}

		}

		return equipmentsPerClassroom;
	}

	public Equipments getEquipments(@PathVariable long equipmentId) {

		Equipments equipments = findById(equipmentId);

		if (equipments == null) {
			throw new RuntimeException("equipment id not found - " + equipmentId);
		}

		return equipments;
	}

	public ResponseEntity<?> addEquipmentsByClassroom(Equipments equipment,	 long id) {

		ClassRoom classroom = new ClassRoom();
		for (ClassRoom c : classroomRepository.findAll()) {
			if (id == c.getId()) {
				classroom = c;
			}
		}

		Equipments e = new Equipments();
		e.setEquipmentName(equipment.getEquipmentName());
		e.setQuantity(equipment.getQuantity());
		e.setClassroom(classroom);

		save(e);
		return ResponseEntity.ok(new MessageResponse("Equipment added successfully!"));

	}

	public Equipments updateEquipmentClassroom(@RequestBody Equipments equipment) {
		Equipments newEquipment = findById(equipment.getId());
		newEquipment.setEquipmentName(equipment.getEquipmentName());
		newEquipment.setQuantity(equipment.getQuantity());
		save(newEquipment);

		return equipment;
	}

	public Equipments addEquipment(Equipments equipment) {

		save(equipment);
		return equipment;
	}

}
