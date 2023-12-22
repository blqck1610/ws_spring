package com.example.demo.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.production.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

	
	@Query(nativeQuery =  true, value = "SELECT * FROM brand Where UPPER(name) LIKE UPPER(:name) ")
	public Brand getBrandByName(@Param("name") String name);
}
