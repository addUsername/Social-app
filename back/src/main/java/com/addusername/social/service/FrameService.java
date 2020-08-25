package com.addusername.social.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addusername.social.entities.content.Frame;
import com.addusername.social.repository.FrameRepository;

@Transactional
@Service
public class FrameService {
	
	@Autowired
	FrameRepository repo;
	
	public Optional<Frame> getById(Long id){
		return repo.findById(id);		
	}
	public void save(Frame frame) {
		repo.save(frame);
	}

}
