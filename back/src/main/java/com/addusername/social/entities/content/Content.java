package com.addusername.social.entities.content;

import java.util.ArrayList;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//sera añadido a mano para poder conectar los repos mediante la variable username. pero sin union real en la bbdd y asi llamar solo al repo de
	//Testear si se pueden gardar 2 username iguales
	@Column(unique = true)
	private String username;
	
	@OneToMany
	private List<FollowRequest> followRequests = new ArrayList<>();
	
	@OneToMany
	private List<PrivateMessage> pms = new ArrayList<>();
	
	@ManyToMany
	private List<Content> friend_ids = new ArrayList<>();
	
	@OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
	private List<Frame> frames = new ArrayList<Frame>();
	
	private String avatarPath;
	//private String pathAvatar = Imagen por defecto

	public Content(@NotNull String username, List<Content> friend_ids, List<Frame> frames,List<FollowRequest> followRequests) {
		this.username = username;
		this.friend_ids = friend_ids;
		this.frames = frames;
		this.followRequests = followRequests;
	}
	public Content(@NotNull String username, List<Content> friend_ids, List<Frame> frames) {
		this.username = username;
		this.friend_ids = friend_ids;
		this.frames = frames;
	}
	public Content(String username, List<FollowRequest> followRequests, List<PrivateMessage> pms,
			List<Content> friend_ids, List<Frame> frames, String avatarPath) {
		super();
		this.username = username;
		this.followRequests = followRequests;
		this.pms = pms;
		this.friend_ids = friend_ids;
		this.frames = frames;
		this.avatarPath = avatarPath;
	}
	
}
