package com.addusername.social.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addusername.social.entities.Client;
import com.addusername.social.entities.content.Content;
import com.addusername.social.repository.ContentRepository;

@Transactional
@Service
public class ContentService {
	
	public Optional<Content> findById(Long id){
		return repo.findById(id);
	}
	@Autowired
	ContentRepository repo;
	public Optional<Content> getByUsername(String nu){
		return repo.findByUsername(nu);
	}
	public boolean existsByUsername(String nu) {
		return repo.existsByUsername(nu);
	}
	public void save(Content content) {
		repo.save(content);
	}

}
