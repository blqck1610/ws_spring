package com.example.demo.entity.purchasing;

import java.util.Objects;

import com.example.demo.entity.production.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Item {

	private Product product;
	private int quantity = 0;
	private Double price;
	
	
	public void setPrice() {
		if(this.product.getSales() != null) {
			this.price = (this.getProduct().getPrice() - (this.product.getSales().getSalesValue() / 100) * this.product.getPrice()) * this.getQuantity();
		}
		else {
			this.price = this.getProduct().getPrice() * this.getQuantity();
		}
	}
	

	
	
}
