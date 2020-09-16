package com.addusername.social.dto;

import java.io.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//this class will be used to generate friend list on vue, we need an array of friends, each of them should contain
//Username, avatar
@NoArgsConstructor @Getter @Setter
public class userCardDTO {

	private String username;
	private File avatar;
}
