package com.eniso.tama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.eniso.tama.entity.ClassRoom;
import com.eniso.tama.entity.Equipments;
import com.eniso.tama.repository.ClassRoomRepository;
import com.eniso.tama.repository.EquipmentsRepository;


@Service
@ComponentScan(basePackageClasses = EquipmentsRepository.class)
public class EquipmentsServiceImpl implements EquipmentsService {

    private EquipmentsRepository equipmentsRepository;

    public EquipmentsServiceImpl() {
    }

    @Autowired
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


}
