package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Equipments;
import com.eniso.tama.entity.Session;

@Repository
public interface EquipmentsRepository extends JpaRepository<Equipments, Long> {
	List<Equipments> findAllByDeletedFalse();
	Optional<Equipments> findByIdAndDeletedFalse(long id);
    List<Equipments> findByClassroomIdAndDeletedFalse(Long classroomId);

    Optional<Equipments> findByIdAndClassroomIdAndDeletedFalse(Long id, Long classroomId);
    
}

