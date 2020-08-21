package es.addusername.api.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import es.addusername.api.Entities.Credentials;
import es.addusername.api.Repository.UserRepositoryInterface;

public class AppUserDetailService implements UserDetailsService {

	@Autowired
    private UserRepositoryInterface userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

	        Credentials user = userRepository.findOneByUserName(username).getCreentials();
	        if (user == null) {
	            throw new UsernameNotFoundException("User " + username + " not found :(");
	        }

	        return org.springframework.security.core.userdetails.User
	                .withUsername(username)
	                .password(user.getPassword())
	                .authorities(user.getAuthorities())
	                .accountExpired(false) //Defines if the account is expired or not. Default is false.
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build();

	    }
		
	

}
