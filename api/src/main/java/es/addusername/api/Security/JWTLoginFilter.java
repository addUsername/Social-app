package es.addusername.api.Security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.addusername.api.Entities.Credentials;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	 public JWTLoginFilter(String url, AuthenticationManager authManager) {
	        super(new AntPathRequestMatcher(url));
	        setAuthenticationManager(authManager);
	    }

	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws IOException {

	    	//leemos las credenciales enviadas por /login y de alguna maneras las autentificamos?
	        Credentials creds = new ObjectMapper().readValue(req.getInputStream(), Credentials.class);
	        
	        //Aqui hay que poner user details
	        return getAuthenticationManager().authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        creds.getUserName(),
	                        creds.getPassword(),
	                        creds.getAuthorities()
	                )
	        );
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
	        TokenAuthenticationHelper.addAuthentication(res, auth);
	    }
}
