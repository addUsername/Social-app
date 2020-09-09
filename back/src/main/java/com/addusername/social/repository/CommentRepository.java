package com.addusername.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addusername.social.entities.content.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
