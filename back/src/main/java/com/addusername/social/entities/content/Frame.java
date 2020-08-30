package com.addusername.social.entities.content;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//el frame cero sera el home, con los comentarios vacios o no..
@Entity
@NoArgsConstructor @Getter @Setter
public class Frame {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "content_id")
	private Content content;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Media media;
	
	@OneToMany
	private List<Comment> comments;
	
	@Column(name = "text")
	private String text;

	public Frame(Content content, Media media, List<Comment> comments, String text) {
		super();
		this.content = content;
		this.media = media;
		this.comments = comments;
		this.text = text;
	}
	
	
	

}
