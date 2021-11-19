package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findAllByDeletedFalse();
	
	Optional<Event> findByIdAndDeletedFalse(long id);
	
    Optional<Event> findBySessionIdAndDeletedFalse(long id);
}
