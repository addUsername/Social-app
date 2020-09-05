package com.addusername.social.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
	private int MiniImageHeight = 500;
	private int MiniImageWidth = 1000;
	
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
		String mini_image = "null";
		
		//Copiamos y reemplazamos si existe
		try {
			Files.copy(file.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
			mini_image = getThumbnail(saveLocation.toFile(),mediaDTO.getDocType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Persistence
		//if(mediarepo.existsByFilename(filename)) toReturn = "Update frame";
		String toReturn = (!mediarepo.existsByFilename(filename))? "Add frame" : "Update frame";
		Media media = new Media();
		media.setDocumentType(extension.substring(1));
		media.setPath(saveLocation.toFile().getAbsolutePath());
		media.setFilename(filename);
		media.setMini_image(mini_image);
		
		
		Frame frame = new Frame();
		frame.setComments(new ArrayList<Comment>());
		frame.setMedia(media);
		frame.setText(mediaDTO.getText());
		
		Content content = contentService.getByUsername(mediaDTO.getUsername()).get();
		frame.setContent(content);
		frameService.save(frame);
		
		return toReturn;
	}
	
	public Object[] loadFileAsResource(String id) {
			
		
		Object[] objects=new Object[2];
		
		Media media = mediarepo.findById(Long.parseLong(id)).get();
		
		File file = new File( media.getPath());
		
		String type = "video";
		if( (media.getDocumentType().equals("jpg")) || (media.getDocumentType().equals("png"))) type = "image";
		
	    HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(new MediaType(type,media.getDocumentType()));
	    respHeaders.setContentLength(file.length());
		objects[0] = file;
		objects[1] = respHeaders;
		return objects;
	}
	public Object[] loadThumbnailAsResource(String id) {
		
		Object[] objects=new Object[2];
				
		File file = new File( mediarepo.findById(Long.parseLong(id)).get().getMini_image());
		
	    HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(new MediaType("image","png"));
	    respHeaders.setContentLength(file.length());
		objects[0] = file;
		objects[1] = respHeaders;
		return objects;
	}
	
	private String getThumbnail(File file, String type) {
		// si el fichero ha sido creado.. si no que metal null e imagen por defecto
		//almost there, queda mirar bien lo de reducir el tamño manteniendo la escala, coger el tamaño del buffer  meter el lado que tenga 200
		String filename = file.getAbsolutePath();
		String path = filename.substring(0,filename.lastIndexOf(".")) + ".png";
		try {
			
			BufferedImage inputImage=null;
			
			if(type.equals("video")){
				//esto nos permite pillar el frame 50 del video
				Picture picture = FrameGrab.getFrameFromFile(file, 50);
				inputImage = AWTUtil.toBufferedImage(picture); 
			}else {
				inputImage = ImageIO.read(file);
			}
			// creates output image
	        BufferedImage outputImage = new BufferedImage(200,200, inputImage.getType());
	 
	        // scales the input image to the output image
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(inputImage, 0, 0, outputImage.getWidth(), outputImage.getHeight(), null);
	        g2d.dispose();
	        ImageIO.write(outputImage, "png", new File(path));		
			
			
		} catch (IOException | JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
}
