package com.example.demo.repository.purchasing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.purchasing.PurchaseOrderDetail;
import com.example.demo.entity.purchasing.PurchaseOrderHeader;

public interface OrderHeaderRepository extends JpaRepository<PurchaseOrderHeader, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM purchase_order_header e WHERE e.id = :id")
	PurchaseOrderHeader findOrderById(@Param("id") Integer id);

	
	@Query(nativeQuery = true, value = "SELECT * FROM purchase_order_header e WHERE e.user_id = :id order by id DESC ")
	List<PurchaseOrderHeader> getOrderByUser(@Param("id") int id);


	@Query(value = "SELECT * FROM purchase_order_header order by id desc" , nativeQuery =  true)
	List<PurchaseOrderHeader> getAll();
}



