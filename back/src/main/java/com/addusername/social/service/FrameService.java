package com.addusername.social.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addusername.social.dto.CommentDTO;
import com.addusername.social.dto.FrameDTO;
import com.addusername.social.entities.content.Comment;
import com.addusername.social.entities.content.Frame;
import com.addusername.social.repository.CommentRepository;
import com.addusername.social.repository.FrameRepository;

@Transactional
@Service
public class FrameService {
	
	@Autowired
	FrameRepository repo;
	@Autowired
	CommentRepository commentRepo;
	
	public Optional<Frame> getById(Long id){
		return repo.findById(id);		
	}
	public void save(Frame frame) {
		repo.save(frame);
	}
	public FrameDTO getFrame(String username, Long idFrame) {
		// TODO Auto-generated method stub
		Frame frame = repo.findById(idFrame).get();
		FrameDTO toReturn = new FrameDTO();
		toReturn.setComments(frame.getComments());
		toReturn.setText(frame.getText());
		toReturn.setMedia_id(frame.getMedia().getId());
		toReturn.setMediaType(frame.getMedia(). getDocumentType());
		toReturn.setLikes(frame.getMedia().getLikes());
		
		return toReturn;
	}
	public String setComment(CommentDTO comment) {
		// TODO Auto-generated method stub
		Frame frame = repo.findById(comment.getIdFrame()).get();
		Stream<Comment> frameMssg2 = frame.getComments().stream()
			.filter(commen -> commen.getUsername().startsWith(comment.getUsername()));
		//I know this is ugly but i can't .collect after .fiter idk why it must be a separate step
		List<Comment> frameMssg = frameMssg2.collect(Collectors.toList());	
			
		if(frameMssg.size()>0) {
			Comment com = commentRepo.findById(frameMssg.get(0).getId()).get();
			com.setText(comment.getText());
			com.setEdited(true);
			commentRepo.save(com);
			return "Message updated";
		}
		
		Comment com = new Comment();
		com.setText(comment.getText());
		com.setLikes(0);
		com.setUsername(comment.getUsername());
		com.setEdited(false);
		frame.getComments().add(com);
		commentRepo.save(com);	
		repo.save(frame);
		return "Message added";
		
	}

}
