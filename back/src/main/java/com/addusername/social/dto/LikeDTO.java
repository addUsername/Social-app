package com.addusername.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This object will be used for "likes" interactions, it comes from Vue to SocialController, action -> Like, 
// dislike and "quitLike" are not implemented bc is not a requirement 
@NoArgsConstructor@Getter@Setter
public class LikeDTO {

	private Long objectId;
	private String type; //Could be a enum, unique values {frame,comment} or byte bc speed
}
