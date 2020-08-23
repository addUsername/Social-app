package com.addusername.social.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addusername.social.repository.RolRepository;
import com.addusername.social.entities.Rol;
import com.addusername.social.enums.RolName;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository repo;
	
	public Optional<Rol> getByRolName(RolName rolName){
		return repo.findByRolName(rolName);
	}

}
