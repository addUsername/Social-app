package com.addusername.social.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.addusername.social.dto.Home;
import com.addusername.social.entities.Client;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.entities.content.Media;
import com.addusername.social.repository.ContentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.SyslogOutputStream;

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
		
		Content content = repo.findByUsername(username).get();
		
		Home toReturn = new Home();
		toReturn.setUsername(username);
		toReturn.setIdFrame(content.getFrames().stream()
				.map(Frame::getId)
				.collect(Collectors.toList())				
				);
		toReturn.setImg(content.getFrames().get(0).getMedia().getId().toString());
		toReturn.setImgs(content.getFrames().stream()
				.map(frame -> frame.getMedia().getId().toString())
				.collect(Collectors.toList())
				);
		
		return toReturn;
	}
	

}
