package com.gp.blog.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gp.blog.dto.PostDto;
import com.gp.blog.payloads.ApiResponse;
import com.gp.blog.payloads.PostResponse;
import com.gp.blog.service.FileService;
import com.gp.blog.service.PostService;
import com.gp.blog.util.AppConstant;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto,
			@PathVariable  Integer userId,
			@PathVariable  Integer categoryId
			)
	{
		PostDto created_post=postService.createPost(postDto, userId, categoryId);
		
		System.out.println("PostDTO Controller: "+ created_post);
		
		return new ResponseEntity<>(created_post, HttpStatus.CREATED);
	}
	
	// getPost
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto>getPost(@PathVariable Integer postId){
		
		PostDto post=postService.getPostById(postId);
		
		 return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	// getallPost
		@GetMapping("/posts")
		public ResponseEntity<PostResponse>  getAllPost(
				@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
				@RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSise,
				@RequestParam(value="sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
				@RequestParam(value="sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
				){
			
			PostResponse postRes=postService.getAllPost(pageNumber,pageSise,sortBy,sortDir);
			
			 return new ResponseEntity<>(postRes,HttpStatus.OK);
		}
		
	
	//delete Post
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		
		ApiResponse apiResponse=new ApiResponse("Deleted sucessfully", true);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	
	//Update post
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto>updatePost( @RequestBody PostDto postDto,
			@PathVariable Integer postId){
		PostDto post=postService.updatePost(postDto, postId);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	//get Post By category 
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
		
	List<PostDto>postDto=postService.getPostByCategory(categoryId);
	
		return new ResponseEntity<>(postDto, HttpStatus.OK);
	}
	
	//getPostBy User
	

	
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
			
		List<PostDto>postDto=postService.getPostByUser(userId);
		
			return new ResponseEntity<>(postDto, HttpStatus.OK);
		}
		
		
		@GetMapping("/posts/search/{keyword}")
		public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
			List<PostDto> posts=postService.searchPosts(keyword);
			
		
			return new ResponseEntity<>(posts,HttpStatus.OK);
		}
		// post image upload
		
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto>uploadPostImage(
				@PathVariable Integer postId,
				@RequestParam("image")MultipartFile image
				) throws IOException{
		PostDto postDto=postService.getPostById(postId);
		String fileName=fileService.uploadImage(path, image);
		
		
		
		postDto.setImageName(fileName);
		
		this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
			
		}
		
		  //method to serve files
	    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	            @PathVariable("imageName") String imageName,
	            HttpServletResponse response
	    ) throws IOException {

	        InputStream resource = this.fileService.getResource(path, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream());

	    }
 
		
		

}
