package be.tirza.dinningreviewapi.service.impl;

import be.tirza.dinningreviewapi.entity.Restaurant;
import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.repository.RestaurantRepository;
import be.tirza.dinningreviewapi.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    //ToDO fill the pattern
    private Pattern zipCodePattern = Pattern.compile("");

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        //convert DTO to Entity
        Restaurant restaurant = mapToEntity(restaurantDTO);
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        //convert Entity to DTO
        RestaurantDTO restaurantResponse = mapToDTO(newRestaurant);
        return restaurantResponse;
    }

    //Convert Entity to DTO
    private RestaurantDTO mapToDTO(Restaurant restaurant){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setState(restaurant.getState());
        restaurantDTO.setZipCode(restaurant.getZipCode());
        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDTO.setWebsite(restaurant.getWebsite());
        restaurantDTO.setOverallScore(restaurant.getOverallScore());
        restaurantDTO.setPeanutScore(restaurant.getPeanutScore());
        restaurantDTO.setGlutenScore(restaurant.getGlutenScore());
        restaurantDTO.setDairyScore(restaurant.getDairyScore());

        return restaurantDTO;
    }

    //convert DTO to Entity
    private Restaurant mapToEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant= new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setState(restaurantDTO.getState());
        restaurant.setZipCode(restaurantDTO.getZipCode());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setWebsite(restaurantDTO.getWebsite());
        restaurant.setOverallScore(restaurantDTO.getOverallScore());
        restaurant.setPeanutScore(restaurantDTO.getPeanutScore());
        restaurant.setGlutenScore(restaurantDTO.getGlutenScore());
        restaurant.setDairyScore(restaurantDTO.getDairyScore());

        return restaurant;
    }

}
