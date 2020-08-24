package com.addusername.social.dto;

import java.util.List;

import com.addusername.social.entities.content.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor@Getter@Setter
public class MediaDTO {

	public MediaDTO(List<Comment> comments, String url, int likes) {
		super();
		this.comments = comments;
		this.url = url;
		this.likes = likes;
	}
	private List<Comment> comments;
	private String url;
	private int likes;
}
