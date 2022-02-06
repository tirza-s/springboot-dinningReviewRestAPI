package be.tirza.dinningreviewapi.service;

import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.payload.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    RestaurantResponse getAllRestaurant(int pageNo, int pageSize);

    RestaurantDTO getRestaurantById(long id);

    List<RestaurantDTO> getRestaurantByZipCode(String zipCode);

    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, long id);

    void deleteRestaurantById(long id);

}
