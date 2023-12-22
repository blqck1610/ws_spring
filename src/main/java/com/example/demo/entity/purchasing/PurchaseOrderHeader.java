package com.example.demo.entity.purchasing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.person.User;
import com.example.demo.util.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class PurchaseOrderHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private User user;
	private OrderStatus status;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "purchaseOrderHeader", fetch = FetchType.EAGER)
	
	private List<PurchaseOrderDetail> orderDetails = new ArrayList<>();

	private LocalDate orderDate;
	private double totalDue;

	@ManyToOne
	private ShipMethod shipMethod;

	private LocalDateTime shipDate;

	private LocalDateTime modifiedDate;

}
