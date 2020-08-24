package com.addusername.social.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/media")
public class MediaController {

	public void upload() {
		
	}
	
	@GetMapping("/{username}/{ file }")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> download(){
		
	}
}
