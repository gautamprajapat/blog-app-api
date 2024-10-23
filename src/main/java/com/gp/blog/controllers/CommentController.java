package com.gp.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp.blog.dto.CommentDto;
import com.gp.blog.entities.Comment;
import com.gp.blog.payloads.ApiResponse;
import com.gp.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commnetService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto>createComment(
			@RequestBody CommentDto comment,
			@PathVariable Integer postId
			
			){
		CommentDto createdComment=this.commnetService.createComment(comment, postId);
	
		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{commentId}/")
	public ResponseEntity<ApiResponse>deleteComment(
			
			@PathVariable Integer commentId
			
			){
		this.commnetService.deleteComment(commentId);
		
	
		return new ResponseEntity<>(new ApiResponse("Comment Deleted Succesfully",true),HttpStatus.OK);
	}
	

}
