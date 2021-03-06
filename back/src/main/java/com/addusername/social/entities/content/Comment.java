package com.addusername.social.entities.content;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class Comment {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String text;
	private Boolean edited;
	@CreationTimestamp
	private Date date;
	//private Date date; //o string..
	private int likes;
	private String username;
	
	public void addLike() {
		this.likes++;
	}
	
}
