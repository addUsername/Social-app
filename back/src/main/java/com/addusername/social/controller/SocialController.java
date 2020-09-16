package com.addusername.social.controller;

import java.util.List;
//PLS REDUCE THIS v2

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addusername.social.dto.CommentDTO;
import com.addusername.social.dto.JwtDTO;
import com.addusername.social.dto.LikeDTO;
import com.addusername.social.dto.LoginClient;
import com.addusername.social.dto.Message;
import com.addusername.social.dto.UpdateClientDTO;
//fuera
import com.addusername.social.entities.content.Comment;
import com.addusername.social.entities.content.Content;
//dto se queda
import com.addusername.social.entities.content.FollowRequest;
//fuera
//SACAR TODO LO QUE SE PUEDA DE AQUI... EL CONTROLER no debe contener mucha logica
//Quizás un IndexRepository y que este redirija y devuelva el dto
import com.addusername.social.entities.content.Frame;
import com.addusername.social.repository.CommentRepository;
import com.addusername.social.repository.FollowRepository;
import com.addusername.social.security.MyUserDetails;
import com.addusername.social.service.ClientService;
import com.addusername.social.service.ContentService;
import com.addusername.social.service.FrameService;
import com.addusername.social.service.StorageMediaService;

//Ok, SocialController is in charge of follow, like, comments and maybe private messages
@RestController
@RequestMapping("api/social")
public class SocialController {

	
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
	@Autowired
	CommentRepository commentrepo;

	@PostMapping(value = "/follow")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> follow(@RequestBody String userToFollow){
		
		//yep, usernames are sending from vue as "username=" idk why
		userToFollow = userToFollow.substring(0, userToFollow.length()-1);
		System.out.println(userToFollow);
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
	@PostMapping(value = "/like", consumes = {"application/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> setLike(@RequestBody LikeDTO like){
		System.out.println(like.getType());
		System.out.println(like.getObjectId());
		// No auth lel
		switch(like.getType()){
			case "frame":
				Frame frame = frameService.getById(like.getObjectId()).get();
				frame.getMedia().addLike();
				frameService.save(frame);
				break;
			default:
				//comment
				Comment comment = commentrepo.findById(like.getObjectId()).get();
				comment.addLike();
				commentrepo.save(comment);
				break;
		}
		return new ResponseEntity(new Message("liked"), HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "{username}/addComment", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> setOrSetComment(@PathVariable(value = "username") String username,
									@RequestBody CommentDTO comment){
		System.out.println(comment.getText());
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
		
		return new ResponseEntity(new Message(frameService.setComment(comment)), HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "update/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateCredentials(@RequestBody UpdateClientDTO newUser){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(!newUser.getUsername().equals(( (MyUserDetails) auth.getPrincipal() ).getUsername())) {
			return new ResponseEntity(new Message("u are not "+newUser.getUsername()), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(new Message(clientService.updateClient(newUser)), HttpStatus.ACCEPTED);
	}
}
