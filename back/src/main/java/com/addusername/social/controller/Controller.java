package com.addusername.social.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addusername.social.dto.JwtDTO;
import com.addusername.social.entities.Client;
import com.addusername.social.service.ClientService;

@RestController
@RequestMapping("/api/test")
public class Controller {
	
	@Autowired
	ClientService clientService;
	
	
	@GetMapping("roltest")
	@PreAuthorize("hasRole('ADMIN')")
	public HashMap<String,Object> sayHello() {
		System.out.println("hii? :( ");
		Client myclient = clientService.getByUsername("admin").get();
		HashMap<String, Object> myObj = new HashMap<String, Object>();
		myObj.put("token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5ODE0ODI4MSwiZXhwIjoxNTk4MTg0MjgxfQ.XgOoyZlIrFMoK5IxW5E-xCQykqo2ZF0IavsE57LB9o_m5_q5X5PjW1pSOdzdyW_CJ0kxTRGsVDhGgbtDmTm54Q");
		myObj.put("status",200);
		myObj.put("username",myclient.getUsername());
		myObj.put("roles", myclient.getRoles());
		myObj.put("mssg","zona vip xavaal -> authenticated()");
		return myObj;
		
	}
	
	@GetMapping("roltest2")
	@PreAuthorize("hasRole('USER')")
	public String sayHi() {
		
		return "yay USER";
		
	}

}
