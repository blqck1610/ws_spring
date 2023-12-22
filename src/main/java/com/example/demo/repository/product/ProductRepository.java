package com.example.demo.repository.product;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.production.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM product p WHERE p.id = :id", nativeQuery = true)
	Product findProductById(@Param("id") Integer id);

	@Query(value = "SELECT * FROM product  ", nativeQuery = true)
	Page<Product> getPopularProducts(Pageable pageable);

	@Query(value = "SELECT * FROM product ", nativeQuery = true)
	Page<Product> getSaleProduct(Pageable pageable);

	@Query(value = "SELECT * FROM product WHERE gender = :gender", nativeQuery = true)
	Page<Product> getProducts(Pageable pageable, @Param("gender") String gender);

	@Query(nativeQuery = true, value = "SELECT * FROM product p WHERE id = :search or p.name LIKE CONCAT('%', :search , '%') or price LIKE CONCAT('%', :search , '%') " )
	Page<Product> getProductByString(Pageable pageable,@Param("search") String search);

	
	@Query(nativeQuery = true, value = "SELECT * FROM product p "
			+ "WHERE p.brand_id = (select id from brand where upper(name) like upper(:search)) or   p.name LIKE CONCAT('%', :search , '%')  " )
	List<Product> getAllProductByString(@Param("search") String search);
	
}
