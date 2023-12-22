package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.person.UserRoles;
import com.example.demo.entity.purchasing.PurchaseOrderHeader;
import com.example.demo.service.OrderHeaderService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	OrderHeaderService orderHeaderService;

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> userDtoList = userService.getAll();
		return new ResponseEntity<>(userDtoList, HttpStatus.OK);

	}

	


	@GetMapping(value = "/get-roles/{id}")
	public ResponseEntity<List<UserRoles>> getRole(@PathVariable(value = "id") int userId) {

		List<UserRoles> roles = userService.getUserRoles(userId);
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	

	@GetMapping(value = "/ordereds")
	public String getOrderedList(@ModelAttribute("user") UserDto userDto ,Model model) {
		List<PurchaseOrderHeader> ordereds = orderHeaderService.getOrderByUser(userDto);
		model.addAttribute("orders", ordereds);
		return "ordered-list";
	}

}
