package com.addusername.social.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    /* 
     * La anotación @JoinTable significa que JPA va a crear una tabla llamada “usuario_rol” con dos campos: “usuario_id”,
     *  que apunta a la id del Usuario y “rol_id”, que apunta a la id del Rol. Las claves primarias son los id de Usuario
     *  y Rol, mientras que las claves foráneas son los dos campos de la tabla “usuario_rol”. joinColumn indica el name 
     *  del campo de la clave en la entidad Usuario mientras que inverseJoinColumn indica la del Rol.
     *  
     */
    @NotNull
    @ManyToMany
    @JoinTable(name = "client_rol", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

	public Client(@NotNull String name,@NotNull String username,@NotNull String email,@NotNull String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
