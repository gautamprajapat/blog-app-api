package com.gp.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	

}
