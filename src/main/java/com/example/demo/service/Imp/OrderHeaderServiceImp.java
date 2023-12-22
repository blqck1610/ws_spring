package com.example.demo.service.Imp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.person.User;
import com.example.demo.entity.purchasing.Cart;
import com.example.demo.entity.purchasing.Item;
import com.example.demo.entity.purchasing.PurchaseOrderDetail;
import com.example.demo.entity.purchasing.PurchaseOrderHeader;
import com.example.demo.repository.purchasing.OrderDetailRepository;
import com.example.demo.repository.purchasing.OrderHeaderRepository;
import com.example.demo.service.OrderHeaderService;
import com.example.demo.service.UserService;
import com.example.demo.util.OrderStatus;

@Service
public class OrderHeaderServiceImp  implements OrderHeaderService{
	
	@Autowired
	OrderHeaderRepository orderHeaderRepo;
	@Autowired
	OrderDetailRepository orderDetailRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<PurchaseOrderHeader> getAll() {
		return orderHeaderRepo.getAll();
	}

	@Override
	public PurchaseOrderHeader findById(Integer id) {
		return orderHeaderRepo.findOrderById(id);
	}

	@Override
	public PurchaseOrderHeader insert(PurchaseOrderHeader item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PurchaseOrderHeader update(PurchaseOrderHeader item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PurchaseOrderDetail> getAllOrderDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = { SQLException.class, Exception.class })
	public void order(Cart cart, UserDto userDto) {
		PurchaseOrderHeader  orderHeader = new PurchaseOrderHeader();
		for(Item item : cart.getItems()) {
			PurchaseOrderDetail orderDetail = modelMapper.map(item, PurchaseOrderDetail.class);
			orderDetail.setPurchaseDate(LocalDateTime.now());
			orderDetail.setStatus(OrderStatus.WAITING);
			orderDetail.setPurchaseOrderHeader(orderHeader);
			orderHeader.getOrderDetails().add(orderDetail);
			
		}
		orderHeader.setTotalDue(cart.getAmount());
		User user = userService.getUserByUsername(userDto.getUsername());
		orderHeader.setUser(user);
		orderHeader.setOrderDate(LocalDate.now());
		orderHeader.setStatus(OrderStatus.WAITING);
		
		orderHeaderRepo.save(orderHeader);
		
	}

	@Override
	public List<PurchaseOrderHeader> getOrderByUser(UserDto userDto) {
		// TODO Auto-generated method stub

		return orderHeaderRepo.getOrderByUser(userDto.getId());
	}

	@Override
	@Transactional(rollbackFor = { SQLException.class, Exception.class })
	public void doneOrderDetais(Integer id) {
		PurchaseOrderDetail orderDetail = orderDetailRepo.getReferenceById(id);
		orderDetail.setReviewPer(1);
		orderDetail.setStatus(OrderStatus.DONE);
		orderDetailRepo.save(orderDetail);
		
	}

	@Override
	public void accOrder(Integer id) {
		// TODO Auto-generated method stub
		
		PurchaseOrderDetail orderDetail = orderDetailRepo.getReferenceById(id);
		orderDetail.setStatus(OrderStatus.SHIPPING);
		orderDetailRepo.save(orderDetail);
		
	}

	

}
