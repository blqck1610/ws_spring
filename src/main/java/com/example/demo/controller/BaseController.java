package com.example.demo.controller;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.production.Product;
import com.example.demo.entity.purchasing.Cart;
import com.example.demo.service.ProductService;

@Controller
@SessionAttributes(value = {"cart", "user"})
public class BaseController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@RequestMapping(value = {"/","/home"} ) 
	public String home(Model model) {

		Pageable pageable =  (Pageable) PageRequest.of(0, 4);
		Page<Product> menWatch = productService.getProduct(pageable, "male");
		Page<Product> popularWatch = productService.getPopularProduct(pageable);
		Page<Product> femaleWatch = productService.getProduct(pageable, "female");
		Page<Product> salesProduct = productService.getSaleProduct(pageable);
		
		
		model.addAttribute("femaleWatch", femaleWatch);
		model.addAttribute("menWatch", menWatch);
		model.addAttribute("popularWatch", popularWatch);
		model.addAttribute("salesProduct", salesProduct);
		
		return "home";
	}
	
	@RequestMapping(value = "/403")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping(value = "/contact")
	public String getContacts() {
		return "contact";
	}
	
	@ModelAttribute(name = "cart")
	public Cart getCart() {
		return new Cart();
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
	
	
	
	
}
