package com.addusername.social.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.addusername.social.dto.InMediaDTO;
import com.addusername.social.entities.content.Comment;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.entities.content.Media;
import com.addusername.social.repository.MediaRespository;

@Service
public class StorageMediaService {
	//Create a Service class to store and download files on the server, and to store information in the database.
	//we should pick up this from application.propetes
	private String uploadLocation = "src/main/resources/static/media/upload";
	
	//Change to service.. see services vs repositories
	@Autowired
	MediaRespository mediarepo;
	@Autowired
	ContentService contentService;
	@Autowired
	FrameService frameService;
	
	//Save file..
	public String saveFile(MultipartFile file, InMediaDTO mediaDTO) {
		
		//prepare folder
		Path folder = Paths.get(this.uploadLocation + "/" + mediaDTO.getUsername());
		try {
			folder = Files.createDirectories(folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//getting the name
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//this could be a title in the future, just need a unique not id
		String filename = mediaDTO.getFrameId() + extension;
		Path saveLocation = folder.resolve(filename);
		
		//Copiamos y reemplazamos si existe
		try {
			Files.copy(file.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Persistence
		//if(mediarepo.existsByFilename(filename)) toReturn = "Update frame";
		String toReturn = (!mediarepo.existsByFilename(filename))? "Add frame" : "Update frame";
		Media media = new Media();
		media.setDocumentType(extension);
		media.setPath(saveLocation.toFile().getAbsolutePath());
		media.setFilename(filename);
		
		Frame frame = new Frame();
		frame.setComments(new ArrayList<Comment>());
		frame.setMedia(media);
		frame.setText(mediaDTO.getText());
		
		Content content = contentService.getByUsername(mediaDTO.getUsername()).get();
		frame.setContent(content);
		frameService.save(frame);
		
		return toReturn;
	}
	
	public Resource loadFileAsResource(Media media) {
			
		
		Path path = Paths.get(media.getPath());
		if(!mediarepo.existsByFilename(media.getFilename())) return null;
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
}
