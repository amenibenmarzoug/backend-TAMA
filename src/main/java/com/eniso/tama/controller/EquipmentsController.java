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

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Equipments;
import com.eniso.tama.entity.Institution;
import com.eniso.tama.payload.MessageResponse;
import com.eniso.tama.repository.ClassRoomRepository;
import com.eniso.tama.repository.InstitutionRepository;
import com.eniso.tama.service.ClassRoomService;
import com.eniso.tama.service.EquipmentsService;

@RestController
@ComponentScan(basePackageClasses = EquipmentsService.class)
@RequestMapping(value = "/api")
public class EquipmentsController {
    @Autowired
    ClassRoomRepository classroomRepository;

    private EquipmentsService equipmentsService;

    @Autowired
    public EquipmentsController(EquipmentsService equipmentsService) {
        this.equipmentsService = equipmentsService;
    }

    @GetMapping("/equipments")
    public List<Equipments> findAll() {
        return equipmentsService.findAll();
    }

    @GetMapping("/classroom/equipments")
    public List<Equipments> getClassroomsEquipments(@RequestParam("id") long id) {
        List<Equipments> equipmentsPerClassroom = new ArrayList<Equipments>();
        for (Equipments theE : equipmentsService.findAll()) {
            if (theE.getClassroom() != null) {
                if (id == theE.getClassroom().getId()) {

                    equipmentsPerClassroom.add(theE);

                }
            }

        }

        return equipmentsPerClassroom;
    }

    @GetMapping("equipments/{equipmentId}")
    public Equipments getEquipments(@PathVariable long equipmentId) {

        Equipments equipments = equipmentsService.findById(equipmentId);

        if (equipments == null) {
            throw new RuntimeException("equipment id not found - " + equipmentId);
        }

        return equipments;
    }

    @PostMapping("/equipment")
    public Equipments addEquipment(@RequestBody Equipments equipment) {

        equipmentsService.save(equipment);
        return equipment;
    }

    @PostMapping("/equipmentsClassroom")
    public ResponseEntity<?> addEquipmentsByClassroom(@Valid @RequestBody Equipments equipment, @RequestParam("id") long id) {


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

        equipmentsService.save(e);
        return ResponseEntity.ok(new MessageResponse("Equipment added successfully!"));


    }

    // add mapping for PUT /classRoom - update existing classRoom

    @PutMapping("/equipmentClassroom")
    public Equipments updateEquipmentClassroom(@RequestBody Equipments equipment) {
        Equipments newEquipment = equipmentsService.findById(equipment.getId());
        newEquipment.setEquipmentName(equipment.getEquipmentName());
        newEquipment.setQuantity(equipment.getQuantity());
        equipmentsService.save(newEquipment);

        return equipment;
    }

    @DeleteMapping("/equipment/{equipmentId}")
    public String deleteEquipment(@PathVariable long equipmentId) {

        Equipments equipment = equipmentsService.findById(equipmentId);

        // throw exception if null

        if (equipment == null) {
            throw new RuntimeException("the equipment id is not found - " + equipmentId);
        }

        equipmentsService.deleteById(equipmentId);

        return "Deleted equipment id - " + equipmentId;
    }

}
