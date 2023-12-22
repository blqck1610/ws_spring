package com.example.demo.entity.production;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Sales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany(mappedBy = "sales" , orphanRemoval = true, cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<ProductSales> productSales;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
