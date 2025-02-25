package com.eniso.tama.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eniso.tama.entity.Role;
import com.eniso.tama.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(Roles role);

    @Query("select role from Role role")
    Stream<Role> getAllRolesStream();
}
