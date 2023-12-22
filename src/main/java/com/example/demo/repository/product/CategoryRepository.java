package com.example.demo.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.production.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
