package com.gp.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.blog.entities.User;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByuserMail(String userMail);

}
