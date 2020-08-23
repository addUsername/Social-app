package com.addusername.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addusername.social.entities.Rol;
import com.addusername.social.enums.RolName;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByRolName(RolName rolName);
}