package com.example.demo.repository.purchasing;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.purchasing.PurchaseOrderDetail;

public interface OrderDetailRepository extends JpaRepository<PurchaseOrderDetail, Integer> {

}
