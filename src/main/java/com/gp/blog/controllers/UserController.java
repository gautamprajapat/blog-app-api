package com.gp.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp.blog.dto.UserDto;
import com.gp.blog.payloads.ApiResponse;
import com.gp.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createdUserDto= userService.addUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		List<UserDto>users=userService.getAllUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto>getUserById(@PathVariable Integer id ){
		
		UserDto user=userService.getUserById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/user/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto ,@PathVariable Integer id){
		
		UserDto user=userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<ApiResponse>deleteUserById(@PathVariable Integer id){
		
		userService.deleteUser(id);
		ApiResponse apiResponse=new ApiResponse("User Deleted Successfully",true);
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	 
	
	
	
	

}
