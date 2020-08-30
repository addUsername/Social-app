package com.addusername.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addusername.social.dto.Message;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.FollowRequest;
import com.addusername.social.entities.content.Frame;
//SACAR TODO LO QUE SE PUEDA DE AQUI... EL CONTROLER no debe contener mucha logica
import com.addusername.social.repository.FollowRepository;
import com.addusername.social.security.MyUserDetails;
import com.addusername.social.service.ClientService;
import com.addusername.social.service.ContentService;
import com.addusername.social.service.FrameService;
import com.addusername.social.service.StorageMediaService;

@RestController
@RequestMapping("api/social")
public class SocialController {
//ESTO VA A LLEVAR A api/social SocialController.java
	
	@Autowired
	ContentService contentService;
	@Autowired
	FrameService frameService;
	@Autowired
	StorageMediaService storage;
	@Autowired
	ClientService clientService;
	@Autowired
	FollowRepository followrepo;
	
//	
//	@GetMapping(value = "/Social", produces = MediaType.APPLICATION_JSON_VALUE)
//	@PreAuthorize("hasRole('USER')")
//	public ResponseEntity<?> getFrame(
//			@Valid @RequestBody BindingResult bindingResult,
//			@RequestParam InMediaDTO extraInfo){
//		
//		if(bindingResult.hasErrors()) return new ResponseEntity(new Message("Response entity malformed :S"), HttpStatus.BAD_REQUEST);		
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
//		if(!extraInfo.getUsername().equals(( (MyUserDetails) auth.getPrincipal() ).getUsername())) {
//			return new ResponseEntity(new Message("u are not "), HttpStatus.BAD_REQUEST);
//		}
//		
//		//A Partir de aqui todo esto va algun service, que nos devuelva String si ok oq
//		
//		Frame frame = frameService.getById(Long.parseLong(extraInfo.getFrameId())).get();
//		Media media = frame.getMedia();
//		OutMediaDTO out = new OutMediaDTO();
//		
//		//change to async method?
//		Resource resource = storage.loadFileAsResource(media);
//				
//		out.setComments(frame.getComments());
//		out.setText(frame.getText());
//		out.setLikes(media.getLikes());
//		out.setDocumentType(media.getDocumentType());
//		Object[] res = {resource, frame};
//		return new ResponseEntity(res, HttpStatus.OK);
//	}

	@PostMapping(value = "/follow")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> follow(@RequestBody String userToFollow){
		
		//Conseguimos el ID del user que quiere seguir
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id =  ((MyUserDetails) auth.getPrincipal()).getId();
		//A Partir de aqui todo esto va en client service, que nos devuelva String si ok oq
		
		//conseguimos el content del user a segui y actualizamos su lista de followrequest
		Content content = contentService.getByUsername(userToFollow).get();		
		FollowRequest followRequest = new FollowRequest(id,content,false);
		try {
			followrepo.save(followRequest);
		}catch(Exception e) {
			return new ResponseEntity(new Message("Duplicate entry"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Request send"), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/addFriend", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> setFriends(@RequestBody String id){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		FollowRequest followreq = followrepo.findById(Long.parseLong(id)).get();
		Content content = followreq.getContent();
		
		if(!content.getUsername().equals( ((MyUserDetails) auth.getPrincipal()).getUsername()) ) return new ResponseEntity(new Message("U are not"), HttpStatus.ACCEPTED);
		//A Partir de aqui todo esto va en client service, que nos devuelva String si ok oq
		Content make_this_user_friend = contentService.findById(followreq.getFollowerId()).get();
		
		List <Content> hola = content.getFriend_ids();
		hola.add(make_this_user_friend);
		content.setFriend_ids(hola);
		
		followreq.setAcepted(true);
		contentService.save(content);
		
		return new ResponseEntity(new Message("Add friend"), HttpStatus.ACCEPTED);
	}
	
	//TO DO
	@PostMapping(value = "/like", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> setLike(@RequestBody String id){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Frame frame = frameService.getById(Long.parseLong(id)).get();
		frame.getMedia().addLike();
		frameService.save(frame);
		return new ResponseEntity(new Message("liked"), HttpStatus.ACCEPTED);
	}
	
	/*
	@PostMapping("/{username}/new")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addContent(@PathVariable(value = "username") String username, @Valid @RequestBody ContentClient content, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) return new ResponseEntity(new Message("bindingResult.hasErrors"), HttpStatus.BAD_REQUEST);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(!username.equals( ((MyUserDetails) auth.getPrincipal()).getUsername()) ) return new ResponseEntity(new Message("u are not "+username), HttpStatus.BAD_REQUEST);		

		Content add_frame_to_content = contentrepo.findByUsername(username).get();

		Media media = new Media();
		media.setLikes(0);
		media.setPath(content.getContenido());
	
		Frame frame = new Frame();
		frame.setMedia(media);
		frame.setComments(new ArrayList<Comment>());
		
		List <Frame> frames = add_frame_to_content.getFrames();
		frames.add(frame);
		add_frame_to_content.setFrames(frames);
		Object object = contentrepo.save(add_frame_to_content);		

		return new ResponseEntity(object, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/{username}/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> viewContent(@PathVariable(value = "username") String username,@PathVariable(value = "id") Long id){
				
		Boolean isFriend = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = ((MyUserDetails) auth.getPrincipal()).getUsername();
		List <Content> friends = contentrepo.findByUsername(username).get().getFriend_ids();
		
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
	*/
}
