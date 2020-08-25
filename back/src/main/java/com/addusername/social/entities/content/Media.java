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
	@Column(name = "upload_dir")
	private String uploadDir;
	@Column(name = "path")
	private String path;
	
	public Media(String filename, String documentType, String uploadDir, String path) {
		super();
		this.likes = 0;
		this.filename = filename;
		this.documentType = documentType;
		this.uploadDir = uploadDir;
		this.path = path;
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
