package com.example.demo.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.production.Product;
import com.example.demo.entity.production.Sales;
import com.example.demo.entity.production.Watch;

@Repository
public interface testRepo extends JpaRepository<Sales, Integer> {

	@Query( nativeQuery = true,  value = "SELECT * FROM wa.product p inner join( select distinct product_sales.product_id from wa.product_sales) s on p.id = s.product_id;")
	Product getProduct();
	
	@Query( nativeQuery = true, value = "SELECT * FROM product WHERE id = :id")
	Watch getWatch(@Param("id") Integer id);
	
	@Query( nativeQuery = true, value = "SELECT * FROM sales where id = 1")
	Sales getSales();
}
