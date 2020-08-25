package com.addusername.social.entities.content;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor@Setter@Getter
public class FollowRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long followerId;	
	@DateTimeFormat()
	private Date date;
	@OneToOne
	private Content content;
	
	public FollowRequest(Long followerId, Date date,Content content) {
		super();
		this.followerId = followerId;
		this.date = date;
		this.content = content;
	}
	
	

}
