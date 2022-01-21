package com.mphasis.foodbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.foodbox.exception.NotFoundException;
import com.mphasis.foodbox.model.Cuisine;
import com.mphasis.foodbox.model.FoodBox;
import com.mphasis.foodbox.service.CuisineService;
import com.mphasis.foodbox.service.FoodBoxService;

/**
* API Controller to product-related requests and responses
*
*/
@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	FoodBoxService foodBoxService;
	
	@Autowired
	CuisineService cuisineService;
	
	@GetMapping("/products")
	public List<FoodBox> getProducts() {
		return foodBoxService.getEnabledProducts();
	}
	
	@GetMapping("/products/{id}")
	public FoodBox getProductById(@PathVariable Long id) throws NotFoundException {
		return foodBoxService.getProductById(id);
		
	}
	
	@GetMapping("/cuisines")
	public List<Cuisine> getCuisines() {
		return cuisineService.getCuisines();
	}
	
	@GetMapping("/cuisines/{id}")
	public Cuisine getCuisineById(@PathVariable Long id) throws NotFoundException {
		return cuisineService.getCuisineById(id);
	}
}
