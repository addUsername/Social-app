package com.addusername.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addusername.social.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByUsername(String nu);
    boolean existsByUsername(String nu);
    boolean existsByEmail(String email);

}
