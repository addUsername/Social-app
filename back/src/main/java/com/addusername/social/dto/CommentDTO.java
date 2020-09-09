package com.addusername.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// We use CommentDTO from vue to socialControlel, actions -> just new and edit, del implementation is trivial
@NoArgsConstructor@Getter@Setter
public class CommentDTO {
	private Long idFrame;
	private String text;
	private String username;
}
