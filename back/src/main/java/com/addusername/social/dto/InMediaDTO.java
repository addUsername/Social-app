package com.addusername.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Getter@Setter
public class InMediaDTO {

	//este nos permitira hacer la busqueda en la DB del objeto Media sin saber su ID
	private String docType;
	private String username;
	private String text;
	private Boolean isHome;
	private Long frameId;
	
	public InMediaDTO(String docType, String username, String text, Boolean isHome, Long frameId) {
		this.isHome = isHome;
		this.docType = docType;
		this.username = username;
		this.text = text;
		this.frameId = frameId;		
	}	
}

