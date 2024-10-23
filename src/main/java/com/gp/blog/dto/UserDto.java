package com.gp.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	
	private Integer userId;
	@NotNull
	@Size(min=2,message="Username must be minimum 2 character")
	private String userName;
	@NotEmpty
	
	@Email(message="Email address is not valid")
	private String userMail;
	
	@NotEmpty
	@Size(min=8,max=100,message="Password length must be 6 digits")
	private String userPassword;
	@NotEmpty
	private String about;

}
