package be.tirza.dinningreviewapi.service;

import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.payload.RestaurantResponse;


public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    RestaurantResponse getAllRestaurant(int pageNo, int pageSize, String sortBy, String sortDir);

    RestaurantDTO getRestaurantById(long id);

    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, long id);

    void deleteRestaurantById(long id);

}
