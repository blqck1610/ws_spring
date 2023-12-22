package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.purchasing.PurchaseOrderHeader;
import com.example.demo.repository.purchasing.OrderHeaderRepository;
import com.example.demo.service.OrderHeaderService;

@Controller
@SessionAttributes("admin")
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	OrderHeaderService orderHeaderService;
	
	@Autowired
	ModelMapper modelMapper;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value =  {"/", "/dashboard"})
	public String dashboard() {
		
		
		return "admin-dashboard";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value =  "/order-list")
	public String orderList(Model model) {
		
		List<PurchaseOrderHeader> orders = orderHeaderService.getAll();
		model.addAttribute("orders", orders);
		return "admin-orderlist";
	}
	
	
	
	@PostMapping("/acc-order")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String accOrder(Integer orderDetailsId) {
		orderHeaderService.accOrder(orderDetailsId);
		
		return "redirect:/admin/order-list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ModelAttribute(name = "admin")
	public UserDto getUser(Authentication authentication) {
		if(authentication == null) {
			return null;
		}
		else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			return modelMapper.map(userDetails.getUser(), UserDto.class);
			
		}
		
	}
	
	
}
