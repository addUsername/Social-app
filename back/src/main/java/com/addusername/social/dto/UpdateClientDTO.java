package com.addusername.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Getter@Setter
public class UpdateClientDTO {

	private String username;
	private Boolean delete;
	private Boolean suspend;
	private String newPassword;
	private String newEmail;
}
