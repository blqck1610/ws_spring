package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.person.User;
import com.example.demo.entity.person.UserRoles;
import com.example.demo.exception.UserAlreadyExistAuthenticationException;

public interface UserService extends BaseService<UserDto> {

	UserDto registerUser(RegisterRequest request) throws UserAlreadyExistAuthenticationException;

	UserDto getUserDtoByUsername(String username);

	List<UserRoles> getUserRoles(int userId);
	
	int changePassword(int userId, String oldPassword, String newPassword);
	
	int updateUser(UserDto user );
	
	User getUserByUsername(String username);
	
	

}
