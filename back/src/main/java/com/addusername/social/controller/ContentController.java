package com.addusername.social.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addusername.social.dto.ContentClient;
import com.addusername.social.dto.Message;
import com.addusername.social.entities.Client;
import com.addusername.social.entities.content.Comment;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.entities.content.Media;
import com.addusername.social.repository.ClientRepository;
import com.addusername.social.repository.ContentRepository;
import com.addusername.social.security.MyUserDetails;

@RestController
@RequestMapping("api/media")
public class ContentController {

	@Autowired
	ContentRepository contentService;
	@Autowired
	ClientRepository clientrepo;
	
	@PostMapping("/{username}/new")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addContent(@PathVariable(value = "username") String username,@Valid @RequestBody ContentClient content, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) return new ResponseEntity(new Message("bindingResult.hasErrors"), HttpStatus.BAD_REQUEST);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(!username.equals( ((MyUserDetails) auth.getPrincipal()).getUsername() )) return new ResponseEntity(new Message("u are not "+username), HttpStatus.BAD_REQUEST);		

		Content add_frame_to_content = contentService.findByUsername(username).get();

		Media media = new Media();
		media.setLikes(0);
		media.setPath(content.getContenido());
	
		Frame frame = new Frame();
		frame.setMedia(media);
		frame.setComments(new ArrayList<Comment>());
		
		List <Frame> frames = add_frame_to_content.getFrames();
		frames.add(frame);
		add_frame_to_content.setFrames(frames);
		Object object = contentService.save(add_frame_to_content);		

		return new ResponseEntity(object, HttpStatus.ACCEPTED);
	}
}
