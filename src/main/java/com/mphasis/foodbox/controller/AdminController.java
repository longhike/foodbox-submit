package com.mphasis.foodbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.foodbox.exception.BadRequestException;
import com.mphasis.foodbox.exception.NotFoundException;
import com.mphasis.foodbox.model.Cuisine;
import com.mphasis.foodbox.model.FoodBox;
import com.mphasis.foodbox.service.CuisineService;
import com.mphasis.foodbox.service.FoodBoxService;
import com.mphasis.foodbox.utility.FoodBoxUtility;

/**
* API Controller to admin-related requests and responses
*
*/
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	FoodBoxService foodBoxService;
	@Autowired
	CuisineService cuisineService;
	
	@GetMapping("/products") 
	public List<FoodBox> getProducts() {
		return foodBoxService.getProducts();
	}
	
	@GetMapping("/cuisines") 
	public List<Cuisine> getCuisines() {
		return cuisineService.getCuisines();
	}
	
	@GetMapping("/products/{id}")
	public FoodBox getProductById(@PathVariable Long id) throws NotFoundException {
		return foodBoxService.getProductById(id);
	}
	
	@GetMapping("/cuisines/{id}")
	public Cuisine getCuisineById(@PathVariable Long id) throws NotFoundException {
		return cuisineService.getCuisineById(id);
	}
	
	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public FoodBox addProduct(@RequestBody FoodBoxUtility newFoodBox) throws BadRequestException {

		return foodBoxService.addProduct(newFoodBox);
	}
	
	@PostMapping("/cuisines")
	@ResponseStatus(HttpStatus.CREATED)
	public Cuisine addCuisine(@RequestBody Cuisine newCuisine) throws BadRequestException {
		return cuisineService.addCuisine(newCuisine);
	}
	
	@PutMapping("/products")
	@ResponseStatus(HttpStatus.OK)
	public FoodBox updateProduct(@RequestBody FoodBoxUtility updatedFoodBox) throws BadRequestException {
		return foodBoxService.updateProduct(updatedFoodBox);
	}
	
	@PutMapping("/cuisines")
	@ResponseStatus(HttpStatus.OK)
	public Cuisine updateCuisine(@RequestBody Cuisine newCuisine) throws BadRequestException {
		return cuisineService.updateCuisine(newCuisine);
	}
	
	@DeleteMapping("/products/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long id) {
		foodBoxService.deleteProduct(id);
	}
	
	@DeleteMapping("/cuisines/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCuisine(@PathVariable Long id) {
		cuisineService.deleteCuisine(id);
	}

}
