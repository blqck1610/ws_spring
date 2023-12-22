package com.example.demo.entity.purchasing;

import java.time.LocalDateTime;

import com.example.demo.entity.production.Product;
import com.example.demo.util.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PurchaseOrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private PurchaseOrderHeader purchaseOrderHeader;

	@ManyToOne
	private Product product;
	private int quantity;
	private double price;
	
	@Column(nullable = false, columnDefinition = "int default 0")
	private int reviewPer;
	
	private OrderStatus status;

	private LocalDateTime purchaseDate;

	private LocalDateTime modifiedDate;

}
