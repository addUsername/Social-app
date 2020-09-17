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
import java.util.Random;

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
	private final int MiniImageHeight = 400;
	private final int MiniImageWidth = 400;
	private final int seed = 9999999; //max num generated
	
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
		System.out.println("originalFileName "+ originalFileName);
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//this could be a title in the future, just need a unique not id
		String filename = new Random().nextInt(this.seed) + extension;
		Path saveLocation = folder.resolve(filename);
		String mini_image = "null";
		
		//Copiamos y reemplazamos si existe
		try {
			Files.copy(file.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
			mini_image = getThumbnail(saveLocation.toFile(),mediaDTO.getDocType());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String toReturn = "";
		Media media = new Media();
		Frame frame = new Frame();
		//Persistence, looks pretty ugly, refactor..
		
		//SOLVED!! Rules for Vue setting frameId (1 == home, -1 == new frame, [frame id readed from response] == get in (H)
		if ( !mediarepo.existsById(mediaDTO.getFrameId())) {
			toReturn = (mediaDTO.getIsHome())? "Home updated":"Post uploaded";
			}else {			
			try {
				toReturn = "Update frame";
				media = mediarepo.findById(mediaDTO.getFrameId()).get();
				Files.delete(folder.resolve(media.getFilename()));
				//esto creo que esta mal, aunqe en la DB idFrame and idMedia are equals (bc 1to1) i dont need
				//to search by media_id
				//borrar este metodo
				//frame = frameService.findByMedia_id(mediaDTO.getFrameId().get();
				frame = frameService.getById(mediaDTO.getFrameId()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		media.setDocumentType(mediaDTO.getDocType());
		media.setPath(saveLocation.toFile().getAbsolutePath());
		media.setFilename(filename);
		media.setMini_image(mini_image);
		if (frame.getComments() == null) frame.setComments(new ArrayList<Comment>());
		frame.setMedia(media);
		if (mediaDTO.getText() != "") frame.setText(mediaDTO.getText());
		
		Content content = contentService.getByUsername(mediaDTO.getUsername()).get();
		frame.setContent(content);
		frameService.save(frame);
		
		return toReturn;
	}
public String saveFileAvatar(MultipartFile file, String username) {
		
	Path folder = Paths.get(this.uploadLocation + "/avatars/");
	try {
		folder = Files.createDirectories(folder);
	} catch (IOException e) {
		e.printStackTrace();
	}
	//getting the name
	String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
	System.out.println("originalFileName "+ originalFileName);
	//this could be a title in the future, just need a unique not id
	Path saveLocation = folder.resolve(username+".jpg");
	
	//Copiamos y reemplazamos si existe
	String avatarPath = "";
	try {
		Files.copy(file.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
		avatarPath = getThumbnail(saveLocation.toFile(),"image/jpg");
	} catch (IOException e) {
		e.printStackTrace();
	}
	Content content = contentService.getByUsername(username).get();
	content.setAvatarPath(avatarPath);
	contentService.save(content);
	
	return "Avatar updated";
	}
	
	public Object[] loadFileAsResource(String id) {
			
		
		Object[] objects=new Object[2];
		
		Media media = mediarepo.findById(Long.parseLong(id)).get();
		
		File file = new File( media.getPath());
		
		//String type = "video";
		//if( (media.getDocumentType().equals("jpg")) || (media.getDocumentType().equals("png"))) type = "image";
		
	    HttpHeaders respHeaders = new HttpHeaders();
	    System.out.println(media.getDocumentType());
	    
	    //this shit is bc we have to create a new media like MediaType(type, subtype);
	    System.out.println(media.getDocumentType().split("/")[0]+ media.getDocumentType().split("/")[1] );
		respHeaders.setContentType(new MediaType(media.getDocumentType().split("/")[0], media.getDocumentType().split("/")[1] ));
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
			
			if(type.equals("video/mp4")){
				//esto nos permite pillar el frame 50 del video
				Picture picture = FrameGrab.getFrameFromFile(file, 10);
				inputImage = AWTUtil.toBufferedImage(picture); 
			}else {
				inputImage = ImageIO.read(file);
			}
			// creates output image, size 200x200, prob we should put this values as constant outside
	        BufferedImage outputImage = new BufferedImage(MiniImageWidth, MiniImageHeight, inputImage.getType());
	 
	        // scales the input image to the output image, wii
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

	public Object[] loadAvatarAsResource(String username) {
		// TODO Auto-generated method stub
		Object[] objects=new Object[2];
		
		File file = new File((contentService.findByUsername(username)).get().getAvatarPath());
		
	    HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(new MediaType("image","png"));
	    respHeaders.setContentLength(file.length());
		objects[0] = file;
		objects[1] = respHeaders;
		return objects;
	}
	
}
