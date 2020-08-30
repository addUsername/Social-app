package com.addusername.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addusername.social.entities.content.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
	
	//podremos comprobar primero si existe y despues buscarlo.. y pillarlo con el get como siempre.
	Optional<Content> findByUsername(String nu);
    boolean existsByUsername(String nu);

}
