package com.addusername.social.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.addusername.social.entities.content.Comment;
import com.addusername.social.entities.content.Content;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.entities.content.Media;
import com.addusername.social.repository.MediaRespository;

@Service
public class StorageMediaService {
	//Create a Service class to store and download files on the server, and to store information in the database.
	private String uploadLocation = "/static/media/upload";
	
	@Autowired
	MediaRespository mediarepo;
	@Autowired
	ContentService contentService;
	/*
	@Autowired
	public StorageMediaService(Media media) {	
	    this.fileLocation = Paths.get(media.getUploadDir())	
	            .toAbsolutePath().normalize();	
	    try {	
	        Files.createDirectories(this.fileLocation);
		    } catch (Exception ex) {
		        System.out.println("Could not create the directory where the uploaded files will be stored.");
		    }
	}
	*/										//no es necesario ¿?
	public String saveFile(MultipartFile file, String username, String frameId, String docType) {
		
		//prepare folder
		Path folder = Paths.get(this.uploadLocation + "/" + username);
		try {
			Files.createDirectories(folder);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		//getting the name
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String filename = frameId + extension;		
		Path saveLocation = folder.resolve(filename);
		
		//Copiamos y reemplazamos si existe
		try {
			Files.copy(file.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		//Media persistence
		if(mediarepo.existsByFilename(filename)) return "contenido actualizado";
		
		Media media = new Media();
		media.setDocumentType(docType);
		media.setPath(saveLocation.toString());
		media.setFilename(filename);
		Frame frame = new Frame();
		frame.setComments(new ArrayList<Comment>());
		frame.setMedia(media);
		
		Content content = contentService.getByUsername(username).get();
		List<Frame> frames = content.getFrames();
		frames.add(frame);
		
		contentService.save(content);
				
		return "Contenido actualizado omguush";
	}
	
	public Resource loadFileAsResource(String filePath) {
		
		Path path = Paths.get(filePath);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
}
