package com.addusername.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.FollowRequest;

public interface FollowRepository extends JpaRepository<FollowRequest, Long> {
	List<FollowRequest> findByContent(Content content);
}
