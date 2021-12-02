package be.tirza.dinningreviewapi.service;

import be.tirza.dinningreviewapi.payload.RestaurantDTO;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
    List<RestaurantDTO> getAllRestaurant();
    RestaurantDTO getRestaurantById(long id);
    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, long id);
    void deleteRestaurantById(long id);


}
