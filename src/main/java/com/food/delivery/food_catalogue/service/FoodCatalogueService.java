package com.food.delivery.food_catalogue.service;

import com.food.delivery.food_catalogue.dto.FoodCataloguePage;
import com.food.delivery.food_catalogue.dto.FoodItemDTO;
import com.food.delivery.food_catalogue.dto.Restaurant;
import com.food.delivery.food_catalogue.entity.FoodItem;
import com.food.delivery.food_catalogue.mapper.FoodItemMapper;
import com.food.delivery.food_catalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
       FoodItem savedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
       return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        // get food item list
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        // get restaurant details from Restaurant Listing Microservice
        Restaurant restaurant =  fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        // combine
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setRestaurant(restaurant);
        foodCataloguePage.setFoodItemsList(foodItemList);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        String applicationName = "restaurant-listing";
        return restTemplate.getForObject("http://"+ applicationName +"/restaurant/fetchRestaurantById/" + restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
