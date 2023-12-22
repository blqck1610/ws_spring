package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.person.UserRoles;
import com.example.demo.service.Imp.UserServiceImp;


@RestController
@RequestMapping(value ="test")
public class CustomerController {

	UserServiceImp userService;

	//test
	@GetMapping(value = "/{id}/getRole")
	public ResponseEntity<List<UserRoles>> getRole(
			@PathVariable(value = "id") int userId ){
		
		List<UserRoles> roles = userService.getUserRoles(userId);
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
}
