package com.addusername.social.service;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addusername.social.dto.Home;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.repository.ContentRepository;

@Transactional
@Service
public class ContentService {
	

	@Autowired
	ContentRepository repo;
	@Autowired
	StorageMediaService storage;
	
	
	public Optional<Content> findById(Long id){
		return repo.findById(id);
	}
	public Optional<Content> findByUsername(String nu){
		return repo.findByUsername(nu);
	}
	public Optional<Content> getByUsername(String nu){
		return repo.findByUsername(nu);
	}
	public boolean existsByUsername(String nu) {
		return repo.existsByUsername(nu);
	}
	public void save(Content content) {
		repo.save(content);
	}
	
	public Home getHome(String username) {
		//Home is just the first frame entered.. lel.. good enough
		Content content = repo.findByUsername(username).get();
		
		Home toReturn = new Home();
		toReturn.setUsername(username);
		toReturn.setIdFrame(content.getFrames().stream()
				.map(Frame::getId)
				.collect(Collectors.toList())				
				);
		toReturn.setImg(content.getFrames().get(0).getMedia().getId().toString());		
		toReturn.setText(content.getFrames().get(0).getText());
		
		toReturn.setLikes(content.getFrames().stream()
				.map(frame -> frame.getMedia().getLikes())
				.collect(Collectors.toList())
				);
		
		toReturn.setImgs(content.getFrames().stream()
				.map(frame -> frame.getMedia().getId().toString())
				.collect(Collectors.toList())
				);
		
		return toReturn;
	}

}
