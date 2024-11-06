package com.gp.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gp.blog.entities.User;
import com.gp.blog.exceptions.ResourceNotFoundException;
import com.gp.blog.repositories.UserRepo;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user by userName from database
	User user=userRepo.findByuserMail(username).orElseThrow(
				()->new ResourceNotFoundException("User", "userMail"+" "+username, 0));
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		return user;
	}

}
