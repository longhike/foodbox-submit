package com.mphasis.foodbox.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.foodbox.exception.BadRequestException;
import com.mphasis.foodbox.exception.NotFoundException;
import com.mphasis.foodbox.model.Cuisine;
import com.mphasis.foodbox.model.FoodBox;
import com.mphasis.foodbox.repository.CuisineRepository;
import com.mphasis.foodbox.repository.FoodBoxRepository;
import com.mphasis.foodbox.utility.FoodBoxUtility;

/**
* FoodBox service class to handle business logic related to foodboxes
*
*/
@Service
public class FoodBoxService {

	@Autowired
	FoodBoxRepository foodBoxRepo;
	@Autowired
	CuisineRepository cuisineRepo;

	public List<FoodBox> getProducts() {
		return foodBoxRepo.findAll();
	}

	public List<FoodBox> getEnabledProducts() {
		return foodBoxRepo.findAll().stream().filter(b -> b.getIsEnabled() == true).collect(Collectors.toList());
	}

	public FoodBox getProductById(Long id) throws NotFoundException {
		try {
			return foodBoxRepo.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException("The foodbox referenced by id " + id + " doesn't exist");
		}
		
	}

	public FoodBox addProduct(FoodBoxUtility newFoodBox) throws BadRequestException {
		try {
			Cuisine cuisine = cuisineRepo.findById(newFoodBox.getCuisineId()).get();
			FoodBox fb = new FoodBox(newFoodBox.getName(), newFoodBox.getDescription(), newFoodBox.getPrice(),
					newFoodBox.getDiscount(), cuisine, newFoodBox.getIsEnabled());

			return foodBoxRepo.save(fb);
		} catch (Exception e) {
			throw new BadRequestException("Product Name already in use! Please use a different name.");
		}
	}

	public FoodBox updateProduct(FoodBoxUtility updatedFoodBox) throws BadRequestException {
		try {
			Cuisine cuisine = cuisineRepo.findById(updatedFoodBox.getCuisineId()).get();
			FoodBox fb = new FoodBox(updatedFoodBox.getId(), updatedFoodBox.getName(), updatedFoodBox.getDescription(),
					updatedFoodBox.getPrice(), updatedFoodBox.getDiscount(), cuisine, updatedFoodBox.getIsEnabled());

			return foodBoxRepo.save(fb);
		} catch (Exception e) {
			throw new BadRequestException("Product Name already in use! Please use a different name.");
		}
	}

	public void deleteProduct(Long id) {
		foodBoxRepo.deleteById(id);
		;
	}
}
