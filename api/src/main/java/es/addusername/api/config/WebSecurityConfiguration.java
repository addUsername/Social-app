package es.addusername.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import es.addusername.api.Security.JWTAuthenticationFilter;
import es.addusername.api.Security.JWTLoginFilter;
import es.addusername.api.Service.AppUserDetailService;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//ojo, esta logica aqui quizas este mal.. pero lo necesito para el configure? fijo que no
	@Autowired
	private AppUserDetailService UserDetailservice;
	
	//Cors configuration.. alles gut
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
            .and()	
                .csrf()
                .ignoringAntMatchers("/login")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
                .authorizeRequests()
                .antMatchers("/onlyforadmin/**").hasAuthority("ADMIN")
                .antMatchers("/secured/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/**").permitAll()
            .and()
         // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
         // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //This should be changed for a called to user repo
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	
        auth
                .inMemoryAuthentication()
                .
                .withUser("admin").password("{noop}password").authorities("USER", "ADMIN")
            .and()
                .withUser("user").password("{noop}password").authorities("USER");
    }

}
