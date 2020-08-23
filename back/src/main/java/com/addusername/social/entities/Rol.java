package com.addusername.social.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.addusername.social.enums.RolName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private RolName rolName;

	public Rol() {}
	
	public Rol(@NotNull RolName rolName) {
		this.rolName = rolName;
	}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public RolName getRolName() {
		return rolName;
	}

	public void setRolName(RolName rolName) {
		this.rolName = rolName;
	}
	
    

}
