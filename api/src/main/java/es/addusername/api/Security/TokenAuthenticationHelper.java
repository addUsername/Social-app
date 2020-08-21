/**
 * 
 */
package es.addusername.api.Security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author SERGI
 *
 */
public class TokenAuthenticationHelper {
    private static final long EXPIRATION_TIME = 1000*60 ;	// 1 hour
    private static final String SECRET = "MyCoolSecret";	//key value, claro este es el secreto que js nunca sabra.. jiji guay
    private static final String COOKIE_BEARER = "COOKIE-BEARER"; //Two steps, check if login and roles, I think!

    private TokenAuthenticationHelper() {
        throw new IllegalStateException("Utility class");
    }

    static void addAuthentication(HttpServletResponse res, Authentication auth) {
    	//en res vamos añadiendo mierda a la respuesta (cookie with token)

        String authorities = auth.getAuthorities().stream() //que pollas son las authorities?? Un String con "los roles", see GrantedAuthor object
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwt = Jwts.builder()
                .setSubject(auth.getName()) //aqui ya ha pasado por auth miralo
                .claim("authorities", authorities) //Comprobamos los roles aqui?
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
        //Ok aqui hay que mirar cuando se llama a este metodo porque es cuando se añade la cookie a res por la cual se pasa el jwt
        Cookie cookie = new Cookie(COOKIE_BEARER, jwt);
        //Para hacer la cookie segura y que js no la lea¿?.. EXACTO, js solo tiene que leer la requesta y para obtenerla tiene que enviar esta cookie primero
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
    	//leemos la cookie
        Cookie cookie = WebUtils.getCookie(request, COOKIE_BEARER);
        String token = cookie != null ? cookie.getValue() : null;

        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get("authorities").toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            String userName = claims.getSubject();
            
            return userName != null ? new UsernamePasswordAuthenticationToken(userName, null, authorities) : null;
        }
        return null;
    }

}
