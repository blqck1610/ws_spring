package com.example.demo.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.production.ProductReview;

public interface ReviewRepository  extends JpaRepository<ProductReview, Integer>{

	
	@Query(nativeQuery =  true, value =  "SELECT * FROM product_review WHERE product_id = :id")
	public Page<ProductReview> getProductReview(Pageable pageable , @Param("id") Integer id);

	
	@Query(nativeQuery = true, value = "SELECT AVG(rating) FROM product_review WHERE product_id = :id")
	public Double getAVGProduct(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "SELECT count(id) FROM product_review WHERE product_id = :id" )
	public Integer getCount(@Param("id") Integer id);
}
