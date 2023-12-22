package com.example.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.FilterRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.production.Product;
import com.example.demo.entity.production.ProductReview;
import com.example.demo.entity.production.Watch;

public interface ProductService extends BaseService<Product> {

	Page<Product> getPopularProduct(Pageable pageable);

	Page<Product> getSaleProduct(Pageable pageable);

	Page<Product> getProduct(Pageable pageable, String gender);
	
	
	Watch getWatchById(Integer id);

	Page<Product> getProductByString(Pageable pageable, String search);

	List<Product> getProductCustom(  FilterRequest filfer);

	Page<ProductReview> getProductReview(Integer id, Integer page);

	Double getAVGProduct(Integer id);

	Integer getCount(Integer id);

	void addReview(UserDto userDto, Integer productId, Integer rating, Integer orderDetailsId, String title,
			String reviewerName, String comments);

}
