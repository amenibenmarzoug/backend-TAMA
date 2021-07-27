package com.eniso.tama.service;

import java.util.List;

import com.eniso.tama.entity.Equipments;

public interface EquipmentsService {

    public List<Equipments> findAll();

    public Equipments findById(long theId);

    public void save(Equipments equipment);

    public void deleteById(long id);

    public List<Equipments> findByClassroom(Equipments theEquipment);

}
