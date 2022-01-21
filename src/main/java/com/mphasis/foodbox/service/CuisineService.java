package com.mphasis.foodbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.foodbox.exception.BadRequestException;
import com.mphasis.foodbox.exception.NotFoundException;
import com.mphasis.foodbox.model.Cuisine;
import com.mphasis.foodbox.repository.CuisineRepository;

/**
* Cuisine service class to handle business logic related to cuisines
*
*/
@Service
public class CuisineService {
	
	@Autowired
	CuisineRepository cuisineRepo;
	
	public List<Cuisine> getCuisines() {
		return cuisineRepo.findAll();
	}
	
	public Cuisine getCuisineById(Long id) throws NotFoundException {
		try {
			return cuisineRepo.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException("The cuisine referenced by id " + id + " doesn't exist");
		}
		
	}
	
	public Cuisine addCuisine(Cuisine cuisine) throws BadRequestException {
		try {
			return cuisineRepo.save(cuisine);
		} catch (Exception e) {
			throw new BadRequestException("Cuisine name already in use! Please use a different name.");
		}
	}
	
	public Cuisine updateCuisine(Cuisine cuisine) throws BadRequestException {
		try {
			return cuisineRepo.save(cuisine);
		} catch (Exception e) {
			throw new BadRequestException("Cuisine name already in use! Please use a different name.");
		}
	}
	
	public void deleteCuisine(Long id) {
		cuisineRepo.deleteById(id);
	}
}
