package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.purchasing.Cart;
import com.example.demo.entity.purchasing.PurchaseOrderDetail;
import com.example.demo.entity.purchasing.PurchaseOrderHeader;

public interface OrderHeaderService extends BaseService<PurchaseOrderHeader> {
	
	List<PurchaseOrderDetail> getAllOrderDetails();

	void order(Cart cart, UserDto user);

	List<PurchaseOrderHeader> getOrderByUser(UserDto userDto);

	void doneOrderDetais(Integer id);
	
	
	void accOrder(Integer id);
	

}
