package com.addusername.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addusername.social.entities.content.Media;

public interface MediaRespository extends JpaRepository<Media, Long> {
	//lo vamos a usar para encontrar el fichero por la id que nos pasa el cliente como @RequestPart("metadata") MetadataFileDTO desde ContentController SI SI	
	Optional<Media> findByFilename(String nu);
	boolean existsByFilename(String nu);

}
