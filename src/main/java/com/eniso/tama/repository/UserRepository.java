package com.eniso.tama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByEmail(String email);
	Boolean existsByEmail(String email);

}
