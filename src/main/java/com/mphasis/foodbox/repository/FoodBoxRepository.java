package com.mphasis.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.foodbox.model.FoodBox;

/**
* interface for our FoodBox repository
*
*/
public interface FoodBoxRepository extends JpaRepository<FoodBox, Long> {

}
