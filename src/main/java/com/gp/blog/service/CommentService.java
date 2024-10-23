package com.gp.blog.service;

import com.gp.blog.dto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);

}
