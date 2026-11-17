package com.food.delivery.food_catalogue.controller;

import com.food.delivery.food_catalogue.dto.FoodCataloguePage;
import com.food.delivery.food_catalogue.dto.FoodItemDTO;
import com.food.delivery.food_catalogue.entity.FoodItem;
import com.food.delivery.food_catalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin(origins = "http://localhost:4200")
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService foodCatalogueService;

    // To Add Food Items in Food DB
    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItemAdded = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemAdded, HttpStatus.CREATED);
    }

    // Fetch Restaurant Details and Food Item List from Restaurant ID
    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantAndFoodItemById(@PathVariable Integer restaurantId) {
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
