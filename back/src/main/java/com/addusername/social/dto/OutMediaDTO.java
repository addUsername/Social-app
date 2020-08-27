package com.addusername.social.dto;

import java.util.List;

import javax.persistence.Column;

import com.addusername.social.entities.content.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor@Getter@Setter
public class OutMediaDTO {

	private List<Comment> comments;
	private int likes;
	private String text;
	private String documentType;
	private Long frameId;
	public OutMediaDTO(List<Comment> comments, int likes, String text, String documentType, Long frameId) {
		super();
		this.comments = comments;
		this.likes = likes;
		this.text = text;
		this.documentType = documentType;
		this.frameId = frameId;
	}
	
}
	
