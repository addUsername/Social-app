package com.addusername.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addusername.social.entities.content.FollowRequest;

public interface FollowRepository extends JpaRepository<FollowRequest, Long> {

}
