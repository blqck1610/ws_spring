package com.example.demo.entity.production;

import java.time.LocalDate;

import com.example.demo.entity.person.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;

	private String reviewerName;
	private String title;
	private int rating;
	private String comments;

	private LocalDate reviewDate;

	private LocalDate modifiedDate;

}
