package com.addusername.social.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.addusername.social.dto.InFile_etraInfoDTO;
import com.addusername.social.dto.InMediaDTO;
import com.addusername.social.dto.Message;
import com.addusername.social.dto.OutMediaDTO;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.entities.content.Media;
import com.addusername.social.security.MyUserDetails;
import com.addusername.social.service.ClientService;
import com.addusername.social.service.ContentService;
import com.addusername.social.service.FrameService;
import com.addusername.social.service.StorageMediaService;

@RestController
@RequestMapping("api/content")
public class ContentController {

	
	@Autowired
	ContentService contentService;
	@Autowired
	FrameService frameService;
	@Autowired
	StorageMediaService storage;
	@Autowired
	ClientService clientService;
	
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
		String message = storage.saveFile(file, media);
		
		return new ResponseEntity(new Message(message), HttpStatus.OK);
	}
	
	@GetMapping(value = "/Social", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getFrame(
			@Valid @RequestBody BindingResult bindingResult,
			@RequestParam InMediaDTO extraInfo){
		
		if(bindingResult.hasErrors()) return new ResponseEntity(new Message("Response entity malformed :S"), HttpStatus.BAD_REQUEST);		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(!extraInfo.getUsername().equals(( (MyUserDetails) auth.getPrincipal() ).getUsername())) {
			return new ResponseEntity(new Message("u are not "), HttpStatus.BAD_REQUEST);
		}
		
		
		Frame frame = frameService.getById(Long.parseLong(extraInfo.getFrameId())).get();
		Media media = frame.getMedia();
		OutMediaDTO out = new OutMediaDTO();
		
		//change to async method?
		Resource resource = storage.loadFileAsResource(media);
				
		out.setComments(frame.getComments());
		out.setText(frame.getText());
		out.setLikes(media.getLikes());
		out.setDocumentType(media.getDocumentType());
		Object[] res = {resource, frame};
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@GetMapping(value = "view/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getContent(
			@Valid @RequestBody BindingResult bindingResult,
			@PathVariable(value = "username") String username){
		
		Boolean isFriend = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = ((MyUserDetails) auth.getPrincipal()).getUsername();
		List <Content> friends = contentService.getByUsername(username).get().getFriend_ids();
		
		for(Content friend:friends) {
			if(friend.getUsername().equals(user)) {
				isFriend = true;
				break;
			};
		}
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);
		
		Content content = contentService.getByUsername(username).get();
		return new ResponseEntity(null);
	}
	
	@GetMapping(value = "/follow", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> follow(@RequestBody String userToFollow){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = ((MyUserDetails) auth.getPrincipal()).getUsername();		
		Long id = clientService.getByUsername(user).get().getId();
		
				
		return new ResponseEntity(new Message("peticion enviada"), HttpStatus.ACCEPTED);
	}
	/*
	@GetMapping(value = "/addFriend", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> setFriends
	*/
	/*
	@PostMapping("/{username}/new")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addContent(@PathVariable(value = "username") String username, @Valid @RequestBody ContentClient content, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) return new ResponseEntity(new Message("bindingResult.hasErrors"), HttpStatus.BAD_REQUEST);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(!username.equals( ((MyUserDetails) auth.getPrincipal()).getUsername()) ) return new ResponseEntity(new Message("u are not "+username), HttpStatus.BAD_REQUEST);		

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
	
	
	@GetMapping("/{username}/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> viewContent(@PathVariable(value = "username") String username,@PathVariable(value = "id") Long id){
				
		Boolean isFriend = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = ((MyUserDetails) auth.getPrincipal()).getUsername();
		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();
		
		for(Content friend:friends) {
			if(friend.getUsername().equals(user)) {
				isFriend = true;
				break;
			};
		}
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);

		Frame frame = frameService.getById(id).get();
		Media media = frame.getMedia();
		MediaDTO send = new MediaDTO(frame.getComments(),media.getPath(),media.getLikes());
		
		return new ResponseEntity(send, HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> viewPpal(@PathVariable(value = "username") String username){
		//Aqui deberiamos hacer una pagina ppal del pive
		Boolean isFriend = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = ((MyUserDetails) auth.getPrincipal()).getUsername();
		List <Content> friends = contentService.findByUsername(username).get().getFriend_ids();
		
		for(Content friend:friends) {
			if(friend.getUsername().equals(user)) {
				isFriend = true;
				break;
			};
		}
		if(!isFriend ) return new ResponseEntity(new Message("u are not "+username+ " friend :("), HttpStatus.BAD_REQUEST);

		return new ResponseEntity(contentService.findById(id).get(), HttpStatus.ACCEPTED);
	}
	*/
}
