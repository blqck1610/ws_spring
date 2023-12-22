package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.purchasing.Cart;
import com.example.demo.entity.purchasing.Item;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping(value ="/cart")
@SessionAttributes(value ="cart")
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	
	
	
	@GetMapping("/process")
	public String process(@RequestParam("id") int id, @RequestParam("quantity") int quantity, @ModelAttribute("cart") Cart cart) {
		cartService.changeCart(cart, quantity, id);
		
		
		return "order";
	}
	@GetMapping("/remove")
	public String remove(@RequestParam("id") int id, @ModelAttribute("cart") Cart cart) {
		cartService.removeItem(id, cart);
		
		return "order";
	}
	
	
	
	
//	@RequestMapping(value = "/add-to-cart", method = { RequestMethod.POST, RequestMethod.GET })
	@PostMapping("/add-to-cart")
	public String addToCart( @ModelAttribute("cart") Cart cart, Model model, Integer productId) {
		
		
		Item item = new Item();
		item.setProduct(productService.findById(productId));
		item.setQuantity(item.getQuantity() + 1);
		item.setPrice();
		cart.addItem(item);
		
		
	
		model.addAttribute("product", item.getProduct());
		String utl = "/product/Watches/" + item.getProduct().getId();
		return "redirect:" + utl;
	}
	
}
