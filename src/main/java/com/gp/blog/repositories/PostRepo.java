package com.gp.blog.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gp.blog.entities.Category;
import com.gp.blog.entities.Post;
import com.gp.blog.entities.User;

public interface PostRepo  extends JpaRepository<Post, Integer>{
	
	List<Post>findByUser(User user);
	List<Post>findByCategory(Category category);
	
	List<Post>findBytitleContaining(String title);
	@Query("select p from Post p where p.title like:key")
	List<Post>searchPostByTitle(@Param("key")String title);
	
	
}

