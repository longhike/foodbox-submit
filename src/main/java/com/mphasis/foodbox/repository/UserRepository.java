package com.mphasis.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.foodbox.model.User;

/**
* interface for our User repository
*
*/
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
