package com.gp.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	

}
