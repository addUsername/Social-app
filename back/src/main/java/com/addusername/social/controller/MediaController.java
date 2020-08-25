package com.addusername.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addusername.social.dto.Message;
import com.addusername.social.entities.content.Content;
import com.addusername.social.repository.ContentRepository;
import com.addusername.social.security.MyUserDetails;

@RestController
@RequestMapping("api/media")
public class MediaController {

	@Autowired
	ContentRepository contentService;
	
//	public void upload() {
//		
//	}
//	
//	@GetMapping("/{username}/{ file }")
//	@PreAuthorize("hasRole('USER')")
//	public ResponseEntity<?> download(@PathVariable(value = "username") String username,@PathVariable(value = "file") String file){
//		
//		Boolean isFriend = false;
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String user = ((MyUserDetails) auth.getPrincipal()).getUsername();
//		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();
//		
//		for(Content friend:friends) {
//			if(friend.getUsername().equals(user)) {
//				isFriend = true;
//				break;
//			};
//		}
//		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);
//
//	}
}
