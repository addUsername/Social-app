package com.addusername.social.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.addusername.social.repository.ClientRepository;
import com.addusername.social.repository.ContentRepository;
import com.addusername.social.dto.UpdateClientDTO;
import com.addusername.social.entities.Client;
@Service
@Transactional
public class ClientService {
	
	@Autowired //make it private?
	ClientRepository repo;
	//fuck encapsulation lol
	@Autowired
	ContentRepository contentRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
		//aqui deberiamos guardar en otra tabla los usernames/emails, asi aunque se borre el user estos quedan invalidados..
		// es facil
		repo.save(client);
	}
	//Needs implements suspend account, like deleting from db but store it in another entity
	//to be able to recive.. cool idea. 
	public String updateClient(UpdateClientDTO newUser) {
		//Looks silly and un-debugable but im not refactoring these
		
		if(newUser.getSuspend()) return "Account suspended";
		
		if(newUser.getDelete()) {
			repo.delete(repo.findByUsername(newUser.getUsername()).get());
			contentRepo.delete(contentRepo.findByUsername(newUser.getUsername()).get());			
			return "Account deleted";
		}
		
		Client client = repo.findByUsername(newUser.getUsername()).get();
		if (newUser.getNewPassword() != ""){
			client.setPassword(passwordEncoder.encode(newUser.getNewPassword()));
		}else {
			client.setEmail(newUser.getNewEmail());
		}
		repo.save(client);
		
		return "Account updated";
	}
}
