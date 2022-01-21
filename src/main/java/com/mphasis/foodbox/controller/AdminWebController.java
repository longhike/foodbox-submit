package com.mphasis.foodbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mphasis.foodbox.exception.NotFoundException;
import com.mphasis.foodbox.model.Cuisine;
import com.mphasis.foodbox.model.FoodBox;
import com.mphasis.foodbox.service.CuisineService;
import com.mphasis.foodbox.service.FoodBoxService;

/**
* Controller to admin-related views
*
*/
@Controller
public class AdminWebController {

	@Autowired
	FoodBoxService foodBoxService;
	@Autowired
	CuisineService cuisineService;

	@GetMapping("/admin")
	public String getAdminConsole() {
		return "admin";
	}

	@GetMapping("/admin/add/product")
	public String getAdminAddProduct(Model model) {
		List<Cuisine> cuisines = cuisineService.getCuisines();
		model.addAttribute("cuisines", cuisines);
		return "add-product";
	}

	@GetMapping("/admin/add/cuisine")
	public String getAdminAddCuisine() {
		return "add-cuisine";
	}

	@GetMapping("/admin/update/product")
	public String getAdminUpdateProduct(@RequestParam Long productId, Model model) {
		try {
			FoodBox foodBox = foodBoxService.getProductById(productId);
			List<Cuisine> cuisines = cuisineService.getCuisines();
			model.addAttribute("product", foodBox);
			model.addAttribute("cuisines", cuisines);
			return "update-product";
		} catch (NotFoundException nfe) {
			return "redirect:/error";
		}

	}

	@GetMapping("/admin/update/cuisine")
	public String getAdminUpdateCuisine(@RequestParam Long cuisineId, Model model) {
		try {
			Cuisine cuisine = cuisineService.getCuisineById(cuisineId);
			model.addAttribute("cuisine", cuisine);
			return "update-cuisine";
		} catch (NotFoundException nfe) {
			return "redirect:/error";
		}
	}

}
