package com.addusername.social.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addusername.social.dto.JwtDTO;
import com.addusername.social.dto.LoginClient;
import com.addusername.social.dto.Message;
import com.addusername.social.dto.NewClient;
import com.addusername.social.entities.Client;
import com.addusername.social.entities.Rol;
import com.addusername.social.enums.RolName;
import com.addusername.social.security.MyUserDetails;
import com.addusername.social.security.jwt.JwtProvider;
import com.addusername.social.service.ClientService;
import com.addusername.social.service.RolService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/new")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewClient nuevoUsuario, BindingResult bindingResult){
	    if(bindingResult.hasErrors())
	        return new ResponseEntity(new Message("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
	if(clientService.existsByUsername(nuevoUsuario.getUsername()))
	    return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
	if(clientService.existsByEmail(nuevoUsuario.getEmail()))
	    return new ResponseEntity(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
	
	//Creamos usuario final
	Client usuario =
	        new Client(nuevoUsuario.getName(), nuevoUsuario.getUsername(), nuevoUsuario.getEmail(),
	                passwordEncoder.encode(nuevoUsuario.getPassword()));
	System.out.println("creando role set");
	
	Set<String> rolesStr = nuevoUsuario.getRoles();
	Set<Rol> roles = new HashSet<>();
	for (String rol : rolesStr) {
	    switch (rol) {
	        case "admin":
	        	//Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
	            Rol rolAdmin = rolService.getByRolName(RolName.ROLE_ADMIN).get();
	            roles.add(rolAdmin);
	            break;
	        default:
	            Rol rolUser = rolService.getByRolName(RolName.ROLE_USER).get();
	            roles.add(rolUser);
	    }
	}
    usuario.setRoles(roles);
    clientService.save(usuario);
    return new ResponseEntity(new Message("usuario guardado"), HttpStatus.CREATED);
	}
	
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginClient loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getAuthorities());
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
}

	