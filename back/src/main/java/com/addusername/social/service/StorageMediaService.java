package com.addusername.social.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;
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
	private int MiniImageHeight = 100;
	private int MiniImageWidth = 100;
	
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
			mini_image = getThumbnail(saveLocation.toFile());
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
	public Resource loadThumnailAsResource(Media media) {
		//testar
		
		Path path = Paths.get(media.getMini_image());
		if(!mediarepo.existsByFilename(media.getFilename())) return null;
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	private String getThumbnail(File file) {
		//falta la persistencia y que antes se compruebe si el fichero ha sido creado.. si no que metal null e imagen por defecto
		
		String filename = file.getAbsolutePath();
		String path = filename.substring(0,filename.lastIndexOf(".")) + ".png";
		try {
			//pillamos el frame
			Picture picture = FrameGrab.getFrameFromFile(file, 50);
			//creamos mini frame
			Picture mini = Picture.create(MiniImageWidth, MiniImageHeight, ColorSpace.RGB);
			
			Transform transform = ColorUtil.getTransform(picture.getColor(), ColorSpace.RGB);
			transform.transform(picture, mini);
			BufferedImage bufferedImage = AWTUtil.toBufferedImage(mini); 
			ImageIO.write(bufferedImage, "png", new File(path));
			
		} catch (IOException | JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
}
