package com.gp.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gp.blog.dto.PostDto;
import com.gp.blog.entities.Category;
import com.gp.blog.entities.Post;
import com.gp.blog.entities.User;
import com.gp.blog.exceptions.ResourceNotFoundException;
import com.gp.blog.payloads.PostResponse;
import com.gp.blog.repositories.CategoryRepo;
import com.gp.blog.repositories.PostRepo;
import com.gp.blog.repositories.UserRepo;
import com.gp.blog.service.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		 User user = userRepo.findById(userId)
			        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		 
		 Category category=categoryRepo.findById(categoryId)
				 .orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		 
		 
		 Post post=modelMapper.map(postDto, Post.class);
		 
		 post.setImageName("default.png");
		 post.setAddedDate(new Date());
		 post.setUser(user);
		 post.setCategory(category);
		 
		 Post saved_post=postRepo.save(post);
		 System.out.println("Saved Post:"+saved_post);
		
		return modelMapper.map(saved_post, PostDto.class);
	}



	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post=postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		 
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost=postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
		
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		
		Pageable p =PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> postPage=postRepo.findAll(p);
		
		List<Post>posts=postPage.getContent();
		
		List<PostDto>postsDto=posts.stream().map((post)->modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postRes=new PostResponse();
		postRes.setContent(postsDto);
		postRes.setPageNumber(postPage.getNumber());
		postRes.setPageSize(postPage.getSize());
		postRes.setTotalElement(postPage.getTotalElements());
		postRes.setTotalPages(postPage.getTotalPages());
		postRes.setLastPage(postPage.isLast());
		
		
		return postRes;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post=postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category=categoryRepo.findById(categoryId)
				 .orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		 
		List<Post> posts= postRepo.findByCategory(category);
		List<PostDto>PostsDto=posts.stream().
				map((post)-> modelMapper.map(post,PostDto.class ))
				.collect(Collectors.toList());
		
		
		
		return PostsDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		 User user = userRepo.findById(userId)
			        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		 
		 List<Post> posts=postRepo.findByUser(user);
		 
		 List<PostDto>postsDto=posts.stream()
				 .map((post)->modelMapper.map(post,PostDto.class))
				 .collect(Collectors.toList());
		 
		return postsDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post>posts=postRepo.findBytitleContaining(keyword);
//		List<Post>posts=postRepo.searchPostByTitle(keyword);
		 List<PostDto>postsDto=posts.stream()
				 .map((post)->modelMapper.map(post,PostDto.class))
				 .collect(Collectors.toList());
		return postsDto;
	}

}