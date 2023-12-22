package com.example.demo.service.Imp;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.person.User;
import com.example.demo.entity.person.UserRoles;
import com.example.demo.exception.UserAlreadyExistAuthenticationException;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.UserRolesRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Role;

import lombok.Data;

@Service
@Data
public class UserServiceImp implements UserDetailsService, UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);

	}

	public List<UserRoles> getUserRoles(int userId) {
		return userRolesRepository.getUserRolesById(userId);
	}

	@Override
	@Transactional(rollbackFor = { SQLException.class, Exception.class })
	public UserDto registerUser(RegisterRequest request) throws UserAlreadyExistAuthenticationException {
		if(getUserDtoByUsername(request.getUsername()) != null) {
			throw new UserAlreadyExistAuthenticationException("user already registered");
		}
		else {
			
			User user = modelMapper.map(request, User.class);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			UserRoles userRoles = new UserRoles();
			userRoles.setRoleName(Role.ROLE_USER);
			userRoles.setUser(user);
			userRepository.save(user);
			userRolesRepository.save(userRoles);
			return modelMapper.map(user, UserDto.class);
		}
	}

	

	

	@Override
	public UserDto getUserDtoByUsername(String username) {
		if(userRepository.findUserByUsername(username) == null) {
			return null;
		}
		
		return modelMapper.map(userRepository.findUserByUsername(username), UserDto.class);

	}

	@Override
	public List<UserDto> getAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto findById(Integer id) {
		User user = userRepository.findUserById(id);
		return modelMapper.map(user, UserDto.class);
	}
	@Override
	public User getUserByUsername(String username) {
		 
		 if(userRepository.findUserByUsername(username) == null) {
				return null;
			}
			
			return userRepository.findUserByUsername(username);
	 }
	

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto update(UserDto item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto insert(UserDto item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changePassword(int userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(UserDto user) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
