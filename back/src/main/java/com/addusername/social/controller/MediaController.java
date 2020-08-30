package com.addusername.social.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.addusername.social.dto.FrameDTO;
import com.addusername.social.dto.Home;
import com.addusername.social.dto.InMediaDTO;
import com.addusername.social.dto.Message;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.security.MyUserDetails;
import com.addusername.social.service.ContentService;
import com.addusername.social.service.FrameService;
import com.addusername.social.service.StorageMediaService;

@RestController
@RequestMapping("api/media")
public class MediaController {

	//PPALMENTE MANEJARA PETICIONES GET y POST DE CONTENIDO, 2 ENDPOINTS
	//1) CONTENT - like user front page, contains FRAME list (with miniatures!!)
	//2) FRAME
	@Autowired
	ContentService contentService;
	@Autowired
	StorageMediaService storageService;
	@Autowired
	FrameService frameService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> uploadFrame(
			@RequestPart("media") InMediaDTO media,
			@RequestPart("file") MultipartFile file){
		
		//if(bindingResult.hasErrors()) return new ResponseEntity(new Message("Response entity malformed :S"), HttpStatus.BAD_REQUEST);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(!media.getUsername().equals(( (MyUserDetails) auth.getPrincipal() ).getUsername())) {
			return new ResponseEntity(new Message("u are not "+media.getUsername()), HttpStatus.BAD_REQUEST);
		}
		String message = storageService.saveFile(file, media);
		
		return new ResponseEntity(new Message(message), HttpStatus.OK);
	}
	
	@GetMapping(value = "/home/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getUserFrontpage(@PathVariable(value = "username") String username){
		
		//todo este bloque se puede ir fuera..
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = ((MyUserDetails) auth.getPrincipal());
		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();
		
		Boolean isFriend = false;
		if(user.getUsername().equalsIgnoreCase(username)) {
			isFriend = true;
		}else {
			isFriend = friends.stream()
				.map(Content::getId)
				.anyMatch(id -> id == user.getId());			
		}		
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);
		
		
		//LOGIC HERE
		//Miniaturas, img ppal, followers¿?
		Home home = contentService.getHome(username);
		return new ResponseEntity(home, HttpStatus.OK);
	}
	
	@GetMapping(value = "/home/{id:.+}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getThumbnailFile(@PathVariable(value = "username") String username,
										@PathVariable(value = "id") String idMedia,
										HttpServletResponse response){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = ((MyUserDetails) auth.getPrincipal());
		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();		
		Boolean isFriend = false;
		if(user.getUsername().equalsIgnoreCase(username)) {
			isFriend = true;
		}else {
			isFriend = friends.stream()
				.map(Content::getId)
				.anyMatch(id -> id == user.getId());			
		}		
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);
		
		//LOGIC HERE
		Object[] resource = storageService.loadThumbnailAsResource(idMedia);
		return new ResponseEntity<FileSystemResource>(
		        new FileSystemResource( (File) resource[0]), (HttpHeaders) resource[1], HttpStatus.OK
		    );
		
	}
	@GetMapping(value = "/home/big/{id:.+}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getBigFile(@PathVariable(value = "username") String username,
										@PathVariable(value = "id") String idMedia,
										HttpServletResponse response){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = ((MyUserDetails) auth.getPrincipal());
		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();		
		Boolean isFriend = false;
		if(user.getUsername().equalsIgnoreCase(username)) {
			isFriend = true;
		}else {
			isFriend = friends.stream()
				.map(Content::getId)
				.anyMatch(id -> id == user.getId());			
		}		
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);
		
		//LOGIC HERE
		Object[] resource = storageService.loadFileAsResource(idMedia);
		return new ResponseEntity<FileSystemResource>(
		        new FileSystemResource( (File) resource[0]), (HttpHeaders) resource[1], HttpStatus.OK
		    );
		
	}
	
	@GetMapping(value = "/home/frame/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getFrame(@PathVariable(value = "id") Long frameId){
		
		Frame frame = frameService.getById(frameId).get();
		String username = frame.getContent().getUsername();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = ((MyUserDetails) auth.getPrincipal());
		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();		
		Boolean isFriend = false;
		if(user.getUsername().equalsIgnoreCase(username)) {
			isFriend = true;
		}else {
			isFriend = friends.stream()
				.map(Content::getId)
				.anyMatch(id -> id == user.getId());			
		}		
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);
		
		//LOGIC HERE
		FrameDTO frameDTO = new FrameDTO();
		frameDTO.setComments(frame.getComments());
		frameDTO.setText(frame.getText());
		frameDTO.setMedia_id(frame.getMedia().getId());
		
		return new ResponseEntity(frameDTO, HttpStatus.OK);
		
	}
	
	
}
	
