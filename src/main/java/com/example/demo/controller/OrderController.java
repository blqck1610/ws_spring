package com.example.demo.controller;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.purchasing.Cart;
import com.example.demo.service.OrderHeaderService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes(names = {"cart", "user"})
public class OrderController {
	@Autowired
	OrderHeaderService orderHeaderService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping(value = "/checkout")
	public String getOrder() {
		
		return "order";
	}
	
	@PostMapping(value = "/checkout")
	public String order(SessionStatus status, HttpSession session, @ModelAttribute("user") UserDto user, @ModelAttribute("cart") Cart cart , Model model) {
		orderHeaderService.order(cart, user);
		cart.setItems(new ArrayList<>());		
		
		return "done";
	}
	
	@ModelAttribute(name = "user")
	public UserDto getUser(Authentication authentication) {
		if(authentication == null) {
			return null;
		}
		else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			return modelMapper.map(userDetails.getUser(), UserDto.class);
			
		}
		
	}
	
	@PostMapping(value = "/order/done")
	public String doneOrder(Integer id ) {
		orderHeaderService.doneOrderDetais(id);
		
		
		return "redirect:/user/ordereds";
	}
	
}
