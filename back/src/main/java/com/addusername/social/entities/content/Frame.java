package com.addusername.social.entities.content;

import java.util.List;

import javax.persistence.CascadeType;
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
	private Media Media;
	
	@OneToMany
	private List<Comment> comments;

	public Frame(Content content, com.addusername.social.entities.content.Media media, List<Comment> comments) {
		super();
		this.content = content;
		Media = media;
		this.comments = comments;
	}
	
	
	

}
