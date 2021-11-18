package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Equipments;

@Repository
public interface EquipmentsRepository extends JpaRepository<Equipments, Long> {
    List<Equipments> findByClassroomId(Long classroomId);

    Optional<Equipments> findByIdAndClassroomId(Long id, Long classroomId);
    
}

