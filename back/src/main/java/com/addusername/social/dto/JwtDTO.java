package com.addusername.social.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
/**
 * La clase JwtDTO es la que va a empaquetar el json web token e irá del servidor al cliente y viceversa.
 **/
public class JwtDTO {
	
	private String token;
	private String bearer = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDTO() {}
	public JwtDTO(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		System.out.println(authorities);
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the bearer
	 */
	public String getBearer() {
		return bearer;
	}
	/**
	 * @param bearer the bearer to set
	 */
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("holaaa" + authorities);
		return authorities;
	}
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		System.out.println(authorities);
		this.authorities = authorities;
	}
	
	
}
