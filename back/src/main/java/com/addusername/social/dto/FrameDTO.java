package com.addusername.social.dto;

import java.util.List;

import com.addusername.social.entities.content.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Getter@Setter
public class FrameDTO {
	
	private Long frameId;
	private Long media_id;
	private List<Comment> comments;
	private String text;
	private String mediaType;
	private int likes;
}
