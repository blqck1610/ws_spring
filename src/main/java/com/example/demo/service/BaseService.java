package com.example.demo.service;

import java.util.List;

public interface BaseService<T> {
	 List<T> getAll();
	 T findById(Integer id);
	 T insert(T item);
	 int deleteById(Integer id);
	 T update(T item);
	 

}
