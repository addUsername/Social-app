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
	private String url;
	private int likes;
	private String text;
	@Column(name = "document_type")
	private String documentType;
	
}
	
