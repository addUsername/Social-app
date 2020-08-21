package es.addusername.api.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//should be unique check before get here!
	@Column(name = "username", length = 64,nullable = false,unique = true)
	private String username;
	
	//Prob cambiar LAZY
	@OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Credentials creentials;
		
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "friend_ids")
	private List<User> friends;

	/*
	@OneToMany(mappedBy = "frame", fetch = FetchType.LAZY)
	private List<Frame> frame_ids = new ArrayList<Frame>();
	*/
	
	public User() {}

	public User(String username, Credentials creentials, List<User> friends) {
		super();
		this.username = username;
		this.creentials = creentials;
		this.friends = friends;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Credentials getCreentials() {
		return creentials;
	}

	public void setCreentials(Credentials creentials) {
		this.creentials = creentials;
	}
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	
}
