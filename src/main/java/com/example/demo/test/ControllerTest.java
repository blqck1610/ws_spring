package com.example.demo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.production.Product;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping(value = "/test")
public class ControllerTest {
	
	

	@Autowired
	ProductService productService;
	@GetMapping("/a")
	public String test(Model model) {
		
		int page = 1;
		Pageable pageable =  (Pageable) PageRequest.of(0, 8);
		Page<Product> product = productService.getPopularProduct(pageable);
		int totalPages = product.getTotalPages();
		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("products", product);
		
		return "list-product";
	}
	
	@GetMapping("/aa")
	public String testA(Model model) {
		String[] test1 = {};
		String[] test2 = {};
		model.addAttribute("test1", test1);
		model.addAttribute("test2", test2);
		return "test";
	}
	@PostMapping("/ab")
	public String testB(String[] test1 , String[] test2, Model model) {
		for(String s : test1) {
			System.out.println(s);
		}
		System.out.println();
		for(String s : test2) {
			System.out.println(s);
		}
		model.addAttribute("test1", test1);
		model.addAttribute("test2", test2);
		
		return "test";
	}
}
