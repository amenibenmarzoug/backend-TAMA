package com.eniso.tama.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eniso.tama.entity.Equipments;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ClassRoomRepository;
import com.eniso.tama.service.EquipmentsService;

@RestController
@ComponentScan(basePackageClasses = EquipmentsService.class)
@RequestMapping(value = "/api")
public class EquipmentsController {
	@Autowired
	ClassRoomRepository classroomRepository;

	@Autowired
	private EquipmentsService equipmentsService;

	public EquipmentsController(EquipmentsService equipmentsService) {
		this.equipmentsService = equipmentsService;
	}

	@GetMapping("/equipments")
	public List<Equipments> findAll() {
		return equipmentsService.findAll();
	}

	@GetMapping("/classroom/equipments")
	public List<Equipments> getClassroomsEquipments(@RequestParam("id") long id) {
		return equipmentsService.getClassroomsEquipments(id);
	}

	@GetMapping("equipments/{equipmentId}")
	public Equipments getEquipments(@PathVariable long equipmentId) {

		return equipmentsService.getEquipments(equipmentId);
	}

	@PostMapping("/equipment")
	public Equipments addEquipment(@RequestBody Equipments equipment) {

		return equipmentsService.addEquipment(equipment);
	}

	@PostMapping("/equipmentsClassroom")
	public ResponseEntity<?> addEquipmentsByClassroom(@Valid @RequestBody Equipments equipment,
			@RequestParam("id") long id) {

		return equipmentsService.addEquipmentsByClassroom(equipment, id);

	}

	// add mapping for PUT /classRoom - update existing classRoom

	@PutMapping("/equipmentClassroom")
	public Equipments updateEquipmentClassroom(@RequestBody Equipments equipment) {
		return equipmentsService.updateEquipmentClassroom(equipment);
	}

	@DeleteMapping("/equipment/{equipmentId}")
	public ResponseEntity<?> deleteEquipment(@PathVariable long equipmentId) {

		Equipments equipment = equipmentsService.findById(equipmentId);

		// throw exception if null

		if (equipment == null) {
			throw new RuntimeException("the equipment id is not found - " + equipmentId);
		}

		// equipmentsService.deleteById(equipmentId);
		equipmentsService.deleteEquipment(equipmentId);

		return ResponseEntity.ok(new MessageResponse("Equipement supprimé avec succès"));
	}

	@DeleteMapping("/equipment/omit/{equipmentId}")
	public ResponseEntity<?> omitEquipment(@PathVariable long equipmentId) {

		Equipments equipment = equipmentsService.findById(equipmentId);

		// throw exception if null

		if (equipment == null) {
			throw new RuntimeException("the equipment id is not found - " + equipmentId);
		}

		equipmentsService.deleteById(equipmentId);

		return ResponseEntity.ok(new MessageResponse("Equipement supprimé avec succès"));
	}

}