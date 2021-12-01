package be.tirza.dinningreviewapi.service;

import be.tirza.dinningreviewapi.payload.RestaurantDTO;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
}
