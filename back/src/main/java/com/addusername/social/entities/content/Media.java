package com.addusername.social.entities.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class Media {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String path;
	
	@Column
	private int likes = 0;

	public Media(@NotBlank String path, int likes) {
		super();
		this.path = path;
		this.likes = likes;
	}
	
}
