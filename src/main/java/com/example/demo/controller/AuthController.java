package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.UserAlreadyExistAuthenticationException;
import com.example.demo.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/register")
	public String register(Model model) {
		RegisterRequest request = new RegisterRequest();
		model.addAttribute("registerRequest", request);
		return "register";
	}

	@PostMapping(value = "/register")
	public String registerNewUser(@Valid @ModelAttribute("registerRequest") RegisterRequest request, Model model) {
		try {
			userService.registerUser(request);
			return "home";

		} catch (UserAlreadyExistAuthenticationException e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", e.getMessage());
			return "register";
		}

	}

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

}
