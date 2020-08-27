package com.addusername.social.entities.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//asi se bindea con las propiedades con prefijo file del app.properties, MIRAR SI SIGUE SEINDO NECESARIO uploadDir
@ConfigurationProperties(prefix ="file")
@Entity
@NoArgsConstructor @Getter @Setter
public class Media {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (name = "likes")
	private int likes;
	@Column(name = "filename")
	private String filename;
	@Column(name = "document_type")
	private String documentType;
	@Column(name = "path")
	private String path;
	@Column(name = "mini_image")
	private String mini_image;
	
	public Media(String filename, String documentType, String path, String mini_image) {
		super();
		this.likes = 0;
		this.filename = filename;
		this.documentType = documentType;
		this.path = path;
		this.mini_image = mini_image;
	}

	public int addLike() {
		this.likes += 1;
		return likes;
	}
	public int disLike() {
		this.likes += -1;
		return likes;
	}
	
	
}
