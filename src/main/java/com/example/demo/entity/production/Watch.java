package com.example.demo.entity.production;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("1")
public class Watch extends Product {

	
	private String model;
	private String movement;
	private String label;
	private String engine;
	private String powerReverse;
	private String dialColor;
	private String type;
	private Double caseSize;
	private String caseMaterial;
	private String caseShape;
	private String caseBack;
	private String bandMaterial;
	private String bandType;
	private String bandColor;
	private String bandWidth;
	
}
