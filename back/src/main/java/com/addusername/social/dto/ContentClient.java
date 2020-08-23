package com.addusername.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * This Class will be sent to Vue, inside a response, so we copy and modify if neccesary Content.class
 * 
 **/
@NoArgsConstructor @Setter @Getter
public class ContentClient {

	private String username;
	private String title;
	private String text;
	private String contenido;
	
}
