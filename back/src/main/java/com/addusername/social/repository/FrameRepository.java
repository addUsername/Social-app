package com.addusername.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addusername.social.entities.content.Frame;
import com.addusername.social.entities.content.Media;

public interface FrameRepository extends JpaRepository<Frame, Long> {
	
	Optional<Media> findByMedia_id(Long media_id);

}
