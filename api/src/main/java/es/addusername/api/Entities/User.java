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
	
	//Añadir validators aqui..
	@Column(name = "password", length = 64,nullable = false,unique = true)
    private String password;
	
	//Añadir validators aqui..
	@Column(name = "email", length = 64,nullable = false,unique = true)
    private String email;
	
	//Ver como se almacena y el tipo de relacion 1:1 o si tiene sentido
	@Column(name = "authorities")
	private Collection<GrantedAuthority> authorities;

		
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "friend_ids")
	private List<User> friends;

	/*
	@OneToMany(mappedBy = "frame", fetch = FetchType.LAZY)
	private List<Frame> frame_ids = new ArrayList<Frame>();
	
	public User(String username, String password, String email, Collection<GrantedAuthority> authorities,
			List<Frame> frame_ids, List<User> friends) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		this.frame_ids = frame_ids;
		this.friends = friends;
	}*/
	public User() {}
	
	//De momento, luego borrar
	public User(String username, String password, String email, Collection<GrantedAuthority> authorities,
			 List<User> friends) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		this.friends = friends;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	
}
