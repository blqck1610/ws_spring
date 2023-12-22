package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dto.FilterRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.person.User;
import com.example.demo.entity.production.Product;
import com.example.demo.entity.production.ProductReview;
import com.example.demo.entity.production.Watch;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/product")
@SessionAttributes({ "cart", "user" })
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/Watches/{id}")
	public String getWatchById(@PathVariable(value = "id") Integer id, Model model, Integer page) {
		if (page == null) {
			page = 0;
		} else {
			page -= 1;
		}
		Watch watch = productService.getWatchById(id);
		Page<ProductReview> reviews = productService.getProductReview(id, page);
		Double avgScore = productService.getAVGProduct(id);

		model.addAttribute("page", page + 1);
		model.addAttribute("avgScore", avgScore);
		model.addAttribute("reviews", reviews);
		model.addAttribute("product", watch);

		return "product-details";

	}

	@PostMapping("/search")
	public String getSearch(String onSales, String searchText, Model model, String[] filterBrands, String[] filterPrice,
			String[] filterGender, String sort, Integer page) {

		
		FilterRequest filterRequest = new FilterRequest(searchText, sort, filterBrands, filterPrice, filterGender,
				onSales);

		if (page == null) {
			page = 0;
		} else {
			page -= 1;
		}
		
		Pageable pageable = (Pageable) PageRequest.of(page, 8);
		List<Product> products = productService.getProductCustom(filterRequest);

		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(), products.size());
		int totalPage = (products != null) ? 1 + products.size() / pageable.getPageSize() : 1;
		products = products.subList(start, end);

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("filter", filterRequest);
		model.addAttribute("pageNo", page + 1);
		model.addAttribute("products", products);
		
		
		return "list-product";
	}
	@GetMapping("/trends")
	public String getList( Integer page , Model model) {

		if (page == null) {
			page = 0;
		} else {
			page -= 1;
		}
		
		Pageable pageable = (Pageable) PageRequest.of(page, 8);
		List<Product> products = productService.getAll();

		int start = (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(), products.size());
		int totalPage = (products != null) ? 1 + products.size() / pageable.getPageSize() : 1;
		products = products.subList(start, end);

		model.addAttribute("totalPage", totalPage);
		
		model.addAttribute("pageNo", page + 1);
		model.addAttribute("products", products);
		
		
		return "trending";
	}


	@PostMapping(value = "/add-review")
	public String addReview(@ModelAttribute("user") UserDto userDto, Integer productId, Integer rating ,
			Integer orderDetailsId, String title, String reviewerName, String comments) {
		System.out.println("userid=" + userDto.getId());
		System.out.println("productid=" + productId);
		System.out.println("rating=" + rating);
		System.out.println("orderdetailsid=" + orderDetailsId);
		System.out.println("reviewerName=" + reviewerName);
		System.out.println("title=" + title);
		System.out.println("comment=" + comments);
		productService.addReview(userDto, productId, rating + 1, orderDetailsId, title, reviewerName, comments);
		return "redirect:/user/ordereds";
	}

}
