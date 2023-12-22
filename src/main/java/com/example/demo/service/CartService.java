package com.example.demo.service;

import com.example.demo.entity.purchasing.Cart;

public interface CartService {

	void removeItem(int id, Cart cart);

	void changeCart(Cart cart, int quantity, int id);

}