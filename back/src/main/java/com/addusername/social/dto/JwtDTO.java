package com.addusername.social.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * La clase JwtDTO es la que va a empaquetar el json web token e irá del servidor al cliente y viceversa.
 **/
@NoArgsConstructor @Getter @Setter
public class JwtDTO {
	
	private String token;
	private String bearer = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDTO(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}
	
	
}
