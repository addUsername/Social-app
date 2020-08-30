package com.addusername.social.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor@Getter@Setter
public class Home {
	
	public String username;	
	private String img;
	private List<String> imgs;
	private List<Long> idFrame;
	//private List<Resource> imgs;
	
}
