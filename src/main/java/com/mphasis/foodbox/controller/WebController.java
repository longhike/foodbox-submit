package com.mphasis.foodbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.foodbox.exception.BadRequestException;
import com.mphasis.foodbox.model.User;
import com.mphasis.foodbox.service.UserService;

/**
* Controller to handle main application view and authorization routing
*
*/
@Controller
public class WebController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	// No post route for login because spring security handles this
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	// No get route for logout because spring security handles this
	
	@PostMapping("/register")
	public String doRegister(User user) {
		try {
			userService.saveUser(user);
			return "redirect:/login";
		} catch (BadRequestException e) {
			user.setError(true);
			return "register";
		}
	}
	
	@GetMapping("/products")
	public String getProductsPage() {
		return "products";
	}
	
	@GetMapping("/cart")
	public String getCartPage() {
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String getCheckoutPage() {
		return "checkout";
	}
	
	@GetMapping("/confirm")
	public String getConfirmationPage() {
		return "confirm";
	}
	
}