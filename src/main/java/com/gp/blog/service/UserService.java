package com.gp.blog.service;

import java.util.List;

import com.gp.blog.dto.UserDto;
import com.gp.blog.entities.User;

public interface UserService {
	
	 UserDto addUser(UserDto userDto);

	 UserDto updateUser(UserDto userDto,Integer userId);
	
	 UserDto getUserById(Integer userId);
	 List<UserDto>getAllUsers();
	 
	 void deleteUser(Integer userId);
	 
	 
	
	
}
