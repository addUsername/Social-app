package com.addusername.social.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Getter@Setter
public class InFile_etraInfoDTO implements Serializable {
	
	private InMediaDTO extraInfo;
	private MultipartFile file;
	
	public InFile_etraInfoDTO(InMediaDTO extraInfo, MultipartFile file) {
		this.extraInfo = extraInfo;
		this.file = file;
	}
}
