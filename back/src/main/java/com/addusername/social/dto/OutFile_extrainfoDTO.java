package com.addusername.social.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@Setter@Getter
public class OutFile_extrainfoDTO {

	private OutMediaDTO extraInfo;
	private MultipartFile file;
	
	public OutFile_extrainfoDTO(OutMediaDTO extraInfo, MultipartFile file) {
		this.extraInfo = extraInfo;
		this.file = file;
	}
}
