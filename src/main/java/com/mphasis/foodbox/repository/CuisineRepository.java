package com.mphasis.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.foodbox.model.Cuisine;

/**
* interface for our Cuisine repository
*
*/
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

}
