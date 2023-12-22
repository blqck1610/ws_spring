package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterRequest {

	
	private String searchText ;
	private String sort;
	
	private List<String> filterBrand ;
	private List<String> filterPrice ;
	private List<String> filterGender;
	private String onSales ;
	public FilterRequest(String searchText2, String sort2, String[] filterBrands, String[] filterPrice2,
			String[] filterGender2, String onSales2) {
		this.searchText = searchText2;
		this.sort = sort2;
		this.filterBrand = (filterBrands != null) ? Arrays.asList(filterBrands) : new ArrayList<>();
		if(this.filterBrand != null ) {
			for(String s : this.filterBrand) {
				System.out.println(s);
			}
		}
		this.filterPrice = (filterPrice2 != null) ? Arrays.asList(filterPrice2) : new ArrayList<>();
		this.filterGender = (filterGender2 != null) ? Arrays.asList(filterGender2) : new ArrayList<>();
		
		this.onSales = onSales2;
		
		
	}
}
