package com.example.demo.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.purchasing.Cart;
import com.example.demo.entity.purchasing.Item;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@Service
public class CartServiceImp implements CartService {
	
	
	@Autowired
	ProductService productService;

	@Override
	public void changeCart(Cart cart, int quantity, int id) {
		Item i = new Item();
		for(Item item : cart.getItems()) {
			if(item.getProduct().getId() == id) {
				i = item;
			}
		}
		if(i != null) {
			i.setQuantity(i.getQuantity() + quantity);
			if(i.getQuantity() <= 0 ) {
				removeItem(id, cart);
			}
		}
		
		
	}
	@Override
	public void removeItem(int id, Cart cart) {
		
		Item i = null;
		for(Item item : cart.getItems()) {
			if(item.getProduct().getId() == id) {
				i = item;
			}
		}
		cart.getItems().remove(i);
	}
	
	
	
}
