package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.production.Product;
import com.example.demo.entity.production.Sales;
import com.example.demo.entity.production.Watch;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/rest-api-test")
public class RestControllerTest {

	@Autowired
	testRepo test;
	@Autowired
	ProductService productService;
	@GetMapping("/get-watch/{id}")
	public ResponseEntity<Watch> getProduct(@PathVariable(value = "id") Integer id){
		Watch product = test.getWatch(id);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}
	@GetMapping("/get-list")
	public ResponseEntity<Page<Product>> getList(){
		Pageable pageable =  (Pageable) PageRequest.of(0, 4);
		Page<Product> menWatch = productService.getProduct(pageable, "male");
		return new ResponseEntity<>(menWatch, HttpStatus.OK);
	}
	
	@DeleteMapping("/del")
	public ResponseEntity<String> del(){
		Sales sales = test.getSales();
		test.delete(sales);
		
		return new ResponseEntity<>("oke", HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Watch> getWatchById(@PathVariable(value = "id") Integer id ){
		Watch watch = productService.getWatchById(id);
		
		return new ResponseEntity<>(watch, HttpStatus.OK);
		
	}
	
	
}
