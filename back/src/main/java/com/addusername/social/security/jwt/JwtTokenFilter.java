package com.addusername.social.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.addusername.social.service.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtProvider jwtprovider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			if(token !=null && jwtprovider.validateToken(token)){
				String username = jwtprovider.getUsernameFromToken(token); 
				UserDetails myuser = userDetailsServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(myuser, null, myuser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}				
		}catch (Exception e){
			logger.error("fail en método doFilter " + e.getMessage());
	    }
		filterChain.doFilter(request, response);
	}
	
	 private String getToken(HttpServletRequest request){
		 
	        String authReq = request.getHeader("Authorization");
	        if(authReq != null && authReq.startsWith("Bearer "))
	            return authReq.replace("Bearer ", "");
	        return null;
	    }
}
