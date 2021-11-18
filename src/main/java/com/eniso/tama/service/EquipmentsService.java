package com.eniso.tama.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eniso.tama.entity.Equipments;

public interface EquipmentsService {
	
	public List<Equipments> findAll();
	
	public Equipments findById(long theId);
	
	public void save(Equipments equipment);
	
	public void deleteById(long id);
	
	public List<Equipments> findByClassroom(Equipments theEquipment);
	
	public List<Equipments> getClassroomsEquipments( long id);
	
	public Equipments getEquipments(long equipmentId); 

	public  Equipments addEquipment( Equipments equipment); 
	
	public Equipments updateEquipmentClassroom(Equipments equipment) ;
	
	public ResponseEntity<?> addEquipmentsByClassroom(Equipments equipment, long id );
	
	public void deleteEquipment(long id) ; 
}