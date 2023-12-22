package com.example.demo.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.production.Watch;

public interface WatchRepository extends JpaRepository<Watch, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM product e WHERE e.id = :id")
	Watch getWatchById(@Param("id") Integer id);
	
}
