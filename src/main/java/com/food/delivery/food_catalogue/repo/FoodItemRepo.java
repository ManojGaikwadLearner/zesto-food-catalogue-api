package com.food.delivery.food_catalogue.repo;

import com.food.delivery.food_catalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
}
