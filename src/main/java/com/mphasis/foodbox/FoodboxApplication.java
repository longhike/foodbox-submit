package com.mphasis.foodbox;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mphasis.foodbox.exception.BadRequestException;
import com.mphasis.foodbox.model.Cuisine;
import com.mphasis.foodbox.model.FoodBox;
import com.mphasis.foodbox.model.User;
import com.mphasis.foodbox.repository.CuisineRepository;
import com.mphasis.foodbox.repository.FoodBoxRepository;
import com.mphasis.foodbox.service.UserService;

@SpringBootApplication
public class FoodboxApplication implements CommandLineRunner {
	
	@Autowired
	FoodBoxRepository foodBoxRepo;
	
	@Autowired
	CuisineRepository cuisineRepo;
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(FoodboxApplication.class, args);
	}
	
	@Override
	public void run(String ...args) {
		
		Cuisine c1 = new Cuisine("Japanese");
		Cuisine c2 = new Cuisine("American");
		Cuisine c3 = new Cuisine("Italian");
		
		cuisineRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		FoodBox f1 = new FoodBox("Sushi","This is sushi",25.2, 0d,c1,true);
		FoodBox f2 = new FoodBox("Burger","This is a burger",10.5, .05,c2,true);
		FoodBox f3 = new FoodBox("Pasta","This is pasta",20d, 0d,c3,true);
		FoodBox f4 = new FoodBox("Udon","This is udon",20d, 0d,c1,false);
		
		foodBoxRepo.saveAll(Arrays.asList(f1,f2,f3,f4));
		
		User admin = new User();
		admin.setEmail("admin@gmail.com");
		admin.setName("admin");
		admin.setRoles("ROLE_ADMIN");
		admin.setAddress("123 Main Street");
	    admin.setPassword("admin_pass");
		try {
			userService.saveUser(admin);
		} catch (BadRequestException e) {
			e.printStackTrace();
		}
		
	}

}
