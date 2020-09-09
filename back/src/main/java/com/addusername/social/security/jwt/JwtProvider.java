package com.addusername.social.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.addusername.social.security.MyUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	
	private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	//Obtenemos la propiedad del application propities ¿o creo que no.. dice que no se usa
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    //@See https://vuejsdevelopers.com/2019/04/15/api-security-jwt-json-web-tokens/
    public String generateToken(Authentication auth) {
    	MyUserDetails myuser = (MyUserDetails) auth.getPrincipal();
    	
    	
    	/*
    	 * It's important to note that the payload is not secure. Anyone can decode the token and see exactly what's in the payload. 
    	 * For that reason, we usually include an ID rather than sensitive identifying information like the user's email.
    	 */
    	return Jwts.builder().setSubject(myuser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret) //hashing algorithms
                .compact();
    }
    
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token broken " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("token not supported " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("token expired " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("token void " +e.getMessage());
        } catch (SignatureException e) {
            logger.error("error signature" +e.getMessage());
        }
        return false;
    }

}
