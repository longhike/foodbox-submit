package com.mphasis.foodbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mphasis.foodbox.exception.BadRequestException;
import com.mphasis.foodbox.model.User;
import com.mphasis.foodbox.repository.UserRepository;
/**
* User service class to handle business logic related to user
*
*/
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public void saveUser(User user) throws BadRequestException {
		try {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String password = bcrypt.encode(user.getPassword());
			user.setPassword(password);
			if (user.getRoles() == null) user.setRoles("ROLE_USER");
			user.setActive(true);
			userRepo.save(user);
		} catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Email already in use.");
		}
	}
}
