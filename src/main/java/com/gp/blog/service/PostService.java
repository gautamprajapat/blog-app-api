package com.gp.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gp.blog.dto.PostDto;
import com.gp.blog.entities.Post;
import com.gp.blog.payloads.PostResponse;


public interface PostService {
	
	//Create
	PostDto  createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//UpDate
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//getAllPost
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//getPost
	
	PostDto getPostById(Integer postId);
	
	//get Post By category
	
	List<PostDto>getPostByCategory(Integer categoryId);
	
	//get all Post By user
	List<PostDto>getPostByUser(Integer userId);
	
	//search Post
	List<PostDto>searchPosts(String keyword);
	
	

}
