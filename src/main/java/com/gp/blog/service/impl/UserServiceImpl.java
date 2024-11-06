package com.gp.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.blog.dto.UserDto;
import com.gp.blog.entities.User;
import com.gp.blog.exceptions.ResourceNotFoundException;
import com.gp.blog.repositories.UserRepo;
import com.gp.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUser(UserDto userDto) {
		
		
		User user =modelMapper.map(userDto, User.class);
		userRepo.save(user);
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	    // Fetch the user from the database
	    User userFromDb = userRepo.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

	    // Set only the updatable fields, leaving userId untouched
	    userFromDb.setUserName(userDto.getUserName());
	    userFromDb.setUserMail(userDto.getUserMail());
	    userFromDb.setAbout(userDto.getAbout());
	    userFromDb.setUserPassword(userDto.getUserPassword());

	    // Save the updated user
	    User updatedUser = userRepo.save(userFromDb);

	    // Convert to UserDto and return
	    return modelMapper.map(updatedUser, UserDto.class);
	}


	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=userRepo.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
	
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User>users=userRepo.findAll();
		
		List<UserDto>userDto=users.stream()
				.map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		userRepo.delete(user);

	}
	
//	public User dtoToUser(UserDto userDto) {
//		User user= new User();
//		user.setUserId(userDto.getUserId());
//		user.setUserName(userDto.getUserName());
//		user.setUserMail(userDto.getUserMail());
//		user.setUserPassword(userDto.getUserPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
//		
//	}
	
//	public UserDto userToUserDto(User user) {
//		UserDto userDto=new UserDto();
//		userDto.setUserId(user.getUserId());
//		userDto.setUserName(user.getUserName());
//		userDto.setUserMail(user.getUserMail());
//		userDto.setUserPassword(user.getUserPassword());
//		userDto.setAbout(user.getAbout());
//		return userDto;
//	}
	
	

}
