package com.addusername.social.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.addusername.social.entities.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class MyUserDetails implements UserDetails {

	private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	

	public MyUserDetails(Long id, String name, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	/*
	 * Esta clase la utilizaremos para la autenticación. Lo más importante es el método estático build, donde
	 *  se crea una lista de privilegios (GrantedAutoriry) y se le asignan en el constructor al UsuarioPrincipal
	 *   que devuelve el método.
	 */
	public static MyUserDetails build(Client client){
        List<GrantedAuthority> authorities =
                client.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolName().name())).collect(Collectors.toList());
        return new MyUserDetails(client.getId(), client.getName(), client.getUsername(), client.getEmail(), client.getPassword(), authorities);
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
