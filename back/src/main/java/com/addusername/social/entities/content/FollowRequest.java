package com.addusername.social.entities.content;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//Con esto evitamos duplicados en la lista followRequest de Content, haciendo unique el par content_id, followe_id
@Table(uniqueConstraints=@UniqueConstraint(
		columnNames = {"content_id", "followerId"}))

@NoArgsConstructor@Setter@Getter
public class FollowRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(name = "followerId")
	private Long followerId;
	//funciona xd, see how to implement in other entities and make it (auto)updatable at modify operations
	@CreationTimestamp
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "content_id")
	private Content content;
	
	private boolean acepted;
	
	public FollowRequest(Long followerId,Content content, boolean acepted) {
		super();
		this.followerId = followerId;
		this.content = content;
		this.acepted = acepted;
	}
	
	

}
