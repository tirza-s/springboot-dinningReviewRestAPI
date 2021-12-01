package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.entity.Restaurant;
import be.tirza.dinningreviewapi.payload.RestaurantDTO;
import be.tirza.dinningreviewapi.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //create a restaurant rest api
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO){ // convert json to java object
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    //get all restaurant rest api
    @GetMapping
    public List<RestaurantDTO> getAllRestaurant() {
        return restaurantService.getAllRestaurant();
    }

    //get restaurant by id
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable(name = "id") long id){
      return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/{zipCode}")
    public ResponseEntity<RestaurantDTO> getRestaurantByZipCode(@PathVariable(name = "zipCode") long zipCode){
        return ResponseEntity.ok(restaurantService.getRestaurantByZipCode(zipCode));
    }

}
