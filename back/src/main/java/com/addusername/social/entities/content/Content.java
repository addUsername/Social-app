package com.addusername.social.entities.content;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor @Getter @Setter
public class Content {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long username;
	
	@ManyToMany
	private List<Content> friend_ids;
	
	@OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
	private List<Frame> frames;
}
