package com.gp.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor

public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer postId;
	@Column(name="post_title",length=100,nullable = false)
	private String title;
	@Column(name="post_content",length=1000,nullable = false)
	private String content;
	@Column(name="post_image",nullable = false)
	private String imageName;
	
	private Date addedDate;
	
	/*
	 * Note this resource is sub resource which have two parent Category and User
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment>comments=new HashSet<>();
	
	
	
	
	

}
