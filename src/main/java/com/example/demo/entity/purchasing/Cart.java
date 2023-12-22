package com.example.demo.entity.purchasing;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart {
	
	private int id;
	private List<Item> items = new ArrayList<>();
	private Double amount  = 0.0;
	private Double saving = 0.0;
	private Discount discount ;
	
	
	public void addItem(Item item) {
		Boolean hasI = false;
		int index = 0;
		for(Item i : items) {
			if(i.getProduct().getId() == item.getProduct().getId()) {
				hasI = true;
				index = items.indexOf(i);
				break;
			}
		}
		if(hasI) {
			items.get(index).setQuantity(items.get(index).getQuantity() + item.getQuantity());
		}
		else {
			this.items.add(item);
		}
		this.setAmount(this.getAmount());
		this.setSaving(this.getSaving());
	}
	
	public double getAmount() {
		this.amount = 0.0;
		for(Item item : this.getItems()) {
			this.amount += item.getPrice() * item.getQuantity();
		}
		return this.amount;
		
	}
	public double getSaving() {
		this.saving =  0.0;
		for(Item item : this.getItems()) {
			this.saving += item.getProduct().getPrice() * item.getQuantity();
		}
		this.saving -= this.amount;
		return this.saving;
	}
	
	
}
