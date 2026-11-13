package com.food.delivery.food_catalogue.service;

import com.food.delivery.food_catalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;
}
