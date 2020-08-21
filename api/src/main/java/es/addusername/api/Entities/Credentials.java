package es.addusername.api.Entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;


//Esto nos puede salvar algun error en el futuro

//import org.springframework.security.core.userdetails.User;
//public class Credentials extends User implements Serializable {

public class Credentials implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String userName;
	
	//Validators?? mediante sql triggers taria guay y en vue tb, para que no salte el error aqui
	@Column(nullable = false, length = 64)
	private String password;	

	//Añadir validators aqui..
	@Column(name = "email", length = 64,nullable = false,unique = true)
    private String email;
	
	//Ver como se almacena y el tipo de relacion 1:1 o si tiene sentido
	@Column(name = "authorities", nullable = false)
	private Collection<GrantedAuthority> authorities;

	public Credentials() {}

	public Credentials(String userName, String password, String email, Collection<GrantedAuthority> authorities) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	

}
