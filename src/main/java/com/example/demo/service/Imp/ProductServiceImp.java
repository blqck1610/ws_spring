package com.example.demo.service.Imp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FilterRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.person.User;
import com.example.demo.entity.production.Brand;
import com.example.demo.entity.production.Product;
import com.example.demo.entity.production.ProductReview;
import com.example.demo.entity.production.Watch;
import com.example.demo.entity.purchasing.PurchaseOrderDetail;
import com.example.demo.repository.product.BrandRepository;
import com.example.demo.repository.product.ProductRepository;
import com.example.demo.repository.product.ReviewRepository;
import com.example.demo.repository.product.WatchRepository;
import com.example.demo.repository.purchasing.OrderDetailRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	ReviewRepository productReviewRepository;
	@Autowired
	ProductRepository productRepo;

	@Autowired
	WatchRepository watchRepository;

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	ModelMapper modelmapper;

	@Override
	public List<Product> getAll() {
		return productRepo.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productRepo.findProductById(id);
	}

	@Override
	public Product insert(Product item) {

		return productRepo.save(item);
	}

	@Override
	public int deleteById(Integer id) {
		productRepo.deleteById(id);
		return 1;
	}

	@Override
	public Product update(Product item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getPopularProduct(Pageable pageable) {
		return productRepo.getPopularProducts(pageable);
	}

	@Override
	public Page<Product> getSaleProduct(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepo.getSaleProduct(pageable);
	}

	@Override
	public Page<Product> getProduct(Pageable pageable, String gender) {
		// TODO Auto-generated method stub
		return productRepo.getProducts(pageable, gender);
	}

	@Override
	public Watch getWatchById(Integer id) {

		return watchRepository.getWatchById(id);
	}

	@Override
	public Page<Product> getProductByString(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		return productRepo.getProductByString(pageable, search);
	}

	public List<Product> getAllProductByString(String search) {
		// TODO Auto-generated method stub
		return productRepo.getAllProductByString(search);
	}

	@Override
	public List<Product> getProductCustom(FilterRequest filfer) {
		List<Product> products = getAllProductByString(filfer.getSearchText());

		if (filfer.getFilterBrand() != null && filfer.getFilterBrand().size() > 0) {
			products = filterBrand(filfer.getFilterBrand(), products);

		}
		if(filfer.getOnSales() != null) {
			products = getProductSales(products);
		}

//		int start = (int) pageable.getOffset();
//		int end = Math.min(start + pageable.getPageSize(), products.size());
//
//		products = products.subList(start, end);

		return products;
	}
	
	public List<Product> getProductSales(List<Product> products){
		List<Product> p =  new ArrayList<>();
		for(Product product : products) {
			
			if (product.getSales() != null ) {
				
				p.add(product);
			}
		}
		
		return p;
	}

	public List<Product> filterBrand(List<String> brands, List<Product> products) {
		List<Product> result = new ArrayList<>();
		for (String brandName : brands) {
			Brand brand = brandRepository.getBrandByName(brandName);
			for (Product product : products) {
				if (product.getBrand().getId() == brand.getId()) {
					result.add(product);
				}
			}
		}

		return result;
	}

	@Override
	public Page<ProductReview> getProductReview(Integer id, Integer page) {
		Pageable pageable = PageRequest.of(page, 8);
		return productReviewRepository.getProductReview(pageable, id);
	}
	@Override
	public Double getAVGProduct(Integer id) {

		return productReviewRepository.getAVGProduct(id);
	}
	@Override
	public Integer getCount(Integer id) {
		return productReviewRepository.getCount(id);
	}

	@Override
	@Transactional(rollbackFor = { SQLException.class, Exception.class })
	public void addReview(UserDto userDto, Integer productId, Integer rating, Integer orderDetailsId, String title,
			String reviewerName, String comments) {
		User user = modelmapper.map(userDto, User.class);
		Product product = productRepo.getReferenceById(productId);
		
		ProductReview review = new ProductReview();
		review.setUser(user);
		review.setProduct(product);
		review.setComments(comments);
		review.setRating(rating);
		review.setReviewerName(reviewerName);
		review.setTitle(title);
		review.setReviewDate(LocalDate.now());
		productReviewRepository.save(review);
		
		PurchaseOrderDetail orderDetail = orderDetailRepository.getReferenceById(orderDetailsId);
		orderDetail.setReviewPer(0);
		orderDetailRepository.save(orderDetail);
	}

}
