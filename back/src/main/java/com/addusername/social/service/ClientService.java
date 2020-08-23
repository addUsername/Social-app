package com.addusername.social.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addusername.social.repository.ClientRepository;
import com.addusername.social.entities.Client;
@Service
@Transactional
public class ClientService {
	
	@Autowired //make it private?
	ClientRepository repo;
	
	public Optional<Client> getByUsername(String nu){
		return repo.findByUsername(nu);
	}
	public boolean existsByUsername(String nu) {
		return repo.existsByUsername(nu);
	}
	public boolean existsByEmail(String email){
		return repo.existsByEmail(email);
	}
	public void save(Client client) {
		repo.save(client);
	}
}
