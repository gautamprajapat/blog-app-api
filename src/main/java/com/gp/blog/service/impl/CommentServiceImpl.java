package com.gp.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.blog.dto.CommentDto;
import com.gp.blog.entities.Comment;
import com.gp.blog.entities.Post;
import com.gp.blog.exceptions.ResourceNotFoundException;
import com.gp.blog.repositories.CommentRepo;
import com.gp.blog.repositories.PostRepo;
import com.gp.blog.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		
		Post post=postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		
		
		comment.setPost(post);
		Comment savedComment=commentRepo.save(comment);
		
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment=commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		
		
		commentRepo.delete(comment);
	}

}
